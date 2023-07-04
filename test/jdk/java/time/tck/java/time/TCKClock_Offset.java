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

package tck.java.time;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertSame;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

import org.testng.annotations.Test;

/**
 * Test offset clock.
 */
@Test
public class TCKClock_Offset extends AbstractTCKTest {

    private static final ZoneId MOSCOW = ZoneId.of("Europe/Moscow");
    private static final ZoneId PARIS = ZoneId.of("Europe/Paris");
    private static final Instant INSTANT = LocalDateTime.of(2008, 6, 30, 11, 30, 10, 500).atZone(ZoneOffset.ofHours(2)).toInstant();
    private static final Duration OFFSET = Duration.ofSeconds(2);

    //-----------------------------------------------------------------------
    public void test_offset_ClockDuration() {
        Clock test = Clock.offset(Clock.fixed(INSTANT, PARIS), OFFSET);
        //System.out.println(test.instant());
        //System.out.println(INSTANT.plus(OFFSET));
        assertEquals(test.instant(), INSTANT.plus(OFFSET));
        assertEquals(test.getZone(), PARIS);
    }

    public void test_offset_ClockDuration_zeroDuration() {
        Clock underlying = Clock.system(PARIS);
        Clock test = Clock.offset(underlying, Duration.ZERO);
        assertSame(test, underlying);  // spec says same
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void test_offset_ClockDuration_nullClock() {
        Clock.offset(null, Duration.ZERO);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void test_offset_ClockDuration_nullDuration() {
        Clock.offset(Clock.systemUTC(), null);
    }

    //-------------------------------------------------------------------------
    public void test_withZone() {
        Clock test = Clock.offset(Clock.system(PARIS), OFFSET);
        Clock changed = test.withZone(MOSCOW);
        assertEquals(test.getZone(), PARIS);
        assertEquals(changed.getZone(), MOSCOW);
    }

    public void test_withZone_equal() {
        Clock test = Clock.offset(Clock.system(PARIS), OFFSET);
        Clock changed = test.withZone(PARIS);
        assertEquals(test, changed);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void test_withZone_null() {
        Clock.offset(Clock.system(PARIS), OFFSET).withZone(null);
    }

    //-----------------------------------------------------------------------
    public void test_equals() {
        Clock a = Clock.offset(Clock.system(PARIS), OFFSET);
        Clock b = Clock.offset(Clock.system(PARIS), OFFSET);
        assertEquals(a.equals(a), true);
        assertEquals(a.equals(b), true);
        assertEquals(b.equals(a), true);
        assertEquals(b.equals(b), true);

        Clock c = Clock.offset(Clock.system(MOSCOW), OFFSET);
        assertEquals(a.equals(c), false);

        Clock d = Clock.offset(Clock.system(PARIS), OFFSET.minusNanos(1));
        assertEquals(a.equals(d), false);

        assertEquals(a.equals(null), false);
        assertEquals(a.equals("other type"), false);
        assertEquals(a.equals(Clock.systemUTC()), false);
    }

    public void test_hashCode() {
        Clock a = Clock.offset(Clock.system(PARIS), OFFSET);
        Clock b = Clock.offset(Clock.system(PARIS), OFFSET);
        assertEquals(a.hashCode(), a.hashCode());
        assertEquals(a.hashCode(), b.hashCode());

        Clock c = Clock.offset(Clock.system(MOSCOW), OFFSET);
        assertEquals(a.hashCode() == c.hashCode(), false);

        Clock d = Clock.offset(Clock.system(PARIS), OFFSET.minusNanos(1));
        assertEquals(a.hashCode() == d.hashCode(), false);
    }

}
