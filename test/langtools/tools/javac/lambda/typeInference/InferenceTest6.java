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

import java.util.*;

public class InferenceTest6 {
    public static void main(String[] args) {
        InferenceTest6 test = new InferenceTest6();
        test.method1(n -> {});
        test.method1((SAM1<String>)n -> {});
        test.method1((SAM1<Integer>)n -> {n++;});
        test.method1((SAM1<Comparator<String>>)n -> {List<String> list = Arrays.asList("string1", "string2"); Collections.sort(list,n);});
        test.method1((SAM1<Thread>)n -> {n.start();});
    }

    interface SAM1<X> {
        void m1(X arg);
    }

    <X> void method1(SAM1<X> s) {}
}
