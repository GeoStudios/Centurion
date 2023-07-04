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

/**
 * @test
 * @bug 8030016
 * @summary computeIfAbsent would generate spurious access
 */

import java.util.*;

public class ComputeIfAbsentAccessOrder {
    public static void main(String args[]) throws Throwable {
        LinkedHashMap<String,Object> map = new LinkedHashMap<>(2, 0.75f, true);
        map.put("first", null);
        map.put("second", null);

        map.computeIfAbsent("first", l -> null); // should do nothing

        String key = map.keySet().stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("no value"));
        if(!"first".equals(key)) {
            throw new RuntimeException("not expected value " + "first" + "!=" + key);
        }
    }
}
