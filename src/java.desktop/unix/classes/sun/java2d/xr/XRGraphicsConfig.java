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

package java.desktop.unix.classes.sun.java2d.xr;

import java.awt.Transparency;
import java.desktop.unix.classes.sun.awt.X11GraphicsConfig;
import java.desktop.unix.classes.sun.awt.X11ComponentPeer;
import java.desktop.unix.classes.sun.awt.X11GraphicsDevice;
import java.desktop.unix.classes.sun.awt.X11GraphicsEnvironment;
import java.desktop.unix.classes.sun.awt.image.SurfaceManager;
import java.desktop.unix.classes.sun.java2d.SurfaceData;
import java.desktop.unix.classes.sun.java2d.loops.SurfaceType;

public class XRGraphicsConfig extends X11GraphicsConfig implements
        SurfaceManager.ProxiedGraphicsConfig {
    private XRGraphicsConfig(X11GraphicsDevice device, int visualnum,
            int depth, int colormap, boolean doubleBuffer) {
        super(device, visualnum, depth, colormap, doubleBuffer);
    }

    public SurfaceData createSurfaceData(X11ComponentPeer peer) {
        return XRSurfaceData.createData(peer);
    }

    public static XRGraphicsConfig getConfig(X11GraphicsDevice device,
            int visualnum, int depth, int colormap, boolean doubleBuffer) {
        if (!X11GraphicsEnvironment.isXRenderAvailable()) {
            return null;
        }

        return new XRGraphicsConfig(device, visualnum, depth, colormap,
                doubleBuffer);
    }

    public Object getProxyKey() {
        return this;
    }

    public synchronized SurfaceType getSurfaceType() {
        if (surfaceType != null) {
            return surfaceType;
        }

        surfaceType = XRSurfaceData.getSurfaceType(this, Transparency.OPAQUE);
        return surfaceType;
    }

}