#!/usr/bin/make -f

# Note: You can override all variables by storing them
# in an external file called "make.mk".
-include conf/make.mk

# Target platform:
# foxp, mst12, echop, mst5, mst10, valencia

MACHINE ?= foxp
DISTRO ?= samygo

USER_MACHINE := $(MACHINE)

MAKE_IMAGE_BB ?= base-image
#core-image-minimal

# Adjust according to the number CPU cores to use for parallel build.
# Default: Number of processors in /proc/cpuinfo, if present, or 1.
NR_CPU := $(shell [ -f /proc/cpuinfo ] && grep -c '^processor\s*:' /proc/cpuinfo || echo 1)
BB_NUMBER_THREADS ?= $(NR_CPU)
PARALLEL_MAKE ?= -j $(NR_CPU)

XSUM ?= md5sum

BUILD_DIR = $(CURDIR)/build
TOPDIR = $(BUILD_DIR)/$(DISTRO)
DL_DIR = $(CURDIR)/sources
SSTATE_DIR = $(TOPDIR)/sstate-cache
TMPDIR = $(TOPDIR)/tmp
DEPDIR = $(TOPDIR)/.deps
EXTERNAL_CROSS_DIR = $(CURDIR)/tools
BBLAYERS ?= \
	$(CURDIR)/meta-$(MACHINE) \
	$(CURDIR)/meta-$(DISTRO) \
	$(CURDIR)/meta-openembedded/meta-multimedia \
	$(CURDIR)/meta-openembedded/meta-networking \
	$(CURDIR)/meta-openembedded/meta-oe \
	$(CURDIR)/meta-openembedded/meta-xfce \
	$(CURDIR)/meta-openembedded/meta-webserver \
	$(CURDIR)/openembedded-core/meta

CONFFILES = \
	bitbake.env \
	conf/opensamygo.conf \
	$(TOPDIR)/conf/bblayers.conf \
	$(TOPDIR)/conf/local.conf

CONFDEPS = \
	$(DEPDIR)/.bitbake.env.$(BITBAKE_ENV_HASH) \
	$(DEPDIR)/.opensamygo.conf.$(OPENSAMYGO_CONF_HASH) \
	$(DEPDIR)/.bblayers.conf.$(MACHINE).$(BBLAYERS_CONF_HASH) \
	$(DEPDIR)/.local.conf.$(MACHINE).$(LOCAL_CONF_HASH)

GIT ?= git
GIT_REMOTE := $(shell $(GIT) remote)
GIT_USER_NAME := $(shell $(GIT) config user.name)
GIT_USER_EMAIL := $(shell $(GIT) config user.email)

hash = $(shell echo $(1) | $(XSUM) | awk '{print $$1}')

.DEFAULT_GOAL := all
all: init usage

$(BBLAYERS):
	[ -d $@ ] || $(MAKE) $(MFLAGS) update

init: $(BBLAYERS) $(CONFFILES)

help:
	@echo "Your options:"
	@echo
	@echo "  * Update the SDK"
	@echo "      $$ $(MAKE) update"
	@echo
	@echo "  * Select a new target machine:"
	@echo "      $$ echo MACHINE=foxp >> conf/make.conf"
	@echo "    [Valid values: foxp, mst12, echop, mst10, valencia]"
	@echo
	@echo "  * Build a firmware image for the selected target machine:"
	@echo "      $$ $(MAKE) image"
	@echo
	@echo "  * Build a firmware image for a different target machine:"
	@echo "      $$ $(MAKE) image MACHINE=foxp"
	@echo "    [Valid values: foxp, mst12, echop, mst10, valencia]"
	@echo
	@echo "  * Download all source files at once:"
	@echo "      $$ $(MAKE) download"
	@echo
	@echo "  * Set up the environment to build recipes manually:"
	@echo "      $$ source bitbake.env"
	@echo "      $$ cd build/$(DISTRO)"
	@echo "      $$ bitbake <target>"
	@echo "    [Replace <target> with a recipe name, e.g. $(MAKE_IMAGE_BB) or virtual/kernel]"
	@echo
	@echo "Your current settings:"
	@echo "  DISTRO:	$(DISTRO)"
	@echo "  MACHINE:	$(USER_MACHINE)"
	@echo
	@echo "  BB_NUMBER_THREADS = $(BB_NUMBER_THREADS)"
	@echo "  PARALLEL_MAKE = $(PARALLEL_MAKE)"
	@echo
