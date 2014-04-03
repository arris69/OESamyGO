ORIG_PN = "CoreScript"
DESCRIPTION = "SamyGO Extensions ${ORIG_PN}"
LICENSE = "CLOSED"
SECTION = "Gallery/Games"
MAINTAINER = "Ser Lev Arris <arris@ZsoltTech.Com>"
HOMEPAGE = "http://www.samygo.tv"

PR = "r03"

RECOMMENDS = ""
RRECOMMENDS = ""
DEPENDS = ""
RDEPENDS = ""

# from r01
# SRCDATE = "20100201"
# r02
# SRCDATE = "20100308"
# r03 (fix for subdir search, nested hack)
SRCDATE = "20100327"
SRCREV = "r1235"

SRC_URI = "svn://samygo.svn.sourceforge.net/svnroot/samygo/SamyGO-Extensions;module=${PN};protocol=https"
SRC_URI = "svn://svn.code.sf.net/p/samygo/code/SamyGO-Extensions;module=${ORIG_PN}"

PACKAGES = "${PN}-dbg ${PN} ${PN}-bin ${PN}-dev ${PN}-doc ${PN}-locale"

# Enable this to generate package per MACHINE
# PACKAGE_ARCH = ${MACHINE_ARCH}
PACKAGE_ARCH = "noarch"

S = "${WORKDIR}/${ORIG_PN}/trunk"

do_install () {
	mkdir -p ${D}/etc
	install -m 0755 rcSGO ${D}/
	install -m 0755 rc.sysinit ${D}/etc/
        mkdir -p ${D}/usr/share/doc/${ORIG_PN}
        cp -a doc/* ${D}/usr/share/doc/${ORIG_PN}/ || true
}

FILES_${PN} = "/rcSGO /etc/rc.sysinit"
FILES_${PN}-doc = "/usr/share/doc/${ORIG_PN}"
