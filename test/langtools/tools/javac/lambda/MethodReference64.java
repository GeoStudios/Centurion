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
class MethodReference64 {
    interface ClassFactory {
        Object m();
    }

    interface ArrayFactory {
        Object m(int i);
    }

    @interface Anno { }

    enum E { }

    interface I { }

    static class Foo<X> { }

    void m(ClassFactory cf) { }
    void m(ArrayFactory cf) { }

    void testAssign() {
        ClassFactory c1 = Anno::new; //error
        ClassFactory c2 = E::new; //error
        ClassFactory c3 = I::new; //error
        ClassFactory c4 = Foo<?>::new; //error
        ClassFactory c5 = 1::new; //error
        ArrayFactory a1 = Foo<?>[]::new; //ok
        ArrayFactory a2 = Foo<? extends String>[]::new; //error
    }

    void testMethod() {
        m(Anno::new); //error
        m(E::new); //error
        m(I::new); //error
        m(Foo<?>::new); //error
        m(1::new); //error
        m(Foo<?>[]::new); //ok - resolves to m(ArrayFactory)
        m(Foo<? extends String>[]::new); //error
    }
}
