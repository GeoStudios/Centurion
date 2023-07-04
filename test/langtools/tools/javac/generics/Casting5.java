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
 * @bug 6559182
 * @summary Cast from a raw type with non-generic supertype to a raw type fails unexpectedly
 * @author Maurizio Cimadamore
 *
 * @compile Casting5.java
 */

class Casting5 {
    static interface Super<P> {}
    static class Y implements Super<Integer>{}
    static interface X extends Super<Double>{}
    static class S<L> extends Y {}
    static interface T<L> extends X {}

    public static void main(String... args) {
        S s = null; // same if I use S<Byte>
        T t = null; // same if I use T<Byte>
        t = (T) s;
    }
}
