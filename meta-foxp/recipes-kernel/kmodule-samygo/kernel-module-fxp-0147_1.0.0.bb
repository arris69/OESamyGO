DESCRIPTION = "SamyGO Special Kernel Module for security removal patch for UExxF7xxx and UExxF8xxx TV"
HOMEPAGE = "http://www.samygo.tv"
SECTION = "kernel/modules"
PRIORITY = "optional"
LICENSE = "CLOSED"
VD_KERNEL_MODULE_VERSION="0147"



#RDEPENDS = "kernel (${KERNEL_VERSION})"
DEPENDS = "virtual/kernel"
PR = "r0"

SRC_URI = " \
	http://www.samygo.tv/juzis28/openembeded/kernel-module-FXP-${VD_KERNEL_MODULE_VERSION}_1.0.0.tar.gz;name=modules \
	file://modules \
"

SRC_URI[modules.md5sum] = "f3229537547bb61605c3aa56a4f7c652"
SRC_URI[modules.sha256sum] = "4fbd55351593b71f043b16b19472f0483c5619664ac421db0c13463c8df80da2"


S = "${WORKDIR}/lib/modules/${VD_KERNEL_MODULE_VERSION}"

inherit module

do_compile () {
}

do_install () {
	install -d ${D}${base_libdir}/modules/${VD_KERNEL_MODULE_VERSION}
	install -m 0644 ${S}/*${KERNEL_OBJECT_SUFFIX} ${D}${base_libdir}/modules/${VD_KERNEL_MODULE_VERSION}
}

