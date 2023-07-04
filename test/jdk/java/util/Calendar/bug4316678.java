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

import java.io.*;
import java.util.*;
import java.text.*;

/**
 * @test
 * @bug 4316678
 * @summary test that Calendar's Serializasion works correctly.
 * @library /java/text/testlib
 */
public class bug4316678 extends IntlTest {

    public static void main(String[] args) throws Exception {
        new bug4316678().run(args);
    }

    public void Test4316678() throws Exception {
        GregorianCalendar gc1;
        GregorianCalendar gc2;
        TimeZone saveZone = TimeZone.getDefault();

        try {
            TimeZone.setDefault(TimeZone.getTimeZone("PST"));

            gc1 = new GregorianCalendar(2000, Calendar.OCTOBER, 10);
            try (ObjectOutputStream out
                    = new ObjectOutputStream(new FileOutputStream("bug4316678.ser"))) {
                out.writeObject(gc1);
            }

            try (ObjectInputStream in
                    = new ObjectInputStream(new FileInputStream("bug4316678.ser"))) {
                gc2 = (GregorianCalendar)in.readObject();
            }

            gc1.set(Calendar.DATE, 16);
            gc2.set(Calendar.DATE, 16);
            if (!gc1.getTime().equals(gc2.getTime())) {
                errln("Invalid Time :" + gc2.getTime() +
                    ", expected :" + gc1.getTime());
            }
        } finally {
            TimeZone.setDefault(saveZone);
        }
    }
}
