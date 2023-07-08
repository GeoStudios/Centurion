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

package java.desktop.macosx.classes.sun.awt;


import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.security.AccessController;
import java.security.PrivilegedAction;















@SuppressWarnings("removal")
public class PlatformGraphicsInfo {

    static {
        AccessController.doPrivileged((PrivilegedAction<Void>) () -> {
            System.loadLibrary("awt");
            return null;
        });
    }

    public static GraphicsEnvironment createGE() {
        return new CGraphicsEnvironment();
    }

    public static Toolkit createToolkit() {
        return new sun.lwawt.macosx.LWCToolkit();
    }

    /**
     * Returns true if the WindowServer is available, false otherwise.
     *
     * @return true if the WindowServer is available, false otherwise
     */
    public static native boolean isInAquaSession();

    public static boolean getDefaultHeadlessProperty() {
         return !isInAquaSession();
    }

    /*
     * Called from java.awt.GraphicsEnvironment when
     * getDefaultHeadlessProperty() has returned true, and
     * the application has called an API that requires headful.
     */
    public static String getDefaultHeadlessMessage() {
        return
            "\nThe application is not running in a desktop session,\n" +
            "but this program performed an operation which requires it.";
    }

}
