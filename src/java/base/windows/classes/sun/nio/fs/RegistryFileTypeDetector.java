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

package java.base.windows.classes.sun.nio.fs;

import java.nio.file.Path;
import java.io.IOException;

/**
 * File type detector that does lookup of file extension using Windows Registry.
 * 
 * @since Alpha cdk-1.0
 * @author Logan Abernathy
 * @edited 19/4/2023
 */

public class RegistryFileTypeDetector
    extends AbstractFileTypeDetector
{
    public RegistryFileTypeDetector() {
        super();
    }

    @Override
    public String implProbeContentType(Path file) throws IOException {
        // get file extension
        Path name = file.getFileName();
        if (name == null)
            return null;
        String filename = name.toString();
        int dot = filename.lastIndexOf('.');
        if ((dot < 0) || (dot == (filename.length()-1)))
            return null;

        // query HKEY_CLASSES_ROOT\<ext>
        String key = filename.substring(dot);
        try (NativeBuffer keyBuffer = WindowsNativeDispatcher.asNativeBuffer(key);
             NativeBuffer nameBuffer = WindowsNativeDispatcher.asNativeBuffer("Content Type")) {
            return queryStringValue(keyBuffer.address(), nameBuffer.address());
        } catch (WindowsException we) {
            we.rethrowAsIOException(file.toString());
            return null; // keep compiler happy
        }
    }

    private static native String queryStringValue(long subKey, long name);

    static {
        // nio.dll has dependency on net.dll
        jdk.internal.loader.BootLoader.loadLibrary("net");
        jdk.internal.loader.BootLoader.loadLibrary("nio");
    }
}
