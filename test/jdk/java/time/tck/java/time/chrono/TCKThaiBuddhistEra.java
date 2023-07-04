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

package tck.java.time.chrono;

import static java.time.temporal.ChronoField.ERA;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.time.chrono.Era;
import java.time.chrono.ThaiBuddhistChronology;
import java.time.chrono.ThaiBuddhistEra;
import java.time.temporal.ValueRange;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Test.
 */
@Test
public class TCKThaiBuddhistEra {

    @DataProvider(name = "ThaiBuddhistEras")
    Object[][] data_of_eras() {
        return new Object[][] {
                    {ThaiBuddhistEra.BEFORE_BE, "BEFORE_BE", 0},
                    {ThaiBuddhistEra.BE, "BE", 1},
        };
    }


    //-----------------------------------------------------------------------
    // valueOf()
    //-----------------------------------------------------------------------
    @Test(dataProvider="ThaiBuddhistEras")
    public void test_valueOf(ThaiBuddhistEra era , String eraName, int eraValue) {
        assertEquals(era.getValue(), eraValue);
        assertEquals(ThaiBuddhistEra.of(eraValue), era);
        assertEquals(ThaiBuddhistEra.valueOf(eraName), era);
    }

    //-----------------------------------------------------------------------
    // values()
    //-----------------------------------------------------------------------
    @Test
    public void test_values() {
        List<Era> eraList = ThaiBuddhistChronology.INSTANCE.eras();
        ThaiBuddhistEra[] eras = ThaiBuddhistEra.values();
        assertEquals(eraList.size(), eras.length);
        for (ThaiBuddhistEra era : eras) {
            assertTrue(eraList.contains(era));
        }
    }

    //-----------------------------------------------------------------------
    // range()
    //-----------------------------------------------------------------------
    @Test
    public void test_range() {
        for (ThaiBuddhistEra era : ThaiBuddhistEra.values()) {
            assertEquals(era.range(ERA), ValueRange.of(0, 1));
        }
    }

}
