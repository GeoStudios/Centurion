/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.macosx.classes.sun.nio.fs;

import java.nio.file.Path;
import java.nio.file.spi.FileTypeDetector;
import jdk.internal.util.StaticProperty;
import sun.security.action.GetPropertyAction;

/**
 * MacOSX implementation of FileSystemProvider
 * 
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 23/4/2023
 */

class MacOSXFileSystemProvider extends BsdFileSystemProvider {
    public MacOSXFileSystemProvider() {
        super();
    }

    @Override
    MacOSXFileSystem newFileSystem(String dir) {
        return new MacOSXFileSystem(this, dir);
    }

    @Override
    FileTypeDetector getFileTypeDetector() {
        Path userMimeTypes = Path.of(StaticProperty.userHome(), ".mime.types");

        return chain(new MimeTypesFileTypeDetector(userMimeTypes),
                     new UTIFileTypeDetector());
    }
}
