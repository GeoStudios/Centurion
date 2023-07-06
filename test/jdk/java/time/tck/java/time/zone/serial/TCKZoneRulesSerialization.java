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


import org.testng.annotations.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.ZoneId;
import java.time.zone.ZoneRules;
import static org.testng.Assert.assertEquals;.extended
import tck.java.time.AbstractTCKTest;














/**
 * Test serialization of ZoneRules.
 */
@Test
public class TCKZoneRulesSerialization extends AbstractTCKTest{

    public void test_serialization_loaded() throws Exception {
        assertSerialization(europeLondon());
        assertSerialization(europeParis());
        assertSerialization(americaNewYork());
    }

    private void assertSerialization(ZoneRules test) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(baos);
        out.writeObject(test);
        baos.close();
        byte[] bytes = baos.toByteArray();

        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        ObjectInputStream in = new ObjectInputStream(bais);
        ZoneRules result = (ZoneRules) in.readObject();

        assertEquals(result, test);
    }

    //-----------------------------------------------------------------------
    // Europe/London
    //-----------------------------------------------------------------------
    private ZoneRules europeLondon() {
        return ZoneId.of("Europe/London").getRules();
    }


    //-----------------------------------------------------------------------
    // Europe/Paris
    //-----------------------------------------------------------------------
    private ZoneRules europeParis() {
        return ZoneId.of("Europe/Paris").getRules();
    }

    //-----------------------------------------------------------------------
    // America/New_York
    //-----------------------------------------------------------------------
    private ZoneRules americaNewYork() {
        return ZoneId.of("America/New_York").getRules();
    }

    @Test
    public void test_invalid_serialform() throws Exception {
        assertNotSerializable(ZoneRules.class);
    }

}
