# http://lists.openembedded.org/pipermail/openembedded-core/2013-March/076128.html

DEPENDS_append = " virtual/librpc"

do_configure_prepend() {
    export CFLAGS="${CFLAGS} -I${STAGING_INCDIR}/tirpc"
    export LDFLAGS="-ltirpc ${LDFLAGS} "
}
