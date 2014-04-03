DESCRIPTION = "mount a http url as a file"
AUTHOR = "by hramrach, marionraven, tomas_m"
HOMEPAGE = "http://httpfs.sourceforge.net/"
SECTION = "console/network"
PRIORITY = "optional"
LICENSE = "GPLv2"
DEPENDS = "fuse"
PR = "r02"

SRC_URI = "${SOURCEFORGE_MIRROR}/httpfs/${P}.tar.gz"
SRC_URI_append_samygo += " file://04_04_httpfs.init.dis"

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
