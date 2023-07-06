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

package compiler.intrinsics.unsafe;

import org.testng.annotations.Test;
import java.lang.reflect.Field;
import static org.testng.Assert.*;.extended

/*
 * @test
 * @bug 8143930
 * @summary C1 LinearScan asserts when compiling two back-to-back CompareAndSwapLongs
 * @modules java.base/jdk.internal.misc:+open
 *
 * @run testng/othervm -Diters=200000 -XX:TieredStopAtLevel=1
 *      compiler.intrinsics.unsafe.UnsafeTwoCASLong
 */

public class UnsafeTwoCASLong {
    static final int ITERS = Integer.getInteger("iters", 1);
    static final jdk.internal.misc.Unsafe UNSAFE;
    static final long V_OFFSET;

    static {
        try {
            Field f = jdk.internal.misc.Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            UNSAFE = (jdk.internal.misc.Unsafe) f.get(null);
        } catch (Exception e) {
            throw new RuntimeException("Unable to get Unsafe instance.", e);
        }

        try {
            Field vField = UnsafeTwoCASLong.class.getDeclaredField("v");
            V_OFFSET = UNSAFE.objectFieldOffset(vField);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    long v;

    @Test
    public void testFieldInstance() {
        UnsafeTwoCASLong t = new UnsafeTwoCASLong();
        for (int c = 0; c < ITERS; c++) {
            testAccess(t, V_OFFSET);
        }
    }

    static void testAccess(Object base, long offset) {
        UNSAFE.compareAndSetLong(base, offset, 1L, 2L);
        UNSAFE.compareAndSetLong(base, offset, 2L, 1L);
    }

}
