/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.unix.classes.jdk.internal.loader;

import java.io.File;
import java.util.ArrayList;

/**
 * @since Pre Java 1
 * @author Logan Abernathy
 * @edited 20/4/2023 
 */
class ClassLoaderHelper {

    private ClassLoaderHelper() {}

    /**
     * Returns true if loading a native library only if
     * it's present on the file system.
     */
    static boolean loadLibraryOnlyIfPresent() {
        return true;
    }

    /**
     * Returns an alternate path name for the given file
     * such that if the original pathname did not exist, then the
     * file may be located at the alternate location.
     * For most platforms, this behavior is not supported and returns null.
     */
    static File mapAlternativeName(File lib) {
        return null;
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
