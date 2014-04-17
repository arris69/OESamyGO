# samygo-extensions-image 
#
# Image configuration for the SamyGO Extensions Distribuion 
#
#
# Copyright Ser Lev Arris <arris@ZsoltTech.Com> (c) 2009-2010
# License: GPL (see http://www.gnu.org/licenses/gpl.txt for a copy of the license)
#
# Filename: ${DISTRO_NAME}-Extensions-image.bb
# Date: 04-Mai-2010 
# $Id: SamyGO-All-Extensions_v0.03.6.bb 1722 2011-03-29 16:19:22Z arris $

DESCRIPTION = "<description>"
MAINTAINER = "Ser Lev Arris <arris@ZsoltTech.Com>"
HOMEPAGE = "http://stb.ZsoltTech.Com"

INHIBIT_DEFAULT_DEPS = "1"
BUILD_ALL_DEPS = "1"

export IMAGE_BASENAME = "${PN}-${PV}-${PR}-for"

FEED_URIS += " \
                no-arch##${HOMEPAGE}/SamyGO-OE.dev/ipk/all \
                base##${HOMEPAGE}/SamyGO-OE.dev/ipk/${TARGET_ARCH}/base \
                python##${HOMEPAGE}/SamyGO-OE.dev/ipk/${TARGET_ARCH}/python \
                perl##${HOMEPAGE}/SamyGO-OE.dev/ipk/${TARGET_ARCH}/perl \
                debug##${HOMEPAGE}/SamyGO-OE.dev/ipk/${TARGET_ARCH}/debug \
"

FEED_DEPLOYDIR_BASE_URI = "http://stb.zsolttech.com/SamyGO-OE.dev/ipk"

IMAGE_INSTALL = "${INSTALL_PACKAGES} ${INSTALL_X_PACKAGES} "
IMAGE_LINGUAS = ""

IMAGE_RDEPENDS = ""

INSTALL_X_PACKAGES = " \
"

INSTALL_PACKAGES_foxp = " \
opkg \
\
less vim gdb \
procps \
initscripts \
kernel-modules \
eject \
netcat \
net-tools \
unzip wget ssmtp \
util-linux-losetup \
    gzip \
coreutils \
mc python perl \
dosfstools \
base-files-doc \
apache2 php-cgi \
busybox \
samba-server \
"

INSTALL_PACKAGES = " \
less vim gdb \
cksfv crypt-xor squashfs-tools unsquashfs-tools procps \
ADMsmb \
InitScripts \
kernel-modules \
wlan-rt3070usb wlan-rt3070sta wlan-rt3370usb \
kernel-module-evdev kernel-module-usbhid \
kernel-module-sg kernel-module-sr-mod kernel-module-pktcdvd kernel-module-cdrom \
kernel-module-firmware-class \
eject \
netcat \
elfpatcher \
nmap \
net-tools \
unzip wget ssmtp \
util-linux-losetup \
unrar tar gzip \
fbgrab \
fbset \
fbtest \
coreutils \
mc \
dosfstools \
base-files-doc \
apache2 php-cgi \
ntfs-3g httpfs2 \
afpfs-ng \
busybox \
aircrack-ng tcpdump \
libgcal-samples \
curl curl-certs \ 
transmission \
ifrename \
samba-server xupnpd curlftpfs \
"

INSTALL_PACKAGES_append_echop = " rtorrent StarterLib elfpatcher gdbserver"
INSTALL_PACKAGES_append_armv6 = " busybox StarterLib injectso avrfix2-noncip avrfix2-cip AudioSwitch wlan-rt25usb ulan-dm9601 SGOsd"
INSTALL_PACKAGES_append_arm = " strace"
INSTALL_PACKAGES_append_armv5te = " StarterLib ulan-dm9601 fuse-module rt73-chumby rt2570-k2wrlz rt73-k2wrlz wlan-rt2870usb"
# temp change due custom machine
# INSTALL_PACKAGES_append_armv6 = " StarterLib injectso s2-liplianin-modules rt73-chumby rt2570-k2wrlz rt73-k2wrlz wlan-rt2870usb"
INSTALL_PACKAGES_append_armv6 = " InitScripts busybox StarterLib injectso s2-liplianin-modules rt2570-k2wrlz rt73-k2wrlz wlan-rt2870usb fuse-module"
#  dvb-apps dvb-femon mumudvb"

# INSTALL_PACKAGES_append_armv7a = " kernel-module-hid fuse-module"
# test for T-GAP
# INSTALL_PACKAGES_append_armv7a = " rtorrent kernel-module-hid kernel-module-fuse wlan-rt2870usb"
# INSTALL_PACKAGES_append_armv7a8 = " rtorrent kernel-module-hid kernel-module-fuse wlan-rt2870usb"
INSTALL_PACKAGES_append_genoa = " rtorrent kmod-pty StarterLib elfpatcher gdbserver"
INSTALL_PACKAGES_append_echob := " rtorrent StarterLib elfpatcher gdbserver"
# xfsprogs shipped with VALD* ?

