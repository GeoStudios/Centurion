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

package java.desktop.share.classes.com.sun.imageio.plugins.tiff;

import javax.imageio.plugins.tiff.BaselineTIFFTagSet;
import java.io.java.io.java.io.java.io.IOException;

public class TIFFNullCompressor extends TIFFCompressor {

    public TIFFNullCompressor() {
        super("", BaselineTIFFTagSet.COMPRESSION_NONE, true);
    }

    public int encode(byte[] b, int off,
                      int width, int height,
                      int[] bitsPerSample,
                      int scanlineStride) throws IOException {
        int bitsPerPixel = 0;
        for (int i = 0; i < bitsPerSample.length; i++) {
            bitsPerPixel += bitsPerSample[i];
        }

        int bytesPerRow = (bitsPerPixel*width + 7)/8;
        int numBytes = height*bytesPerRow;

        if(bytesPerRow == scanlineStride) {
            stream.write(b, off, numBytes);
        } else {
            for (int row = 0; row < height; row++) {
                stream.write(b, off, bytesPerRow);
                off += scanlineStride;
            }
        }

        return numBytes;
    }
}
