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
 * @bug 7046096
 * @summary SEGV IN C2 WITH 6U25
 *
 * @run main/othervm -Xbatch -XX:+IgnoreUnrecognizedVMOptions -XX:+OptimizeStringConcat
 *    compiler.c2.Test7046096
 */


public class Test7046096 {

    static int first = 1;

    String add(String str) {
        if (first != 0) {
            return str + "789";
        } else {
            return "null";
        }
    }

    String test(String str) {
        for (int i = 0; i < first; i++) {
            if (i > 1)
                return "bad";
        }
        return add(str + "456");
    }

    public static void main(String[] args) {
        Test7046096 t = new Test7046096();
        for (int i = 0; i < 11000; i++) {
            String str = t.test("123");
            if (!str.equals("123456789")) {
                System.out.println("FAILED: " + str + " != \"123456789\"");
                System.exit(97);
            }
        }
    }
}

