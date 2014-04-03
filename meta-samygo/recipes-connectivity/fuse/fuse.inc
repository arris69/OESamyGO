DESCRIPTION = "With FUSE it is possible to implement a fully functional filesystem in a userspace program"
HOMEPAGE = "http://fuse.sf.net"
LICENSE = "GPL"
DEPENDS = "fakeroot-native"

LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"

INC_PR = "r03"

SRC_URI = "${SOURCEFORGE_MIRROR}/fuse/fuse-${PV}.tar.gz"

inherit autotools pkgconfig

# Kernel data not imported??? for what the hell we inherit module??? *Arris *
KERNEL_MAJOR_VERSION = "${MACHINE_KERNEL_VERSION}"

fakeroot do_install() {
	oe_runmake install DESTDIR=${D}
}
