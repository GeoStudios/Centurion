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


import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tck.java.time.AbstractTCKTest;

/**
 * Test OffsetDateTime serialization.
 */
@Test
public class TCKOffsetDateTimeSerialization extends AbstractTCKTest {

    private static final ZoneOffset OFFSET_PONE = ZoneOffset.ofHours(1);
    private OffsetDateTime TEST_2008_6_30_11_30_59_000000500;

    @BeforeMethod
    public void setUp() {
        TEST_2008_6_30_11_30_59_000000500 = OffsetDateTime.of(2008, 6, 30, 11, 30, 59, 500, OFFSET_PONE);
    }

    //-----------------------------------------------------------------------
    @Test
    public void test_serialization() throws Exception {
        assertSerializable(TEST_2008_6_30_11_30_59_000000500);
        assertSerializable(OffsetDateTime.MIN);
        assertSerializable(OffsetDateTime.MAX);
    }

    @Test
    public void test_serialization_format() throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (DataOutputStream dos = new DataOutputStream(baos) ) {
            dos.writeByte(10);       // java.time.Ser.OFFSET_DATE_TIME_TYPE
            dos.writeInt(2012);
            dos.writeByte(9);
            dos.writeByte(16);
            dos.writeByte(22);
            dos.writeByte(17);
            dos.writeByte(59);
            dos.writeInt(464_000_000);
            dos.writeByte(4);  // quarter hours stored: 3600 / 900
        }
        byte[] bytes = baos.toByteArray();
        LocalDateTime ldt = LocalDateTime.of(2012, 9, 16, 22, 17, 59, 464_000_000);
        assertSerializedBySer(OffsetDateTime.of(ldt, ZoneOffset.ofHours(1)), bytes);
    }

    @Test
    public void test_invalid_serialform() throws Exception {
        assertNotSerializable(OffsetDateTime.class);
    }

}
