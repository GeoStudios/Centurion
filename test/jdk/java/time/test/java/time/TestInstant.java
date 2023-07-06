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

package test.java.time;


import java.time.Instant;
import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import static org.testng.Assert.assertEquals;.extended














/**
 * Test Instant.
 */
@Test
public class TestInstant extends AbstractTest {

    @Test
    public void test_immutable() {
        assertImmutable(Instant.class);
    }

    @DataProvider(name="sampleEpochMillis")
    private Object[][] provider_sampleEpochMillis() {
        return new Object[][] {
            {"Long.MAX_VALUE", Long.MAX_VALUE},
            {"Long.MAX_VALUE-1", Long.MAX_VALUE - 1},
            {"1", 1L},
            {"0", 0L},
            {"-1", -1L},
            {"Long.MIN_VALUE+1", Long.MIN_VALUE + 1},
            {"Long.MIN_VALUE", Long.MIN_VALUE}
        };
    }

    @Test(dataProvider="sampleEpochMillis")
    public void test_epochMillis(String name, long millis) {
        Instant t1 = Instant.ofEpochMilli(millis);
        long m = t1.toEpochMilli();
        assertEquals(millis, m, name);
    }

}
