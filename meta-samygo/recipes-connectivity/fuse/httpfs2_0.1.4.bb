DESCRIPTION = "mount a http url as a file"
AUTHOR = "by hramrach, marionraven, tomas_m"
HOMEPAGE = "http://httpfs.sourceforge.net/"
SECTION = "console/network"
PRIORITY = "optional"
LICENSE = "GPLv2"
DEPENDS = "fuse"
PR = "r02"

LIC_FILES_CHKSUM = "file://debian/copyright;md5=fd3cd07405c5ebe1c7faaa696366c01b"

SRC_URI = "${SOURCEFORGE_MIRROR}/httpfs/${P}.tar.gz \
	file://04_04_httpfs.init.dis"

SRC_URI[md5sum] = "340b7f55c474f96b33fb855441dca0f1"
SRC_URI[sha256sum] = "f7ccbb9f258233e7fdb7c55339fa69050e5df9ded08896edf2e4d3144234976e"

S = "${WORKDIR}/${P}"

inherit autotools

EXTRA_OEMAKE = " CC="${CC}" httpfs2"

do_install(){
	install -d ${D}/${bindir}
	install -m 755 httpfs2 ${D}/${bindir}/
}

do_install_append_samygo(){
	install -d ${D}/etc/init.d
	install -m 755 ${WORKDIR}/04_04_httpfs.init.dis ${D}/etc/init.d
}
