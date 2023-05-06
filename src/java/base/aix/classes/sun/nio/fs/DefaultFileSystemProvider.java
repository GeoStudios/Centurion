/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.aix.classes.sun.nio.fs;

import java.nio.file.FileSystem;

/**
 * Creates this platform's default FileSystemProvider.
 * 
 * @since Alpha cdk-1.0
 * @author Logan Abernathy
 * @edited 17/4/2023
 */

public class DefaultFileSystemProvider {
    private static final AixFileSystemProvider INSTANCE
        = new AixFileSystemProvider();

    private DefaultFileSystemProvider() { }

    /**
     * Returns the platform's default file system provider.
     */
    public static AixFileSystemProvider instance() {
        return INSTANCE;
    }

    /**
     * Returns the platform's default file system.
     */
    public static FileSystem theFileSystem() {
        return INSTANCE.theFileSystem();
    }
}
