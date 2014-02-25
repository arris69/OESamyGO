DESCRIPTION = "SamyGO Special Kernel Module for security removal patch for SEK-1000 Evolution Kit"
HOMEPAGE = "http://www.samygo.tv"
SECTION = "kernel/modules"
PRIORITY = "optional"
LICENSE = "CLOSED"
VD_KERNEL_MODULE_VERSION="0153"



#RDEPENDS = "kernel (${KERNEL_VERSION})"
DEPENDS = "virtual/kernel"
PR = "r3"

SRC_URI = " \
	http://www.samygo.tv/juzis28/openembeded/kernel-module-TBFXP-${VD_KERNEL_MODULE_VERSION}_${PV}.tar.gz;name=modules \
	file://modules \
"

SRC_URI[modules.md5sum] = "35bb4b3827133bf80bd3f88a6744cfa3"
SRC_URI[modules.sha256sum] = "d3b2f3362f778e7cf023dc7407b783b8dc4ca6849dce5ee2197151fcdc9a298d"

S = "${WORKDIR}/lib/modules/${VD_KERNEL_MODULE_VERSION}"

inherit module

do_compile () {
}

do_install () {
	install -d ${D}${base_libdir}/modules/${VD_KERNEL_MODULE_VERSION}
	install -m 0644 ${S}/*${KERNEL_OBJECT_SUFFIX} ${D}${base_libdir}/modules/${VD_KERNEL_MODULE_VERSION}
}

