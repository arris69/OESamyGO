DESCRIPTION = "EncFS provides an encrypted filesystem in user-space. It runs without any special permissions and uses the FUSE library and Linux kernel module to provide the filesystem interface."
HOMEPAGE = "http://www.arg0.net/encfs"
SECTION = "console/network"
PRIORITY = "optional"
LICENSE = "GPL"
DEPENDS = "fuse boost"
RDEPENDS = ""
RDEPENDS_T-DUMMY = "kernel-module-fuse"

PR = "r01"

SRC_URI = "http://encfs.googlecode.com/files/${P}.tgz"
SRC_URI_append_samygo += " file://04_04_encfs.init \
"

S = "${WORKDIR}/${P}"

inherit autotools pkgconfig

do_install_append_samygo(){
        install -d ${D}/etc/init.d
        install -m 755 ${WORKDIR}/04_04_encfs.init ${D}/etc/init.d/04_04_encfs.init.dis
}

