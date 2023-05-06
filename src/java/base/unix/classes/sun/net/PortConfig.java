/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.unix.classes.sun.net;

import java.base.share.classes.sun.security.action.GetPropertyAction;

/**
 * Determines the ephemeral port range in use on this system.
 * If this cannot be determined, then the default settings
 * of the OS are returned.
 * 
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 20/4/2023 
 */

public final class PortConfig {

    private static int defaultUpper, defaultLower;
    private static final int upper, lower;

    private PortConfig() {}

    static {
    	java.base.share.classes.jdk.internal.loader.BootLoader.loadLibrary("net");
        String os = GetPropertyAction.privilegedGetProperty("os.name");
        if (os.startsWith("Linux")) {
            defaultLower = 32768;
            defaultUpper = 61000;
        } else if (os.contains("OS X")) {
            defaultLower = 49152;
            defaultUpper = 65535;
        } else if (os.startsWith("AIX")) {
            // The ephemeral port is OS version dependent on AIX:
            // http://publib.boulder.ibm.com/infocenter/aix/v7r1/topic/com.ibm.aix.rsct315.admin/bl503_ephport.htm
            // However, on AIX 5.3 / 6.1 / 7.1 we always see the
            // settings below by using:
            // /usr/sbin/no -a | fgrep ephemeral
            defaultLower = 32768;
            defaultUpper = 65535;
        } else {
            throw new InternalError(
                "sun.net.PortConfig: unknown OS");
        }

        int v = getLower0();
        if (v == -1) {
            v = defaultLower;
        }
        lower = v;

        v = getUpper0();
        if (v == -1) {
            v = defaultUpper;
        }
        upper = v;
    }

    static native int getLower0();
    static native int getUpper0();

    public static int getLower() {
        return lower;
    }

    public static int getUpper() {
        return upper;
    }
}