INSTALL_PACKAGES_append_mips = " xfsprogs strace fuse-module wlan-rt3572sta rt2570-k2wrlz rt73-k2wrlz wlan-rt2870usb"
# rt2570-k2wrlz 1.6.4? if ok ad to global package-list
INSTALL_PACKAGES_append_mipsel = " xfsprogs strace kernel-module-fuse rt2570-k2wrlz rt73-k2wrlz"
INSTALL_PACKAGES_append_mipsel32r2 = " xfsprogs strace kernel-module-fuse rt2570-k2wrlz rt73-k2wrlz"

INSTALL_PACKAGES_append_sh4 = " fuse-module wlan-rt3572sta busybox-static rt2570-k2wrlz rt73-k2wrlz wlan-rt2870usb"

INSTALL_PACKAGES_T-DUMMY = "cksfv crypt-xor squashfs-tools unsquashfs-tools strace procps \
				module-init-tools u-boot InitScripts kernel-modules eject netcat \
				unzip wget ssmtp util-linux-losetup unrar tar gzip fbgrab \
				fbset fbtest coreutils mc dosfstools base-files-doc fuse-module busybox"

INSTALL_PACKAGES_T-CHL5DAUC = "coreutils which net-tools busybox InitScripts wlan-rt3070usb wlan-rt3070sta wlan-rt3370usb wlan-rt3572sta wlan-rt2870usb rt2570-k2wrlz rt73-k2wrlz ssmtp injectso elfpatcher kernel-module-sunrpc kernel-module-lockd kernel-module-nfs kernel-module-nfs-acl kernel-module-firmware-class fuse-module php-cgi"

# for cip add avrfix2-cip for other avrfix2-noncip

IMAGE_LOGIN_MANAGER = ""

IMAGE_DEV_MANAGER = ""
IMAGE_INIT_MANAGER = ""
IMAGE_INITSCRIPTS = ""

IMAGE_DEVICE_TABLES = ""

IMAGE_FSTYPES = "zip rfs"
IMAGE_FSTYPES = "zip"
IMAGE_CMD_rfs = " \
        U_SIZE=$((( $(du -s -c -b ${IMAGE_ROOTFS}/mtd_tlib/ | cut -f1 | head -1) / 4096 + $(find ${IMAGE_ROOTFS}/mtd_tlib | wc -l) ) * 4096 )) ;\
        dd if=/dev/zero of=${DEPLOY_DIR_IMAGE}/${IMAGE_NAME}.rfs bs=$U_SIZE count=1 ;\
        mkrfs -l -v -d ${IMAGE_ROOTFS}/mtd_tlib \
        ${DEPLOY_DIR_IMAGE}/${IMAGE_NAME}.rfs"
IMAGE_CMD_zip = " \
        cd ${IMAGE_ROOTFS}/mtd_tlib && zip ${EXTRA_IMAGECMD} \
        ${DEPLOY_DIR_IMAGE}/${IMAGE_NAME}.zip ."
EXTRA_IMAGECMD_zip = "-r"

ROOTFS_POSTPROCESS_COMMAND = "rootfs_postprocess"

inherit image

OBJDUMP = "${CROSS_DIR}/bin/${HOST_PREFIX}objdump -p"
OBJDUMP = "${CROSS_BIN_DIR}objdump -p"
LIB_LIST = "${S}/liblist"

