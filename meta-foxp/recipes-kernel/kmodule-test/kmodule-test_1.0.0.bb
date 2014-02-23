DESCRIPTION = "Kernel Module Development"
HOMEPAGE = "http://www.samygo.tv"
SECTION = "kernel/modules"
PRIORITY = "optional"
LICENSE = "GPL"


LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=4d92cd373abda3937c2bc47fbc49d690 \
                    file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

#RDEPENDS = "kernel (${KERNEL_VERSION})"
DEPENDS = "virtual/kernel"
PR = "r0"

SRC_URI = " \
	file://kmodule_test.c \
	file://kmodule_test.h \
	file://Makefile \
"

S = "${WORKDIR}"

inherit module

do_compile () {
	unset CFLAGS CPPFLAGS CXXFLAGS LDFLAGS CC LD CPP
	oe_runmake 'MODPATH="${D}${base_libdir}/modules/${KERNEL_VERSION}/kernel/drivers/ecu"' \
		'KERNEL_SOURCE="${STAGING_KERNEL_DIR}"' \
		'KDIR="${STAGING_KERNEL_DIR}"' \
		'KERNEL_VERSION="${KERNEL_VERSION}"' \
		'CC="${KERNEL_CC}"' \
		'LD="${KERNEL_LD}"'

}

do_install () {
	install -d ${D}${base_libdir}/modules/${KERNEL_VERSION}/kernel/drivers/test
	install -m 0644 ${S}/kmodule_test*${KERNEL_OBJECT_SUFFIX} ${D}${base_libdir}/modules/${KERNEL_VERSION}/kernel/drivers/test
}

