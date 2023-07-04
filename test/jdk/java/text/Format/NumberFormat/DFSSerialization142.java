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
 * No at-test for this test, because it needs to be run on JDK 1.4.2
 * Instead, the resulting serialized file
 * DecimalFormatSymbols.142 is archived.
 */

import java.awt.*;
import java.text.*;
import java.util.*;
import java.io.*;

public class DFSSerialization142 {

    public static void main(String[] args)
    {
        try {

            DecimalFormatSymbols dfs= new DecimalFormatSymbols();
            System.out.println("Default currency symbol in the default locale : "  + dfs.getCurrencySymbol());
            dfs.setCurrencySymbol("*SpecialCurrencySymbol*");
            System.out.println("The special currency symbol is set : "  + dfs.getCurrencySymbol());
            FileOutputStream ostream = new FileOutputStream("DecimalFormatSymbols.142");
            ObjectOutputStream p = new ObjectOutputStream(ostream);
            p.writeObject(dfs);
            ostream.close();
            System.out.println("DecimalFormatSymbols saved ok.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
