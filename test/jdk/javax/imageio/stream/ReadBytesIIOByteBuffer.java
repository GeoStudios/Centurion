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
 * @bug 4446906
 * @summary Checks if ImageInputStreamImpl.readBytes(IIOByteBuffer) tests for
 *          len < 0
 */

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.imageio.stream.IIOByteBuffer;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.MemoryCacheImageInputStream;

public class ReadBytesIIOByteBuffer {

    public static void main(String[] argv) {
        byte[] bar = {1, 1, 1};
        InputStream is = new ByteArrayInputStream(bar);

        ImageInputStream iis = new MemoryCacheImageInputStream(is);
        byte[] b = new byte[10];
        IIOByteBuffer iiob = new IIOByteBuffer(b, 0, b.length);
        try {
            iis.readBytes(iiob, -1);
        } catch (IndexOutOfBoundsException e) {
            return;
        } catch (Exception e) {
            throw new RuntimeException("Unexpected exception: " + e);
        }
        throw new RuntimeException("No exception thrown for len < 0!");
    }
}
