PACKAGECONFIG[libtirpc] = "LIBS=-ltirpc,,libtirpc,"

do_configure_append() {
	bbnote "hi samba!!!!! ${OE_CONFIG}"
#	sed -i "s/-ldl/& -ltirpc -lpthread/" Makefile
}
