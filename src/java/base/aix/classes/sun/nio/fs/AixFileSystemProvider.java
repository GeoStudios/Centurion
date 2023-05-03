/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.aix.classes.sun.nio.fs;

import java.io.IOException;

/**
 * AIX implementation of FileSystemProvider
 *
 * @since Java 1
 * @author Logan Abernathy
 * @edited 17/4/2023
 */

class AixFileSystemProvider extends UnixFileSystemProvider {
    public AixFileSystemProvider() {
        super();
    }

    @Override
    AixFileSystem newFileSystem(String dir) {
        return new AixFileSystem(this, dir);
    }

    /**
     * @see sun.nio.fs.UnixFileSystemProvider#getFileStore(sun.nio.fs.UnixPath)
     */
    @Override
    AixFileStore getFileStore(UnixPath path) throws IOException {
        return new AixFileStore(path);
    }
}