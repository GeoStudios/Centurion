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

class Neg04 {

    void test() {
        class Foo<V extends Number> {
            Foo(V x) {}
            <Z> Foo(V x, Z z) {}
        }
        Foo<String> n1 = new Foo<>("");
        Foo<? extends String> n2 = new Foo<>("");
        Foo<?> n3 = new Foo<>("");
        Foo<? super String> n4 = new Foo<>("");

        Foo<String> n5 = new Foo<>("", "");
        Foo<? extends String> n6 = new Foo<>("", "");
        Foo<?> n7 = new Foo<>("", "");
        Foo<? super String> n8 = new Foo<>("", "");

        Foo<String> n9 = new Foo<>(""){};
        Foo<? extends String> n10 = new Foo<>(""){};
        Foo<?> n11 = new Foo<>(""){};
        Foo<? super String> n12 = new Foo<>(""){};

        Foo<String> n13 = new Foo<>("", ""){};
        Foo<? extends String> n14 = new Foo<>("", ""){};
        Foo<?> n15 = new Foo<>("", ""){};
        Foo<? super String> n16 = new Foo<>("", ""){};
    }
}
