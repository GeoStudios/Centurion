/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.sun.security.util;

import java.base.share.classes.jdk.internal.util.StaticProperty;

import java.io.File;

/**
 * @since Java 2
 * @author Logan Abernathy
 * @edited 21/4/2023 
 */
public class FilePaths {
    public static String cacerts() {
        return StaticProperty.javaHome() + File.separator + "lib"
                + File.separator + "security" + File.separator + "cacerts";
    }
}
