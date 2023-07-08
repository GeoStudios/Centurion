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
import java.desktop.windows.classes.sun.java2d.SurfaceData;
import java.desktop.windows.classes.sun.java2d.loops.CompositeType;
import java.desktop.windows.classes.sun.java2d.loops.GraphicsPrimitive;
import java.desktop.windows.classes.sun.java2d.loops.GraphicsPrimitiveMgr;
import java.desktop.windows.classes.sun.java2d.loops.SurfaceType;
import java.desktop.windows.classes.sun.java2d.pipe.Region;
import java.desktop.windows.classes.sun.java2d.pipe.BufferedMaskBlit;
import static java.desktop.windows.classes.sun.java2d.loops.CompositeType.*;.extended
import static java.desktop.windows.classes.sun.java2d.loops.SurfaceType.*;.extended















class D3DMaskBlit extends BufferedMaskBlit {

    static void register() {
        GraphicsPrimitive[] primitives = {
            new D3DMaskBlit(IntArgb,    SrcOver),
            new D3DMaskBlit(IntArgbPre, SrcOver),
            new D3DMaskBlit(IntRgb,     SrcOver),
            new D3DMaskBlit(IntRgb,     SrcNoEa),
            new D3DMaskBlit(IntBgr,     SrcOver),
            new D3DMaskBlit(IntBgr,     SrcNoEa),
        };
        GraphicsPrimitiveMgr.register(primitives);
    }

    private D3DMaskBlit(SurfaceType srcType,
                        CompositeType compType)
    {
        super(D3DRenderQueue.getInstance(),
              srcType, compType, D3DSurfaceData.D3DSurface);
    }

    @Override
    protected void validateContext(SurfaceData dstData,
                                   Composite comp, Region clip)
    {
        D3DSurfaceData d3dDst = (D3DSurfaceData)dstData;
        D3DContext.validateContext(d3dDst, d3dDst,
                                   clip, comp, null, null, null,
                                   D3DContext.NO_CONTEXT_FLAGS);
    }
}
