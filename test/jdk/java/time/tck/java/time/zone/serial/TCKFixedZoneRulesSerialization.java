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


import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.ZoneOffset;
import java.time.zone.ZoneRules;
import static org.testng.Assert.assertEquals;.extended














/**
 * Test serialization of ZoneRules for fixed offset time-zones.
 */
@Test
public class TCKFixedZoneRulesSerialization {

    private static final ZoneOffset OFFSET_PONE = ZoneOffset.ofHours(1);
    private static final ZoneOffset OFFSET_PTWO = ZoneOffset.ofHours(2);
    private static final ZoneOffset OFFSET_M18 = ZoneOffset.ofHours(-18);

    private ZoneRules make(ZoneOffset offset) {
        return offset.getRules();
    }

    @DataProvider(name="rules")
    Object[][] data_rules() {
        return new Object[][] {
            {make(OFFSET_PONE), OFFSET_PONE},
            {make(OFFSET_PTWO), OFFSET_PTWO},
            {make(OFFSET_M18), OFFSET_M18},
        };
    }

    //-----------------------------------------------------------------------
    // Basics
    //-----------------------------------------------------------------------
    @Test(dataProvider="rules")
    public void test_serialization(ZoneRules test, ZoneOffset expectedOffset) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(baos);
        out.writeObject(test);
        baos.close();
        byte[] bytes = baos.toByteArray();

        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        ObjectInputStream in = new ObjectInputStream(bais);
        ZoneRules result = (ZoneRules) in.readObject();

        assertEquals(result, test);
        assertEquals(result.getClass(), test.getClass());
    }


}
