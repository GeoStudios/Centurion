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

package java.desktop.unix.classes.sun.java2d.x11;


import java.awt.image.*;
import java.desktop.unix.classes.sun.awt.*;
import java.desktop.unix.classes.sun.java2d.*;
import java.desktop.unix.classes.sun.java2d.loops.*;
import java.desktop.unix.classes.sun.java2d.pipe.*;















public abstract class XSurfaceData extends SurfaceData {
    static boolean isX11SurfaceDataInitialized = false;

    public static boolean isX11SurfaceDataInitialized() {
        return isX11SurfaceDataInitialized;
    }

    public static void setX11SurfaceDataInitialized() {
        isX11SurfaceDataInitialized = true;
    }

    public XSurfaceData(SurfaceType surfaceType, ColorModel cm) {
        super(surfaceType, cm);
    }

    protected native void initOps(X11ComponentPeer peer, X11GraphicsConfig gc, int depth);

    protected static native long XCreateGC(long pXSData);

    protected static native void XResetClip(long xgc);

    protected static native void XSetClip(long xgc, int lox, int loy, int hix, int hiy, Region complexclip);

    protected native void flushNativeSurface();

    protected native boolean isDrawableValid();

    protected native void setInvalid();

    protected static native void XSetGraphicsExposures(long xgc, boolean needExposures);
}
