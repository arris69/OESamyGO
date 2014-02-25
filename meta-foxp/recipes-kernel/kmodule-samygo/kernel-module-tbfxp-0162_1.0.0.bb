DESCRIPTION = "SamyGO Special Kernel Module for security removal patch for SEK-1000 Evolution Kit"
HOMEPAGE = "http://www.samygo.tv"
SECTION = "kernel/modules"
PRIORITY = "optional"
LICENSE = "CLOSED"
VD_KERNEL_MODULE_VERSION="0162"



#RDEPENDS = "kernel (${KERNEL_VERSION})"
DEPENDS = "virtual/kernel"
PR = "r4"

SRC_URI = " \
	http://www.samygo.tv/juzis28/openembeded/kernel-module-TBFXP-${VD_KERNEL_MODULE_VERSION}_${PV}.tar.gz;name=modules \
	file://modules \
"

SRC_URI[modules.md5sum] = "e9f9bc6962e96c3c6edeb2ad68daa22a"
SRC_URI[modules.sha256sum] = "6267a7b14581755e0d55a06eafc154df5015237c34de527d6bfc4976613bebd2"


S = "${WORKDIR}/lib/modules/${VD_KERNEL_MODULE_VERSION}"

inherit module

do_compile () {
}

do_install () {
	install -d ${D}${base_libdir}/modules/${VD_KERNEL_MODULE_VERSION}
	install -m 0644 ${S}/*${KERNEL_OBJECT_SUFFIX} ${D}${base_libdir}/modules/${VD_KERNEL_MODULE_VERSION}
}

