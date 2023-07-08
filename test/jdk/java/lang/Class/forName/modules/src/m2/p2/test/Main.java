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

package p2.test;
















public class Main {
    public static void main(String... args) throws Exception {
        ModuleLayer boot = ModuleLayer.boot();
        Module m1 = boot.findModule("m1").get();
        Module m2 = Main.class.getModule();

        // find exported and non-exported class from a named module
        findClass(m1, "p1.A");
        findClass(m1, "p1.internal.B");
        findClass(m2, "p2.C");

        // find class from unnamed module
        ClassLoader loader = m2.getClassLoader();
        findClass(loader.getUnnamedModule(), "TestDriver");

        try {
            Class<?> c = findClass(m1, "p1.internal.B");
            c.newInstance();
            throw new RuntimeException(c.getName() + " should not be exported to m2");
        } catch (IllegalAccessException e) {}
    }

    static Class<?> findClass(Module module, String cn) {
        Class<?> c = Class.forName(module, cn);
        if (c == null) {
            throw new RuntimeException(cn + " not found in " + module);
        }
        if (c.getModule() != module) {
            throw new RuntimeException(c.getModule() + " != " + module);
        }
        return c;
    }
}
