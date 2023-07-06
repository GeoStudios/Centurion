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

package p1;

import p2.c2;

public class c1ReadEdge {
    public c1ReadEdge() {
        // Establish read edge from module m1x, where c1ReadEdge is defined,
        // to the unnamed module, where p2.c2 will be defined.
        Module m1x = c1ReadEdge.class.getModule();
        ClassLoader loader = c1ReadEdge.class.getClassLoader();
        Module unnamed_module = loader.getUnnamedModule();
        m1x.addReads(unnamed_module);

        // Attempt access - access should succeed
        p2.c2 c2_obj = new p2.c2();
        c2_obj.method2();
    }
}