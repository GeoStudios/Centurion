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

class MethodReference52 {

    interface Clone1 {
        int[] m();
    }

    interface Clone2 {
        Object m();
    }

    interface WrongClone {
        long[] m();
    }

    interface GetClass {
        Class<? extends List> m();
    }

    interface WrongGetClass {
        Class<List<String>> m();
    }

    void test(int[] iarr, List<String> ls) {
        Clone1 c1 = iarr::clone; //ok
        Clone2 c2 = iarr::clone; //ok - type more generic
        WrongClone c3 = iarr::clone; //bad return type
        GetClass c4 = ls::getClass; //ok
        WrongGetClass c5 = ls::getClass; //bad return type
    }
}
