/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.linux.classes.sun.nio.fs;

import java.nio.file.FileSystem;

/**
 * Creates this platform's default FileSystemProvider.
 * 
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 23/4/2023
 */

public class DefaultFileSystemProvider {
    private static final LinuxFileSystemProvider INSTANCE
        = new LinuxFileSystemProvider();

    private DefaultFileSystemProvider() { }

    /**
     * Returns the platform's default file system provider.
     */
    public static LinuxFileSystemProvider instance() {
        return INSTANCE;
    }

    /**
     * Returns the platform's default file system.
     */
    public static FileSystem theFileSystem() {
        return INSTANCE.theFileSystem();
    }
}
