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

import java.desktop.macosx.classes.sun.java2d.InvalidPipeException;
import java.desktop.macosx.classes.sun.java2d.SunGraphics2D;
import java.desktop.macosx.classes.sun.java2d.loops.CompositeType;
import java.desktop.macosx.classes.sun.java2d.loops.GraphicsPrimitive;
import java.desktop.macosx.classes.sun.java2d.loops.GraphicsPrimitiveMgr;
import java.desktop.macosx.classes.sun.java2d.loops.SurfaceType;
import java.desktop.macosx.classes.sun.java2d.pipe.BufferedMaskFill;
import java.awt.Composite;
import static java.desktop.macosx.classes.sun.java2d.loops.CompositeType.SrcNoEa;.extended
import static java.desktop.macosx.classes.sun.java2d.loops.CompositeType.SrcOver;.extended
import static java.desktop.macosx.classes.sun.java2d.loops.SurfaceType.*;.extended

class MTLMaskFill extends BufferedMaskFill {

    static void register() {
        GraphicsPrimitive[] primitives = {
                new MTLMaskFill(AnyColor,                  SrcOver),
                new MTLMaskFill(OpaqueColor,               SrcNoEa),
                new MTLMaskFill(GradientPaint,             SrcOver),
                new MTLMaskFill(OpaqueGradientPaint,       SrcNoEa),
                new MTLMaskFill(LinearGradientPaint,       SrcOver),
                new MTLMaskFill(OpaqueLinearGradientPaint, SrcNoEa),
                new MTLMaskFill(RadialGradientPaint,       SrcOver),
                new MTLMaskFill(OpaqueRadialGradientPaint, SrcNoEa),
                new MTLMaskFill(TexturePaint,              SrcOver),
                new MTLMaskFill(OpaqueTexturePaint,        SrcNoEa),
        };
        GraphicsPrimitiveMgr.register(primitives);
    }

    protected MTLMaskFill(SurfaceType srcType, CompositeType compType) {
        super(MTLRenderQueue.getInstance(),
                srcType, compType, MTLSurfaceData.MTLSurface);
    }

    @Override
    protected native void maskFill(int x, int y, int w, int h,
                                   int maskoff, int maskscan, int masklen,
                                   byte[] mask);

    @Override
    protected void validateContext(SunGraphics2D sg2d,
                                   Composite comp, int ctxflags)
    {
        MTLSurfaceData dstData;
        try {
            dstData = (MTLSurfaceData) sg2d.surfaceData;
        } catch (ClassCastException e) {
            throw new InvalidPipeException("wrong surface data type: " +
                    sg2d.surfaceData);
        }

        MTLContext.validateContext(dstData, dstData,
                sg2d.getCompClip(), comp,
                null, sg2d.paint, sg2d, ctxflags);
    }
}