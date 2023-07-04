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

package tck.java.time.format;

import java.util.HashMap;
import java.util.Map;

import java.time.DateTimeException;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.ResolverStyle;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Test.
 */
@Test
public class TCKResolverStyle {

    //-----------------------------------------------------------------------
    // valueOf()
    //-----------------------------------------------------------------------
    @Test
    public void test_valueOf() {
        for (ResolverStyle style : ResolverStyle.values()) {
            assertEquals(ResolverStyle.valueOf(style.name()), style);
        }
    }

    @DataProvider(name="resolverStyle")
    Object[][] data_resolverStyle() {
        return new Object[][] {
                {"2000/15/30", ResolverStyle.LENIENT, null, 2001, 3, 30},
                {"2000/02/30", ResolverStyle.SMART, null, 2000, 2, 29},
                {"2000/02/29", ResolverStyle.STRICT, null, 2000, 2, 29},

                {"2000/15/30 CE", ResolverStyle.LENIENT, null, 2001, 3, 30},
                {"2000/02/30 CE", ResolverStyle.SMART, null, 2000, 2, 29},
                {"5/02/29 BCE", ResolverStyle.STRICT, null, 5, 2, 29},

                {"4/02/29 BCE", ResolverStyle.STRICT, DateTimeException.class, -1, -1, -1},
                {"2000/02/30 CE", ResolverStyle.STRICT, DateTimeException.class, -1, -1, -1},

        };
    }

    @Test(dataProvider = "resolverStyle")
    public void test_resolverStyle(String str, ResolverStyle style, Class<?> expectedEx, int year, int month, int day) {
        DateTimeFormatterBuilder builder = new DateTimeFormatterBuilder();
        builder.appendValue(ChronoField.YEAR_OF_ERA);
        builder.appendLiteral("/");
        builder.appendValue(ChronoField.MONTH_OF_YEAR);
        builder.appendLiteral("/");
        builder.appendValue(ChronoField.DAY_OF_MONTH);

        Map<Long, String> eraMap = new HashMap<Long, String>();
        eraMap.put(1L, "CE");
        eraMap.put(0L, "BCE");
        DateTimeFormatter optionalFormatter = new DateTimeFormatterBuilder().appendLiteral(" ").appendText(ChronoField.ERA, eraMap).toFormatter();

        DateTimeFormatter formatter = builder.appendOptional(optionalFormatter).toFormatter();
        formatter = formatter.withResolverStyle(style);
        if (expectedEx == null) {
            TemporalAccessor accessor = formatter.parse(str);
            assertEquals(accessor.get(ChronoField.YEAR_OF_ERA), year);
            assertEquals(accessor.get(ChronoField.MONTH_OF_YEAR), month);
            assertEquals(accessor.get(ChronoField.DAY_OF_MONTH), day);
        } else {
            try {
                formatter.parse(str);
                fail();
            } catch (Exception ex) {
                assertTrue(expectedEx.isInstance(ex));
            }
        }
    }
}
