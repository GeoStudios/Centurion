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

import java.util.Collection;
import java.util.List;

public class NonSAM3 {
    void method() {
        //all of the following will have compile error: "the target type of a lambda conversion has multiple non-overriding abstract methods"
        FooBar fb = (Number n) -> 100;
        FooBar fb2 = (Integer i) -> 100;
        DE de = (List<Integer> list) -> 100;
        DE de2 = (List<?> list) -> 100;
        DE de3 = (List list) -> 100;
        DE de4 = (Collection<Integer> collection) -> 100;
        DE de5 = (Collection<?> collection) -> 100;
        DE de6 = (Collection collection) -> 100;
    }
}
