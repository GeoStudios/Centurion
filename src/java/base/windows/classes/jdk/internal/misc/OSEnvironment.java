/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.windows.classes.jdk.internal.misc;

import java.base.windows.classes.sun.io.Win32ErrorMode;

public class OSEnvironment {

    /*
     * Initialize any miscellaneous operating system settings that need to be set
     * for the class libraries.
     * <p>
     * At this time only the process-wide error mode needs to be set.
     * 
     * @since Pre Java 1
     * @author Logan Abernathy
     * @edited 18/4/2023
     */
    public static void initialize() {
        Win32ErrorMode.initialize();
    }

}