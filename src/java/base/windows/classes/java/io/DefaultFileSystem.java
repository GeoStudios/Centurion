/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.windows.classes.java.io;

/**
 * @since Alpha cdk-1.0
 * @author Logan Abernathy
 * @edited 18/4/2023
*/

final class DefaultFileSystem {

    private DefaultFileSystem() {}

    /**
     * Return the FileSystem object for Windows platform.
     */
    public static FileSystem getFileSystem() {
        return new WinNTFileSystem();
    }
}