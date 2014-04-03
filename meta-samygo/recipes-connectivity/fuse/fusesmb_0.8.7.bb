DESCRIPTION = "With SMB for Fuse you can seamlessly browse your network neighbourhood as were it on your own filesystem."
AUTHOR = "Vincent Wagelaar <vincent@ricardis.tudelft.nl>"
HOMEPAGE = "http://www.ricardis.tudelft.nl/~vincent/fusesmb/"
SECTION = "console/network"
PRIORITY = "optional"
LICENSE = "GPLv2"

LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"

DEPENDS = "glib-2.0 fuse samba"
#RDEPENDS = "libsmbclient samba-client samba-config"
#RDEPENDS_foxp = "samba"
#RDEPENDS_T-DUMMY = "kernel-module-fuse libsmbclient"

PR = "r12"

SRC_URI = "http://www.ricardis.tudelft.nl/~vincent/fusesmb/download/${P}.tar.gz"
SRC_URI = "http://pkgs.fedoraproject.org/repo/pkgs/fuse-smb/${P}.tar.gz/ff5adc291b18fd452fe4694cbe45dabd/${P}.tar.gz"
SRC_URI_append_samygo += " file://04_04_fusesmb.init \
			file://selp-max_read.patch;patch=1 \
			file://selp-pidfile.patch;patch=1 \
"

SRC_URI[md5sum] = "ff5adc291b18fd452fe4694cbe45dabd"
SRC_URI[sha256sum] = "e15fcbba71327aa0e733f324680a3ed5ea232317b68d21d4114fe298ec13ff89"

S = "${WORKDIR}/${P}"

inherit autotools pkgconfig

do_configure_prepend(){
	eval "export ac_cv_prog_NMBLOOKUP=yes"
}

do_install_append_samygo(){
        install -d ${D}/etc/init.d
        install -m 755 ${WORKDIR}/04_04_fusesmb.init ${D}/etc/init.d/04_04_fusesmb.init.dis
}

