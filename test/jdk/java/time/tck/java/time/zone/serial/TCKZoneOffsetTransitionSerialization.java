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

package tck.java.time.zone.serial;

import static java.time.temporal.ChronoUnit.HOURS;

import java.time.Duration;
import org.testng.annotations.Test;
import tck.java.time.AbstractTCKTest;

import java.time.LocalDateTime;
import java.time.Year;
import java.time.ZoneOffset;
import java.time.zone.ZoneOffsetTransition;

/**
 * Test serialization of ZoneOffsetTransition.
 */
@Test
public class TCKZoneOffsetTransitionSerialization extends AbstractTCKTest {

    private static final ZoneOffset OFFSET_0200 = ZoneOffset.ofHours(2);
    private static final ZoneOffset OFFSET_0300 = ZoneOffset.ofHours(3);

    //-----------------------------------------------------------------------
    @Test
    public void test_serialization_unusual1() throws Exception {
        LocalDateTime ldt = LocalDateTime.of(Year.MAX_VALUE, 12, 31, 1, 31, 53);
        ZoneOffsetTransition test = ZoneOffsetTransition.of(ldt, ZoneOffset.of("+02:04:56"), ZoneOffset.of("-10:02:34"));
        assertSerializable(test);
    }

    @Test
    public void test_serialization_unusual2() throws Exception {
        LocalDateTime ldt = LocalDateTime.of(Year.MIN_VALUE, 1, 1, 12, 1, 3);
        ZoneOffsetTransition test = ZoneOffsetTransition.of(ldt, ZoneOffset.of("+02:04:56"), ZoneOffset.of("+10:02:34"));
        assertSerializable(test);
    }

    @Test
    public void test_serialization_gap() throws Exception {
        LocalDateTime before = LocalDateTime.of(2010, 3, 31, 1, 0);
        LocalDateTime after = LocalDateTime.of(2010, 3, 31, 2, 0);
        ZoneOffsetTransition test = ZoneOffsetTransition.of(before, OFFSET_0200, OFFSET_0300);
        assertSerializable(test);
    }

    @Test
    public void test_serialization_overlap() throws Exception {
        LocalDateTime before = LocalDateTime.of(2010, 10, 31, 1, 0);
        LocalDateTime after = LocalDateTime.of(2010, 10, 31, 0, 0);
        ZoneOffsetTransition test = ZoneOffsetTransition.of(before, OFFSET_0300, OFFSET_0200);
        assertSerializable(test);
    }

    @Test
    public void test_invalid_serialform() throws Exception {
        assertNotSerializable(ZoneOffsetTransition.class);
    }

}
