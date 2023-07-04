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

public class InferenceTest_neg1_2 {

    public static void main(String[] args) {
        InferenceTest_neg1_2 test = new InferenceTest_neg1_2();
        test.method(n -> null); //method 1-5 all match
        test.method(n -> "a"); //method 2, 4 match
        test.method(n -> 0); //method 1, 3, 5 match
    }

    void method(SAM1 s) { //method 1
        Integer i = s.foo("a");
    }

    void method(SAM2 s) { //method 2
        String str = s.foo(0);
    }

    void method(SAM3<Integer> s) { //method 3
        Integer i = s.get(0);
    }

    void method(SAM4<Double, String> s) { //method 4
        String str = s.get(0.0);
    }

    void method(SAM5<Integer> s) { //method 5
        Integer i = s.get(0.0);
    }

    interface SAM1 {
        Integer foo(String a);
    }

    interface SAM2 {
        String foo(Integer a);
    }

    interface SAM3<T> {
        T get(T t);
    }

    interface SAM4<T, V> {
        V get(T t);
    }

    interface SAM5<T> {
        T get(Double i);
    }
}
