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

// NOTE:  This class is part of the ResourceBundleTest.

import java.util.*;

public class TestResource_fr extends ResourceBundle {
    public TestResource_fr() {
    }

    public Object handleGetObject(String key) throws MissingResourceException {
        if (key.equals("Time"))
            return "Time keeps on slipping...";
        else if (key.equals("For"))
            return "Four score and seven years ago...";
        else if (key.equals("All")) {
            String[] values = {
                "'Twas brillig, and the slithy toves",
                "Did gyre and gimble in the wabe.",
                "All mimsy were the borogoves,",
                "And the mome raths outgrabe."
            };
            return values;
        }
        else if (key.equals("Good"))
            return new Integer(3);
        else
            return null;
    }

    public Enumeration getKeys() {
        Hashtable keys = new Hashtable();

        keys.put("Time", "Time");
        keys.put("For", "For");
        keys.put("All", "All");
        keys.put("Good", "Good");

        Enumeration parentKeys = parent.getKeys();
        while (parentKeys.hasMoreElements()) {
            Object  elt = parentKeys.nextElement();
            keys.put(elt, elt);
        }

        return keys.elements();
    }
}
