DESCRIPTION = "A small image just capable of allowing a device to boot."

DEPENDS +="kmod-native"
# require conf/distro/include/image-${MACHINE}.inc <- for what? we can use VAR_${MACHINE}_append shit...
QEMU_TARGETS = "arm mips mipsel i386"

IMAGE_VERSION = "${DISTRO_VERSION}"
IMAGE_NAME = "${DISTRO_NAME}-${IMAGE_VERSION}-${MACHINE}-${DATE}"
IMAGE_ROOTFS = "${TMPDIR}/rootfs"

IMAGE_LINK_NAME = ""
IMAGE_INSTALL = "${INSTALL_PACKAGES} ${INSTALL_X_PACKAGES} ${ROOTFS_PKGMANAGE_BOOTSTRAP} "
IMAGE_FSTYPES = "jffs2 squashfs tar.bz2"
IMAGE_LINGUAS = " "

LICENSE = "MIT"


IMAGE_ROOTFS_SIZE = "8192"
BUILD_ALL_DEPS = "1"
inherit image

INSTALL_X_PACKAGES = ""
INSTALL_PACKAGES = "samba-client samba-server samba-config"
