#!/bin/sh

DONT_BUILD="\
	acl-native       byacc-native            flex-native             kern-tools-native          m4-native          pkgconfig-native         rpm-native             util-macros-native \
	apr-native       cryptodev-linux-native  gdbm-native             kmod-native                makedepend-native  popt-native              serf-native            xcb-proto-native \
	apr-util-native  db-native               gettext-minimal-native  ldconfig-native            makedevs-native    prelink-native           shadow-native          xproto-native \
	attr-native      dbus-glib-native        gettext-native          libffi-native              mtd-utils-native   pseudo-native            sqlite3-native         xz-native \
	autoconf-native  dbus-native             glib-2.0-native         libpcre-native             ncurses-native     python-native            squashfs-tools-native  zlib-native \
	automake-native  e2fsprogs-native        gnome-common-native     libtool-native             openssl-native     python-pygobject-native  subversion-native \
	bc-native        elfutils-native         gnu-config-native       libxml-parser-perl-native  opkg-native        python-pyrex-native      texinfo-dummy-native \
	beecrypt-native  expat-native            gtk-doc-stub-native     libxml2-native             opkg-utils-native  python-scons-native      u-boot-mkimage-native \
	binutils-native  fakeroot-native         gzip-native             lz4-native                 ossp-uuid-native   quilt-native             update-rc.d-native \
	bison-native     file-native             intltool-native         lzo-native                 perl-native        readline-native          util-linux-native \
	"

mkdir -p build/samygo/conf
touch build/samygo/conf/sanity.conf

make image
