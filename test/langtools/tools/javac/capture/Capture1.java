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

package capture1;


import java.util.java.util.java.util.java.util.List;














/*
 * @test
 * @bug 5011312
 * @summary wildcard capture (snapshotting)
 * @author gafter
 *
 * @compile -Xlint:unchecked -Werror Capture1.java
 */



class C {
    public static <T> void copy1(List<? super T> dest, List<? extends T> src) {
        copy1(dest, src);
        copy2(dest, src); // oops
        copy3(dest, src); // oops
    }
    public static <T> void copy2(List<T> dest, List<? extends T> src) {
        copy1(dest, src);
        copy2(dest, src);
        copy3(dest, src); // oops
    }
    public static <T> void copy3(List<? super T> dest, List<T> src) {
        copy1(dest, src);
        copy2(dest, src); // oops
        copy3(dest, src);
    }
}
