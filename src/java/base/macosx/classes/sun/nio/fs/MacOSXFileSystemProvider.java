/*
 * Geo Studios Protective License
 *
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 *
 * Whoever collects this software or tool may not distribute the copy that has been obtained.
 *
 * This software or tool may not be used to gain a commercial or monetary advantage.
 *
 * Copyright will be included in any software or tool using this license, no matter the size or type of software or tool.
 *
 * This software or tool is not under any patent, but the software or tool shall not be
 * sold or uploaded as some other product or without the original creators consent and
 * permission. If the following happens, consequences will occur due to following
 * instructions or not following the rules written in this document.
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
