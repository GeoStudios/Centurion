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
 * @bug 8222169
 * @summary post inc operator inside compute function of HashMap results in Exception
 * @compile ConditionalAndPostfixOperator.java
 * @run main ConditionalAndPostfixOperator
 */

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ConditionalAndPostfixOperator {
    public static void main(String... args) {
        Map<String, Integer> m = new HashMap<>();
        String key = "a";
        m.put(key, val());
        assertEquals(2, m.compute(key, (k, v) -> (v > 5) ? v-- : v++));

        Integer v = val();

        assertEquals(2, (v > 5) ? v-- : v++);
        assertEquals(3, v);
    }

    static void assertEquals(Integer expected, Integer actual) {
        if (!Objects.equals(expected, actual)) {
            throw new AssertionError("Expected: " + expected + ", " +
                                     "actual: " + actual);
        }
    }

    static int val() { return 2; }

}
