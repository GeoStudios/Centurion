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

import p.A;

class Test1 {
    String ss = "      p.A      yes";
    String sa = "p.A   p.A#publ yes";
    String sq = "p.A   p.A#prot no ";
    String sr = "Test2 p.A#prot no ";
    String sx = "p.A   p.A#priv no ";

    String s2 = "      Test2      yes";
    String s3 = "Test2 Test2#stat yes";

    static class Test1a {
        String s1 = "Test2 Test2#priv no";
    }
}

class Test2 extends A {
    private int priv;
    static int stat;

    String ss = "      p.A      yes";
    String sa = "p.A   p.A#publ yes";
    String sq = "p.A   p.A#prot no ";
    String sr = "Test2 p.A#prot yes";
    String sx = "p.A   p.A#priv no ";

    static class Test2a {
        String s1 = "Test2 Test2#priv yes";
    }
}
