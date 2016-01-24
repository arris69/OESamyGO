require sdk-common.inc

# sun's java needs to be repacked JAVA_MIRROR is set in conf/distro/include/sane-toolchain-sun-java.inc 

PR = "22"

# [http://xxx.xxxxxxxxx.xxx/jdk1.5.0_22.tgz]
# provide checksume here and not in conf/checksums.ini, than you have to extract
# the data from sun's jdk-1_5_0_22-linux-i586.bin for your self!!!!!
SRC_URI[archive.md5sum] = "a065da5773088737f246976731d25554"
SRC_URI[archive.sha256sum] = "6cbae53efea6e040911280a23a18bb229c2506edb1f3b0e2b80419cebae5cd21"

LIC_FILES_CHKSUM = "file://LICENSE;md5=1bdeb3b2dfa8d126bc9b74ce2428485e"
 
PARALLEL_MAKE = ""
INHIBIT_DEFAULT_DEPS = "1"
INHIBIT_PACKAGE_STRIP = "1"
NATIVE_INSTALL_WORKS = "1"
BBCLASSEXTEND = "native nativesdk"

PROVIDES += "ecj-initial ecj-bootstrap-native libecj-bootstrap classpath classpath-minimal virtual/java-initial virtual/java-native virtual/javac-native"

