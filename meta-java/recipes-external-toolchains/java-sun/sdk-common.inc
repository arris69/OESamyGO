DESCRIPTION = "Java technology's versatility, efficiency, platform portability, and security make it the ideal technology for network computing. From laptops to datacenters, game consoles to scientific supercomputers, cell phones to the Internet, Java is everywhere!"
HOMEPAGE = "http://www.java.com/download/manual.jsp"
SECTION = "devel"
LICENSE = "JRE"

JAVA_MIRROR = "http://stb.zsolttech.com"

PARALLEL_MAKE = ""
INHIBIT_DEFAULT_DEPS = "-99"
INHIBIT_PACKAGE_STRIP = "1"
NATIVE_INSTALL_WORKS = "1"
BBCLASSEXTEND = "native"

SRC_URI = "${JAVA_MIRROR}/jdk${PV}_${PR}.tgz;name=archive"

#PROVIDES += " virtual/java-initial ecj-initial libecj-bootstrap virtual/java virtual/javac virtual/javac-native xerces-j"
PROVIDES = " "

INC_PR = "r01"

FILESDIR = "${@os.path.dirname(bb.data.getVar('FILE',d,1))}/jdk${PV}"

#
# Set some default values
#

S = "${WORKDIR}/jdk${PV}_${PR}"

do_configure(){
	:
}

do_compile () {
        :
}

do_install () {
	rm -rf ${S}/patches
	rm -rf ${S}/db/docs
	rm -rf ${S}/db/demo
	rm -rf ${S}/demo
	rm -rf ${S}/sample
	install -d ${D}${datadir} ${D}${bindir} ${D}${datadir}/classpath-initial 
	mv ${S} ${D}${datadir}/java
	ln -s ../share/java/jre/bin/java ${D}${bindir}/
	ln -s ../share/java/jre/bin/java ${D}${bindir}/java-initial
	ln -s ../share/java/bin/javac ${D}${bindir}/
	ln -s ../share/java/bin/javac ${D}${bindir}/ecj-initial
	chmod -R +w ${D}${datadir}/java
}

