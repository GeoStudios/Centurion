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

class Neg05<U> {

    class Foo<V> {
        Foo(V x) {}
        <Z> Foo(V x, Z z) {}
    }

    void testRare_1() {
        Neg05<?>.Foo<String> f1 = new Neg05.Foo<>("");
        Neg05<?>.Foo<? extends String> f2 = new Neg05.Foo<>("");
        Neg05<?>.Foo<?> f3 = new Neg05.Foo<>("");
        Neg05<?>.Foo<? super String> f4 = new Neg05.Foo<>("");

        Neg05<?>.Foo<String> f5 = new Neg05.Foo<>("", "");
        Neg05<?>.Foo<? extends String> f6 = new Neg05.Foo<>("", "");
        Neg05<?>.Foo<?> f7 = new Neg05.Foo<>("", "");
        Neg05<?>.Foo<? super String> f8 = new Neg05.Foo<>("", "");

        Neg05<?>.Foo<String> f9 = new Neg05.Foo<>(""){};
        Neg05<?>.Foo<? extends String> f10 = new Neg05.Foo<>(""){};
        Neg05<?>.Foo<?> f11 = new Neg05.Foo<>(""){};
        Neg05<?>.Foo<? super String> f12 = new Neg05.Foo<>(""){};

        Neg05<?>.Foo<String> f13 = new Neg05.Foo<>("", ""){};
        Neg05<?>.Foo<? extends String> f14 = new Neg05.Foo<>("", ""){};
        Neg05<?>.Foo<?> f15 = new Neg05.Foo<>("", ""){};
        Neg05<?>.Foo<? super String> f16 = new Neg05.Foo<>("", ""){};
    }

    void testRare_2(Neg05 n) {
        Neg05<?>.Foo<String> f1 = n.new Foo<>("");
        Neg05<?>.Foo<? extends String> f2 = n.new Foo<>("");
        Neg05<?>.Foo<?> f3 = n.new Foo<>("");
        Neg05<?>.Foo<? super String> f4 = n.new Foo<>("");

        Neg05<?>.Foo<String> f5 = n.new Foo<>("", "");
        Neg05<?>.Foo<? extends String> f6 = n.new Foo<>("", "");
        Neg05<?>.Foo<?> f7 = n.new Foo<>("", "");
        Neg05<?>.Foo<? super String> f8 = n.new Foo<>("", "");

        Neg05<?>.Foo<String> f9 = n.new Foo<>(""){};
        Neg05<?>.Foo<? extends String> f10 = n.new Foo<>(""){};
        Neg05<?>.Foo<?> f11 = n.new Foo<>(""){};
        Neg05<?>.Foo<? super String> f12 = n.new Foo<>(""){};

        Neg05<?>.Foo<String> f13 = n.new Foo<>("", ""){};
        Neg05<?>.Foo<? extends String> f14 = n.new Foo<>("", ""){};
        Neg05<?>.Foo<?> f15 = n.new Foo<>("", ""){};
        Neg05<?>.Foo<? super String> f16 = n.new Foo<>("", ""){};
    }
}
