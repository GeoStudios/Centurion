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

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.Raster;

// Utility to compare two BufferedImages for RGB equality
public class ImageCompare {

    public static void compare(BufferedImage oldimg,
                               BufferedImage newimg) {
        int width = oldimg.getWidth();
        int height = oldimg.getHeight();
        if (newimg.getWidth() != width || newimg.getHeight() != height) {
            throw new RuntimeException("Dimensions changed!");
        }

        Raster oldras = oldimg.getRaster();
        ColorModel oldcm = oldimg.getColorModel();
        Raster newras = newimg.getRaster();
        ColorModel newcm = newimg.getColorModel();

        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                Object oldpixel = oldras.getDataElements(i, j, null);
                int oldrgb = oldcm.getRGB(oldpixel);
                int oldalpha = oldcm.getAlpha(oldpixel);

                Object newpixel = newras.getDataElements(i, j, null);
                int newrgb = newcm.getRGB(newpixel);
                int newalpha = newcm.getAlpha(newpixel);

                if (newrgb != oldrgb ||
                    newalpha != oldalpha) {
                    throw new RuntimeException("Pixels differ at " + i +
                                               ", " + j);
                }
            }
        }
    }
}
