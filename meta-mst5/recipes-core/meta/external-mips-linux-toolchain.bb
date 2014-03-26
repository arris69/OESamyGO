#require recipes-core/glibc/glibc-package.inc

INHIBIT_DEFAULT_DEPS = "1"
CSL_VER_MAIN ??= ""

# License applies to this recipe code, not the toolchain itself
SUMMARY = "External VDLinux-mips34kc-toolchain-lite toolchain"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

DEPENDS += "${@base_conditional('PREFERRED_PROVIDER_linux-libc-headers', PN, '', 'linux-libc-headers', d)}"
PROVIDES += "\
	linux-libc-headers \
	virtual/${TARGET_PREFIX}gcc \
	virtual/${TARGET_PREFIX}g++ \
	virtual/${TARGET_PREFIX}gcc-initial \
	virtual/${TARGET_PREFIX}gcc-intermediate \
	virtual/${TARGET_PREFIX}binutils \
	virtual/${TARGET_PREFIX}libc-for-gcc \
	virtual/${TARGET_PREFIX}libc-initial \
	virtual/${TARGET_PREFIX}compilerlibs \
	virtual/libc \
	virtual/libintl \
	virtual/libiconv \
	virtual/linux-libc-headers \
	glibc-thread-db \
	libgcc \
	eglibc \
	"
CSL_VER_MAIN = "4.3.3"
PV = "${CSL_VER_MAIN}"
PR = "r1"


SRC_URI = "http://download.samygo.tv/Toolchains/VDLinux-mips34kc-toolchain-lite-20100223.tar.xz \
file://SUPPORTED"

SRC_URI[md5sum] = "8d7de8e5035f1a711f999b8d1161abff"
SRC_URI[sha256sum] = "c7e783eb13a8cf82466586825657ae02e6af79b8c58e7b04fbeb61a488dc88ff"

CSL_RELOCATE = "/VDLinux-mips34kc-toolchain-lite-20100223"


