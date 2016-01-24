require sdk-common.inc

# sun's java needs to be repacked JAVA_MIRROR is set in conf/distro/include/sane-toolchain-sun-java.inc 

PR = "40"

# [http://xxx.xxxxxxxxx.xxx/jdk1.6.0_20.tgz]
# provide checksume here and not in conf/checksums.ini, than you have to extract
# the data from sun's jdk-6u20-linux-i586.bin for your self!!!!!
SRC_URI[archive.md5sum] = "bb5163d8b368ddfc374dbcb19a07574e"
SRC_URI[archive.sha256sum] = "7573672e2594d4a054cc62982b4b499cfaa84d3d35c19d844db1ededd2e5602d"

LIC_FILES_CHKSUM = "file://LICENSE;md5=620eb6b17d24a0cfe95457dd20a19343"
 
PARALLEL_MAKE = ""
INHIBIT_DEFAULT_DEPS = "1"
INHIBIT_PACKAGE_STRIP = "1"
NATIVE_INSTALL_WORKS = "1"
BBCLASSEXTEND = "native nativesdk"

# PROVIDES += "ecj-initial classpath-initial classpath-minimal classpath java-sun javac-sun virtual/java-initial virtual/java virtual/javac virtual/javac-native"

