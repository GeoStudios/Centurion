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

class Neg01<X extends Number> {

    Neg01(X x) {}

    <Z> Neg01(X x, Z z) {}

    void test() {
        Neg01<String> n1 = new Neg01<>("");
        Neg01<? extends String> n2 = new Neg01<>("");
        Neg01<?> n3 = new Neg01<>("");
        Neg01<? super String> n4 = new Neg01<>("");

        Neg01<String> n5 = new Neg01<>("", "");
        Neg01<? extends String> n6 = new Neg01<>("", "");
        Neg01<?> n7 = new Neg01<>("", "");
        Foo<? super String> n8 = new Neg01<>("", "");

        Neg01<String> n9 = new Neg01<>("", ""){};
        Neg01<? extends String> n10 = new Neg01<>("", ""){};
        Neg01<?> n11 = new Neg01<>("", ""){};
        Neg01<? super String> n12 = new Neg01<>("", ""){};

        Neg01<String> n13 = new Neg01<>(""){};
        Neg01<? extends String> n14 = new Neg01<>(""){};
        Neg01<?> n15 = new Neg01<>(""){};
        Neg01<? super String> n16 = new Neg01<>(""){};

    }
}
