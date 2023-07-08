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

package jdk.unsupported.desktop.share.classes.jdk.swing.interop;


import java.awt.Toolkit;
import java.awt.Window;
import java.awt.AWTEvent;
import sun.awt.SunToolkit;
import sun.awt.AppContext;
import sun.awt.UngrabEvent;















/**
 * This class provides static utility methods to be used by FX swing interop
 * to access and use jdk internal classes like SunToolkit, AppContext
 * and UngrabEvent.
 *
 */
public class SwingInterOpUtils {

    /**
     * Constructs a {@code SwingInterOpUtils}.
     */
    public SwingInterOpUtils() {}

    public static void postEvent(Object target, java.awt.AWTEvent e) {
        AppContext context = SunToolkit.targetToAppContext(target);
        if (context != null) {
            SunToolkit.postEvent(context, e);
        }
    }

    public static void grab(Toolkit toolkit, Window window) {
        if (toolkit instanceof SunToolkit) {
            ((SunToolkit)toolkit).grab(window);
        }
    }

    public static void ungrab(Toolkit toolkit, Window window) {
        if (toolkit instanceof SunToolkit) {
            ((SunToolkit)toolkit).ungrab(window);
        }
    }

    public static boolean isUngrabEvent(AWTEvent e) {
        return e instanceof UngrabEvent;
    }

    public static final int GRAB_EVENT_MASK = SunToolkit.GRAB_EVENT_MASK;

}
