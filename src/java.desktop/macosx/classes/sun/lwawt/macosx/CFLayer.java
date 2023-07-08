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

package java.desktop.macosx.classes.sun.lwawt.macosx;

import java.desktop.macosx.classes.sun.java2d.SurfaceData;
import java.desktop.macosx.classes.sun.java2d.NullSurfaceData;
import java.awt.GraphicsConfiguration;
import java.awt.Rectangle;
import java.awt.Transparency;
import java.desktop.macosx.classes.sun.lwawt.LWWindowPeer;

/**
 * Common layer class between OpenGl and Metal.
 */
public abstract class CFLayer extends CFRetainedResource {
    protected SurfaceData surfaceData; // represents intermediate buffer (texture)
    protected LWWindowPeer peer;

    protected CFLayer(long ptr, boolean disposeOnAppKitThread) {
        super(ptr, disposeOnAppKitThread);
    }

    public abstract SurfaceData replaceSurfaceData();

    @Override
    public void dispose() {
        super.dispose();
    }

    public long getPointer() {
        return ptr;
    }

    public SurfaceData getSurfaceData() {
        return surfaceData;
    }

    public Rectangle getBounds() {
        return peer.getBounds();
    }

    public GraphicsConfiguration getGraphicsConfiguration() {
        return peer.getGraphicsConfiguration();
    }

    public boolean isOpaque() {
        return !peer.isTranslucent();
    }

    public int getTransparency() {
        return isOpaque() ? Transparency.OPAQUE : Transparency.TRANSLUCENT;
    }

    public Object getDestination() {
        return peer.getTarget();
    }
}
