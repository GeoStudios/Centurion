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

package test.java.time.format;

import static org.testng.Assert.assertSame;

import java.time.format.DecimalStyle;
import java.util.Locale;

import org.testng.annotations.Test;

/**
 * Test DecimalStyle.
 */
@Test
public class TestDecimalStyle {

    @Test
    public void test_of_Locale_cached() {
        DecimalStyle loc1 = DecimalStyle.of(Locale.CANADA);
        DecimalStyle loc2 = DecimalStyle.of(Locale.CANADA);
        assertSame(loc1, loc2);
    }

    //-----------------------------------------------------------------------
    @Test
    public void test_ofDefaultLocale_cached() {
        DecimalStyle loc1 = DecimalStyle.ofDefaultLocale();
        DecimalStyle loc2 = DecimalStyle.ofDefaultLocale();
        assertSame(loc1, loc2);
    }

}
