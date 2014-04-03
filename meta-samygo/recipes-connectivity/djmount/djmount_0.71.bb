DESCRIPTION = "mount UPnP server content as a linux filesystem"
HOMEPAGE = "http://djmount.sourceforge.net/"
LICENSE = "GPL"

LIC_FILES_CHKSUM = "file://COPYING;md5=eb723b61539feef013de476e68b5c50a"

DEPENDS = "libupnp fuse"
# RDEPENDS = "fuse-utils fuse-module"
# RDEPENDS = "fuse-module libupnp"
# RDEPENDS_T-DUMMY = "kernel-module-fuse libupnp"
RRECOMMENDS_${PN} = "kernel-module-fuse"

PR = "r08"

INITSCRIPT_NAME = "djmount"

inherit autotools pkgconfig

EXTRA_OECONF = "--with-external-libupnp --with-fuse-prefix=${STAGING_DIR}/${TARGET_SYS} --without-external-talloc"

SRC_URI = "${SOURCEFORGE_MIRROR}/djmount/djmount-0.71.tar.gz \
	file://fix-mkv-mime.patch;patch=1 \
	file://selp-buffsize.patch;patch=1 \
	file://configure.ac.patch \
	file://rt_bool_arg_enable.m4.patch \
"

SRC_URI[md5sum] = "c922753e706c194bf82a8b6ca77e6a9a"
SRC_URI[sha256sum] = "aa5bb482af4cbd42695a7e396043d47b53d075ac2f6aa18a8f8e11383c030e4f"

do_configure_prepend() {
	cp ${STAGING_DATADIR_NATIVE}/gettext/config.rpath ${S}/libupnp/config.aux/config.rpath
}

