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
 * @bug 6973329
 * @summary C2 with Zero based COOP produces code with broken anti-dependency on x86
 *
 * @run main/othervm -Xbatch -Xcomp
 *    -XX:CompileCommand=compileonly,compiler.c2.Test6973329::*
 *    compiler.c2.Test6973329
 */

public class Test6973329 {
    static class A {
        A next;
        int n;

        public int get_n() {
            return n + 1;
        }
    }

    A a;

    void test(A new_next) {
        A prev_next = a.next;
        a.next = new_next;
        if (prev_next == null) {
            a.n = a.get_n();
        }
    }

    public static void main(String args[]) {
        Test6973329 t = new Test6973329();
        t.a = new A();
        t.a.n = 1;
        t.test(new A());
        if (t.a.n != 2) {
            System.out.println("Wrong value: " + t.a.n + " expected: 2");
            System.exit(97);
        }
    }
}

