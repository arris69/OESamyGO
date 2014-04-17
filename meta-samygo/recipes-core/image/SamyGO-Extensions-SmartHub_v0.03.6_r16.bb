# samygo-extensions-image-bugfix 
#
# Image configuration for the SamyGO Extensions Distribuion 
#
#
# Copyright Ser Lev Arris <arris@ZsoltTech.Com> (c) 2009-2010
# License: GPL (see http://www.gnu.org/licenses/gpl.txt for a copy of the license)
#
# Filename: ${DISTRO_NAME}-Extensions-image.bb
# Date: 01-Oct-2011 
# $Id$

require SamyGO-All-Extensions_${PV}_${PR}.bb

FEED_URIS = ""

IMAGE_RDEPENDS = ""
IMAGE_NAME = "${IMAGE_BASENAME}-${MACHINE}-${DATETIME}"

IMAGE_INSTALL += " samba-server httpfs2"
IMAGE_INSTALL_append_genoa += " samba-server httpfs2 kernel-module-ftdi-sio kernel-module-usbserial"
IMAGE_INSTALL_append_echop += " samba-server httpfs2 kernel-module-ftdi-sio kernel-module-usbserial"
IMAGE_INSTALL_append_echob += " samba-server httpfs2 kernel-module-ftdi-sio kernel-module-usbserial"
IMAGE_INSTALL_append_titania8 += " samba-server httpfs2 kernel-module-ftdi-sio kernel-module-usbserial curlftpfs"

DIST_SAPPS += " smbd"

insert_feed_uris(){
	:
}

ROOTFS_POSTPROCESS_COMMAND = "rootfs_postprocessp "
rootfs_postprocessp() {

	rootfs_postprocess
		rm -rf ${IMAGE_ROOTFS}/mtd_tlib/${DISTRO_NAME}/opt/privateer/lib/dvbdev
		mkdir -p ${IMAGE_ROOTFS}/mtd_tlib/CSS
		mkdir -p ${IMAGE_ROOTFS}/mtd_tlib/JavaScript
#		cp -vf ${OE_DIR}/oe.custom/recipes/samba/files/samygo/smb.conf ${IMAGE_ROOTFS}/mtd_tlib/${DISTRO_NAME}/etc/samba/
		echo '[global]
wins support = no
unix charset = UTF-8
dos charset = UTF-8
display charset = UTF-8
security = share

# set workgroup, own share etc...
include = /mtd_rwarea/smb.conf
# workgroup = SAMYGO
# netbios name = Samsung-TV
# server string = Some Server

load printers = no
printing = bsd
printcap name = /dev/null
disable spoolss = yes
guest account = root
guest ok = yes
guest only = yes
null passwords = yes
smb passwd file = /dtv/pp

[Samsung TV]
comment = USB Drives on TV
path = /dtv/usb
public = yes
writable = yes' > ${IMAGE_ROOTFS}/mtd_tlib/${DISTRO_NAME}/etc/samba/smb.conf

		echo '<?xml version="1.0" encoding="UTF-8"?>
		<widget>
		<icon>'${DISTRO_NAME}'/SamyGO.png
		</icon>
		<preview></preview>
		<previewimg>'${DISTRO_NAME}'/SamyGO.png</previewimg>
		<previewjs></previewjs>
		<type>user</type>
		<cpname>SamyGOE</cpname>
		<cpauthjs>SamyGOE</cpauthjs>
		<movie>n</movie>
		<srcctl>n</srcctl>
		<ver>3.615</ver>
		<mgrver>1.020</mgrver>
		<ticker>n</ticker>
		<audiomute>n</audiomute>
		<videomute>n</videomute>
		<login>y</login>
		<childlock>y</childlock>
		<keymode>1</keymode>
		<dcont>y</dcont>
		<widgetname>SamyGOE</widgetname>
		<cplogo>'${DISTRO_NAME}'/SamyGO.png</cplogo>
		<preIcon>'${DISTRO_NAME}'/SamyGO.png</preIcon>
		<description>
		Enjoy the various Extensions.
		</description>
		<width>960</width>
		<height>540</height>
		<author>
		<name>SamyGO Co. Ltd.</name>
		<email></email>
		<link>http://www.samygo.tv</link>
		<organization>SamyGO Co. Ltd.</organization>
		</author>
		</widget>' > ${IMAGE_ROOTFS}/mtd_tlib/config.xml

		echo 'var Main = {}
var widgetAPI = new Common.API.Widget();
Main.onLoad = function () {
	widgetAPI.sendReadyEvent();}
Main.onUnload = function() {}' > ${IMAGE_ROOTFS}/mtd_tlib/JavaScript/Main.js

	echo 'body {
	margin: 0;
	padding: 0;
	background-color: transparent;
	}
	#welcome {
	position: absolute;
	left: 50px;
	top: 50px;
	width: 500px;
	height: 70px;
	background-color: #0000AF;
	color: #99FFFF;
	font-size: 30px;
	text-align: center;
	}' > ${IMAGE_ROOTFS}/mtd_tlib/CSS/Main.css

