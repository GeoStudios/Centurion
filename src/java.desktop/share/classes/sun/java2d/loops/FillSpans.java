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

package java.desktop.share.classes.sun.java2d.loops;

import java.desktop.share.classes.sun.java2d.SunGraphics2D;
import java.desktop.share.classes.sun.java2d.SurfaceData;
import java.desktop.share.classes.sun.java2d.pipe.SpanIterator;

/*
 */

/**
 * FillSpans
 * 1) draw solid color onto destination surface
 * 2) rectangular areas to fill come from SpanIterator
 */
public class FillSpans extends GraphicsPrimitive
{
    public static final String methodSignature = "FillSpans(...)";

    public static final int primTypeID = makePrimTypeID();

    public static FillSpans locate(SurfaceType srctype,
                                   CompositeType comptype,
                                   SurfaceType dsttype)
    {
        return (FillSpans)
            GraphicsPrimitiveMgr.locate(primTypeID,
                                        srctype, comptype, dsttype);
    }

    protected FillSpans(SurfaceType srctype,
                        CompositeType comptype,
                        SurfaceType dsttype)
    {
        super(methodSignature, primTypeID, srctype, comptype, dsttype);
    }

    public FillSpans(long pNativePrim,
                     SurfaceType srctype,
                     CompositeType comptype,
                     SurfaceType dsttype)
    {
        super(pNativePrim, methodSignature, primTypeID, srctype, comptype, dsttype);
    }

    private native void FillSpans(SunGraphics2D sg2d, SurfaceData dest,
                                  int pixel, long pIterator, SpanIterator si);

    /**
     * All FillSpan implementors must have this invoker method
     */
    public void FillSpans(SunGraphics2D sg2d, SurfaceData dest,
                          SpanIterator si)
    {
        FillSpans(sg2d, dest, sg2d.pixel, si.getNativeIterator(), si);
    }

    public GraphicsPrimitive traceWrap() {
        return new TraceFillSpans(this);
    }

    private static class TraceFillSpans extends FillSpans {
        FillSpans target;

        public TraceFillSpans(FillSpans target) {
            super(target.getSourceType(),
                  target.getCompositeType(),
                  target.getDestType());
            this.target = target;
        }

        public GraphicsPrimitive traceWrap() {
            return this;
        }

        public void FillSpans(SunGraphics2D sg2d, SurfaceData dest,
                              SpanIterator si)
        {
            tracePrimitive(target);
            target.FillSpans(sg2d, dest, si);
        }
    }
}
