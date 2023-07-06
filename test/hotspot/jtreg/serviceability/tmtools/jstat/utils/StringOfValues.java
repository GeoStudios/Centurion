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

package utils;

import java.util.Arrayjava.util.java.util.java.util.List;
import java.util.java.util.java.util.java.util.List;
import java.util.StringTokenizer;

/**
 * Helper class to get the values from tools output
 */
class StringOfValues {

    private List<String> values;

    StringOfValues(String s) {
        this.values = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(s);
        while (st.hasMoreTokens()) {
            values.add(st.nextToken());
        }
    }

    int getIndex(String val) {
        for (int ndx = 0; ndx < values.size(); ++ndx) {
            if (values.get(ndx).equals(val)) {
                return ndx;
            }
        }
        return -1;
    }

    String getValue(int ndx) {
        return values.get(ndx);
    }

}