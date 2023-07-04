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

import java.util.ArrayList;

class T6554097 {

    @SuppressWarnings("unchecked") final ArrayList[] v1 = { new ArrayList() {} };
    @SuppressWarnings("unchecked")       ArrayList[] v2 = { new ArrayList() {} };

    public static void m1() throws Throwable {
            @SuppressWarnings("unchecked") final ArrayList[] v3 = { new ArrayList() {} };
            @SuppressWarnings("unchecked")       ArrayList[] v4 = { new ArrayList() {} };
    }

    final ArrayList[] v5 = { new ArrayList() {} };
          ArrayList[] v6 = { new ArrayList() {} };

    public static void m2() throws Throwable {
        final ArrayList[] v7 = { new ArrayList() {} };
              ArrayList[] v8 = { new ArrayList() {} };
    }
}

