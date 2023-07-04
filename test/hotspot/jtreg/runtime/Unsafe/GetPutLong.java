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
 * @summary Verify behaviour of Unsafe.get/putLong
 * @library /test/lib
 * @modules java.base/jdk.internal.misc
 *          java.management
 * @run main GetPutLong
 */

import java.lang.reflect.Field;
import jdk.internal.misc.Unsafe;
import static jdk.test.lib.Asserts.*;

public class GetPutLong {
    public static void main(String args[]) throws Exception {
        Unsafe unsafe = Unsafe.getUnsafe();
        Test t = new Test();
        Field field = Test.class.getField("l");

        long offset = unsafe.objectFieldOffset(field);
        assertEquals(-1L, unsafe.getLong(t, offset));
        unsafe.putLong(t, offset, 0L);
        assertEquals(0L, unsafe.getLong(t, offset));

        long address = unsafe.allocateMemory(8);
        unsafe.putLong(address, 1L);
        assertEquals(1L, unsafe.getLong(address));
        unsafe.freeMemory(address);

        long arrayLong[] = { -1, 0, 1, 2 };
        int scale = unsafe.arrayIndexScale(arrayLong.getClass());
        offset = unsafe.arrayBaseOffset(arrayLong.getClass());
        for (int i = 0; i < arrayLong.length; i++) {
            assertEquals(unsafe.getLong(arrayLong, offset), arrayLong[i]);
            offset += scale;
        }
    }

    static class Test {
        public long l = -1L;
    }
}
