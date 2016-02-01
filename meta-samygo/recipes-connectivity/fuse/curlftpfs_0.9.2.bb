DESCRIPTION = "This is a filesystem client based on the FTP File Transfer Protocol using FUSE."
AUTHOR = "Robson Braga Araujo - <brag@users.sf.net>"
HOMEPAGE = "http://curlftpfs.sourceforge.net/"
SECTION = "console/network"
PRIORITY = "optional"
LICENSE = "GPLv2"
DEPENDS = "glib-2.0 fuse curl"
RDEPENDS_${PN}_append_samygo += " libcurl "
PR = "r01"

SRC_URI = "${SOURCEFORGE_MIRROR}/curlftpfs/${P}.tar.gz"
SRC_URI_append_samygo += " file://04_04_curlftp.init.dis"

S = "${WORKDIR}/${P}"

LDFLAGS += "-lglib-2.0"

inherit autotools

do_configure_prepend(){
        eval "export ac_cv_func_malloc_0_nonnull=yes"
	eval "export ac_cv_func_realloc_0_nonnull=yes"
}
do_install_append_samygo(){
	install -d ${D}/etc/init.d
	install -m 755 ${WORKDIR}/04_04_curlftp.init.dis ${D}/etc/init.d
}
