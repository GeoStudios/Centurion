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
 * @bug 7123229
 * @summary (coll) EnumMap.containsValue(null) returns true
 * @author ngmr
 */

import java.util.EnumMap;
import java.util.Map;

public class UniqueNullValue {
    static enum TestEnum { e00, e01 }

    public static void main(String[] args) {
        Map<TestEnum, Integer> map = new EnumMap<>(TestEnum.class);

        map.put(TestEnum.e00, 0);
        if (false == map.containsValue(0)) {
            throw new RuntimeException("EnumMap unexpectedly missing 0 value");
        }
        if (map.containsValue(null)) {
            throw new RuntimeException("EnumMap unexpectedly holds null value");
        }

        map.put(TestEnum.e00, null);
        if (map.containsValue(0)) {
            throw new RuntimeException("EnumMap unexpectedly holds 0 value");
        }
        if (false == map.containsValue(null)) {
            throw new RuntimeException("EnumMap unexpectedly missing null value");
        }
    }
}
