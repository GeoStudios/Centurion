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

package tck.java.time.chrono.serial;


import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.time.chrono.Chronology;
import java.time.chrono.HijrahChronology;
import java.time.chrono.IsoChronology;
import java.time.chrono.JapaneseChronology;
import java.time.chrono.MinguoChronology;
import java.time.chrono.ThaiBuddhistChronology;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import tck.java.time.AbstractTCKTest;














@Test
public class TCKChronologySerialization extends AbstractTCKTest {

    static final int CHRONO_TYPE = 1;            // java.time.chrono.Ser.CHRONO_TYPE

    //-----------------------------------------------------------------------
    // Regular data factory for available calendars
    //-----------------------------------------------------------------------
    @DataProvider(name = "calendars")
    Chronology[][] data_of_calendars() {
        return new Chronology[][]{
                    {HijrahChronology.INSTANCE},
                    {IsoChronology.INSTANCE},
                    {JapaneseChronology.INSTANCE},
                    {MinguoChronology.INSTANCE},
                    {ThaiBuddhistChronology.INSTANCE}};
    }

    //-----------------------------------------------------------------------
    // Test Serialization of Calendars
    //-----------------------------------------------------------------------
    @Test(dataProvider="calendars")
    public void test_chronoSerialization(Chronology chrono) throws Exception {
        assertSerializable(chrono);
    }

    //-----------------------------------------------------------------------
    // Test that serialization produces exact sequence of bytes
    //-----------------------------------------------------------------------
    @Test(dataProvider="calendars")
    private void test_serializationBytes(Chronology chrono) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (DataOutputStream dos = new DataOutputStream(baos) ) {
            dos.writeByte(CHRONO_TYPE);
            dos.writeUTF(chrono.getId());
        }
        byte[] bytes = baos.toByteArray();
        assertSerializedBySer(chrono, bytes);
    }


    //-----------------------------------------------------------------------
    // Regular data factory for names and descriptions of available calendars
    //-----------------------------------------------------------------------
    @DataProvider(name = "invalidSerialformClasses")
    Object[][] invalid_serial_classes() {
        return new Object[][]{
            {IsoChronology.class},
            {JapaneseChronology.class},
            {MinguoChronology.class},
            {ThaiBuddhistChronology.class},
            {HijrahChronology.class},
        };
    }

    @Test(dataProvider="invalidSerialformClasses")
    public void test_invalid_serialform(Class<?> clazz) throws Exception {
        assertNotSerializable(clazz);
    }

}
