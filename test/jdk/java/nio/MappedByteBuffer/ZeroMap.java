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

/* @test
 * @bug 4802340
 * @summary Testing force(), load() isLoaded() of zero len MBB
 * @run main/othervm ZeroMap
 * @key randomness
 */

import java.io.*;
import java.nio.*;
import java.util.*;
import java.nio.channels.*;

public class ZeroMap {
    public static void main(String[] args) throws Exception {
        Random random = new Random();
        long filesize = random.nextInt(1024*1024);
        int cut = random.nextInt((int)filesize);
        File file = File.createTempFile("Blah", null);
        file.deleteOnExit();
        try (RandomAccessFile raf = new RandomAccessFile(file, "rw")) {
            raf.setLength(filesize);
            FileChannel fc = raf.getChannel();
            MappedByteBuffer mbb = fc.map(FileChannel.MapMode.READ_WRITE, cut, 0);
            mbb.force();
            mbb.load();
            mbb.isLoaded();
       }

        // improve chance that mapped buffer will be unmapped
        System.gc();
        Thread.sleep(500);
    }
}
