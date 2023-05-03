/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.unix.classes.java.io;

/**
 * @since Java 2
 * @author Logan Abernathy
 * @edited 20/4/2023 
 */
class DefaultFileSystem {

    /**
     * Return the FileSystem object for Unix-based platform.
     */
    public static FileSystem getFileSystem() {
        return new UnixFileSystem();
    }
}
