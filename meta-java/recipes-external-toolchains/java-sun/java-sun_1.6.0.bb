require sdk-common.inc

# sun's java needs to be repacked JAVA_MIRROR is set in conf/distro/include/sane-toolchain-sun-java.inc 

PR = "20"

# [http://xxx.xxxxxxxxx.xxx/jdk1.6.0_20.tgz]
# provide checksume here and not in conf/checksums.ini, than you have to extract
# the data from sun's jdk-6u20-linux-i586.bin for your self!!!!!
SRC_URI[archive.md5sum] = "d81d9cdb7ef45599ae2362b88f8d57cc"
SRC_URI[archive.sha256sum] = "c8e9af49876aaf19edaaf8a77c95f93ce0be3e271731c2b6cf811b51f45f6c1c"

LIC_FILES_CHKSUM = "file://LICENSE;md5=620eb6b17d24a0cfe95457dd20a19343"
 
PARALLEL_MAKE = ""
INHIBIT_DEFAULT_DEPS = "1"
INHIBIT_PACKAGE_STRIP = "1"
NATIVE_INSTALL_WORKS = "1"
BBCLASSEXTEND = "native nativesdk"

PROVIDES += "ecj-initial ecj-bootstrap-native libecj-bootstrap classpath classpath-minimal virtual/java-initial virtual/java-native virtual/javac-native"

