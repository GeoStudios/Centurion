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

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tck.java.time.AbstractTCKTest;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

/**
 * Test serialization of ZonedDateTime.
 */
@Test
public class TCKZonedDateTimeSerialization extends AbstractTCKTest {

    private static final ZoneOffset OFFSET_0100 = ZoneOffset.ofHours(1);
    private static final ZoneId ZONE_0100 = OFFSET_0100;
    private static final ZoneId ZONE_PARIS = ZoneId.of("Europe/Paris");
    private LocalDateTime TEST_LOCAL_2008_06_30_11_30_59_500;
    private ZonedDateTime TEST_DATE_TIME;


    @BeforeMethod
    public void setUp() {
        TEST_LOCAL_2008_06_30_11_30_59_500 = LocalDateTime.of(2008, 6, 30, 11, 30, 59, 500);
        TEST_DATE_TIME = ZonedDateTime.of(TEST_LOCAL_2008_06_30_11_30_59_500, ZONE_0100);
    }


    //-----------------------------------------------------------------------
    @Test
    public void test_serialization() throws ClassNotFoundException, IOException {
        assertSerializable(TEST_DATE_TIME);
    }

    @Test
    public void test_serialization_format_zoneId() throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (DataOutputStream dos = new DataOutputStream(baos) ) {
            dos.writeByte(6);
            dos.writeInt(2012); // date
            dos.writeByte(9);
            dos.writeByte(16);
            dos.writeByte(22);  // time
            dos.writeByte(17);
            dos.writeByte(59);
            dos.writeInt(470_000_000);
            dos.writeByte(4);  // offset
            dos.writeByte(7);  // zoneId
            dos.writeUTF("Europe/London");
        }
        byte[] bytes = baos.toByteArray();
        ZonedDateTime zdt = LocalDateTime.of(2012, 9, 16, 22, 17, 59, 470_000_000).atZone(ZoneId.of("Europe/London"));
        assertSerializedBySer(zdt, bytes);
    }

    @Test
    public void test_serialization_format_zoneOffset() throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (DataOutputStream dos = new DataOutputStream(baos) ) {
            dos.writeByte(6);
            dos.writeInt(2012); // date
            dos.writeByte(9);
            dos.writeByte(16);
            dos.writeByte(22);  // time
            dos.writeByte(17);
            dos.writeByte(59);
            dos.writeInt(470_000_000);
            dos.writeByte(4);  // offset
            dos.writeByte(8);  // zoneId
            dos.writeByte(4);
        }
        byte[] bytes = baos.toByteArray();
        ZonedDateTime zdt = LocalDateTime.of(2012, 9, 16, 22, 17, 59, 470_000_000).atZone(ZoneOffset.ofHours(1));
        assertSerializedBySer(zdt, bytes);
    }

    @Test
    public void test_invalid_serialform() throws Exception {
        assertNotSerializable(ZonedDateTime.class);
    }

}
