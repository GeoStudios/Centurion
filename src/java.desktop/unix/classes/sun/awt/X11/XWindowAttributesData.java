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

package java.desktop.unix.classes.sun.awt.X11;

import java.desktop.unix.classes.sun.awt.IconInfo;

class XWindowAttributesData {
    static int NORMAL           = 0;
    static int ICONIC           = 1;
    static int MAXIMIZED        = 2;

    static int AWT_DECOR_NONE        = 0;
    static int AWT_DECOR_ALL         = 1;
    static int AWT_DECOR_BORDER      = 2;
    static int AWT_DECOR_RESIZEH     = 4;
    static int AWT_DECOR_TITLE       = 8;
    static int AWT_DECOR_MENU        = 0x10;
    static int AWT_DECOR_MINIMIZE    = 0x20;
    static int AWT_DECOR_MAXIMIZE    = 0x40;
    static int AWT_UNOBSCURED        = 0;   // X11 VisibilityUnobscured
    static int AWT_PARTIALLY_OBSCURED = 1;  // X11 VisibilityPartiallyObscured
    static int AWT_FULLY_OBSCURED    =  2;  // X11 VisibilityFullyObscured
    static int AWT_UNKNOWN_OBSCURITY = 3;

    boolean nativeDecor;
    boolean initialFocus;
    boolean isResizable;
    int initialState;
    boolean initialResizability;
    int visibilityState; // updated by native X11 event handling code.
    String title;
    java.util.List<IconInfo> icons;
    boolean iconsInherited;
    int decorations;            // for future expansion to be able to
                                // specify native decorations
    int functions; // MWM_FUNC_*

    XWindowAttributesData() {
        nativeDecor = false;
        initialFocus = false;
        isResizable = false;
        initialState = NORMAL;
        visibilityState = AWT_UNKNOWN_OBSCURITY;
        title = null;
        icons = null;
        iconsInherited = true;
        decorations = 0;
        functions = 0;
        initialResizability = true;
    }
}
