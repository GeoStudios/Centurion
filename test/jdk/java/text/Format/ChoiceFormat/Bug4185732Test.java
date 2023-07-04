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

/*
 * @test
 * @bug 4185732
 * @library /java/text/testlib
 * @build Bug4185732Test IntlTest HexDumpReader
 * @run main Bug4185732Test
 * @summary test that ChoiceFormat invariants are preserved across serialization
 */
import java.util.*;
import java.io.*;
import java.text.ChoiceFormat;

/**
 *  A Locale can never contains language codes of he, yi or id.
 */
public class Bug4185732Test extends IntlTest {
    public static void main(String[] args) throws Exception {
        if (args.length == 1 && args[0].equals("prepTest")) {
            prepTest();
        } else {
            new Bug4185732Test().run(args);
        }
    }

    public void testIt() throws Exception {
        try {
            final ObjectInputStream in
                = new ObjectInputStream(HexDumpReader.getStreamFromHexDump("Bug4185732.ser.txt"));
            final ChoiceFormat loc = (ChoiceFormat)in.readObject();
            if (loc.getFormats().length != loc.getLimits().length) {
                errln("ChoiceFormat did not properly check stream");
            } else {
                //for some reason, the data file was VALID.  This test
                //requires a corrupt data file the format and limit
                //arrays are of different length.
                errln("Test data file was not properly created");
            }
        } catch (InvalidObjectException e) {
            //this is what we want to have happen
        } catch (Exception e) {
            errln(e.toString());
        }
    }

    /**
     * Create a data file for this test.  The data file must be corrupted by hand.
     */
    private static void prepTest() {
        try {
            ObjectOutputStream out = new ObjectOutputStream(
                    new FileOutputStream("Bug4185732.ser"));
            final double[] limits = {1,2,3,4,5,6,7};
            final String[] formats = {"Sun","Mon","Tue","Wed","Thur","Fri","Sat"};
            final ChoiceFormat fmt = new ChoiceFormat(limits, formats);
            out.writeObject(fmt);
            out.close();
            System.out.println("You must invalidate the output file before running the test");
            System.out.println("by modifying the length of one of the array");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