# perform some patches to the rootfs
rootfs_postprocess() {

		rm -rf ${IMAGE_ROOTFS}/etc/udhcpc.d || true
		rm -rf ${IMAGE_ROOTFS}/etc/mod* || true
		rm -rf ${IMAGE_ROOTFS}/etc/profile.d || true
		rm -rf ${IMAGE_ROOTFS}/etc/update-* || true
		rm -rf ${IMAGE_ROOTFS}/etc/default || true

		# on some devices we have no StarterLib
		install -d ${IMAGE_ROOTFS}/mtd_appdata/Images_960x540 || true
		install -d ${IMAGE_ROOTFS}/usr/share/fonts || true
		install -d ${IMAGE_ROOTFS}/usr/share/sounds || true
		install -d ${IMAGE_ROOTFS}/usr/share/doc || true
		install -d ${IMAGE_ROOTFS}/mtd_tlib/${DISTRO_NAME} || true
		install -d ${IMAGE_ROOTFS}/mtd_tlib/${DISTRO_NAME}/Eng_Res || true
		mkdir 	-p ${IMAGE_ROOTFS}/mtd_tlib/${DISTRO_NAME}/usr/share/fonts
		# we need this file later
		touch ${IMAGE_ROOTFS}/mtd_tlib/${DISTRO_NAME}/clmeta.dat

	for j in ${DIST_RES} ; do
		find ${IMAGE_ROOTFS}/mtd_appdata/Images_960x540 -name "${j}" -print0 -exec mv {} ${IMAGE_ROOTFS}/mtd_tlib/${DISTRO_NAME}/Eng_Res/ \;
		find ${IMAGE_ROOTFS}/usr/share/fonts -name "${j}" -print0 -exec mv {} ${IMAGE_ROOTFS}/mtd_tlib/${DISTRO_NAME}/Eng_Res/ \;
		find ${IMAGE_ROOTFS}/usr/share/sounds -name "${j}" -print0 -exec mv {} ${IMAGE_ROOTFS}/mtd_tlib/${DISTRO_NAME}/Eng_Res/ \; || true
	done

	mv -fv ${IMAGE_ROOTFS}/usr/share/doc ${IMAGE_ROOTFS}/mtd_tlib/${DISTRO_NAME}/
	mv -fv ${IMAGE_ROOTFS}/usr/share/common-licenses ${IMAGE_ROOTFS}/mtd_tlib/${DISTRO_NAME}/doc/ 
	mv -fv ${IMAGE_ROOTFS}/usr/share/xupnpd ${IMAGE_ROOTFS}/mtd_tlib/${DISTRO_NAME}/usr/share/ || true
	mv -fv ${IMAGE_ROOTFS}/usr/share/X11 ${IMAGE_ROOTFS}/mtd_tlib/${DISTRO_NAME}/usr/share/ || true
	mv -fv ${IMAGE_ROOTFS}/usr/share/fonts/X11 ${IMAGE_ROOTFS}/mtd_tlib/${DISTRO_NAME}/usr/share/fonts/ || true
	mv -fv ${IMAGE_ROOTFS}/usr/share/transmission ${IMAGE_ROOTFS}/mtd_tlib/${DISTRO_NAME}/usr/share/ || true
	rm -rf ${IMAGE_ROOTFS}/boot || true
	rm -rf ${IMAGE_ROOTFS}/usr/share

	# rm -rf ${IMAGE_ROOTFS}/usr/lib/ipkg

	rm -rf ${IMAGE_ROOTFS}/var

	if [ "${MACHINE}" == "T-CHL5DAUC" -o "${MACHINE}" == "T-CHL5DEUC" ] ; then
		opkg-cl ${IPKG_ARGS}  --autoremove remove obexpush kernel-module-bluetooth kernel-module-bnep \
					kernel-module-hidp kernel-module-l2cap kernel-module-rfcomm kernel-module-sco kernel-module-hci-usb \
					strace gdb mc wget unrar tar gzip unzip wakelan ${DISTRO_BLUETOOTH_MANAGER} 
		rm -f ${IMAGE_ROOTFS}/bin/gunz* || true
		rm -rf ${IMAGE_ROOTFS}/etc/terminfo || true
		rm -f ${IMAGE_ROOTFS}/usr/sbin/httpd || true
	fi

	if [ "${MACHINE}" == "T-GAP8DEUC" -o "${MACHINE}" == "T-GAPDEUC" -o "${MACHINE}" == "T-GAS6DEUC" ] ; then
		opkg-cl ${IPKG_ARGS}  --force-depends --autoremove remove obexpush kernel-module-bluetooth kernel-module-bnep \
					kernel-module-hidp kernel-module-l2cap kernel-module-rfcomm kernel-module-sco \
					kernel-module-hci-usb wlan-rt3070usb wlan-rt3070sta wlan-rt3370usb kmod-pty \
					wlan-rt2870usb wlan-rt3572sta ${DISTRO_BLUETOOTH_MANAGER}
	fi

	for i in usb_fault.ko usbcore.ko usb-storage.ko *-hcd.ko rfs.ko fsr*.ko xsr*.ko debug.ko sdpGMAC.ko \
			scsi_wait_scan.ko ntfs.ko mdrv-emac.ko 8139too.ko sdp_mac.ko ; do
		find ${IMAGE_ROOTFS}/lib/modules -name "${i}" -print0 -exec rm -f {} \;
	done

	if [ -d ${IMAGE_ROOTFS}/mtd_rwarea/bluetooth ] ; then
		cp -a ${IMAGE_ROOTFS}/mtd_rwarea/bluetooth ${IMAGE_ROOTFS}/etc/
	fi

	mkdir -p ${IMAGE_ROOTFS}/opt/${CUSTOM_RELEASE}/lib
	mkdir -p ${IMAGE_ROOTFS}/opt/${CUSTOM_RELEASE}/bin
	mkdir -p ${IMAGE_ROOTFS}/opt/${CUSTOM_RELEASE}/sbin
	mkdir -p ${IMAGE_ROOTFS}/opt/${CUSTOM_RELEASE}/usr/bin
	mkdir -p ${IMAGE_ROOTFS}/opt/${CUSTOM_RELEASE}/usr/sbin

	# suid files ignore LD_ vars
	chmod -s ${IMAGE_ROOTFS}/sbin/hci* || true
	bbnote "from ROOTFS_POSTPROCESS_COMMAND ignore libs ${IGNORED_LIBS}"
	echo > ${LIB_LIST}
	for i in ${IMAGE_ROOTFS}/usr/lib/* ${IMAGE_ROOTFS}/bin/* ${IMAGE_ROOTFS}/sbin/* ${IMAGE_ROOTFS}/usr/bin/* ${IMAGE_ROOTFS}/usr/sbin/* ${IMAGE_ROOTFS}/mtd_tlib/${DISTRO_NAME}/lib*.so ; do
		${OBJDUMP} $i | grep NEEDED | cut -d ' ' -f9- >> ${LIB_LIST}
		sort <${LIB_LIST} | uniq >${LIB_LIST}.sort
	done
	# try to find correct libs
	for j in `cat ${LIB_LIST}.sort` libthread_db.so.1 ; do
		for i in `find ${IMAGE_ROOTFS}/lib -name $j` `find ${IMAGE_ROOTFS}/usr/lib -name $j` ; do
			REAL=$(readlink -f $i)
			install ${REAL} ${IMAGE_ROOTFS}/opt/${CUSTOM_RELEASE}/lib/$j && rm -vf $i
		done
	done
	for j in ${IGNORED_LIBS} ; do
		rm -fv ${IMAGE_ROOTFS}/opt/${CUSTOM_RELEASE}/lib/$j || true
	done
	for j in ${NO_SHARE_LIBS} ; do
		rm -fv ${IMAGE_ROOTFS}/opt/${CUSTOM_RELEASE}/lib/$j || true
	done

	# get apache modules, if any
	if [ -d ${IMAGE_ROOTFS}/usr/lib/apache2 ] ; then
		install -d ${IMAGE_ROOTFS}/opt/${CUSTOM_RELEASE}/usr/lib
		mv ${IMAGE_ROOTFS}/usr/lib/apache2 ${IMAGE_ROOTFS}/opt/${CUSTOM_RELEASE}/usr/lib/
	fi
	
	for j in ${CUSTOM_LIBS} ; do
		find ${IMAGE_ROOTFS} -name "${j}" -type f -exec mv {} ${IMAGE_ROOTFS}/opt/${CUSTOM_RELEASE}/lib/ \;
	done

	rm -fr ${IMAGE_ROOTFS}/lib/*.so* ${IMAGE_ROOTFS}/usr/lib*
	
	# try to find & dereference binaries (f... vfat)
	for j in /bin /usr/bin /sbin /usr/sbin ; do
		for i in `find ${IMAGE_ROOTFS}${j} -type l` ; do
			REAL=$(readlink -f $i)
			if [ "$(dirname $REAL)" = "/usr/bin" -o "$(dirname $REAL)" = "/usr/sbin" -o "$(dirname $REAL)" = "/bin" -o "$(dirname $REAL)" = "/sbin" ] ; then
				echo "rm -f $i && install -m 755 ${IMAGE_ROOTFS}${REAL} $i"
				rm -f $i && $(install -m 755 ${IMAGE_ROOTFS}${REAL} $i || true)
			else
				echo "rm -f $i && install -m 755 ${REAL} $i"
				rm -f $i && $(install -m 755 ${REAL} $i || true)
			fi
		done
	done
	date "+%m%d%H%M%Y" > ${IMAGE_ROOTFS}/etc/timestamp
	echo "${CUSTOM_RELEASE} (${DISTRO_NAME}) $(date "+%m%d%H%M%Y")" > ${IMAGE_ROOTFS}/etc/release
	rm -f ${IMAGE_ROOTFS}/etc/init.d/[a-z]*

	for j in ${IGNORED_APPS} ; do
		find ${IMAGE_ROOTFS} -name "${j}" | xargs rm -rvf
	done
	for j in ${DIST_APPS} ; do
		find ${IMAGE_ROOTFS} -name "${j}" -type f -exec mv {} ${IMAGE_ROOTFS}/opt/${CUSTOM_RELEASE}/usr/bin/ \;
	done
	for j in ${DIST_SAPPS} ; do
		find ${IMAGE_ROOTFS} -name "${j}" -type f -exec mv {} ${IMAGE_ROOTFS}/opt/${CUSTOM_RELEASE}/usr/sbin/ \;
	done

	rm -rf ${IMAGE_ROOTFS}/usr/sbin ${IMAGE_ROOTFS}/usr/bin ${IMAGE_ROOTFS}/etc/rc?.d ${IMAGE_ROOTFS}/bin/* ${IMAGE_ROOTFS}/sbin/*
	install -d ${IMAGE_ROOTFS}/mtd_tlib/${DISTRO_NAME}
	for i in etc usr bin sbin opt lib ; do 
		cp -af ${IMAGE_ROOTFS}/$i ${IMAGE_ROOTFS}/mtd_tlib/${DISTRO_NAME}/ 
		if [ -d ${IMAGE_ROOTFS}/$i ] ; then
			rm -rf ${IMAGE_ROOTFS}/$i
		else
			bbnote "$i not here..."
		fi
	done

	# busybox is a present if shipped
	if [ -x ${IMAGE_ROOTFS}/mtd_tlib/${DISTRO_NAME}/opt/${CUSTOM_RELEASE}/usr/bin/busybox ] ; then
		mv ${IMAGE_ROOTFS}/mtd_tlib/${DISTRO_NAME}/opt/${CUSTOM_RELEASE}/usr/bin/busybox \
			${IMAGE_ROOTFS}/mtd_tlib/${DISTRO_NAME}/bin/
	fi

	# take latest version (before checked in)
#	cp -af /home/Video-HD/samygo/SamyGO-Extensions/CoreScript/trunk/rcSGO ${IMAGE_ROOTFS}/rcSGO
#	cp -af /home/Video-HD/samygo/SamyGO-Extensions/CoreScript/trunk/rc.sysinit ${IMAGE_ROOTFS}/mtd_tlib/${DISTRO_NAME}/etc/
#
#	cp -af /home/Video-HD/samygo/SamyGO-Extensions/InitScripts/trunk/init.d/*.ini* ${IMAGE_ROOTFS}/mtd_tlib/${DISTRO_NAME}/etc/init.d/
##	cp -af /home/Video-HD/samygo/SamyGO-Extensions/InitScripts/trunk/init.d/*.init ${IMAGE_ROOTFS}/mtd_tlib/${DISTRO_NAME}/etc/init.d/
## troubles on d-series?
##	mv ${IMAGE_ROOTFS}/mtd_tlib/${DISTRO_NAME}/etc/init.d/01_03_hotplug.init.dis ${IMAGE_ROOTFS}/mtd_tlib/${DISTRO_NAME}/etc/init.d/01_03_hotplug.init

	mkdir -p ${IMAGE_ROOTFS}/mtd_tlib/${DISTRO_NAME}/firmware
#	cp -f /lib/firmware/*7045* ${IMAGE_ROOTFS}/mtd_tlib/${DISTRO_NAME}/firmware/
#	cp -f /lib/firmware/*702x* ${IMAGE_ROOTFS}/mtd_tlib/${DISTRO_NAME}/firmware/
#	cp -f /lib/firmware/*9015* ${IMAGE_ROOTFS}/mtd_tlib/${DISTRO_NAME}/firmware/
#	cp -f /lib/firmware/*af9015* ${IMAGE_ROOTFS}/mtd_tlib/${DISTRO_NAME}/firmware/
#	cp -f /lib/firmware/AF15* ${IMAGE_ROOTFS}/mtd_tlib/${DISTRO_NAME}/firmware/
	echo 'Get some from: http://www.linuxtv.org/downloads/firmware/' > ${IMAGE_ROOTFS}/mtd_tlib/${DISTRO_NAME}/firmware/README
	echo 'dvb-usb-vp702x-02.fw http://www.slackforum.de/forum/index.php?t=getfile&id=33&' >> ${IMAGE_ROOTFS}/mtd_tlib/${DISTRO_NAME}/firmware/README
	echo 'dvb-usb-vp7021N.fw http://vp702x.zsolttech.com/' >> ${IMAGE_ROOTFS}/mtd_tlib/${DISTRO_NAME}/firmware/README

	mkdir -p ${IMAGE_ROOTFS}/mtd_tlib/${DISTRO_NAME}/etc/apache2/modules.d
#	cp -af /usr/src/OpenEmbedded/SamsungDTV-SELP/httpd.conf.in ${IMAGE_ROOTFS}/mtd_tlib/${DISTRO_NAME}/etc/apache2/httpd.conf
	rm -f ${IMAGE_ROOTFS}/mtd_tlib/${DISTRO_NAME}/etc/apache2/httpd.conf

	mv ${IMAGE_ROOTFS}/rcSGO ${IMAGE_ROOTFS}/mtd_tlib/${DISTRO_NAME}/
	cp -a ${IMAGE_ROOTFS}/mtd_rwarea/samba ${IMAGE_ROOTFS}/mtd_tlib/${DISTRO_NAME}/etc
	
	echo "you can remove me if you like to test (may buggy) graphical stuff." > ${IMAGE_ROOTFS}/mtd_tlib/${DISTRO_NAME}/plain.txt
	echo "you can remove me if you like to enable logging to /mtd_down/libSamyGO.log." > ${IMAGE_ROOTFS}/mtd_tlib/${DISTRO_NAME}/nolog.txt
	echo "you can remove me if you won't have verbose script output messages" > ${IMAGE_ROOTFS}/mtd_tlib/${DISTRO_NAME}/verbose.txt
	rm -rf ${IMAGE_ROOTFS}/mtd_appdata || true
	# fix for feed
	install -d ${IMAGE_ROOTFS}/etc/opkg ${IMAGE_ROOTFS}/var/lib/opkg

	        rm -rf ${IMAGE_ROOTFS}/mtd_tlib/${DISTRO_NAME}/Eng_Res
        	rm -f ${IMAGE_ROOTFS}/mtd_tlib/${DISTRO_NAME}/libSamyGO-new.so
        	mv -vf ${IMAGE_ROOTFS}/mtd_tlib/${DISTRO_NAME}/libSamyGO-old.so ${IMAGE_ROOTFS}/mtd_tlib/${DISTRO_NAME}/libSamyGO.so || true
#        	mv -vf ${IMAGE_ROOTFS}/mtd_tlib/${DISTRO_NAME}/etc/init.d/04_04_samba.init ${IMAGE_ROOTFS}/mtd_tlib/${DISTRO_NAME}/etc/init.d/04_04_samba.init.dis
        	rm -f ${IMAGE_ROOTFS}/mtd_tlib/${DISTRO_NAME}/*.txt
        	rm -f ${IMAGE_ROOTFS}/mtd_tlib/${DISTRO_NAME}/SamyGO
		DU_BYTES=$(du -b -s ${IMAGE_ROOTFS}/mtd_tlib/${DISTRO_NAME} | cut -f1 | tr -d [:space:])
        	sed -i -e s,SG_PLACEHOLDER,${DU_BYTES}, ${IMAGE_ROOTFS}/mtd_tlib/${DISTRO_NAME}/clmeta.dat
echo '#!/bin/sh
# run once script to make gathering of device infos easier
. /dtv/SGO.env

ln -sf $SYSROOT/etc/ssmtp /dtv/ssmtp

destroy_self()' > ${IMAGE_ROOTFS}/mtd_tlib/${DISTRO_NAME}/etc/init.d/99_99_z_sendinfo.init
echo "{" >> ${IMAGE_ROOTFS}/mtd_tlib/${DISTRO_NAME}/etc/init.d/99_99_z_sendinfo.init
echo '	rm -f "$(which sendmail)"
	rm -f "$(which ssmtp)"
	rm -rf "$SYSROOT/etc/ssmtp"
        rm -f "$1"' >> ${IMAGE_ROOTFS}/mtd_tlib/${DISTRO_NAME}/etc/init.d/99_99_z_sendinfo.init
echo "}" >> ${IMAGE_ROOTFS}/mtd_tlib/${DISTRO_NAME}/etc/init.d/99_99_z_sendinfo.init
echo '
INFOFILE="/dtv/info"
files="/.version /dtv/otn* /sys/selp/vd/lspinfo/* /proc/mo* /proc/version /proc/cpuinfo /proc/cmd* /proc/bus/usb/devices /dtv/SGO.env /proc/scsi/scsi"

sleep 50

echo -e "Subject: auto info for extensions '${MACHINE_CLASS}'\n" > $INFOFILE
for i in $files ; do
	echo $i >> $INFOFILE
	cat $i >> $INFOFILE 2>&1
done
echo "$SYSROOT/*" >> $INFOFILE
ls -l $SYSROOT/* >> $INFOFILE 2>&1
echo "/dtv/*" >> $INFOFILE
ls -l /dtv/* >> $INFOFILE 2>&1
echo "ifconfig" >> $INFOFILE
ifconfig -a >> $INFOFILE 2>&1
echo "env" >> $INFOFILE
env >> $INFOFILE 2>&1
echo "ps" >> $INFOFILE
ps eux >> $INFOFILE 2>&1
echo "netstat" >> $INFOFILE
netstat -tau >> $INFOFILE 2>&1
echo "dmesg" >> $INFOFILE
dmesg >> $INFOFILE 2>&1
echo "busybox test" >> $INFOFILE
busybox find /etc >> $INFOFILE 2>&1
echo "init scripts versions" >> $INFOFILE
grep Version $SYSROOT/etc/init.d/* >> $INFOFILE
echo "some sys info" >> $INFOFILE
busybox find /sys/bus >> $INFOFILE
busybox find /sys/devices >> $INFOFILE
busybox find /sys/block >> $INFOFILE
busybox find /proc/bus >> $INFOFILE
echo "platform devs" >> $INFOFILE
cat /sys/devices/platform/*/*/*/manufacturer >> $INFOFILE
# hexdump /mtd_exe/Factory.dat >> $INFOFILE 2>&1

if [ -f "$SYSROOT/noinfo.txt" ] ; then
	echo -e "Subject: auto info for extensions, user cancel\n" > $INFOFILE
fi

cat $INFOFILE | sendmail samy@zsolttech.com && destroy_self "$0"
' >> ${IMAGE_ROOTFS}/mtd_tlib/${DISTRO_NAME}/etc/init.d/99_99_z_sendinfo.init
chmod a+x ${IMAGE_ROOTFS}/mtd_tlib/${DISTRO_NAME}/etc/init.d/99_99_z_sendinfo.init
# touch ${IMAGE_ROOTFS}/mtd_tlib/${DISTRO_NAME}/noinfo.txt

# MidnightCommander shit
mv ${IMAGE_ROOTFS}/mtd_rwarea/mc ${IMAGE_ROOTFS}/mtd_tlib/${DISTRO_NAME}/ || true
echo '#!/bin/sh

echo "Start SamyGO Midnight Commander"

[ -e /dtv/SGO.env ] || exit

. /dtv/SGO.env

# [ -e /mtd_rwarea/mc/mc.hint ] || ln -sf $SYSROOT/mc /mtd_rwarea/mc
export MC_DATADIR=$SYSROOT/mc

TERM=xterm-color

mc $*

exit' > ${IMAGE_ROOTFS}/mtd_tlib/${DISTRO_NAME}/bin/mc.sh
chmod a+x ${IMAGE_ROOTFS}/mtd_tlib/${DISTRO_NAME}/bin/mc.sh

# NMap shit
echo '#!/bin/sh

echo "Start SamyGO nmap"

[ -e /dtv/SGO.env ] || exit

. /dtv/SGO.env

nmap --servicedb \$SYSROOT/etc/services $*

exit' > ${IMAGE_ROOTFS}/mtd_tlib/${DISTRO_NAME}/bin/nmap.sh
chmod a+x ${IMAGE_ROOTFS}/mtd_tlib/${DISTRO_NAME}/bin/nmap.sh

#cp -ar /home/WebSites/phpsysinfo ${IMAGE_ROOTFS}/mtd_tlib/${DISTRO_NAME}/
#cp /etc/phpsysinfo/config.php ${IMAGE_ROOTFS}/mtd_tlib/${DISTRO_NAME}/phpsysinfo/
#cp ${OE_DIR}/index.php ${IMAGE_ROOTFS}/mtd_tlib/${DISTRO_NAME}/phpsysinfo/file.php
install -d ${IMAGE_ROOTFS}/mtd_tlib/${DISTRO_NAME}/phpsysinfo
touch ${IMAGE_ROOTFS}/mtd_tlib/${DISTRO_NAME}/phpsysinfo/index.php
echo "Missing File" > ${IMAGE_ROOTFS}/mtd_tlib/${DISTRO_NAME}/phpsysinfo/config.php
echo "Missing File" > ${IMAGE_ROOTFS}/mtd_tlib/${DISTRO_NAME}/phpsysinfo/file.php
# latest (slov) phpsysinfo
# cp -ar /home/zsolt/phpsysinfo ${IMAGE_ROOTFS}/mtd_tlib/${DISTRO_NAME}/
sed -i -e 's,/etc/phpsysinfo/,,g' ${IMAGE_ROOTFS}/mtd_tlib/${DISTRO_NAME}/phpsysinfo/index.php

# mdev rules file
echo '# ([hs]d[a-z])    0:0 0660     >disk/%1/0
([hs]d[a-z])            0:0 0660     */sbin/hotplug
([hs]d[a-z])([0-9]+)    0:0 0660     */sbin/hotplug
sda 0:6 0640
cpu_dma_latency 0:0 0660
network_latency 0:0 0660
network_throughput 0:0 0660
# dvb stuff
dvb.* 0:0 0660 *MDEV_HELPER_DIR/dvbdev 
# usbdev.* 0:0 0660 
event.* 0:0 0660 =input/
mice 0:0 0660 =input/   
mouse.* 0:0 0660 =input/
log 0:0 0666        
loop[0-9].* 0:6 0640' > ${IMAGE_ROOTFS}/mtd_tlib/${DISTRO_NAME}/opt/${CUSTOM_RELEASE}/mdev.conf

echo '#!/bin/sh
#         "video", "audio", "sec", "frontend", "demux", "dvr", "ca",
#         "net", "osd"
# MDEV=dvb0.demux1 -> ADAPTER=dvb0 -> N=0
ADAPTER=${MDEV%.*}
N=${ADAPTER#dvb}
# MDEV=dvb0.demux1 -> DEVB_DEV=demux1
DVB_DEV=${MDEV#*.}
	case "$ACTION" in
	add|"")
		mkdir -p dvb/adapter${N}
		mv ${MDEV} dvb/adapter${N}/${DVB_DEV}
	;;
	remove)
		rm -f dvb/adapter${N}/${DVB_DEV}
		rmdir dvb/adapter${N} 2>/dev/null
		rmdir dvb/ 2>/dev/null
	esac' > ${IMAGE_ROOTFS}/mtd_tlib/${DISTRO_NAME}/opt/${CUSTOM_RELEASE}/lib/dvbdev
chmod a+x ${IMAGE_ROOTFS}/mtd_tlib/${DISTRO_NAME}/opt/${CUSTOM_RELEASE}/lib/dvbdev

#if [ "${MACHINE}" == "T-GAPDEUC" ] ; then
#	rm -rf ${IMAGE_ROOTFS}/mtd_tlib/${DISTRO_NAME}/lib/modules/2*
#	echo "wait for Samsung Release" > ${IMAGE_ROOTFS}/mtd_tlib/${DISTRO_NAME}/lib/modules/notice.txt
#fi
}

