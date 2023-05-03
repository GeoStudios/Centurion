/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.macosx.classes.sun.nio.fs;

/**
 * @since Java 2
 * @author Logan Abernathy
 * @edited 23/4/2023
 */

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
