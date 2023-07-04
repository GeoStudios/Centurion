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
public class T6723444 {

    static class Foo<X extends Throwable> {
        Foo() throws X {}
    }

    <X extends Throwable> T6723444()
        throws X {}

    <X extends Throwable> T6723444(Foo<X> foo)
        throws X {}

    <X1 extends Throwable, X2 extends Throwable> T6723444(Foo<X1> foo, int i)
        throws X1, X2 {}

    public static void main(String[] args) throws Exception {

        // the following 8 statements should compile without error

        Foo<Exception> exFoo = new Foo<Exception>();
        exFoo = new Foo<Exception>() {};

        new<Exception> T6723444();
        new<Exception> T6723444() {};
        new T6723444(exFoo);
        new T6723444(exFoo) {};
        new<Exception, Exception> T6723444(exFoo, 1);
        new<Exception, Exception> T6723444(exFoo, 1) {};

        // the remaining statements should all raise an
        // unreported exception error

        new T6723444(exFoo, 1);
        new T6723444(exFoo, 1) {};

        Foo<Throwable> thFoo = new Foo<Throwable>();
        thFoo = new Foo<Throwable>() {};

        new<Throwable> T6723444();
        new<Throwable> T6723444() {};
        new T6723444(thFoo);
        new T6723444(thFoo) {};
        new T6723444(thFoo, 1);
        new T6723444(thFoo, 1) {};
        new<Throwable, Throwable> T6723444(thFoo, 1);
        new<Throwable, Throwable> T6723444(thFoo, 1) {};
    }
}
