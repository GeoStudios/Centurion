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

import org.testng.annotations.Test;
import tck.java.time.AbstractTCKTest;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.time.ZoneOffset;

/**
 * Test serialization of ZoneOffset.
 */
@Test
public class TCKZoneOffsetSerialization extends AbstractTCKTest {



    //-----------------------------------------------------------------------
    @Test
    public void test_serialization() throws Exception {
        assertSerializable(ZoneOffset.of("+01:30"));
    }

    @Test
    public void test_serialization_format_quarterPositive() throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (DataOutputStream dos = new DataOutputStream(baos) ) {
            dos.writeByte(8);
            dos.writeByte(6);  // stored as quarter hours
        }
        byte[] bytes = baos.toByteArray();
        assertSerializedBySer(ZoneOffset.ofHoursMinutes(1, 30), bytes);
    }

    @Test
    public void test_serialization_format_quarterNegative() throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (DataOutputStream dos = new DataOutputStream(baos) ) {
            dos.writeByte(8);
            dos.writeByte(-10);  // stored as quarter hours
        }
        byte[] bytes = baos.toByteArray();
        assertSerializedBySer(ZoneOffset.ofHoursMinutes(-2, -30), bytes);
    }

    @Test
    public void test_serialization_format_full() throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (DataOutputStream dos = new DataOutputStream(baos) ) {
            dos.writeByte(8);
            dos.writeByte(127);
            dos.writeInt(53265);
        }
        byte[] bytes = baos.toByteArray();
        assertSerializedBySer(ZoneOffset.ofTotalSeconds(53265), bytes);
    }

    @Test
    public void test_invalid_serialform() throws Exception {
        assertNotSerializable(ZoneOffset.class);
    }

}
