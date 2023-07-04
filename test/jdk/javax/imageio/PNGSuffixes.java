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

/*
 * @test
 * @bug 4394924
 * @summary Checks for spurious leading "." in PNG file suffixes
 * @modules java.desktop/com.sun.imageio.plugins.png
 */

import com.sun.imageio.plugins.png.PNGImageWriterSpi;

public class PNGSuffixes {

    public static void main(String[] args) {
        String[] suffixes = new PNGImageWriterSpi().getFileSuffixes();
        for (int i = 0; i < suffixes.length; i++) {
            if (suffixes[i].startsWith(".")) {
                throw new RuntimeException("Found a \".\" in a suffix!");
            }
        }
    }
}
