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

/**
 * @test
 * @bug 8155612
 * @summary Aarch64: vector nodes need to support misaligned offset
 *
 * @run main/othervm -XX:-BackgroundCompilation compiler.vectorization.TestVectorUnalignedOffset
 */

package compiler.vectorization;

public class TestVectorUnalignedOffset {

    static void test1(int[] src_array, int[] dst_array, int l) {
        for (int i = 0; i < l; i++) {
            dst_array[i + 250] = src_array[i + 250];
        }
    }

    static void test2(byte[] src_array, byte[] dst_array, int l) {
        for (int i = 0; i < l; i++) {
            dst_array[i + 250] = src_array[i + 250];
        }
    }

    static public void main(String[] args) {
        int[] int_array = new int[1000];
        byte[] byte_array = new byte[1000];
        for (int i = 0; i < 20000; i++) {
            test1(int_array, int_array, int_array.length - 250);
            test2(byte_array, byte_array, byte_array.length - 250);
        }
    }
}
