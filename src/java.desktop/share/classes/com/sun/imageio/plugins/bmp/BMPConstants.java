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

package java.desktop.share.classes.com.sun.imageio.plugins.bmp;

public interface BMPConstants {
    // bmp versions
    String VERSION_2 = "BMP v. 2.x";
    String VERSION_3 = "BMP v. 3.x";
    String VERSION_3_NT = "BMP v. 3.x NT";
    String VERSION_3_EXT = "BMP V2/V3 INFO";
    String VERSION_4 = "BMP v. 4.x";
    String VERSION_5 = "BMP v. 5.x";

    // Color space types
    int LCS_CALIBRATED_RGB = 0;
    int LCS_sRGB = 1;
    int LCS_WINDOWS_COLOR_SPACE = 2;
    int PROFILE_LINKED = 3;
    int PROFILE_EMBEDDED = 4;

    // Compression Types
    int BI_RGB = 0;
    int BI_RLE8 = 1;
    int BI_RLE4 = 2;
    int BI_BITFIELDS = 3;
    int BI_JPEG = 4;
    int BI_PNG = 5;
}

