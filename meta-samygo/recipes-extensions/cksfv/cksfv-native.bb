DESCRIPTION = "Hash Calculator"
LICENSE = "CLOSED"
SECTION = "console/utils"
PV = "1.3.14"
PR = "r1"

SRC_URI = "http://zakalwe.fi/~shd/foss/cksfv/files/cksfv-${PV}.tar.gz"
SRC_URI_append_samygo =+ " file://validinfo_format.patch;patch=1"

S = "${WORKDIR}/cksfv-${PV}"

inherit native

do_configure () {
	./configure	
}

do_install () {
        install -m 0755 src/cksfv ${STAGING_BINDIR}/cksfv
}
