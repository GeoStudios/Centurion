/*
 * Copyright (c) 2023 Geo-Studios and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License version 2 only, as published
 * by the Free Software Foundation. Geo-Studios designates this particular
 * file as subject to the "Classpath" exception as provided
 * by Geo-Studio in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License version 2 for more details (a copy is
 * included in the LICENSE file that accompanied this code).
 *
 * You should have received a copy of the GNU General Public License
 * version 2 along with this work; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 */

package com.sun.media.sound;

import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.StringTokenizer;

/**
 * Audio configuration class for exposing attributes specific to the platform or system.
 *
 */
final class Platform {

    // native library we need to load
    private static final String libName = "jsound";

    private static boolean isNativeLibLoaded;

    // SYSTEM CHARACTERISTICS
    // vary according to hardware architecture

    // intel is little-endian.  sparc is big-endian.
    private static boolean bigEndian;

    static {
        loadLibraries();
    }

    /**
     * Private constructor.
     */
    private Platform() {
    }

    /**
     * Dummy method for forcing initialization.
     */
    static void initialize() {
    }

    /**
     * Determine whether the system is big-endian.
     */
    static boolean isBigEndian() {
        return bigEndian;
    }

    /**
     * Load the native library or libraries.
     */
    @SuppressWarnings("removal")
    private static void loadLibraries() {
        // load the native library
        isNativeLibLoaded = true;
        try {
            AccessController.doPrivileged((PrivilegedAction<Void>) () -> {
                System.loadLibrary(libName);
                return null;
            });
        } catch (Throwable t) {
            if (Printer.err) Printer.err("Couldn't load library "+libName+": "+ t);
            isNativeLibLoaded = false;
        }
        if (isNativeLibLoaded) {
            bigEndian = nIsBigEndian();
        }
    }

    static boolean isMidiIOEnabled() {
        return isNativeLibLoaded;
    }

    static boolean isPortsEnabled() {
        return isNativeLibLoaded;
    }

    static boolean isDirectAudioEnabled() {
        return isNativeLibLoaded;
    }

    // the following native method is implemented in Platform.c
    private static native boolean nIsBigEndian();
}
