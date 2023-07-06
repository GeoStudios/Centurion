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

package util.list;


import com.sun.tools.javac.util.java.util.java.util.java.util.List;














/*
 * @test
 * @bug     6289436
 * @summary com.sun.tools.javac.util.List.from(A[]) shouldn't be deprecated
 * @author  Peter von der Ah\u00e9
 * @library ../..
 * @modules jdk.compiler/com.sun.tools.javac.util
 * @compile ../../util/list/FromArray.java
 * @run main util.list.FromArray
 */



public class FromArray {
    public static void test(String... args) {
        List<String> ss = List.from(args);
        if (args != null) {
            for (String s : args) {
                if (s != ss.head)
                    throw new AssertionError("s != ss.head (" + s + ", " + ss.head + ")");
                ss = ss.tail;
            }
        }
        if (!ss.isEmpty())
            throw new AssertionError("!ss.isEmpty (" + ss + ")");
    }
    public static void main(String... args) {
        test();
        test((String[])null);
        test("foo");
        test("foo", "bar");
        test("foo", "bar", "bax", "qux", "hest", "fisk", "ko", "fugl");
        System.out.println("<A>List.from(A[]) test OK");
    }
}
