SECTION = "console/network"
DEPENDS = "openssl"
DESCRIPTION = "Extremely simple MTA to get mail off the system to a mail hub."
LICENSE = "GPLv2"
PR = "r6"

LIC_FILES_CHKSUM = "file://COPYING;md5=0c56db0143f4f80c369ee3af7425af6e"

SRC_URI = "${DEBIAN_MIRROR}/main/s/ssmtp/ssmtp_${PV}.orig.tar.gz \
           file://ldflags.patch;patch=1 \
           file://configure.patch;patch=1 \
           file://libs-lcrypto.patch;patch=1 \
           file://dont-strip.patch;patch=1 \
           file://ssmtp.conf"

SRC_URI[md5sum] = "957e6fff08625fe34f4fc33d0925bbc9"
SRC_URI[sha256sum] = "2151ad18cb73f9a254f796dde2b48be7318b45410b59fedbb258db5a41044fb5"

S = "${WORKDIR}/${PN}-${PV}"

inherit autotools

CONFFILES_${PN} = "${sysconfdir}/ssmtp/ssmtp.conf ${sysconfdir}/ssmtp/revaliases"
EXTRA_OECONF = "--enable-ssl --sysconfdir=/dtv"
INHIBIT_AUTO_STAGE = "1"

do_compile () {
        oe_runmake 'LDFLAGS=${LDFLAGS}'
}

do_install () {
        oe_runmake 'prefix=${D}${prefix}' 'exec_prefix=${D}${exec_prefix}' \
                   'bindir=${D}${bindir}' 'mandir=${D}${mandir}' \
                   'etcdir=${D}${sysconfdir}' GEN_CONFIG="`which echo`" install
        install -d ${D}${sysconfdir}/ssmtp ${D}${sbindir}
        install -m 0644 ${WORKDIR}/ssmtp.conf ${D}${sysconfdir}/ssmtp/ssmtp.conf
	mv ${D}${bindir}/ssmtp ${D}${sbindir}/sendmail
}

