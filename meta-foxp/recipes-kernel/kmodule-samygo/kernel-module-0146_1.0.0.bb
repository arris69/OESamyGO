DESCRIPTION = "SamyGO Special Kernel Module for security removal patch"
HOMEPAGE = "http://www.samygo.tv"
SECTION = "kernel/modules"
PRIORITY = "optional"
LICENSE = "CLOSED"
VD_KERNEL_MODULE_VERSION="0146"



#RDEPENDS = "kernel (${KERNEL_VERSION})"
DEPENDS = "virtual/kernel"
PR = "r0"

SRC_URI = " \
	http://www.samygo.tv/juzis28/openembeded/kernel-module-FXP-${VD_KERNEL_MODULE_VERSION}_1.0.0.tar.gz;name=modules \
	file://modules \
"

SRC_URI[md5sum] = "d0f95691748c26e4216784d38163c433"
SRC_URI[sha256sum] = "8ecf442d22771e20cb1509dad6cc3ce788b4b8f08d350731165a83e874b672bb"


S = "${WORKDIR}/lib/modules/${VD_KERNEL_MODULE_VERSION}"

inherit module

do_compile () {
}

do_install () {
	install -d ${D}${base_libdir}/modules/${VD_KERNEL_MODULE_VERSION}
	install -m 0644 ${S}/*${KERNEL_OBJECT_SUFFIX} ${D}${base_libdir}/modules/${VD_KERNEL_MODULE_VERSION}
}

