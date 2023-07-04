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
    @test
    @summary test that locale invariants are preserved across serialization
    @library /java/text/testlib
    @run main Bug4184873Test
    @bug 4184873
*/
import java.util.*;
import java.io.*;

/**
 *  A Locale can never contain the following language codes: he, yi or id.
 */
public class Bug4184873Test extends IntlTest {
    public static void main(String[] args) throws Exception {
        if (args.length == 1 && args[0].equals("prepTest")) {
            prepTest();
        } else {
            new Bug4184873Test().run(args);
        }
    }

    public void testIt() throws Exception {
        verify("he");
        verify("yi");
        verify("id");
    }

    private void verify(String lang) {
        try {
            ObjectInputStream in = getStream(lang);
            if (in != null) {
                final Locale loc = (Locale)in.readObject();
                final Locale expected = new Locale(lang, "XX");
                if (!(expected.equals(loc))) {
                    errln("Locale didn't maintain invariants for: "+lang);
                    errln("         got: "+loc);
                    errln("    excpeted: "+expected);
                } else {
                    logln("Locale "+lang+" worked");
                }
                in.close();
            }
        } catch (Exception e) {
            errln(e.toString());
        }
    }

    private ObjectInputStream getStream(String lang) {
        try {
            final File f = new File(System.getProperty("test.src", "."), "Bug4184873_"+lang);
            return new ObjectInputStream(new FileInputStream(f));
        } catch (Exception e) {
            errln(e.toString());
            return null;
        }
    }

    /**
     * Create serialized output files of the test locales.  After they are created, these test
     * files should be corrupted (by hand) to contain invalid locale name values.
     */
    private static void prepTest() {
        outputLocale("he");
        outputLocale("yi");
        outputLocale("id");
    }

    private static void outputLocale(String lang) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(
                    new FileOutputStream("Bug4184873_"+lang));
            out.writeObject(new Locale(lang, "XX"));
            out.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
