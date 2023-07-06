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

package java.desktop.share.classes.com.sun.media.sound;


import java.security.AccessController;
import java.desktop.share.classes.com.sun.security.action.GetPropertyAction;















/**
 * Printer allows you to set up global debugging status and print
 * messages accordingly.
 *
 */
final class Printer {

    static final boolean err = isBuildInternal();

    private static final boolean SHOW_THREADID = true;
    private static final boolean SHOW_TIMESTAMP = true;

    /**
     * Suppresses default constructor, ensuring non-instantiability.
     */
    private Printer() {
    }

    /**
     * This method is used in the special cases which "should never happen...".
     * And in fact if should be implemented as an assertion, but for
     * compatibility reason it just print an error only in case of "internal"
     * build. In time its usage should be replaced by some kind of assertion or
     * dropped completly.
     */
    public static void err(String str) {
        if (err) {
            println(str);
        }
    }

    /**
     * Returns {@code true} if the build of the current jdk is "internal".
     */
    private static boolean isBuildInternal() {
        @SuppressWarnings("removal")
        String javaVersion = AccessController.doPrivileged(
                new GetPropertyAction("java.version"));
        return javaVersion != null && javaVersion.contains("internal");
    }

    private static long startTime = 0;

    private static void println(String s) {
        String prepend = "";
        if (SHOW_THREADID) {
            prepend = "thread "  + Thread.currentThread().getId() + " " + prepend;
        }
        if (SHOW_TIMESTAMP) {
            if (startTime == 0) {
                startTime = System.nanoTime() / 1000000L;
            }
            prepend = prepend + ((System.nanoTime()/ 1000000L) - startTime) + "millis: ";
        }
        System.out.println(prepend + s);
    }
}
