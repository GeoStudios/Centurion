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

/** */
public class AccessibilityTest {
    /**
     * <h1> ... </h1>
     */
    public class Bad_H1 { }

    /**
     * <h3> ... </h3>
     */
    public class Missing_H2 { }

    /**
     * <h2> ... </h2>
     * <h4> ... </h4>
     */
    public class Missing_H3 { }

    /**
     * <h2> ... </h2>
     */
    public void bad_h2() { }

    /**
     * <h4> ... </h4>
     */
    public void missing_h3() { }

    /**
     * <h3> ... </h3>
     * <h5> ... </h5>
     */
    public void missing_h4() { }

    /**
     * <img src="x.jpg">
     */
    public void missing_alt() { }

    /**
     * <table><caption>ok</caption><tr><th>head<tr><td>data</table>
     */
    public void table_with_caption() { }

    /**
     * <table><tr><th>head<tr><td>data</table>
     */
    public void table_without_caption() { }

    /**
     * <table role="presentation"><tr><th>head<tr><td>data</table>
     */
    public void table_presentation() { }

}

