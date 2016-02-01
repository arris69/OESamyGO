FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
DEPENDS = "virtual/librpc quota"
SRC_URI += "file://rpcbind-01-no-yp-support.patch \
		"
