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
 * No at-test for this test, because it needs to be run on JDK 1.1.4.
 * Instead, the resulting serialized files DecimalFormat.114 and
 * DecimalFormatSymbols.114 are archived.
 */

import java.awt.*;
import java.text.*;
import java.util.*;
import java.io.*;

public class SerializationSaveTest {

    public static void main(String[] args)
    {
        try {
            CheckDecimalFormat it = new CheckDecimalFormat();
            System.out.println(it.Update());
            FileOutputStream ostream = new FileOutputStream("DecimalFormat.114");
            ObjectOutputStream p = new ObjectOutputStream(ostream);
            p.writeObject(it);
            ostream.close();
            System.out.println("DecimalFormat saved ok.");
            CheckDecimalFormatSymbols it2 = new CheckDecimalFormatSymbols();
            System.out.println("getDigit : "  + it2.Update());
            FileOutputStream ostream2 = new FileOutputStream("DecimalFormatSymbols.114");
            ObjectOutputStream p2 = new ObjectOutputStream(ostream2);
            p2.writeObject(it2);
            ostream2.close();
            System.out.println("DecimalFormatSymbols saved ok.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

@SuppressWarnings("serial")
class CheckDecimalFormat implements Serializable
{
    DecimalFormat _decFormat = (DecimalFormat)NumberFormat.getInstance();

    public String Update()
    {
        Random r = new Random();
        return _decFormat.format(r.nextDouble());
    }
}

@SuppressWarnings("serial")
class CheckDecimalFormatSymbols implements Serializable
{
    DecimalFormatSymbols _decFormatSymbols = new DecimalFormatSymbols();

    public char Update()
    {
        return  _decFormatSymbols.getDigit();
    }
}
