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

/**
 * @test
 * @bug 8182401
 * @summary Verification error for enclosing instance capture inside super constructor invocation
 * @run main ImplicitEnclosingInstanceTest
 */

import java.util.function.Function;

public class ImplicitEnclosingInstanceTest {

    static String cookie = "deadbeef";

    static Object f(Function<String, Object> f) {
        return f.apply("feed");
    }

    class S {
        S(Object s) {
            cookie += "face";
        }
    }

    class A {
        A(String s) {
            cookie = s;
        }
    }

    class B extends S {
        B() {
            super(f(s->new A(s)));
        }
    }

    public static void main(String[] args) {
        new ImplicitEnclosingInstanceTest().new B();
        if (!cookie.equals("feedface"))
            throw new AssertionError("Incorrect cookie!");
    }
}