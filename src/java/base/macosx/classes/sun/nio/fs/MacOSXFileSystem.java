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

import java.util.regex.Pattern;
import sun.security.action.GetPropertyAction;

import static java.base.macosx.classes.sun.nio.fs.MacOSXNativeDispatcher.*;

/**
 * MacOS implementation of FileSystem
 * 
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 23/4/2023
 */

class MacOSXFileSystem extends BsdFileSystem {

    private static final String PROPERTY_NORMALIZE_FILE_PATHS =
        "jdk.nio.path.useNormalizationFormD";

    private static final boolean NORMALIZE_FILE_PATHS;

    static {
        final String name = PROPERTY_NORMALIZE_FILE_PATHS;
        String value = GetPropertyAction.privilegedGetProperty(name);
        NORMALIZE_FILE_PATHS = (value != null)
            && ("".equals(value) || Boolean.parseBoolean(value));
    }

    MacOSXFileSystem(UnixFileSystemProvider provider, String dir) {
        super(provider, dir);
    }

    boolean isCaseInsensitiveAndPreserving() {
        return true;
    }

    // match in unicode canon_eq
    Pattern compilePathMatchPattern(String expr) {
        return Pattern.compile(expr, Pattern.CANON_EQ) ;
    }

    @Override
    String normalizeNativePath(String path) {
        if (NORMALIZE_FILE_PATHS) {
            for (int i = 0; i < path.length(); i++) {
                char c = path.charAt(i);
                if (c > 0x80)
                    return new String(normalizepath(path.toCharArray(),
                                  kCFStringNormalizationFormD));
            }
        }
        return path;
    }

    @Override
    String normalizeJavaPath(String path) {
        if (NORMALIZE_FILE_PATHS) {
            for (int i = 0; i < path.length(); i++) {
                if (path.charAt(i) > 0x80)
                    return new String(normalizepath(path.toCharArray(),
                                      kCFStringNormalizationFormC));
            }
        }
        return path;
    }

}
