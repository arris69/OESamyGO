DESCRIPTION = "Linux Kernel for Samsung TV's"
SECTION = "kernel"
LICENSE = "GPL"


LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=4d92cd373abda3937c2bc47fbc49d690 \
                    file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"



PATCHLEVEL ?= ""
DEPENDS = "u-boot-mkimage-native fakeroot-native"

SRCREV="a67d3d9e18ef66b76ef330c750904b474ab80700"
SRC_URI = "\
	git://github.com/card2000/VDLinux_3.0.20.git;protocol=git;branch=master \
"
#FILESPATHPKG_prepend = "linux-mst12-3.0.20:"
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRC_URI += "file://defconfig"

SRC_URI[md5sum] = "7d7e4c6185cef95d68c32985acd9b960"
SRC_URI[sha256sum] = "d1e2a977686738268ff09e00d9e857ae4954be1514c3745f1a9c8ddae901ddc7"

S = "${WORKDIR}/git/linux-3.0.20"


COMPATIBLE_HOST = "arm.*-linux"
COMPATIBLE_MACHINE = "mst12"

export ARCH = "arm"
export OS = "Linux"

KERNEL_OBJECT_SUFFIX = "ko"
#KERNEL_OUTPUT = "uImage"
KERNEL_IMAGETYPE = "uImage"

do_configure_prepend() {
	
	mkdir -p ${S}/fs/rfs
	mkdir -p ${S}/fs/tntfs
	mkdir -p ${S}/fs/exfat
	
	cp ${S}/fs/Kconfig.X13 ${S}/fs/Kconfig
	cp -Rf ${WORKDIR}/git/RFS_3.0.0_b044-LinuStoreIII_1.2.0_b040-FSR_1.2.1p1_b139_RTM/fs/rfs_X13_release/* ${S}/fs/rfs
	cp -Rf ${WORKDIR}/git/TUXERA_NTFS/X13_release/* ${S}/fs/tntfs
	cp -Rf ${WORKDIR}/git/exFATs/exFAT.X13_release/* ${S}/fs/exfat
	
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
inherit kernel
