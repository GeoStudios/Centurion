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

package java.desktop.share.classes.sun.java2d.cmm;


import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;















public interface ColorTransform {
    int Any = -1;/* any rendering type, whichever is
                           available */
                        /* search order is icPerceptual,
                           icRelativeColorimetric, icSaturation */

    /* Transform types */
    int In = 1;
    int Out = 2;
    int Gamut = 3;
    int Simulation = 4;

    int getNumInComponents();
    int getNumOutComponents();
    void colorConvert(BufferedImage src, BufferedImage dst);
    void colorConvert(Raster src, WritableRaster dst,
                             float[] srcMinVal, float[]srcMaxVal,
                             float[] dstMinVal, float[]dstMaxVal);
    void colorConvert(Raster src, WritableRaster dst);
    short[] colorConvert(short[] src, short[] dest);
    byte[] colorConvert (byte[] src, byte[] dest);
}
