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
 * @bug 4711694
 * @summary generics: missing bridge method for inherited implementation
 * @author gafter
 *
 * @compile  T4711694.java
 * @run main T4711694
 */

public class T4711694 {
    interface A<T> {
        void f(T u);
    }

    static class B {
        public void f(Integer i) {}
    }

    static abstract class C<T> extends B implements A<T> {
        public void g(T t) {
            f(t);
        }
    }

    static class D extends C<Integer> {
        public static void main(String[] args) {
            new D().g(new Integer(3));
        }
    }

    public static void main(String[] args) {
        D.main(args);
    }
}
