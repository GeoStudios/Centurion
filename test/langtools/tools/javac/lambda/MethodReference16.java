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
 * @bug 8003280
 * @summary Add lambda tests
 *  semantics of statically qualified method reference should not depend on the context
 * @author  Maurizio Cimadamore
 * @run main MethodReference16
 */

public class MethodReference16 {

    static void assertTrue(boolean cond) {
        assertionCount++;
        if (!cond)
            throw new AssertionError();
    }

    static int assertionCount = 0;

    interface SAM {
        void m(MethodReference16 receiver);
    }

    void m() { assertTrue(true); }

    void test() {
        SAM s = (SAM)MethodReference16::m;
        s.m(this);
    }

    public static void main(String[] args) {
        MethodReference16 rec = new MethodReference16();
        SAM s = (SAM)MethodReference16::m;
        s.m(rec);
        rec.test();
        assertTrue(assertionCount == 2);
    }
}