# libs on tv
IGNORED_LIBS = "ld-2.5.90.so 	\
libAdi.so 			\
libAe.so 			\
libc-2.5.90.so 			\
libCore.so 			\
libdl-2.5.90.so 		\
libdl.so 			\
libExtention.so 		\
libFFMpeg.so			\
libFileInput.so			\
libFLVDemux.so			\
libgcc_s.so			\
libGCF.so			\
libglib-2.0.so			\
libGPlayerPorting.so		\
libHttpInput.so			\
libHttpsInput.so		\
libHttp.so			\
libJi.so			\
libJs.so			\
libKonfabulator.so		\
libm-2.5.90.so			\
libMMFCore.so			\
libMMSInput.so			\
libmms.so			\
libMP3Transform.so		\
libnss_dns-2.5.90.so		\
libnss_files-2.5.90.so		\
libPCMOutput.so			\
libPhnd.so			\
libPlApp.so			\
libPlaybackEngine.so		\
libPl.so			\
libPluginTvOEM.so		\
libPluginTv.so			\
libpngGP.so			\
libpthread-2.5.90.so		\
libptp.so			\
libresolv-2.5.90.so		\
librt-2.5.90.so			\
libRTMPInput.so			\
libRTPInput.so			\
libSDAL.so			\
libSDL_image.so			\
libSDL_mixer.so			\
libSDL.so			\
libsmpeg.so			\
libstdc++.so			\
libSui.so			\
libTDImage.so			\
libTD.so			\
libungif.so			\
libusb.so			\
libVideoOutput.so		\
libWsal.so			\
libYahooSDLHWAcceleration.so	\
libYahooTimeManager.so		\
libz.so 			\
libc.so.6 ld-linux.so.3 libpthread.so.0 libgcc_s.so.1 libm.so.6 libdl.so.2 librt.so.1 libstdc++.so.6 libresolv.so.2 \
libpng12.so.0 \
"
# libs provided by tv but different names (3 lines above)

