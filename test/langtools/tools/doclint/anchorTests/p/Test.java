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

/**
 * <a id="dupTest">dupTest</a>
 * <a id="dupTest">dupTest again</a>
 *
 * <a id="dupTestField">dupTestField</a>
 * <a id="dupTestMethod">dupTestMethod</a>

 * <a id="okClass">okClass</a>
 * <a id="okField">okField</a>
 * <a id="okMethod">okMethod</a>
 */
public class Test {
    /** <a id="dupTestField">dupTestField again</a> */
    public int f;

    /** <a id="dupTestMethod">dupTestMethod again</a> */
    public void m() { }

    /**
     * <a id="dupNested">dupNested</a>
     * <a id="dupNested">dupNested again</a>
     * <a id="dupNestedField">dupNestedField</a>
     * <a id="dupNestedMethod">dupNestedMethod</a>
     *
     * <a id="okClass">okClass again</a>
     */
    public class Nested {
        /**
         * <a id="dupNestedField">dupNestedField</a>
         *
         * <a id="okField">okField again</a>
         */
        public int f;

        /**
         * <a id="dupNestedMethod">dupNestedMethod</a>
         *
         * <a id="okMethod">okMethod again</a>
         */
        public void m() { }
    }
}
