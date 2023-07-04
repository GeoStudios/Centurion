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

import java.util.List;

class T8062977 {
    <T extends B, B> T m(Class<B> cb) { return null; }

    void test1(Class<Iterable<?>> cb) {
        List<Integer>[] r1 = m(cb); //fail
        List<Integer> r2 = m(cb); //ok
    }

    void test2(Class<Iterable<?>[]> cb) {
        List<Integer>[] r1 = m(cb); //ok
        List<Integer> r2 = m(cb); //fail
    }

    void test3(Class<Iterable<?>[][]> cb) {
        List<Integer>[][] r1 = m(cb); //ok
        List<Integer>[] r2 = m(cb); //fail
        List<Integer> r3 = m(cb); //fail
    }
}
