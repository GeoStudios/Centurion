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
class MostSpecific12 {

    interface I<T> { void take(T arg1, String arg2); }
    interface J<T> { void take(String arg1, T arg2); }
    interface K { void take(String arg1, String arg2); }

    <T> void m1(I<T> arg) {}
    void m1(K arg) {}

    <T> void m2(J<T> arg) {}
    <T> void m2(K arg) {}

    <T> void m3(I<T> arg) {}
    <T> void m3(J<T> arg) {}

    void test() {
        m1((String s1, String s2) -> {}); // ok
        m2((String s1, String s2) -> {}); // ok
        m3((String s1, String s2) -> {}); // error

        m1(this::referencedMethod); // ok
        m2(this::referencedMethod); // ok
        m3(this::referencedMethod); // error

        m1(String::compareTo); // ok
        m2(String::compareTo); // ok
        m3(String::compareTo); // error
    }

    void referencedMethod(String s1, String s2) {}

}
