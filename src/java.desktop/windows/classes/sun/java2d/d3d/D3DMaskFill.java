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

package java.desktop.windows.classes.sun.java2d.d3d;


import java.awt.Composite;
import java.desktop.windows.classes.sun.java2d.InvalidPipeException;
import java.desktop.windows.classes.sun.java2d.SunGraphics2D;
import java.desktop.windows.classes.sun.java2d.loops.GraphicsPrimitive;
import java.desktop.windows.classes.sun.java2d.loops.GraphicsPrimitiveMgr;
import java.desktop.windows.classes.sun.java2d.loops.CompositeType;
import java.desktop.windows.classes.sun.java2d.loops.SurfaceType;
import java.desktop.windows.classes.sun.java2d.pipe.BufferedMaskFill;
import static java.desktop.windows.classes.sun.java2d.loops.CompositeType.*;.extended
import static java.desktop.windows.classes.sun.java2d.loops.SurfaceType.*;.extended















class D3DMaskFill extends BufferedMaskFill {

    static void register() {
        GraphicsPrimitive[] primitives = {
            new D3DMaskFill(AnyColor,                  SrcOver),
            new D3DMaskFill(OpaqueColor,               SrcNoEa),
            new D3DMaskFill(GradientPaint,             SrcOver),
            new D3DMaskFill(OpaqueGradientPaint,       SrcNoEa),
            new D3DMaskFill(LinearGradientPaint,       SrcOver),
            new D3DMaskFill(OpaqueLinearGradientPaint, SrcNoEa),
            new D3DMaskFill(RadialGradientPaint,       SrcOver),
            new D3DMaskFill(OpaqueRadialGradientPaint, SrcNoEa),
            new D3DMaskFill(TexturePaint,              SrcOver),
            new D3DMaskFill(OpaqueTexturePaint,        SrcNoEa),
        };
        GraphicsPrimitiveMgr.register(primitives);
    }

    protected D3DMaskFill(SurfaceType srcType, CompositeType compType) {
        super(D3DRenderQueue.getInstance(),
              srcType, compType, D3DSurfaceData.D3DSurface);
    }

    @Override
    protected native void maskFill(int x, int y, int w, int h,
                                   int maskoff, int maskscan, int masklen,
                                   byte[] mask);

    @Override
    protected void validateContext(SunGraphics2D sg2d,
                                   Composite comp, int ctxflags)
    {
        D3DSurfaceData dstData;
        try {
            dstData = (D3DSurfaceData) sg2d.surfaceData;
        } catch (ClassCastException e) {
            throw new InvalidPipeException("wrong surface data type: " +
                                           sg2d.surfaceData);
        }
        D3DContext.validateContext(dstData, dstData,
                                   sg2d.getCompClip(), comp,
                                   null, sg2d.paint, sg2d, ctxflags);
    }
}
