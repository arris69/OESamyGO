#
# Configuration to use an external Arago binary toolchain
#

EXTERNAL_TOOLCHAIN ?= "/usr/local/arago-2011.09/${TARGET_ARCH}"
EXTERNAL_TOOLCHAIN = "/usr/src/OpenEmbedded/SamsungDTV-SELP_OE20_github/trunk/tools/arm-v7a15v3r1"

prefix_nativesdk = ""

ARAGO_TARGET_SYS = "arm-v7a15v3r1-linux-gnueabi"
TARGET_PREFIX = "${ARAGO_TARGET_SYS}-"

# Need to use newer linux headers, as 2011.09 has too old ones for newer udev
PREFERRED_PROVIDER_linux-libc-headers = "linux-libc-headers"
PREFERRED_PROVIDER_linux-libc-headers-dev = "linux-libc-headers"
PREFERRED_PROVIDER_virtual/linux-libc-headers = "linux-libc-headers"

PREFERRED_PROVIDER_virtual/${TARGET_PREFIX}gcc = "external-arago-toolchain"
PREFERRED_PROVIDER_virtual/${TARGET_PREFIX}gcc-initial = "external-arago-toolchain"
PREFERRED_PROVIDER_virtual/${TARGET_PREFIX}gcc-intermediate = "external-arago-toolchain"
PREFERRED_PROVIDER_virtual/${TARGET_PREFIX}g++ = "external-arago-toolchain"
PREFERRED_PROVIDER_virtual/${TARGET_PREFIX}binutils = "external-arago-toolchain"
PREFERRED_PROVIDER_virtual/${TARGET_PREFIX}libc-for-gcc = "external-arago-toolchain"
PREFERRED_PROVIDER_virtual/${TARGET_PREFIX}compilerlibs = "external-arago-toolchain"
PREFERRED_PROVIDER_libgcc = "external-arago-toolchain"
PREFERRED_PROVIDER_virtual/libc = "external-arago-toolchain"
PREFERRED_PROVIDER_virtual/libintl = "external-arago-toolchain"
PREFERRED_PROVIDER_virtual/libiconv = "external-arago-toolchain"
PREFERRED_PROVIDER_glibc-thread-db = "external-arago-toolchain"
PREFERRED_PROVIDER_gdbserver = "external-arago-toolchain"

PREFERRED_PROVIDER_gcc-cross-canadian-${TRANSLATED_TARGET_ARCH} ?= "external-arago-sdk-toolchain"
PREFERRED_PROVIDER_binutils-cross-canadian-${TRANSLATED_TARGET_ARCH} ?= "external-arago-sdk-toolchain"
#PREFERRED_PROVIDER_gdb-cross-canadian-${TRANSLATED_TARGET_ARCH} = "external-arago-sdk-toolchain"

# Special case for gdb to be built as part of canadian-cross-sdk, instead of packaged from external toolchain
bindir_pn-gdb-cross-canadian-${TRANSLATED_TARGET_ARCH} = "${exec_prefix}/bin"
PREFERRED_PROVIDER_gdb-cross-canadian-${TRANSLATED_TARGET_ARCH} = "gdb-cross-canadian-${TRANSLATED_TARGET_ARCH}"

GCCVERSION ?= "4.6%"
SDKGCCVERSION ?= "4.6%"
BINUVERSION ?= "2.20%"
EGLIBCVERSION ?= "2.12%"
LINUXLIBCVERSION ?= "3.2"

PREFERRED_VERSION_gcc ?= "${GCCVERSION}"
PREFERRED_VERSION_gcc-cross ?= "${GCCVERSION}"
PREFERRED_VERSION_gcc-cross-initial ?= "${GCCVERSION}"
PREFERRED_VERSION_gcc-cross-intermediate ?= "${GCCVERSION}"
PREFERRED_VERSION_gcc-crosssdk ?= "${SDKGCCVERSION}"
PREFERRED_VERSION_gcc-crosssdk-initial ?= "${SDKGCCVERSION}"
PREFERRED_VERSION_gcc-crosssdk-intermediate ?= "${SDKGCCVERSION}"
PREFERRED_VERSION_gcc-cross-canadian-${TRANSLATED_TARGET_ARCH} ?= "${GCCVERSION}"
PREFERRED_VERSION_gcc-runtime ?= "${GCCVERSION}"
PREFERRED_VERSION_gcc-runtime-nativesdk ?= "${SDKGCCVERSION}"
PREFERRED_VERSION_libgcc ?= "${GCCVERSION}"
PREFERRED_VERSION_libgcc-nativesdk ?= "${SDKGCCVERSION}"
PREFERRED_VERSION_binutils ?= "${BINUVERSION}"
PREFERRED_VERSION_binutils-cross ?= "${BINUVERSION}"
PREFERRED_VERSION_binutils-crosssdk ?= "${BINUVERSION}"
PREFERRED_VERSION_binutils-cross-canadian-${TRANSLATED_TARGET_ARCH} ?= "${BINUVERSION}"
PREFERRED_VERSION_linux-libc-headers ?= "${LINUXLIBCVERSION}"
PREFERRED_VERSION_linux-libc-headers-nativesdk ?= "${LINUXLIBCVERSION}"
PREFERRED_VERSION_eglibc                   ?= "${EGLIBCVERSION}"
PREFERRED_VERSION_eglibc-locale            ?= "${EGLIBCVERSION}"
PREFERRED_VERSION_eglibc-nativesdk         ?= "${EGLIBCVERSION}"
PREFERRED_VERSION_eglibc-initial           ?= "${EGLIBCVERSION}"
PREFERRED_VERSION_eglibc-initial-nativesdk ?= "${EGLIBCVERSION}"
PREFERRED_VERSION_cross-localedef-native   ?= "${EGLIBCVERSION}"

TOOLCHAIN_OPTIONS = " --sysroot=${STAGING_DIR_HOST}"

DISTRO_FEATURES_LIBC = "ipv4 ipv6 libc-backtrace libc-big-macros libc-bsd libc-cxx-tests libc-catgets libc-crypt \
			libc-crypt-ufc libc-db-aliases libc-envz libc-fcvt libc-fmtmsg libc-fstab libc-ftraverse \
			libc-getlogin libc-idn libc-inet-anl libc-libm libc-libm-big \
			libc-memusage libc-nis libc-nsswitch libc-rcmd libc-rtld-debug libc-spawn libc-streams libc-sunrpc \
			libc-utmp libc-utmpx libc-wordexp libc-posix-clang-wchar libc-posix-regexp libc-posix-regexp-glibc \
			libc-posix-wchar-io"

ENABLE_BINARY_LOCALE_GENERATION = "0"
GLIBC_INTERNAL_USE_BINARY_LOCALE = "precompiled"