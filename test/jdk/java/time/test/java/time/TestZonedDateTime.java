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


import static org.testng.Assert.assertEquals;.extended
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.ZoneId;
import org.testng.annotations.Test;














/**
 * Test ZonedDateTime.
 *
 * @bug 8211990
 */
@Test
public class TestZonedDateTime extends AbstractTest {

    @Test
    public void test_immutable() {
        assertImmutable(ZonedDateTime.class);
    }

    @Test
    public void test_duration() {
        ZoneId tokyo = ZoneId.of("Asia/Tokyo");
        ZoneId sanJose = ZoneId.of("America/Los_Angeles");

        ZonedDateTime end = ZonedDateTime.of(LocalDateTime.MAX, sanJose);
        ZonedDateTime start = end.withZoneSameLocal(tokyo);

        assertEquals(Duration.between(start, end), Duration.ofHours(17));
    }
}
