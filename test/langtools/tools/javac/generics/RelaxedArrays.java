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

/*
 * @test
 * @bug 5009693
 * @summary relaxed rules for array of generic type and varargs.
 * @author gafter
 *
 * @compile  RelaxedArrays.java
 * @run main RelaxedArrays
 */

import java.util.ArrayList;
import java.util.List;

public class RelaxedArrays {
    static class StringList extends ArrayList<String> {
    }

    static <T> T select(T... tl) {
        return tl.length == 0 ? null : tl[tl.length - 1];
    }

    public static void main(String[] args) {
        List<String>[] a = new StringList[20];
        if (select("A", "B", "C") != "C") throw new Error();
    }
}
