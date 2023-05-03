/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.unix.classes.sun.nio.fs;

import java.nio.file.FileSystems;
import java.nio.file.spi.FileTypeDetector;
import java.nio.file.spi.FileSystemProvider;

/*
 * @since Java 2
 * @author Logan Abernathy
 * @edited 20/4/2023 
 */

public class DefaultFileTypeDetector {
    private DefaultFileTypeDetector() { }

    public static FileTypeDetector create() {
        FileSystemProvider provider = FileSystems.getDefault().provider();
        return ((UnixFileSystemProvider)provider).getFileTypeDetector();
    }
}
