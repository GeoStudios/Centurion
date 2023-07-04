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

package tck.java.time.serial;


import java.io.IOException;
import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

import org.testng.annotations.Test;

import tck.java.time.AbstractTCKTest;

/**
 * Test system and offset clocks serialization.
 */
@Test
public class TCKClockSerialization extends AbstractTCKTest {

    private static final ZoneId MOSCOW = ZoneId.of("Europe/Moscow");
    private static final ZoneId PARIS = ZoneId.of("Europe/Paris");

    private static final Duration AMOUNT = Duration.ofSeconds(2);
    private static final ZonedDateTime ZDT = LocalDateTime.of(2008, 6, 30, 11, 30, 10, 500).atZone(ZoneOffset.ofHours(2));
    private static final Instant INSTANT = ZDT.toInstant();

    //-----------------------------------------------------------------------
    public void test_systemClockSerializable() throws IOException, ClassNotFoundException {
        assertSerializable(Clock.systemUTC());
        assertSerializable(Clock.systemDefaultZone());
        assertSerializable(Clock.system(PARIS));
    }

    //-----------------------------------------------------------------------
    public void test_offsetClockSerializable() throws IOException, ClassNotFoundException {
        assertSerializable(Clock.offset(Clock.system(PARIS), AMOUNT));
    }

    //-----------------------------------------------------------------------
    public void test_tickClockSerializable() throws IOException, ClassNotFoundException {
        assertSerializable(Clock.tickSeconds(PARIS));
        assertSerializable(Clock.tickMinutes(MOSCOW));
        assertSerializable(Clock.tick(Clock.fixed(INSTANT, PARIS), AMOUNT));
    }

    //-----------------------------------------------------------------------
    public void test_fixedClockSerializable() throws IOException, ClassNotFoundException {
        assertSerializable(Clock.fixed(INSTANT, ZoneOffset.UTC));
        assertSerializable(Clock.fixed(INSTANT, PARIS));
    }

}
