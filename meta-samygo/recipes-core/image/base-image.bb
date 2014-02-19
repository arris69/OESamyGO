DESCRIPTION = "A small image just capable of allowing a device to boot."
DEPENDS += "apache2 dropbear mc patch usbutils strace setserial diffutils gawk grep groff gzip curl wget samba ser2net lua php hexedit vim xclock oscam qemu"
IMAGE_INSTALL = "opkg ${ROOTFS_PKGMANAGE_BOOTSTRAP}"

IMAGE_LINGUAS = " "

LICENSE = "MIT"

inherit image

IMAGE_ROOTFS_SIZE = "8192"
BUILD_ALL_DEPS = "1"
