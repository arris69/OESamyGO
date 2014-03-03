DESCRIPTION = "SamyGO Special Kernel Module for security removal patch for SEK-1000 (Evolution Kit) - Main"
HOMEPAGE = "http://www.samygo.tv"
SECTION = "kernel/modules"
PRIORITY = "optional"
LICENSE = "CLOSED"
VD_KERNEL_MODULE_VERSION="0136"



#RDEPENDS = "kernel (${KERNEL_VERSION})"
DEPENDS = "virtual/kernel"
PR = "r1"

SRC_URI = " \
	http://www.samygo.tv/juzis28/openembeded/kernel-module-ECP-${VD_KERNEL_MODULE_VERSION}_${PV}.tar.gz;name=modules \
	file://modules \
	file://S00samygo.sh \
"

SRC_URI[modules.md5sum] = "c856580b2964093961e06f42e499cf0d"
SRC_URI[modules.sha256sum] = "58c36c5b8ce39268e23da87c87669bc881e78d140cf75996c2b01815b457ee5f"


S = "${WORKDIR}/lib/modules/${VD_KERNEL_MODULE_VERSION}"

inherit module

do_compile () {
}

do_install () {
	install -d ${D}${base_libdir}/modules/${VD_KERNEL_MODULE_VERSION}
	install -m 0644 ${S}/*${KERNEL_OBJECT_SUFFIX} ${D}${base_libdir}/modules/${VD_KERNEL_MODULE_VERSION}
	install -d ${D}${sysconfdir} ${D}${sysconfdir}/init.d

	install -m 0755 ${WORKDIR}/S00samygo.sh ${D}/${sysconfdir}/init.d/S00samygo
	echo "insmod /lib/modules/${VD_KERNEL_MODULE_VERSION}/console.ko" >> ${D}${sysconfdir}/init.d/S00samygo
	echo "insmod /lib/modules/${VD_KERNEL_MODULE_VERSION}/mount.ko" >> ${D}${sysconfdir}/init.d/S00samygo
}


#pkg_postinst_${PN}() {
#	insmod /lib/modules/${KERNEL_MODULE_VERSION}/mount.ko
#}
