require jdk-common.inc
BBCLASSEXTEND += "nativesdk"

PR = "66"

# sun's java needs to be repacked JAVA_MIRROR is set in conf/distro/include/sane-toolchain-sun-java.inc 
# [http://xxx.xxxxxxxxx.xxx/jdk1.8.0_66.tgz]
# provide checksume here and not in conf/checksums.ini, than you have to extract
# the data from sun's jdk-8u66-linux-i586.bin for your self!!!!!
SRC_URI[archive.md5sum] = "bb5163d8b368ddfc374dbcb19a07574e"
SRC_URI[archive.sha256sum] = "7573672e2594d4a054cc62982b4b499cfaa84d3d35c19d844db1ededd2e5602d"

LIC_FILES_CHKSUM = "file://LICENSE;md5=620eb6b17d24a0cfe95457dd20a19343"
