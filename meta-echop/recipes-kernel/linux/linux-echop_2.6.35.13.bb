DESCRIPTION = "Linux Kernel for Samsung TV's"
SECTION = "kernel"
LICENSE = "GPL"


LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=4d92cd373abda3937c2bc47fbc49d690 \
                    file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"


inherit kernel

PATCHLEVEL ?= ""
DEPENDS = "u-boot-mkimage-native fakeroot-native"

#SRC_URI = "\
#	http://192.168.0.37/source/kernel/echop/VDLinux_2.6.35.11.tgz \
#"

SRC_URI = "\
	http://download.samygo.tv/oesources/kernel/echop/VDLinux_2.6.35.11.tgz \
"

#FILESPATHPKG_prepend = "linux-echop-2.6.35.13:"
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRC_URI += "file://defconfig"

SRC_URI[md5sum] = "7d7e4c6185cef95d68c32985acd9b960"
SRC_URI[sha256sum] = "d1e2a977686738268ff09e00d9e857ae4954be1514c3745f1a9c8ddae901ddc7"


S = "${WORKDIR}/VDLinux_2.6.35.11/linux-2.6.35.11"

COMPATIBLE_HOST = "arm.*-linux"
COMPATIBLE_MACHINE = "echop"

export ARCH = "arm"
export OS = "Linux"

KERNEL_OBJECT_SUFFIX = "ko"
#KERNEL_OUTPUT = "uImage"
KERNEL_IMAGETYPE = "uImage"


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
