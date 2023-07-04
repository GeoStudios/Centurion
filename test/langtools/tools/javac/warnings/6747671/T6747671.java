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


class T6747671<E> {

    static class B<X> {}

    class A<X> {
        class X {}
        class Z<Y> {}
    }


    A.X x1;//raw warning
    A.Z z1;//raw warning

    T6747671.B<Integer> b1;//ok
    T6747671.B b2;//raw warning

    A<String>.X x2;//ok
    A<String>.Z<Integer> z2;//ok
    A<B>.Z<A<B>> z3;//raw warning (2)

    void test(Object arg1, B arg2) {//raw warning
        boolean b = arg1 instanceof A;//ok
        Object a = (A)arg1;//ok
        A a2 = new A() {};//raw warning (2)
        a2.new Z() {};//raw warning
    }

    @TA B @TA[] arr = new @TA B @TA [0];//JDK-8022567: raw warning (2)
    //todo: 8057688 type annotations in type argument position are lost
    Class<B[]> classes1;//no warning
    Class<B>[] classes2;//no warning

    @java.lang.annotation.Target(java.lang.annotation.ElementType.TYPE_USE)
    @interface TA { }
}
