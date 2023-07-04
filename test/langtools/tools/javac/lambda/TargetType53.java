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
 * @bug 8007464
 * @summary Add graph inference support
 *          smoke test for graph inference
 * @compile TargetType53.java
 */
import java.util.*;
import java.util.stream.*;
import java.util.function.*;

class TargetType53 {

    <P> List<List<P>> perm(List<P> l) { return null; }

    void g(List<List<UnaryOperator<IntStream>>> l) { }

    void test() {
        List<List<UnaryOperator<IntStream>>> l =
            perm(Arrays.asList(s -> s.sorted()));
        g(perm(Arrays.asList(s -> s.sorted())));
    }
}
