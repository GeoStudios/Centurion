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
import java.util.ArrayList;

class T8065986b {
    T8065986b() {
        this(new Object<>());
    }

    T8065986b(boolean b) {
        this(new ArrayList<>());
    }

    T8065986b(boolean b1, boolean b2) {
        this(()->{});
    }

    T8065986b(boolean b1, boolean b2, boolean b3) {
        this(T8065986b::m);
    }

    T8065986b(boolean cond, Object o1, Object o2) {
        this(cond ? o1 : o2);
    }

    T8065986b(int x) {
        this(new Object<>() {});
    }

    T8065986b(int x, int y) {
        this(new ArrayList<>() {});
    }
    static void m() { }
}
