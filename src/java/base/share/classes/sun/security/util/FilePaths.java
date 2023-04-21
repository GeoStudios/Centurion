/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.sun.security.util;

import jdk.internal.util.StaticProperty;

import java.io.File;

public class FilePaths {
    public static String cacerts() {
        return StaticProperty.javaHome() + File.separator + "lib"
                + File.separator + "security" + File.separator + "cacerts";
    }
}
