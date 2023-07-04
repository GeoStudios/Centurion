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
 * @bug 8213479
 * @summary Missing x86_64.ad patterns for 8-bit logical operators with destination in memory
 *
 * @run main/othervm -Xcomp -XX:-Inline -XX:CompileOnly=compiler.c2.Test8bitLogicalOperators::test
 *      compiler.c2.Test8bitLogicalOperators
 */

package compiler.c2;

public class Test8bitLogicalOperators {
    private static byte and = 0b0011, or = 0b0011, xor = 0b0011;
    private static byte mask = 0b0101;

    public static void main(String... args) {
        test();

        if (and != 0b0001 || or != 0b0111 || xor != 0b0110)
            throw new AssertionError("8-bit logical operator failure");
    }

    public static void test() {
        and &= mask;

        or |= mask;

        xor ^= mask;
    }
}
