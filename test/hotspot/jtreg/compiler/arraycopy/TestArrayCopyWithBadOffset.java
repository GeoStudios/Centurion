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

package compiler.arraycopy;
















/*
 * @test
 * @bug 8224539
 * @summary Test arraycopy optimizations with bad src/dst array offsets.
 * @run main/othervm -XX:+IgnoreUnrecognizedVMOptions -Xbatch -XX:+AlwaysIncrementalInline
 *                   compiler.arraycopy.TestArrayCopyWithBadOffset
 */


public class TestArrayCopyWithBadOffset {

    public static byte[] getSrc() {
        return new byte[5];
    }

    // Test bad src offset
    public static void test1(byte[] dst) {
        byte[] src = getSrc();
        try {
            System.arraycopy(src, Integer.MAX_VALUE-1, dst, 0, src.length);
        } catch (Exception e) {
            // Expected
        }
    }

    public static byte[] getDst() {
        return new byte[5];
    }

    // Test bad dst offset
    public static void test2(byte[] src) {
        byte[] dst = getDst();
        try {
            System.arraycopy(src, 0, dst, Integer.MAX_VALUE-1, dst.length);
        } catch (Exception e) {
            // Expected
        }
    }

    public static void main(String[] args) {
        byte[] array = new byte[5];
        for (int i = 0; i < 10_000; ++i) {
            test1(array);
            test2(array);
        }
    }
}