# sh4 based devs
IGNORED_LIBS += " ld-2.5.so ld-linux.so.2 libc-2.5.so libdl-2.5.so libdl.so.2"
# mips based devs
IGNORED_LIBS += " ld.so.1"

IGNORED_LIBS_genoa += " libgcrypt.so.11"
IGNORED_LIBS_echop += " libgcrypt.so.11"

# our libs
CUSTOM_LIBS = "libSGexeDSP-ng*"

# better to link this libs static into apps, some tvs miss libz libcrypto...
NO_SHARE_LIBS = "libssl* libcrypto* libcurl* libxml*"
NO_SHARE_LIBS = "libxml* libglib-2.0*"
NO_SHARE_LIBS = "libcurl*"
NO_SHARE_LIBS = ""
IGNORED_APPS = "*.util-linux *.util-linux-ng *.net-tools *.procps *.shadow *.26 *.coreutils tz* .debug portmap udev generate-modprobe.conf"
DIST_APPS = "${INSTALL_PACKAGES} ${RDEPENDS} SGexeDSP mount djmount wakelan fusesmb fusesmb.cache showmount [sn]mb*l* which gdb netstat mail gunzip fw_printenv ssh scp dbclient pmap ps xxd"
DIST_SAPPS = "ulockmgr_server vsftpd httpd *squashfs opkg opkg-cl ipkg ipkg-cl passwd dropbea* sendmail mdev nmbd"
DIST_RES =  'icon_Information.bmp icon_InfoY.bmp full_bg.bmp FreeMono.ttf FreeMonoBold.ttf bg_sub_top.bmp Message_error.bmp Message_BG.bmp ticker_bg_loading.bmp ticker_loading??.bmp Ani???.bmp loading_alpha_??.png b_mebox_bg_01.bmp bg_trans.bmp bg_no?trans.bmp main_2depth sel.bmp'

# filesystem tools
DIST_SAPPS += " mkfs.vfat mkfs.xfs xfs_repair xfs_growfs"
# aircrack
DIST_SAPPS += " airmon-ng aireplay-ng airodump-ng airtun-ng smbd"
DIST_APPS += " kstats ivstools makeivs airdecap-ng packetforge-ng"
# for x stuff
DIST_APPS += " xauth Xsdl Xfbdev Xvfb ADM-smb mount_afp"
# some tools
# DIST_APPS += " ldd uptime stat basename dirname lshal"
# multimedia apps
# DIST_APPS += " ffprobe ffserver ffplay Xsdl Xvfb"
# dvb apps, bee carefull dvb* moves modules into bin dir!!!!
DIST_APPS += " gnutv femon php php-cgi aria2c *bin/dvb*"
# bluetooth
DIST_SAPPS += " hcid sdpd hciconfig hciattach"
DIST_APPS += " dd l2ping sdptool dund hcitool pand rfcomm hidd obexftpd opd"
DIST_APPS += " calendar_event Get_all_contacts updated_calendar_events new_contact_from Get_all_events"
DIST_SAPPS += " transmission-daemon"
