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

public class C
{
    // U+10400 (\ud801\udc00): DESERET CAPITAL LETTER LONG I (can be start or part)
    // U+1D17B (\ud834\udd7b): MUSICAL SYMBOL COMBINING ACCENT (can only be part)
    // U+1D100 (\ud834\udd00): MUSICAL SYMBOL SINGLE BARLINE (can be none of start nor part)

    // valid tags

    /**
     * @see C#\ud801\udc00method()
     */
    public void \ud801\udc00method() {};

    /**
     * @see C#method\ud801\udc00()
     */
    public void method\ud801\udc00() {};

    /**
     * @see C#method\ud834\udd7b()
     */
    public void method\ud834\udd7b() {};

    /**
     * @serialField \ud801\udc00field int
     * @serialField field\ud801\udc00 int
     * @serialField field\ud834\udd7b int
     */
    public void method1() {};

    // invalid tags - should generate warnings

    /**
     * @see C#method\ud834\udd00()
     */
    public void method2() {};

    /**
     * @serialField field\ud801\ud801 int
     * @serialField \ud834\udd7bfield int
     */
    public void method3() {};
}
