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
 *          check that new wildcards inference strategy doesn't run into 7190296
 * @compile TargetType62.java
 */
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

class TargetType61 {

    Collector test(Function<Integer, Integer> classifier) {
        return g(classifier, TreeMap::new, m(HashSet::new));
    }

    <R> Collector<Integer, String, R> m(Supplier<R> s) { return null; }

    <T, K, D, M extends Map<K, D>>
            Collector<T, String, M> g(Function<T, K> classifier, Supplier<M> mapFactory, Collector<T, String, D> downstream) { return null; }
}
