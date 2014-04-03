DESCRIPTION = "rar2fs is a FUSE based file system that can mount a source RAR archive/volume or a directory containing any number of RAR archives and read the contents as regular files on-the-fly."
AUTHOR = "hans.bec...@gmail.com"
HOMEPAGE = "http://code.google.com/p/rar2fs/"
SECTION = "fuse"
PRIORITY = "optional"
LICENSE = "GPLv2"
DEPENDS = "fuse unrar" 

PR = "r01"

SRC_URI = "http://${PN}.googlecode.com/files/${P}.tar.gz \
	http://forum.samygo.tv/download/file.php?id=993 \
	http://forum.samygo.tv/download/file.php?id=996 \
"

S = "${WORKDIR}/${P}"

EXTRA_OECONF = "--with-unrar-lib=${STAGING_LIBDIR} --with-unrar=${STAGING_INCDIR}/unrar"
inherit autotools pkgconfig

do_unpack2() {
        tar -xvzf ${WORKDIR}/file.php?id=993 -C ${S}/
        tar -xvzf ${WORKDIR}/file.php?id=996 -C ${S}/
}

addtask unpack2 before do_patch after do_unpack

