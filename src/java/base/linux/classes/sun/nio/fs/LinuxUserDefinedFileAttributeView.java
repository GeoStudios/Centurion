/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.linux.classes.sun.nio.fs;

/**
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 23/4/2023
 */

class LinuxUserDefinedFileAttributeView
    extends UnixUserDefinedFileAttributeView
{

    LinuxUserDefinedFileAttributeView(UnixPath file, boolean followLinks) {
        super(file, followLinks);
    }

    @Override
    protected int maxNameLength() {
        return 255;
    }

}
