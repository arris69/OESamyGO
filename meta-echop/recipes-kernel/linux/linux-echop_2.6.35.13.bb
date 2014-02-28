DESCRIPTION = "Linux Kernel for Samsung TV's"
SECTION = "kernel"
LICENSE = "GPL"


LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=4d92cd373abda3937c2bc47fbc49d690 \
                    file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"



PATCHLEVEL ?= ""
DEPENDS = "u-boot-mkimage-native fakeroot-native"

SRCREV="2ef883cfb68140b75446d7358ad4874a86fdb134"
SRC_URI = "\
	git://github.com/card2000/VDLinux_2.6.35.11.git;protocol=git;branch=master \
"

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRC_URI += "file://defconfig"

S = "${WORKDIR}/git/linux-2.6.35.11"

COMPATIBLE_HOST = "arm.*-linux"
COMPATIBLE_MACHINE = "echop"

export ARCH = "arm"
export OS = "Linux"

KERNEL_OBJECT_SUFFIX = "ko"
#KERNEL_OUTPUT = "uImage"
KERNEL_IMAGETYPE = "uImage"
export OS = "Linux"
KERNEL_IMAGEDEST = "/mtd_rwarea/boot"

FILES_kernel-image = "/${KERNEL_IMAGEDEST}/${KERNEL_IMAGETYPE}.gz"
FILES_kernel-vmlinux = "/${KERNEL_IMAGEDEST}/vmlinux*"
do_configure_prepend() {
	rm -Rf ${S}/fs/rfs
	rm -Rf ${S}/fs/tntfs
	rm -Rf ${S}/include/linux/vdlp_version.h
	mkdir -p ${S}/fs/rfs
	mkdir -p ${S}/fs/tntfs
	echo '#define DTV_KERNEL_VERSION "${KERNEL_MODULE_VERSION}, release"' > ${S}/include/linux/vdlp_version.h
	echo '#define DTV_LAST_PATCH "0702, DTV, Echo.P, release, DEU_BRANCH"' >> ${S}/include/linux/vdlp_version.h
	
	cp -Rf ${WORKDIR}/git/RFS_3.0.0_b043-LinuStoreIII_1.2.0_b039-FSR_1.2.1p1_b139_RTM/fs/rfs_Echo.P_release/* ${S}/fs/rfs
	cp -Rf ${WORKDIR}/git/TUXERA_NTFS/Echo.P_release/* ${S}/fs/tntfs
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

# pkg_postinst_kernel-image () {
#		if [ -d /proc/stb ] ; then
#			flash_eraseall /dev/mtd1
#			nandwrite -p /dev/mtd1 /${KERNEL_IMAGEDEST}/${KERNEL_IMAGETYPE}.gz
#		fi
#		rm -f /${KERNEL_IMAGEDEST}/${KERNEL_IMAGETYPE}.gz
#	 true
# }

INHIBIT_PACKAGE_STRIP = "0"

#PACKAGES =+ "kernel-headers"
#FILES_kernel-headers = "${exec_prefix}/src/linux*"
RDEPENDS_${PN} += "kernel-module-ecp-${KERNEL_MODULE_VERSION}"
inherit kernel
