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
class EffectivelyFinalTest {

    void m1(int x) {
        int y = 1;
        new Object() { { System.out.println(x+y); } }; //ok - both x and y are EF
    }

    void m2(int x) {
        int y;
        y = 1;
        new Object() { { System.out.println(x+y); } }; //ok - both x and y are EF
    }

    void m3(int x, boolean cond) {
        int y;
        if (cond) y = 1;
        new Object() { { System.out.println(x+y); } }; //error - y not DA
    }

    void m4(int x, boolean cond) {
        int y;
        if (cond) y = 1;
        else y = 2;
        new Object() { { System.out.println(x+y); } }; //ok - both x and y are EF
    }

    void m5(int x, boolean cond) {
        int y;
        if (cond) y = 1;
        y = 2;
        new Object() { { System.out.println(x+y); } }; //error - y not EF
    }

    void m6(int x) {
        new Object() { { System.out.println(x+1); } }; //error - x not EF
        x++; // Illegal: x is not effectively final.
    }

    void m7(int x) {
        new Object() { { System.out.println(x=1); } }; //error - x not EF
    }

    void m8() {
        int y;
        new Object() { { System.out.println(y=1); } }; //error - y not EF
    }
}
