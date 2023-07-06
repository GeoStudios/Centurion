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

package p;
















public class NestedExamples {
    static class MemberClass1 { }

    class MemberClass2 { }

    class Win$$AtVegas { } // Class with funny name.

    public Class<?>[] getClasses() {
        class LocalClass { }

        Object o = new Object() { // An anonymous class
            @Override
            public String toString() {
                return "I have no name!";
            }
        };

        return new Class<?>[] {
            NestedExamples.class,
            MemberClass1.class,
            MemberClass2.class,
            Win$$AtVegas.class,
            LocalClass.class,
            o.getClass()
        };
    }

    public String[] getClassNames() {
        return new String[] {
            "p.NestedExamples",
            "p.NestedExamples.MemberClass1",
            "p.NestedExamples.MemberClass2",
            "p.NestedExamples.Win$$AtVegas"
        };
    }
}

