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
class T8074381a {
    interface Sup<X> {
        boolean m(X x);
    }

    interface Sub<X> extends Sup<String> {
        boolean m(String s);
    }

    @SuppressWarnings({"deprecation", "removal"})
    void testRaw() {
        Sub s1 = c -> true;
        Sub s2 = Boolean::new;
        Sub s3 = new Sub() {
            @Override
            public boolean m(String o) { return true; }
        };
    }

    @SuppressWarnings({"deprecation", "removal"})
    void testNonRaw() {
        Sub<Integer> s1 = c -> true;
        Sub<Integer> s2 = Boolean::new;
        Sub<Integer> s3 = new Sub<Integer>() {
            @Override
            public boolean m(String o) { return true; }
        };
    }
}
