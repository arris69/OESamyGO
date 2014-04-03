ORIG_PN = "InitScripts"
DESCRIPTION = "SamyGO Extensions ${ORIG_PN}" 
LICENSE = "CLOSED"
SECTION = "Gallery/Games"
MAINTAINER = "Ser Lev Arris <arris@ZsoltTech.Com>"
HOMEPAGE = "http://samygo.sourceforge.net"

PR = "r11"

#RRECOMMENDS += " starterlib corescript nfs-utils-discover samba djmount dropbear vsftpd util-linux-mount util-linux-umount \
#		kernel-module-cifs kernel-module-dummy-hcd kernel-module-g-file-storage \
#		bluez-utils-nodbus fusesmb wakelan"

RRECOMMENDS_${PN} += " starterlib corescript nfs-utils-discover samba djmount dropbear vsftpd util-linux-mount util-linux-umount \
		kernel-module-cifs kernel-module-dummy-hcd kernel-module-g-file-storage \
		fusesmb wakelan"

RPROVIDES_${PN} += "${PN}-functions"
PACKAGES =+ "${PN}-functions"
RDEPENDS_${PN} = "${PN}-functions"
FILES_${PN}-functions = "${sysconfdir}/init.d/01_01_catch_crap.init"

ALTERNATIVE_PRIORITY_${PN}-functions = "190"
ALTERNATIVE_${PN}-functions = "functions"
ALTERNATIVE_LINK_NAME[functions] = "${sysconfdir}/init.d/functions"

# r03
SRCDATE = "20100201"
SRCDATE = "20100313"
SRCREV = "r1327"
SRC_URI = "svn://svn.code.sf.net/p/samygo/code/SamyGO-Extensions;module=${ORIG_PN}"

PACKAGES = "${PN}-dbg ${PN} ${PN}-bin ${PN}-dev ${PN}-doc ${PN}-locale"

# Enable this to generate package per MACHINE
# PACKAGE_ARCH = ${MACHINE_ARCH}
PACKAGE_ARCH = "noarch"

S = "${WORKDIR}/${ORIG_PN}/trunk"

do_install () {
	mkdir -p ${D}/etc/init.d
	install -m 0755 init.d/*init* ${D}/etc/init.d/
	mkdir -p ${D}/usr/share/doc/${ORIG_PN}
	cp -a doc/* ${D}/usr/share/doc/${ORIG_PN}/
}

FILES_${PN} = "/etc/init.d"
FILES_${PN}-doc = "/usr/share/doc/${ORIG_PN}"


