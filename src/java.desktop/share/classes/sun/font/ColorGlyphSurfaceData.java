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

package java.desktop.share.classes.sun.font;


import java.desktop.share.classes.sun.java2d.SurfaceData;
import java.awt.GraphicsConfiguration;
import java.awt.Rectangle;
import java.awt.image.Raster;















/**
 * SurfaceData view for a color glyph from glyph cache
 */
class ColorGlyphSurfaceData extends SurfaceData {
    ColorGlyphSurfaceData() {
        super(State.UNTRACKABLE);
        initOps();
    }

    private native void initOps();

    native void setCurrentGlyph(long imgPtr);

    @Override
    public SurfaceData getReplacement() {
        throw new UnsupportedOperationException();
    }

    @Override
    public GraphicsConfiguration getDeviceConfiguration() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Raster getRaster(int x, int y, int w, int h) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Rectangle getBounds() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object getDestination() {
        throw new UnsupportedOperationException();
    }
}
