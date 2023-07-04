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
 * @bug 8267773
 * @summary Test
 *
 * @compile IntegerMinValue.java
 * @run main/othervm -Xverify:all -Xbatch IntegerMinValue
 *
 * @compile -XDstringConcat=inline IntegerMinValue.java
 * @run main/othervm -Xverify:all -Xbatch IntegerMinValue
 *
 * @compile -XDstringConcat=indy IntegerMinValue.java
 * @run main/othervm -Xverify:all -Xbatch IntegerMinValue
 *
 * @compile -XDstringConcat=indyWithConstants IntegerMinValue.java
 * @run main/othervm -Xverify:all -Xbatch IntegerMinValue
*/

public class IntegerMinValue {

    public void test() {
        int i = Integer.MIN_VALUE;
        String s = "" + i;
        if (!"-2147483648".equals(s)) {
           throw new IllegalStateException("Failed: " + s);
        }
        System.out.println(s);
    }

    public static void main(String[] strArr) {
        IntegerMinValue t = new IntegerMinValue();
        for (int i = 0; i < 100_000; i++ ) {
            t.test();
        }
    }

}