#	@echo "Trouble finding a recipe? Try ./scripts/drepo grep 'search string'"
#	@echo "or ./scripts/drepo find -name \"*recipe*\"."
#	@echo
	@if [ -z "$(GIT_USER_NAME)" -o -z "$(GIT_USER_EMAIL)" ]; then \
		echo "Before doing any commits, please configure your name and email"; \
		echo "address using the following commands:"; \
		echo; \
		echo "  $$ $(GIT) config user.name \"Your Name\""; \
		echo "  $$ $(GIT) config user.email \"mail@example.com\""; \
	else \
		echo "Git has been configured for $(GIT_USER_NAME) <$(GIT_USER_EMAIL)>."; \
		echo "Please submit patches to <card2000@gmx.net>."; \
	fi

usage:
	@echo "[*] Please run '$(MAKE) help' to display further information!"

clean:
	@echo '[*] Deleting generated configuration files'
	@$(RM) $(CONFFILES) $(CONFDEPS)
	@$(MAKE) $(MFLAGS) -C doc clean

distclean: clean
	@echo '[*] Deleting dependencies directory'
	@$(RM) -r $(DEPDIR)
#	@echo '[*] Deleting download directory'
#	@$(RM) -r $(DL_DIR)
	@echo '[*] Deleting tmp directory'
	@$(RM) -r $(TMPDIR)
	@echo '[*] Deleting sstate directory'
	@$(RM) -r $(SSTATE_DIR)
	@echo '[*] Deleting build directory'
	@$(RM) -r $(BUILD_DIR)
	@echo '[*] Deleting git submodules'
	@$(GIT) submodule foreach 'rm -rf .* * 2>/dev/null || true'

doc:
	@$(MAKE) $(MFLAGS) -C doc

image: init
	@echo '[*] Building $(DISTRO) image for $(USER_MACHINE)'
	@. $(CURDIR)/bitbake.env && cd $(TOPDIR) && bitbake $(BITBAKE_EXTRAFLAGS) $(MAKE_IMAGE_BB)

download: init
	@echo '[*] Downloading sources'
	@. $(CURDIR)/bitbake.env && cd $(TOPDIR) && bitbake -cfetchall -k $(MAKE_IMAGE_BB)

update:
	@echo '[*] Updating Git repositories...'
#	@HASH=`$(XSUM) $(MAKEFILE_LIST)`; \
#	if [ -n "$(GIT_REMOTE)" ]; then \
#		$(GIT) pull --ff-only || $(GIT) pull --rebase; \
#	fi; \
#	if [ "$$HASH" != "`$(XSUM) $(MAKEFILE_LIST)`" ]; then \
#		echo '[*] Makefile changed. Restarting...'; \
#		$(MAKE) $(MFLAGS) --no-print-directory $(MAKECMDGOALS); \
#	else \
#		$(GIT) submodule sync && \
#		$(GIT) submodule update --init && \
#		echo "[*] The SDK is now up-to-date."; \
#	fi

.PHONY: all clean doc help image init update usage Makefile

MACHINE_INCLUDE_CONF = $(CURDIR)/conf/$(basename $(@F))-$(MACHINE)-ext.conf
DISTRO_INCLUDE_CONF = $(CURDIR)/conf/$(basename $(@F))-ext.conf

BITBAKE_ENV_HASH := $(call hash, \
	'BITBAKE_ENV_VERSION = "0"' \
	'CURDIR = "$(CURDIR)"' \
	)

bitbake.env: $(DEPDIR)/.bitbake.env.$(BITBAKE_ENV_HASH)
	@echo '[*] Generating $@'
	@echo '# Automatically generated file. Do not edit!' > $@
	@echo 'export PATH=$(CURDIR)/openembedded-core/scripts:$(CURDIR)/bitbake/bin:$${PATH}' >> $@

