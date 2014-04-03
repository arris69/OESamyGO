DESCRIPTION = "FuseISO"
AUTHOR = "adamrimon"
HOMEPAGE = "http://fuseiso.sourceforge.net/"
SECTION = "console/network"
PRIORITY = "optional"
LICENSE = "GPLv2"
DEPENDS = "fuse glib-2.0 zlib"
PR = "r02"

SRC_URI = "http://downloads.sourceforge.net/project/fuseiso/fuseiso/20070708/fuseiso-20070708.tar.bz2"

S = "${WORKDIR}/${P}"

LDFLAGS += " -lglib-2.0 -lz"

inherit autotools

do_install(){
	install -d ${D}/${bindir}
	install -m 755 src/.libs/fuseiso ${D}/${bindir}/
}

#do_install_append_samygo(){
#	install -d ${D}/etc/init.d
#	install -m 755 ${WORKDIR}/04_04_httpfs.init.dis ${D}/etc/init.d
#}

#FILES_${PN} = "${bindir}"
