DESCRIPTION = "SamyGOso ARM Stack manipulator"
HOMEPAGE = "http://www.samygo.tv"
LICENSE = "GPL"


LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=4d92cd373abda3937c2bc47fbc49d690 \
                    file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"


SRC_URI[md5sum] = "e8b6fc873d77b8918d0844054d0f5f17"
SRC_URI[sha256sum] = "8c7f5f69f28f810f07f9f4ab35b31691c18fc11052d9cbee89acc8165a4a16f7"


SRC_URI = " \
   http://www.samygo.tv/juzis28/buildroot/samyGOso.tar.gz \
 "


S = "${WORKDIR}/samyGOso"

do_compile () {
  make
}

do_install () {
   install -d ${D}${bindir}
	install -m 0755 ${S}/samyGOso   ${D}${bindir}
}
