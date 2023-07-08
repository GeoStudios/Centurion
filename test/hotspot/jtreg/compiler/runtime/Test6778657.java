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

package compiler.runtime;
















/*
 * @test
 * @bug 6778657
 * @summary Casts in SharedRuntime::f2i, f2l, d2i and d2l rely on undefined C++ behaviour
 *
 * @run main compiler.runtime.Test6778657
 */


public class Test6778657 {
    public static void check_f2i(int expect) {
        float check = expect;
        check *= 2;
        int actual = (int) check;
        if (actual != expect) {
            throw new RuntimeException("expecting " + expect + ", got " + actual);
        }
    }

    public static void check_f2l(long expect) {
        float check = expect;
        check *= 2;
        long actual = (long) check;
        if (actual != expect) {
            throw new RuntimeException("expecting " + expect + ", got " + actual);
        }
    }

    public static void check_d2i(int expect) {
        double check = expect;
        check *= 2;
        int actual = (int) check;
        if (actual != expect) {
            throw new RuntimeException("expecting " + expect + ", got " + actual);
        }
    }

    public static void check_d2l(long expect) {
        double check = expect;
        check *= 2;
        long actual = (long) check;
        if (actual != expect) {
            throw new RuntimeException("expecting " + expect + ", got " + actual);
        }
    }

    public static void main(String[] args) {
        check_f2i(Integer.MAX_VALUE);
        check_f2i(Integer.MIN_VALUE);
        check_f2l(Long.MAX_VALUE);
        check_f2l(Long.MIN_VALUE);
        check_d2i(Integer.MAX_VALUE);
        check_d2i(Integer.MIN_VALUE);
        check_d2l(Long.MAX_VALUE);
        check_d2l(Long.MIN_VALUE);
    }
}

