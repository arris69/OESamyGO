# --enable-uuid is need for cross-compiling
EXTRA_OECONF = "--with-statduser=nobody \
                --enable-mountconfig \
                --enable-libmount-mount \
                --disable-nfsv41 \
                --enable-uuid \
                --disable-gss \
		--enable-tirpc \
                --disable-nfsdcltrack \
                --with-statdpath=/var/lib/nfs/statd \
               "

PACKAGECONFIG[libtirpc] = "--enable-tirpc,--with-tirpcinclude=${STAGING_INCDIR}/tirpc"

DEPENDS_append += " virtual/librpc"
DEPENDS_nfs-utils-discover = ""
RDEPENDS_nfs-utils-discover = ""
RRECOMMENDS_nfs-utils-discover = ""

PACKAGES =+ "nfs-utils-discover"
FILES_nfs-utils-discover = "${sbindir}/showmount"
