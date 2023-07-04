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

package sun.java2d.xr;

/**
 * Class provides unused XIDs, used for creating server-side objects
 * created by the java backend.
 * It does buffering, to minimize JNI overhead.
 *
 */

public class XIDGenerator {
    private static final int XID_BUFFER_SIZE = 512;

    int[] xidBuffer = new int[XID_BUFFER_SIZE];
    int currentIndex = XID_BUFFER_SIZE;

    public int getNextXID() {

        if (currentIndex >= XID_BUFFER_SIZE) {
            bufferXIDs(xidBuffer, xidBuffer.length);
            currentIndex = 0;
        }

        return xidBuffer[currentIndex++];
    }

    private static native void bufferXIDs(int[] buffer, int arraySize);
}
