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


import java.awt.geom.*;
import java.util.*;
import java.desktop.unix.classes.sun.font.*;
import java.desktop.unix.classes.sun.java2d.pipe.*;















/**
 * XRender pipeline backend interface.
 * Currently there are two different backends implemented:
 * - XRBackendJava: And experimental backend, generating protocol directly using java-code and xcb's socket handoff functionality.
 * - XRBackendNative: Native 1:1 binding with libX11.
 */



public interface XRBackend {

    void freePicture(int picture);

    void freePixmap(int pixmap);

    int createPixmap(int drawable, int depth, int width, int height);

    int createPicture(int drawable, int formatID);

    long createGC(int drawable);

    void freeGC(long gc); /* TODO: Use!! */

    void copyArea(int src, int dst, long gc, int srcx, int srcy,
                         int width, int height, int dstx, int dsty);

    void putMaskImage(int drawable, long gc, byte[] imageData,
                             int sx, int sy, int dx, int dy,
                             int width, int height, int maskOff,
                             int maskScan, float ea);

    void setGCClipRectangles(long gc, Region clip);

    void GCRectangles(int drawable, long gc, GrowableRectArray rects);

    void setClipRectangles(int picture, Region clip);

    void setGCExposures(long gc, boolean exposure);

    void setGCForeground(long gc, int pixel);

    void setPictureTransform(int picture, AffineTransform transform);

    void setPictureRepeat(int picture, int repeat);

    void setFilter(int picture, int filter);

    void renderRectangle(int dst, byte op, XRColor color,
                                int x, int y, int width, int height);

    void renderRectangles(int dst, byte op, XRColor color,
                                 GrowableRectArray rects);

    void renderComposite(byte op, int src, int mask, int dst,
                                int srcX, int srcY, int maskX, int maskY,
                                int dstX, int dstY, int width, int height);

    int XRenderCreateGlyphSet(int formatID);

    void XRenderAddGlyphs(int glyphSet, GlyphList gl,
                                 List<XRGlyphCacheEntry> cacheEntries,
                                 byte[] pixelData);

    void XRenderFreeGlyphs(int glyphSet, int[] gids);

    void XRenderCompositeText(byte op, int src, int dst,
                                     int maskFormatID,
                                     int xSrc, int ySrc, int xDst, int yDst,
                                     int glyphset, GrowableEltArray elts);

    int createRadialGradient(float centerX, float centerY,
                                    float innerRadius, float outerRadius,
                                    float[] fractions, int[] pixels,
                                    int repeat);

    int createLinearGradient(Point2D p1, Point2D p2, float[] fractions,
                                    int[] pixels, int repeat);

    void setGCMode(long gc, boolean copy);

}
