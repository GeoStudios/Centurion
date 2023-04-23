/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package sun.nio.fs;

import java.io.IOException;
import java.nio.file.Path;

/**
 * File type detector that uses a file extension to look up its MIME type
 * via the Apple Uniform Type Identifier interfaces.
 * 
 * @since Pre Java 1
 * @author Logan Abernathy
 * @edited 23/4/2023
 */
class UTIFileTypeDetector extends AbstractFileTypeDetector {
    UTIFileTypeDetector() {
        super();
    }

    private native String probe0(String fileExtension) throws IOException;

    @Override
    protected String implProbeContentType(Path path) throws IOException {
        Path fn = path.getFileName();
        if (fn == null)
            return null;  // no file name

        String ext = getExtension(fn.toString());
        if (ext.isEmpty())
            return null;  // no extension

        return probe0(ext);
    }

    static {
        jdk.internal.loader.BootLoader.loadLibrary("nio");
    }
}
