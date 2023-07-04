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

package java.lang;

import java.lang.System.Logger;
import java.util.ResourceBundle;

/*
 * PatchedUsage is patched into java.base, it will be used by
 * PatchedClient to test when logger client is in patched module
 */
public class PatchedUsage {

    public static Logger getLogger(String name) {
        check();
        return System.getLogger(name);
    }

    public static Logger getLogger(String name, ResourceBundle rb) {
        check();
        return System.getLogger(name, rb);
    }

    private static void check() {
        final Module m = PatchedUsage.class.getModule();
        final ClassLoader moduleCL = m.getClassLoader();
        assertTrue(m.isNamed());
        assertTrue(moduleCL == null);
    }

    private static void assertTrue(boolean b) {
        if (!b) {
            throw new RuntimeException("expected true, but get false.");
        }
    }
}