do_install() {
	install -d ${EXTERNAL_CROSS_DIR}/VDLinux-mips34kc
	cp -a ${WORKDIR}/${CSL_RELOCATE}/* ${EXTERNAL_CROSS_DIR}/VDLinux-mips34kc
	
	install -d ${D}${sysconfdir} ${D}${bindir} ${D}${sbindir} ${D}${base_bindir} ${D}${libdir}
	install -d ${D}${base_libdir} ${D}${base_sbindir} ${D}${datadir} ${D}/usr
	
	if [ -d ${EXTERNAL_TOOLCHAIN}/${CSL_TARGET_SYS}/libc/${CSL_TARGET_CORE} ]; then
		cp -a ${EXTERNAL_TOOLCHAIN}/${CSL_TARGET_SYS}/libc/${CSL_TARGET_CORE}/lib/.  ${D}${base_libdir}
		cp -a ${EXTERNAL_TOOLCHAIN}/${CSL_TARGET_SYS}/libc/${CSL_TARGET_CORE}/etc/.  ${D}${sysconfdir}
		cp -a ${EXTERNAL_TOOLCHAIN}/${CSL_TARGET_SYS}/libc/${CSL_TARGET_CORE}/sbin/. ${D}${base_sbindir}
		if [ ! -e ${EXTERNAL_TOOLCHAIN}/${CSL_TARGET_SYS}/libc/${CSL_TARGET_CORE}/usr/include ]; then
			cp -a ${EXTERNAL_TOOLCHAIN}/${CSL_TARGET_SYS}/libc/usr/include  ${D}/usr/
		fi
		cp -a ${EXTERNAL_TOOLCHAIN}/${CSL_TARGET_SYS}/libc/${CSL_TARGET_CORE}/usr/.  ${D}/usr/
	else
		cp -a ${EXTERNAL_TOOLCHAIN}/${CSL_TARGET_SYS}/libc/lib/.  ${D}${base_libdir}
		cp -a ${EXTERNAL_TOOLCHAIN}/${CSL_TARGET_SYS}/libc/etc/.  ${D}${sysconfdir}
		cp -a ${EXTERNAL_TOOLCHAIN}/${CSL_TARGET_SYS}/libc/sbin/. ${D}${base_sbindir}
		cp -a ${EXTERNAL_TOOLCHAIN}/${CSL_TARGET_SYS}/libc/usr/.  ${D}/usr/
	fi
	
	sed -i -e "s# /lib# ../../lib#g" -e "s# /usr/lib# .#g" ${D}${libdir}/libc.so
	sed -i -e "s# /lib# ../../lib#g" -e "s# /usr/lib# .#g" ${D}${libdir}/libpthread.so
	

	

}

SYSROOT_PREPROCESS_FUNCS += "external_toolchain_sysroot_adjust"
external_toolchain_sysroot_adjust() {
       if [ -n "${CSL_TARGET_CORE}" ]; then
               rm -f ${SYSROOT_DESTDIR}/${CSL_TARGET_CORE}
               ln -s . ${SYSROOT_DESTDIR}/${CSL_TARGET_CORE}
       fi
}



PACKAGES =+ " \
		libgcc \
		libstdc++ libstdc++-staticdev \
		linux-libc-headers \
		gdbserver \
		"

# This test should be fixed to ignore .a files in .debug dirs
INSANE_SKIP_${PN}-dbg = "staticdev"

# We don't care about GNU_HASH in prebuilt binaries
INSANE_SKIP_${PN}-utils += "ldflags"
INSANE_SKIP_${PN}-dev += "ldflags"
INSANE_SKIP_libstdc++ += "ldflags"
INSANE_SKIP_libgcc += "ldflags"
INSANE_SKIP_gdbserver += "ldflags"

PKG_${PN} = "glibc"
PKG_${PN}-dev = "glibc-dev"
PKG_${PN}-staticdev = "glibc-staticdev"
PKG_${PN}-doc = "glibc-doc"
PKG_${PN}-dbg = "glibc-dbg"
PKG_${PN}-pic = "glibc-pic"
PKG_${PN}-utils = "glibc-utils"
PKG_${PN}-gconv = "glibc-gconv"
PKG_${PN}-extra-nss = "glibc-extra-nss"
PKG_${PN}-thread-db = "glibc-thread-db"
PKG_${PN}-pcprofile = "glibc-pcprofile"

PKGV = "${CSL_VER_LIBC}"
PKGV_libgcc = "${CSL_VER_GCC}"
PKGV_libgcc-dev = "${CSL_VER_GCC}"
PKGV_libstdc++ = "${CSL_VER_GCC}"
PKGV_libstdc++-dev = "${CSL_VER_GCC}"
PKGV_libstdc++-staticdev = "${CSL_VER_GCC}"
PKGV_linux-libc-headers = "${CSL_VER_KERNEL}"
PKGV_linux-libc-headers-dev = "${CSL_VER_KERNEL}"
PKGV_gdbserver = "${CSL_VER_GDB}"
PKGV_gdbserver-dbg = "${CSL_VER_GDB}"

FILES_libgcc = "${base_libdir}/libgcc_s.so.1"
FILES_libgcc-dev = "${base_libdir}/libgcc_s.so"
FILES_libstdc++ = "${libdir}/libstdc++.so.*"
FILES_libstdc++-dev = " \
	${includedir}/c++/${PV} \
	${libdir}/libstdc++.so \
	${libdir}/libstdc++.la \
	${libdir}/libsupc++.la \
	"
FILES_libstdc++-staticdev = "${libdir}/libstdc++.a ${libdir}/libsupc++.a"
FILES_linux-libc-headers = " \
	${includedir}/asm* \
	${includedir}/linux \
	${includedir}/mtd \
	${includedir}/rdma \
	${includedir}/scsi \
	${includedir}/sound \
	${includedir}/video \
	"
FILES_gdbserver = "${bindir}/gdbserver"
FILES_gdbserver-dbg = "${bindir}/.debug/gdbserver"


python () {
    if not d.getVar("CSL_VER_MAIN"):
        raise bb.parse.SkipPackage("External toolchain not configured (CSL_VER_MAIN not set).")
}
