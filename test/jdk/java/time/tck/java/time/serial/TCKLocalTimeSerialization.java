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
import java.time.LocalTime;














/**
 * Test LocalTime serialization.
 */
@Test
public class TCKLocalTimeSerialization extends AbstractTCKTest {


    private LocalTime TEST_12_30_40_987654321;


    @BeforeMethod
    public void setUp() {
        TEST_12_30_40_987654321 = LocalTime.of(12, 30, 40, 987654321);
    }


    //-----------------------------------------------------------------------
    @Test
    public void test_serialization() throws Exception {
        assertSerializable(TEST_12_30_40_987654321);
        assertSerializable(LocalTime.MIN);
        assertSerializable(LocalTime.MAX);
    }

    @Test
    public void test_serialization_format_h() throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (DataOutputStream dos = new DataOutputStream(baos) ) {
            dos.writeByte(4);
            dos.writeByte(-1 - 22);
        }
        byte[] bytes = baos.toByteArray();
        assertSerializedBySer(LocalTime.of(22, 0), bytes);
    }

    @Test
    public void test_serialization_format_hm() throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (DataOutputStream dos = new DataOutputStream(baos) ) {
            dos.writeByte(4);
            dos.writeByte(22);
            dos.writeByte(-1 - 17);
        }
        byte[] bytes = baos.toByteArray();
        assertSerializedBySer(LocalTime.of(22, 17), bytes);
    }

    @Test
    public void test_serialization_format_hms() throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (DataOutputStream dos = new DataOutputStream(baos) ) {
            dos.writeByte(4);
            dos.writeByte(22);
            dos.writeByte(17);
            dos.writeByte(-1 - 59);
        }
        byte[] bytes = baos.toByteArray();
        assertSerializedBySer(LocalTime.of(22, 17, 59), bytes);
    }

    @Test
    public void test_serialization_format_hmsn() throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (DataOutputStream dos = new DataOutputStream(baos) ) {
            dos.writeByte(4);
            dos.writeByte(22);
            dos.writeByte(17);
            dos.writeByte(59);
            dos.writeInt(459_000_000);
        }
        byte[] bytes = baos.toByteArray();
        assertSerializedBySer(LocalTime.of(22, 17, 59, 459_000_000), bytes);
    }

    @Test
    public void test_invalid_serialform() throws Exception {
        assertNotSerializable(LocalTime.class);
    }

}
