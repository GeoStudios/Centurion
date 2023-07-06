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

package java.desktop.share.classes.sun.java2d.opengl;


import java.awt.Composite;
import java.desktop.share.classes.sun.font.Glyphjava.util.java.util.java.util.List;
import java.desktop.share.classes.sun.java2d.SunGraphics2D;
import java.desktop.share.classes.sun.java2d.loops.GraphicsPrimitive;
import java.desktop.share.classes.sun.java2d.pipe.BufferedTextPipe;
import java.desktop.share.classes.sun.java2d.pipe.RenderQueue;















class OGLTextRenderer extends BufferedTextPipe {

    OGLTextRenderer(RenderQueue rq) {
        super(rq);
    }

    @Override
    protected native void drawGlyphList(int numGlyphs, boolean usePositions,
                                        boolean subPixPos, boolean rgbOrder,
                                        int lcdContrast,
                                        float glOrigX, float glOrigY,
                                        long[] images, float[] positions);

    @Override
    protected void validateContext(SunGraphics2D sg2d, Composite comp) {
        // assert rq.lock.isHeldByCurrentThread();
        OGLSurfaceData oglDst = (OGLSurfaceData)sg2d.surfaceData;
        OGLContext.validateContext(oglDst, oglDst,
                                   sg2d.getCompClip(), comp,
                                   null, sg2d.paint, sg2d,
                                   OGLContext.NO_CONTEXT_FLAGS);
    }

    OGLTextRenderer traceWrap() {
        return new Tracer(this);
    }

    private static class Tracer extends OGLTextRenderer {
        Tracer(OGLTextRenderer ogltr) {
            super(ogltr.rq);
        }
        protected void drawGlyphList(SunGraphics2D sg2d, GlyphList gl) {
            GraphicsPrimitive.tracePrimitive("OGLDrawGlyphs");
            super.drawGlyphList(sg2d, gl);
        }
    }
}
