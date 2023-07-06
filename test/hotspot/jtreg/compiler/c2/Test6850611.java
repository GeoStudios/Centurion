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

package compiler.c2;

/**
 * @test
 * @bug 6850611
 * @summary int / long arithmetic seems to be broken in 1.6.0_14 HotSpot Server VM (Win XP)
 *
 * @run main/timeout=480 compiler.c2.Test6850611
 */

public class Test6850611 {

    public static void main(String[] args) {
        test();
    }

    private static void test() {
        for (int j = 0; j < 5; ++j) {
            long x = 0;
            for (int i = Integer.MIN_VALUE; i < Integer.MAX_VALUE; ++i) {
                x += i;
            }
            System.out.println("sum: " + x);
            if (x != -4294967295l) {
                System.out.println("FAILED");
                System.exit(97);
            }
        }
    }
}
