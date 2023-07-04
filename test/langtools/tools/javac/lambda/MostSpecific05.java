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
public class MostSpecific05 {

    interface ObjectConverter<T extends Object> {
        T map(Object o);
    }

    interface NumberConverter<T extends Number> {
        T map(Object o);
    }

    static class MyMapper<A extends Object, B extends Number> {
        void map(ObjectConverter<? extends A> m) { }
        void map(NumberConverter<? extends B> m) { }
    }

    public static void main(String[] args) {
        MyMapper<Number, Double> mm = new MyMapper<Number, Double>();
        mm.map(e->1.0); //ambiguous - implicit
        mm.map((Object e)->1.0); //ok
    }
}
