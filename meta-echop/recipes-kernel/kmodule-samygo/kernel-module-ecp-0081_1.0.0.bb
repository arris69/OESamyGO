DESCRIPTION = "SamyGO Special Kernel Module for security removal patch for UExxES7xxx and UExxES8xxx TV"
HOMEPAGE = "http://www.samygo.tv"
SECTION = "kernel/modules"
PRIORITY = "optional"
LICENSE = "CLOSED"
VD_KERNEL_MODULE_VERSION="0081"



#RDEPENDS = "kernel (${KERNEL_VERSION})"
DEPENDS = "virtual/kernel"
PR = "r0"

SRC_URI = " \
	http://www.samygo.tv/juzis28/openembeded/kernel-module-ECP-${VD_KERNEL_MODULE_VERSION}_1.0.0.tar.gz;name=modules \
	file://modules \
"

SRC_URI[modules.md5sum] = "b93c0f786cab35c9f5942e1d139b8fe7"
SRC_URI[modules.sha256sum] = "17a92f551612b6432956b83dff0f41a9b42a0081e24a2a71c16ba8da44d27acb"

S = "${WORKDIR}/lib/modules/${VD_KERNEL_MODULE_VERSION}"

inherit module

do_compile () {
}

do_install () {
	install -d ${D}${base_libdir}/modules/${VD_KERNEL_MODULE_VERSION}
	install -m 0644 ${S}/*${KERNEL_OBJECT_SUFFIX} ${D}${base_libdir}/modules/${VD_KERNEL_MODULE_VERSION}
}

