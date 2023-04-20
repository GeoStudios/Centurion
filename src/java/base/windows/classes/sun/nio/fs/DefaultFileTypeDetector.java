/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.windows.classes.sun.nio.fs;

import java.nio.file.spi.FileTypeDetector;

/*
 * @since Pre Java 1
 * @author Logan Abernathy
 * @edited 19/4/2023
 */

public class DefaultFileTypeDetector {
    private DefaultFileTypeDetector() { }

    public static FileTypeDetector create() {
        return new RegistryFileTypeDetector();
    }
}
