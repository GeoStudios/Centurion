/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package sun.nio.fs;

class BsdUserDefinedFileAttributeView
    extends UnixUserDefinedFileAttributeView
{

    BsdUserDefinedFileAttributeView(UnixPath file, boolean followLinks) {
        super(file, followLinks);
    }

    @Override
    protected int maxNameLength() {
        // see XATTR_MAXNAMELEN in https://github.com/apple/darwin-xnu/blob/master/bsd/sys/xattr.h
        return 127;
    }

}