OPENSAMYGO_CONF_HASH := $(call hash, \
	'OPENSAMYGO_CONF_VERSION = "1"' \
	'CURDIR = "$(CURDIR)"' \
	'BB_NUMBER_THREADS = "$(BB_NUMBER_THREADS)"' \
	'PARALLEL_MAKE = "$(PARALLEL_MAKE)"' \
	'DL_DIR = "$(DL_DIR)"' \
	'SSTATE_DIR = "$(SSTATE_DIR)"' \
	'TMPDIR = "$(TMPDIR)"' \
	)

conf/opensamygo.conf: $(DEPDIR)/.opensamygo.conf.$(OPENSAMYGO_CONF_HASH)
	@echo '[*] Generating $@'
	@test -d $(@D) || mkdir -p $(@D)
	@echo '# Automatically generated file. Do not edit!' > $@
	@echo 'BB_NUMBER_THREADS = "$(BB_NUMBER_THREADS)"' >> $@
	@echo 'PARALLEL_MAKE = "$(PARALLEL_MAKE)"' >> $@
	@echo 'DL_DIR = "$(DL_DIR)"' >> $@
	@echo 'EXTERNAL_CROSS_DIR= "$(EXTERNAL_CROSS_DIR)"' >> $@
	@echo 'SSTATE_DIR = "$(SSTATE_DIR)"' >> $@
	@echo 'TMPDIR = "$(TMPDIR)"' >> $@
	@echo 'BB_GENERATE_MIRROR_TARBALLS = "0"' >> $@
	@echo 'BBINCLUDELOGS = "yes"' >> $@
	@echo 'CONF_VERSION = "1"' >> $@
	@echo 'DISTRO = "$(DISTRO)"' >> $@
	@echo 'EXTRA_IMAGE_FEATURES = "debug-tweaks"' >> $@
	@echo 'USER_CLASSES = "buildstats"' >> $@

LOCAL_CONF_HASH := $(call hash, \
	'LOCAL_CONF_VERSION = "0"' \
	'CURDIR = "$(CURDIR)"' \
	'TOPDIR = "$(TOPDIR)"' \
	'MACHINE = "$(MACHINE)"' \
	)

$(TOPDIR)/conf/local.conf: $(DEPDIR)/.local.conf.$(MACHINE).$(LOCAL_CONF_HASH)
	@echo '[*] Generating $@'
	@test -d $(@D) || mkdir -p $(@D)
	@echo '# Automatically generated file. Do not edit!' > $@
	@echo 'TOPDIR = "$(TOPDIR)"' >> $@
	@echo 'MACHINE = "$(MACHINE)"' >> $@
	@echo 'DISTRO = "$(DISTRO)"' >> $@
	@echo 'ASSUME_PROVIDED += " $(HOST_NATIVES_LIST)"' >> $@
	@echo 'ASSUME_PROVIDED += " $(HOST_CROSS_LIST)"' >> $@
#	@echo 'require $(CURDIR)/conf/open$${DISTRO}.conf' >> $@
	@echo 'require $(CURDIR)/conf/open$(DISTRO).conf' >> $@
	@echo 'include $(DISTRO_INCLUDE_CONF)' >> $@
	@echo 'include $(MACHINE_INCLUDE_CONF)' >> $@

BBLAYERS_CONF_HASH := $(call hash, \
	'BBLAYERS_CONF_VERSION = "0"' \
	'CURDIR = "$(CURDIR)"' \
	'BBLAYERS = "$(BBLAYERS)"' \
	)

$(TOPDIR)/conf/bblayers.conf: $(DEPDIR)/.bblayers.conf.$(MACHINE).$(BBLAYERS_CONF_HASH)
	@echo '[*] Generating $@'
	@test -d $(@D) || mkdir -p $(@D)
	@echo '# Automatically generated file. Do not edit!' > $@
	@echo 'LCONF_VERSION = "4"' >> $@
	@echo 'BBFILES = ""' >> $@
	@echo 'BBLAYERS = "$(BBLAYERS)"' >> $@
	@echo 'include $(DISTRO_INCLUDE_CONF)' >> $@
	@echo 'include $(MACHINE_INCLUDE_CONF)' >> $@

$(CONFDEPS):
	@test -d $(@D) || mkdir -p $(@D)
	@$(RM) $(basename $@).*
	@touch $@
