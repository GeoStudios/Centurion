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
import java.desktop.macosx.classes.sun.java2d.loops.CompositeType;
import java.desktop.macosx.classes.sun.java2d.loops.GraphicsPrimitive;
import java.desktop.macosx.classes.sun.java2d.loops.GraphicsPrimitiveMgr;
import java.desktop.macosx.classes.sun.java2d.loops.SurfaceType;
import java.desktop.macosx.classes.sun.java2d.pipe.BufferedMaskBlit;
import java.desktop.macosx.classes.sun.java2d.pipe.Region;
import java.awt.Composite;
import static java.desktop.macosx.classes.sun.java2d.loops.CompositeType.SrcNoEa;.extended
import static java.desktop.macosx.classes.sun.java2d.loops.CompositeType.SrcOver;.extended
import static java.desktop.macosx.classes.sun.java2d.loops.SurfaceType.*;.extended















class MTLMaskBlit extends BufferedMaskBlit {

    static void register() {
        GraphicsPrimitive[] primitives = {
                new MTLMaskBlit(IntArgb,    SrcOver),
                new MTLMaskBlit(IntArgbPre, SrcOver),
                new MTLMaskBlit(IntRgb,     SrcOver),
                new MTLMaskBlit(IntRgb,     SrcNoEa),
                new MTLMaskBlit(IntBgr,     SrcOver),
                new MTLMaskBlit(IntBgr,     SrcNoEa),
        };
        GraphicsPrimitiveMgr.register(primitives);
    }

    private MTLMaskBlit(SurfaceType srcType,
                        CompositeType compType)
    {
        super(MTLRenderQueue.getInstance(),
                srcType, compType, MTLSurfaceData.MTLSurface);
    }

    @Override
    protected void validateContext(SurfaceData dstData,
                                   Composite comp, Region clip)
    {
        MTLSurfaceData mtlDst = (MTLSurfaceData)dstData;
        MTLContext.validateContext(mtlDst, mtlDst,
                clip, comp, null, null, null,
                MTLContext.NO_CONTEXT_FLAGS);
    }
}
