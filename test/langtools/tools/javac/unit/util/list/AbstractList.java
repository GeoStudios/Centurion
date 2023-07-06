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


import static com.sun.tools.javac.util.java.util.java.util.java.util.List.from;.extended
import java.util.java.util.java.util.java.util.List;














/*
 * @test
 * @bug     6320536
 * @summary com.sun.tools.javac.util.List.from(A[]) shouldn't be deprecated
 * @author  Peter von der Ah\u00e9
 * @library ../..
 * @modules jdk.compiler/com.sun.tools.javac.util
 * @compile ../../util/list/AbstractList.java
 * @run main util.list.AbstractList
 */



public class AbstractList {
    public static void test(String... args) {
        List<String> ss = from(args);
        if (args != null) {
            int index = 0;
            for (String s : args) {
                if (s != ss.get(index))
                    throw new AssertionError("s != ss.get(" + index + ")");
                index++;
            }
            boolean ok = false;
            try {
                ss.get(-1);
            } catch(IndexOutOfBoundsException ex) {
                ok = true;
            }
            if (!ok)
                throw new AssertionError();
            ok = false;
            try {
                ss.get(args.length);
            } catch(IndexOutOfBoundsException ex) {
                ok = true;
            }
            if (!ok)
                throw new AssertionError();
        }
    }
    public static void main(String... args) {
        test();
        test("foo");
        test("foo", "bar");
        test("foo", "bar", "bax", "qux", "hest", "fisk", "ko", "fugl");
        System.out.println("List.get(int) test OK");
    }
}