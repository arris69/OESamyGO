DESCRIPTION = "The Linux* SDK for UPnP* Devices (libupnp) provides developers with an API and open source code for building control points, devices, and bridges that are compliant with Version 1.0 of the Universal Plug and Play Device Architecture Specification."
HOMEPAGE = "http://upnp.sourceforge.net/"
LICENSE = "BSD"

LIC_FILES_CHKSUM = "file://LICENSE;md5=b3190d5244e08e78e4c8ee78544f4863"

LEAD_SONAME = "libupnp"
SRC_URI = "${SOURCEFORGE_MIRROR}/pupnp/${P}.tar.bz2"

SRC_URI[md5sum] = "8918dcf7428cd119d0c8275765ff2833"
SRC_URI[sha256sum] = "58d7cabec2b21c80e28a4e5090bba94a849a8f02450e26c1b985318a36b0bbb3"

inherit autotools pkgconfig

