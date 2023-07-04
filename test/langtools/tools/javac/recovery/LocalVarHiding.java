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

public class LocalVarHiding {
    public void test(Decl l1) {
        System.err.println(l1.originalMethod());

        OverrideVar l1 = new OverrideVar();

        System.err.println(l1.overrideMethod());

        Decl l2 = new Decl();

        System.err.println(l2.originalMethod());

        OverrideVar l2 = new OverrideVar();

        System.err.println(l2.overrideMethod());

        Decl l3 = new Decl();

        System.err.println(l3.originalMethod());

        {
            OverrideVar l3 = new OverrideVar();

            System.err.println(l3.overrideMethod());
        }

        Decl l4 = new Decl();

        System.err.println(l4.originalMethod());

        try {
            throw new OverrideVar();
        } catch (OverrideVar l4) {
            System.err.println(l4.overrideMethod());
        }

        Decl l5 = new Decl();

        System.err.println(l5.originalMethod());

        try (OverrideVar l5 = null) {
            System.err.println(l5.overrideMethod());
        }

        Decl l6 = new Decl();

        System.err.println(l6.originalMethod());

        I i1 = l6 -> {
            System.err.println(l6.overrideMethod());
        };
        I i2 = (OverrideVar l6) -> {
            System.err.println(l6.overrideMethod());
        };
    }

    public class Decl {
        public int originalMethod() {}
    }

    public class OverrideVar extends Exception implements AutoCloseable {
        @Override public void close() throws Exception {}
        public int overrideMethod() {}
    }

    public interface I {
        public void run(OverrideVar ov);
    }
}