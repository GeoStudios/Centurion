/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.windows.classes.sun.security.provider;

/**
 * Native PRNG implementation for Windows. Currently a dummy, we do
 * not support a fully native PRNG on Windows.
 *
 * @since Alpha cdk-1.0
 * @author Logan Abernathy
 * @edited 19/4/2023
 */
public final class NativePRNG {

    // return whether the NativePRNG is available
    static boolean isAvailable() {
        return false;
    }

    public static final class NonBlocking {
        static boolean isAvailable() {
            return false;
        }
    }

    public static final class Blocking {
        static boolean isAvailable() {
            return false;
        }
    }
}