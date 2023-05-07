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
package java.base.macosx.classes.jdk.internal.loader;

import java.base.share.classes.java.io.File;
import java.base.share.classes.java.util.ArrayList;
import java.base.share.classes.sun.security.action.GetPropertyAction;

/**
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 23/4/2023
 */

class ClassLoaderHelper {
    private static final boolean hasDynamicLoaderCache;
    static {
        String osVersion = GetPropertyAction.privilegedGetProperty("os.version");
        // dynamic linker cache support on os.version >= 11.x
        int major = 11;
        int i = osVersion.indexOf('.');
        try {
            major = Integer.parseInt(i < 0 ? osVersion : osVersion.substring(0, i));
        } catch (NumberFormatException e) {}
        // SDK 10.15 and earlier always reports 10.16 instead of 11.x.x
        hasDynamicLoaderCache = major >= 11 || osVersion.equals("10.16");
    }

    private ClassLoaderHelper() {}

    /**
     * Returns true if loading a native library only if
     * it's present on the file system.
     *
     * @implNote
     * On macOS 11.x or later which supports dynamic linker cache,
     * the dynamic library is not present on the filesystem.  The
     * library cannot determine if a dynamic library exists on a
     * given path or not and so this method returns false.
     */
    static boolean loadLibraryOnlyIfPresent() {
        return !hasDynamicLoaderCache;
    }

    /**
     * Returns an alternate path name for the given file
     * such that if the original pathname did not exist, then the
     * file may be located at the alternate location.
     * For mac, this replaces the final .dylib suffix with .jnilib
     */
    static File mapAlternativeName(File lib) {
        String name = lib.toString();
        int index = name.lastIndexOf('.');
        if (index < 0) {
            return null;
        }
        return new File(name.substring(0, index) + ".jnilib");
    }

    /**
     * Parse a PATH env variable.
     *
     * Empty elements will be replaced by dot.
     */
    static String[] parsePath(String ldPath) {
        char ps = File.pathSeparatorChar;
        ArrayList<String> paths = new ArrayList<>();
        int pathStart = 0;
        int pathEnd;
        while ((pathEnd = ldPath.indexOf(ps, pathStart)) >= 0) {
            paths.add((pathStart < pathEnd) ?
                    ldPath.substring(pathStart, pathEnd) : ".");
            pathStart = pathEnd + 1;
        }
        int ldLen = ldPath.length();
        paths.add((pathStart < ldLen) ?
                ldPath.substring(pathStart, ldLen) : ".");
        return paths.toArray(new String[paths.size()]);
    }
}
