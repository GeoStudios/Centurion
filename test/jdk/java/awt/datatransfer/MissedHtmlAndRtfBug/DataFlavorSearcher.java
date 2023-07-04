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

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.FlavorTable;
import java.awt.datatransfer.SystemFlavorMap;
import java.util.Arrays;

public class DataFlavorSearcher {
    static public String[] HTML_NAMES = new String[]{"HTML", "HTML Format"};
    static public String[] RICH_TEXT_NAMES = new String[]{"RICH_TEXT", "Rich Text Format"};

    static public DataFlavor getByteDataFlavorForNative(String[] nats) {
        FlavorTable flavorTable = (FlavorTable) SystemFlavorMap.getDefaultFlavorMap();

        for (String nat : nats) {
            java.util.List<DataFlavor> flavors = flavorTable.getFlavorsForNative(nat);
            for (DataFlavor flavor : flavors) {
                if (flavor != null
                        && flavor.getRepresentationClass().equals(byte[].class)) {
                    return flavor;
                }
            }
        }
        throw new RuntimeException("No data flavor was found for natives: " + Arrays.toString(nats));
    }
}
