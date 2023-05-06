/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.unix.classes.jdk.internal.misc;

/**
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 20/4/2023 
 */

public class OSEnvironment {

    /**
     * Initialize any miscellaneous operating system settings that need to be set
     * for the class libraries.
     * 
     */
    public static void initialize() {
        // no-op on Solaris and Linux
    }
}
