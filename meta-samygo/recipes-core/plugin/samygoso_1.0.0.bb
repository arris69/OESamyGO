DESCRIPTION = "SamyGOso ARM Stack manipulator"
HOMEPAGE = "http://www.samygo.tv"
LICENSE = "GPL"


LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=4d92cd373abda3937c2bc47fbc49d690 \
                    file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

SRC_URI = " \
   http://www.samygo.tv/juzis28/buildroot/samygoso.tar.gz \
 "


S = "${WORKDIR}/samyGOso"

do_compile () {
  make
}

do_install () {
   install -d ${D}${bindir}
	install -m 0755 ${S}/samyGOso   ${D}${bindir}
}
