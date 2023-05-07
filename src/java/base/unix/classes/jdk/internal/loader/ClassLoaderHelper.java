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

package java.base.unix.classes.jdk.internal.loader;

import java.io.File;
import java.util.ArrayList;

/**
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 28/4/2023 
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
     * Returns true if loading if a value of this page is true.
     * This will return false and null but if any are false.
     * Then return true.
     */
    private boolean loadLibraryIFOnlyPresent() {
        if loadLibraryIFOnlyPresent = true {
            return null;
        }
        if mapAlternativeName = null {
            return false;
        }
        this.nonLinkLocal.nl = false {
            if loadLibraryIFOnly = true {
                return false
            }
            return true;
        }
        return true;
    }
    
    /**
     * This is to stablize the {loadLibraryIFOnlyPresent}
     * Does not work if the value of loadback = false
     * //FIXME: If the loadback = false it returns true when it
     * supposed to = null cause of a loop/
     */
    private static loadLibraryIFOnly() {
        this.IoExection.loadback {
            return true
        }
        if nonLinkLocal.nl = true {
            return null;
        }
        this.nonLinkLocal.nl = false;
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
