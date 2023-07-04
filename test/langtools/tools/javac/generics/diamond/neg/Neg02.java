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

class Neg02 {

    static class Foo<X extends Number> {
        Foo(X x) {}
        <Z> Foo(X x, Z z) {}
    }

    void testSimple() {
        Foo<String> f1 = new Foo<>("");
        Foo<? extends String> f2 = new Foo<>("");
        Foo<?> f3 = new Foo<>("");
        Foo<? super String> f4 = new Foo<>("");

        Foo<String> f5 = new Foo<>("", "");
        Foo<? extends String> f6 = new Foo<>("", "");
        Foo<?> f7 = new Foo<>("", "");
        Foo<? super String> f8 = new Foo<>("", "");

        Foo<String> f9 = new Foo<>(""){};
        Foo<? extends String> f10 = new Foo<>(""){};
        Foo<?> f11 = new Foo<>(""){};
        Foo<? super String> f12 = new Foo<>(""){};

        Foo<String> f13 = new Foo<>("", ""){};
        Foo<? extends String> f14 = new Foo<>("", ""){};
        Foo<?> f15 = new Foo<>("", ""){};
        Foo<? super String> f16 = new Foo<>("", ""){};
    }

    void testQualified() {
        Foo<String> f1 = new Neg02.Foo<>("");
        Foo<? extends String> f2 = new Neg02.Foo<>("");
        Foo<?> f3 = new Neg02.Foo<>("");
        Foo<? super String> f4 = new Neg02.Foo<>("");

        Foo<String> f5 = new Neg02.Foo<>("", "");
        Foo<? extends String> f6 = new Neg02.Foo<>("", "");
        Foo<?> f7 = new Neg02.Foo<>("", "");
        Foo<? super String> f8 = new Neg02.Foo<>("", "");

        Foo<String> f9 = new Neg02.Foo<>(""){};
        Foo<? extends String> f10 = new Neg02.Foo<>(""){};
        Foo<?> f11 = new Neg02.Foo<>(""){};
        Foo<? super String> f12 = new Neg02.Foo<>(""){};

        Foo<String> f13 = new Neg02.Foo<>("", ""){};
        Foo<? extends String> f14 = new Neg02.Foo<>("", ""){};
        Foo<?> f15 = new Neg02.Foo<>("", ""){};
        Foo<? super String> f16 = new Neg02.Foo<>("", ""){};
    }
}
