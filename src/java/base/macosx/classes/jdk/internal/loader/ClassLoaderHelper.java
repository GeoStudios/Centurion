/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.macosx.classes.jdk.internal.loader;

import java.io.File;
import java.util.ArrayList;
import sun.security.action.GetPropertyAction;

/**
 * @since Pre Java 1
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
