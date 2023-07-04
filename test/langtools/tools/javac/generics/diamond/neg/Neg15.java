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

class Neg15 {

    interface Predicate<T> {
        default boolean test(T t) {
            System.out.println("Default method");
            return false;
        }
    }


    static void someMethod(Predicate<? extends Number> p) {
        if (!p.test(null))
            throw new Error("Blew it");
    }

    public static void main(String[] args) {

        someMethod(new Predicate<Integer>() {
            public boolean test(Integer n) {
                System.out.println("Override");
                return true;
            }
            boolean test(Integer n, int i) {
                System.out.println("Override");
                return true;
            }
            protected boolean test(Integer n, int i, int j) {
                System.out.println("Override");
                return true;
            }
            private boolean test(Integer n, int i, long j) {
                System.out.println("Override");
                return true;
            }
        });

        someMethod(new Predicate<>() {
            public boolean test(Integer n) { // bad.
                System.out.println("Override");
                return true;
            }
            boolean test(Integer n, int i) { // bad, package access.
                System.out.println("Override");
                return true;
            }
            protected boolean test(Integer n, int i, int j) { // bad, protected access.
                System.out.println("Override");
                return true;
            }
            private boolean test(Integer n, int i, long j) { // OK, private method.
                System.out.println("Override");
                return true;
            }
        });
    }
}
