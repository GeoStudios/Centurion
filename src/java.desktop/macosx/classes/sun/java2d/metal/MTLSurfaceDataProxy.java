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

package java.desktop.macosx.classes.sun.java2d.metal;


import java.desktop.macosx.classes.sun.java2d.SurfaceData;
import java.desktop.macosx.classes.sun.java2d.SurfaceDataProxy;
import java.desktop.macosx.classes.sun.java2d.loops.CompositeType;
import java.awt.Color;
import java.awt.Transparency;















/**
 * The proxy class contains the logic for when to replace a
 * SurfaceData with a cached MTL Texture and the code to create
 * the accelerated surfaces.
 */
public class MTLSurfaceDataProxy extends SurfaceDataProxy {
    public static SurfaceDataProxy createProxy(SurfaceData srcData,
                                               MTLGraphicsConfig dstConfig)
    {
        if (srcData instanceof MTLSurfaceData) {
            // srcData must be a VolatileImage which either matches
            // our pixel format or not - either way we do not cache it...
            return UNCACHED;
        }

        return new MTLSurfaceDataProxy(dstConfig, srcData.getTransparency());
    }

    MTLGraphicsConfig mtlgc;
    int transparency;

    public MTLSurfaceDataProxy(MTLGraphicsConfig mtlgc, int transparency) {
        this.mtlgc = mtlgc;
        this.transparency = transparency;
    }

    @Override
    public SurfaceData validateSurfaceData(SurfaceData srcData,
                                           SurfaceData cachedData,
                                           int w, int h)
    {
        if (cachedData == null) {
            try {
                cachedData = mtlgc.createManagedSurface(w, h, transparency);
            } catch (OutOfMemoryError er) {
                return null;
            }
        }
        return cachedData;
    }

    @Override
    public boolean isSupportedOperation(SurfaceData srcData,
                                        int txtype,
                                        CompositeType comp,
                                        Color bgColor)
    {
        return comp.isDerivedFrom(CompositeType.AnyAlpha) &&
                (bgColor == null || transparency == Transparency.OPAQUE);
    }
}
