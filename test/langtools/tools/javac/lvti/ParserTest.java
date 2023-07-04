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

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import java.util.function.Function;
import java.util.List;

class ParserTest<var extends AutoCloseable> {
    static class TestClass {
        static class var { } //illegal
    }

    static class TestInterface {
        interface var { } //illegal
    }

    static class TestEnum {
        enum var { } //illegal
    }

    static class TestAnno {
        @interface var { } //illegal
    }

    @Target(ElementType.TYPE_USE)
    @interface TA { }

    @interface DA { }

    static abstract class var extends RuntimeException implements AutoCloseable { } //illegal

    var x = null; //illegal

    void test() {
        var[] x1 = null; //illegal
        var x2[] = null; //illegal
        var[][] x3 = null; //illegal
        var x4[][] = null; //illegal
        var[] x5 = null; //illegal
        var x6[] = null; //illegal
        var@TA[]@TA[] x7 = null; //illegal
        var x8@TA[]@TA[] = null; //illegal
        var x9 = null, y = null; //illegal
        final @DA var x10 = m(); //ok
        @DA final var x11 = m(); //ok
    }

    var m() { //illegal
        return null;
    }

    void test2(var x) { //error
        List<var> l1; //error
        List<? extends var> l2; //error
        List<? super var> l3; //error
        try {
            Function<var, String> f = (var x2) -> ""; //ok
        } catch (var ex) { } //error
    }

    void test3(Object o) {
        boolean b1 = o instanceof var; //error
        Object o2 = (var)o; //error
    }

    void test4() throws Exception {
        try (final var resource1 = null; // ok
             var resource2 = null) {     // ok
        }
    }
}
