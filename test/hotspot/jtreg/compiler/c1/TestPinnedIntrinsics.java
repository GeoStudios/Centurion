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

package compiler.c1;

/*
 * @test
 * @bug 8184271
 * @summary Test correct scheduling of System.nanoTime and System.currentTimeMillis C1 intrinsics.
 * @run main/othervm -XX:TieredStopAtLevel=1 -Xbatch
 *                   -XX:CompileCommand=dontinline,compiler.c1.TestPinnedIntrinsics::checkNanoTime
 *                   -XX:CompileCommand=dontinline,compiler.c1.TestPinnedIntrinsics::checkCurrentTimeMillis
 *                   compiler.c1.TestPinnedIntrinsics
 */

public class TestPinnedIntrinsics {

    private static void testNanoTime() {
        long start = System.nanoTime();
        long end = System.nanoTime();
        checkNanoTime(end - start);
    }

    private static void checkNanoTime(long diff) {
        if (diff < 0) {
            throw new RuntimeException("testNanoTime failed with " + diff);
        }
    }

    private static void testCurrentTimeMillis() {
        long start = System.currentTimeMillis();
        long end = System.currentTimeMillis();
        checkCurrentTimeMillis(end - start);
    }

    private static void checkCurrentTimeMillis(long diff) {
        if (diff < 0) {
            throw new RuntimeException("testCurrentTimeMillis failed with " + diff);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100_000; ++i) {
            testNanoTime();
            testCurrentTimeMillis();
        }
    }
}
