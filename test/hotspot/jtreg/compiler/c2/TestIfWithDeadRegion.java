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

import java.util.function.Supplier;

/**
 * @test
 * @bug 8219807
 * @summary Test IfNode::up_one_dom() with dead regions.
 * @compile -XDstringConcat=inline TestIfWithDeadRegion.java
 * @run main/othervm -XX:CompileCommand=compileonly,compiler.c2.TestIfWithDeadRegion::test
 *                   compiler.c2.TestIfWithDeadRegion
 */

public class TestIfWithDeadRegion {

    static String msg;

    static String getString(String s, int i) {
        String current = s + String.valueOf(i);
        System.nanoTime();
        return current;
    }

    static void test(Supplier<String> supplier) {
        msg = supplier.get();
    }

    public static void main(String[] args) {
        for (int i = 0; i < 20_000; ++i) {
            test(() -> getString("Test1", 42));
            test(() -> getString("Test2", 42));
        }
    }
}
