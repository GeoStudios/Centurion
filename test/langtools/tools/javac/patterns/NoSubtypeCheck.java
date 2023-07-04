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
public class NoSubtypeCheck {

    public static void main(Object o, String s, List<String> l) {
        boolean b1 = o instanceof Object v1;
        boolean b2 = o instanceof String v2;
        boolean b3 = s instanceof Object v3;
        boolean b4 = s instanceof String v4;
        boolean b5 = l instanceof List<String> v5;
        boolean b6 = l instanceof List2<String> v6;
        boolean b7 = undef instanceof String v7;
        boolean b8 = o instanceof Undef v7;
    }

    public interface List<T> {}
    public interface List2<T> extends List<T> {}
}
