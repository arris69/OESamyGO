DESCRIPTION = "Linux Kernel for Samsung TV's"
SECTION = "kernel"
LICENSE = "GPL"


LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=4d92cd373abda3937c2bc47fbc49d690 \
                    file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"


PATCHLEVEL ?= ""
DEPENDS = "u-boot-mkimage-native fakeroot-native kmod"


SRCREV="6f379eee75152faa4f0bb16707e7909efa4695ce"
SRC_URI = "\
	git://github.com/card2000/VDLinux_3.0.20.git;protocol=git;branch=master \
	file://defconfig \
"

#FILESPATHPKG_prepend = "linux-foxp-3.0.20:"

S = "${WORKDIR}/git/linux-${PV}"


COMPATIBLE_HOST = "arm.*-linux"
COMPATIBLE_MACHINE = "foxp"

export ARCH = "arm"
export OS = "Linux"

KERNEL_OBJECT_SUFFIX = "ko"
#KERNEL_OUTPUT = "uImage"
KERNEL_IMAGETYPE = "uImage"
KERNEL_IMAGEDEST="mtd_rwcommon/boot"

do_configure_prepend() {
	
	
	mkdir -p ${S}/fs/rfs
	mkdir -p ${S}/fs/tntfs
	mkdir -p ${S}/fs/exfat
	rm -Rf ${S}/include/linux/vdlp_version.h
	
	echo '#define DTV_KERNEL_VERSION "${KERNEL_MODULE_VERSION}, release"' > ${S}/include/linux/vdlp_version.h
	echo '#define DTV_LAST_PATCH "1140, DTV, Fox.P, release, MAIN"' >> ${S}/include/linux/vdlp_version.h
	
	
	cp ${S}/fs/Kconfig.Fox.P ${S}/fs/Kconfig
	cp ${S}/drivers/Kconfig.Fox.P ${S}/drivers/Kconfig
	cp -Rf ${WORKDIR}/git/RFS_3.0.0_b044-LinuStoreIII_1.2.0_b040-FSR_1.2.1p1_b139_RTM/fs/rfs_Fox.P_release/* ${S}/fs/rfs
	cp -Rf ${WORKDIR}/git/TUXERA_NTFS/Fox.P_release/* ${S}/fs/tntfs
	cp -Rf ${WORKDIR}/git/exFATs/exFAT.Fox.P_release/* ${S}/fs/exfat
	
}

do_install_append() {

	#install -d ${D}/boot
	#install -m 0755 vmlinux ${D}/boot/vmlinux
	#echo "/flash/bootlogo.elf" > ${D}/boot/autoexec.bat
	#gzip ${D}/boot/vmlinux
	#echo "/flash/vmlinux.gz" >> ${D}/boot/autoexec.bat
	rm -f arch/arm/boot/mkimage
	rm -f arch/mips/boot/mkimage
	rm -f lib/gen_crc32table
	rm -f firmware/ihex2fw
	oe_runmake headers_install INSTALL_HDR_PATH=${D}${exec_prefix}/src/linux-${KERNEL_VERSION} ARCH=$ARCH
}


INHIBIT_PACKAGE_STRIP = "0"

PACKAGES =+ "kernel-headers"
FILES_kernel-headers = "${exec_prefix}/src/linux*"
INSANE_SKIP_${PN}="vmlinux"

#RDEPENDS_${PN} += "kernel-module-fxp-0146 kernel-module-fxp-0147 kernel-module-tbfxp-0153 kernel-module-tbfxp-0162"
RDEPENDS_${PN} += "kernel-module-fxp-${KERNEL_MODULE_VERSION}"
inherit kernel
