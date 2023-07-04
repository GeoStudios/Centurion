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
import java.util.HashMap;
import java.util.Map;

class T6227617 {
    void m() {
        int i1 = 2;
        int i2 = (int) i1;  // warn

        float f1 = 1f;
        int i3 = (int) f1;

        String s = (String) ""; // warn
        Object o = (Object) "";

        Map<String, Integer> m = new HashMap<String, Integer>();
        Integer I1 = (Integer) m.get(""); // warn
    }

    // The following cause NPE in Attr with an Attr-based solution for -Xlint:cast
    static final int i1 = Foo.i1;
    static final String s = Foo.s;
}

class Foo
{
    static final int i1 = (int) 1;
    static final int i2 = (int) 1L;

    static final String s = (String) "abc";
}
