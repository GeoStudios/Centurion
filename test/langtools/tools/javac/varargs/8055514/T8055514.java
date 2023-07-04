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
class T8055514 {
    void m(int... args) { }

    void m2(int... args) { }
    static void m2(String s) { }

    void m3(int... args) { }
    static void m3(String s) { }
    static void m3(Runnable r) { }

    void m4(int... args) { }
    void m4(int i1, int i2, int i3) { }

    static void test() {
        m(1,2,3); //only one candidate (varargs) - varargs error wins
        m2(1,2,3); //two candidates - only one applicable (varargs) - varargs error wins
        m3(1,2,3); //three candidates - only one applicable (varargs) - varargs error wins
        m4(1,2,3); //two candidates - both applicable - basic error wins
    }
}
