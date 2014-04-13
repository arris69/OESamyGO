PACKAGES =+ "samba-config samba-client samba-server"

#PACKAGECONFIG[libtirpc] = "LIBS=-ltirpc,,libtirpc,"
PACKAGECONFIG[libtirpc] = "LIBS=-ltirpc,LIBS=-ltirpc,libtirpc"
#

sysconfdir = "/mtd_rwarea"
localstatedir = "/dtv"

#EXTRA_OECONF += "\
#        --without-ads \
#        --without-winbind \
#        --without-ldap \
#        --without-krb5"

EXTRA_OECONF += " \ 
		--disable-cups \
		--without-automount \
		--without-pam \
"

do_configure_append() {
	bbnote "hi samba!!!!! ${OE_CONFIG}"
	sed -i "s/-ldl/& -ltirpc -lpthread/" Makefile
}

FILES_samba-client = "${bindir}/smbclient ${bindir}/nmblookup"
FILES_samba-server = "${sbindir}/[sn]mbd"
FILES_samba-config = "${sysconfdir}/samba/*"