if [ ${MACHINE_CLASS} != "titania8" ] ; then
	echo '<!DOCTYPE html>
	<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>SamyGO Extensions</title>
	<link rel="stylesheet" type="text/css" href="CSS/Main.css"/>
	<script type="text/javascript" language="javascript" src="$MANAGER_WIDGET/Common/API/Widget.js"></script>
	<script type="text/javascript" language="javascript" src="JavaScript/Main.js"></script>
	</head>
	<body onload="Main.onLoad();" onunload="Main.onUnload();">
	<div id="welcome">Extensions-Pack is Nested on this Device!</div>
	</body>' > ${IMAGE_ROOTFS}/mtd_tlib/index.html
else
		echo '<?xml version="1.0" encoding="UTF-8"?>
		<widget>
		<icon>'${DISTRO_NAME}'/SamyGO.png
		</icon>
		<preview></preview>
		<previewimg>'${DISTRO_NAME}'/SamyGO.png</previewimg>
		<previewjs></previewjs>
		<type>user</type>
		<cpname>SamyGOE</cpname>
		<cpauthjs>SamyGOE</cpauthjs>
		<apptype>14</apptype>
		<contents>main.lua</contents>
		<movie>y</movie>
		<srcctl>n</srcctl>
		<ver>3.615</ver>
		<mgrver>1.020</mgrver>
		<ticker>y</ticker>
		<audiomute>n</audiomute>
		<videomute>n</videomute>
		<login>y</login>
		<childlock>y</childlock>
		<keymode>1</keymode>
		<dcont>y</dcont>
		<widgetname>SamyGOE</widgetname>
		<cplogo>'${DISTRO_NAME}'/SamyGO.png</cplogo>
		<preIcon>'${DISTRO_NAME}'/SamyGO.png</preIcon>
		<description>
		Enjoy the various Extensions.
		</description>
		<width>960</width>
		<height>540</height>
		<author>
		<name>SamyGO Co. Ltd.</name>
		<email></email>
		<link>http://www.samygo.tv</link>
		<organization>SamyGO Co. Ltd.</organization>
		</author>
		</widget>' > ${IMAGE_ROOTFS}/mtd_tlib/config.xml

	echo '<!DOCTYPE html>
	<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>SamyGO Extensions</title>
	<link rel="stylesheet" type="text/css" href="CSS/Main.css"/>
	<script type="text/javascript" language="javascript" src="$MANAGER_WIDGET/Common/API/Widget.js"></script>
	<script type="text/javascript" language="javascript" src="JavaScript/Main.js"></script>
	</head>
	<body onload="Main.onLoad();" onunload="Main.onUnload();">
	</body>' > ${IMAGE_ROOTFS}/mtd_tlib/index.html

	echo 'function Game_Main(mypath)
  shell_cmd=assert(package.loadlib(mypath.."shell_cmd.so", "shell_cmd"))
  shell_cmd()
end' > ${IMAGE_ROOTFS}/mtd_tlib/main.lua
fi
}
