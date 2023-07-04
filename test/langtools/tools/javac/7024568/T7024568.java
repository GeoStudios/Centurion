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

class Main {
    void test(Obj o) {
        o.test(0, 0, 0, 0, 0, 0, 0, 0, undefined);
    }
}

interface Test {
    public void test(int i1, int i2, int i3, int i4, int i5, int i6, int i7, int i8, String str);
    public void test(int i1, int i2, int i3, int i4, int i5, int i6, int i7, int i8, long l);
}

interface Obj extends Test, A, B, C, D, E {}
interface A extends Test {}
interface B extends A, Test {}
interface C extends A, B, Test {}
interface D extends A, B, C, Test {}
interface E extends A, B, C, D, Test {}
