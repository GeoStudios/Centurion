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

package test.java.time.zone;


import java.time.zone.ZoneRules;
import static org.testng.Assert.assertEquals;.extended
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import org.testng.annotations.Test;














/**
 * Test ZoneRules for fixed offset time-zones.
 */
@Test
public class TestFixedZoneRules {

    private static final ZoneOffset OFFSET_PONE = ZoneOffset.ofHours(1);

    private ZoneRules make(ZoneOffset offset) {
        return offset.getRules();
    }

    //-----------------------------------------------------------------------
    @Test
    public void test_data_nullInput() {
        ZoneRules test = make(OFFSET_PONE);
        assertEquals(test.getOffset((Instant) null), OFFSET_PONE);
        assertEquals(test.getOffset((LocalDateTime) null), OFFSET_PONE);
        assertEquals(test.getValidOffsets(null).size(), 1);
        assertEquals(test.getValidOffsets(null).get(0), OFFSET_PONE);
        assertEquals(test.getTransition(null), null);
        assertEquals(test.getStandardOffset(null), OFFSET_PONE);
        assertEquals(test.getDaylightSavings(null), Duration.ZERO);
        assertEquals(test.isDaylightSavings(null), false);
        assertEquals(test.nextTransition(null), null);
        assertEquals(test.previousTransition(null), null);
    }

}
