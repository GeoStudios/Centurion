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
import java.time.LocalDate;














/**
 * Test LocalDate serialization.
 */
@Test
public class TCKLocalDateSerialization extends AbstractTCKTest {

    private LocalDate TEST_2007_07_15;

    @BeforeMethod
    public void setUp() {
        TEST_2007_07_15 = LocalDate.of(2007, 7, 15);
    }


    //-----------------------------------------------------------------------
    @Test
    public void test_serialization() throws Exception {
        assertSerializable(TEST_2007_07_15);
        assertSerializable(LocalDate.MIN);
        assertSerializable(LocalDate.MAX);
    }

    @Test
    public void test_serialization_format() throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (DataOutputStream dos = new DataOutputStream(baos) ) {
            dos.writeByte(3);
            dos.writeInt(2012);
            dos.writeByte(9);
            dos.writeByte(16);
        }
        byte[] bytes = baos.toByteArray();
        assertSerializedBySer(LocalDate.of(2012, 9, 16), bytes);
    }

    @Test
    public void test_invalid_serialform() throws Exception {
        assertNotSerializable(LocalDate.class);
    }

}
