#
# Configuration to use external toolchain
#
 
#TOOLCHAIN_PATH_ADD = "${EXTERNAL_TOOLCHAIN}/bin:"
TARGET_SYS="mips-linux-gnu"
EXTERNAL_TOOLCHAIN = "${EXTERNAL_CROSS_DIR}/VDLinux-mips34kc"
TOOLCHAIN_PATH_ADD = "${EXTERNAL_TOOLCHAIN}/bin:"
PATH =. "${TOOLCHAIN_PATH_ADD}"

CSL_TARGET_SYS = "${TARGET_SYS}"

TARGET_PREFIX = "${CSL_TARGET_SYS}-"

PREFERRED_PROVIDER_linux-libc-headers = "external-mips-linux-toolchain"
PREFERRED_PROVIDER_linux-libc-headers-dev = "external-mips-linux-toolchain"
PREFERRED_PROVIDER_virtual/${TARGET_PREFIX}gcc = "external-mips-linux-toolchain"
PREFERRED_PROVIDER_virtual/${TARGET_PREFIX}gcc-initial = "external-mips-linux-toolchain"
PREFERRED_PROVIDER_virtual/${TARGET_PREFIX}gcc-intermediate = "mips-linux"
PREFERRED_PROVIDER_virtual/${TARGET_PREFIX}g++ = "external-mips-linux-toolchain"
PREFERRED_PROVIDER_virtual/${TARGET_PREFIX}binutils = "external-mips-linux-toolchain"
PREFERRED_PROVIDER_virtual/${TARGET_PREFIX}libc-for-gcc = "external-mips-linux-toolchain"
PREFERRED_PROVIDER_virtual/${TARGET_PREFIX}libc-initial = "external-mips-linux-toolchain"
PREFERRED_PROVIDER_virtual/${TARGET_PREFIX}compilerlibs = "external-mips-linux-toolchain"
PREFERRED_PROVIDER_libgcc = "external-mips-linux-toolchain"
PREFERRED_PROVIDER_eglibc = "external-mips-linux-toolchain"
PREFERRED_PROVIDER_virtual/libc = "external-mips-linux-toolchain"
PREFERRED_PROVIDER_virtual/libintl = "mips-linux"
PREFERRED_PROVIDER_virtual/libiconv = "external-mips-linux-toolchain"
PREFERRED_PROVIDER_glibc-thread-db = "external-mips-linux-toolchain"
PREFERRED_PROVIDER_virtual/linux-libc-headers = "external-mips-linux-toolchain"
PREFERRED_PROVIDER_virtual/linux-libc-headers-dev = "external-mips-linux-toolchain"
PREFERRED_PROVIDER_gdbserver ??= "external-mips-linux-toolchain"

# No need to re-compile the locale files
GLIBC_INTERNAL_USE_BINARY_LOCALE = "precompiled"
ENABLE_BINARY_LOCALE_GENERATION = ""
TARGET_LDFLAGS_prepend = " -L${EXTERNAL_TOOLCHAIN}/${TARGET_SYS}/lib -Wl,-rpath-link,${EXTERNAL_TOOLCHAIN}/${TARGET_SYS}/lib "

TOOLCHAIN_OPTIONS = " --sysroot=${STAGING_DIR_HOST}"

def csl_target_core(d):
    coredata = {
        'armv7a-vfp-neon': 'armv7-a-neon',
        'mips': 'mips32',
        'mipsel': 'el',
        'ppce500': 'te500v1',
        'ppce500mc': 'te500mc',
        'ppce500v2': 'te500v2',
        'ppce600': 'te600'
    }
    return coredata.get(d.getVar('TUNE_PKGARCH', True), '')

CSL_TARGET_CORE = "el"



addhandler toolchain_setup
toolchain_setup[eventmask] = "bb.event.BuildStarted"
python toolchain_setup () {
    import errno

    d = e.data
    d = d.createCopy()
    d.finalize()

    tarprefix = d.expand('${TARGET_PREFIX}')
    files = []
    if tarprefix == "mips-linux-gnu-":
        files.append((tarprefix + "ld", tarprefix + "ld.bfd"))

    if len(files) > 0:
        ext_bindir = d.expand('${EXTERNAL_TOOLCHAIN}/bin')
        loc_bindir = d.getVar('STAGING_BINDIR_TOOLCHAIN', True)
        bb.utils.mkdirhier(loc_bindir)
        for f in files:
            source = os.path.join(ext_bindir, f[0])
            dest = os.path.join(loc_bindir, f[1])
            try:
                os.symlink(source, dest)
            except OSError as exc:
                if exc.errno == errno.EEXIST:
                    break
                bb.fatal("Unable to populate toolchain binary symlink for %s: %s" % (f[0], f[1]))
}

require conf/distro/include/armv7a_sam-versions.inc

