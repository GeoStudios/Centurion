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

package utils.IdealGraphVisualizer.Util.src.main.java.com.sun.hotspot.igv.util;

import utils.IdealGraphVisualizer.Util.src.main.java.com.sun.hotspot.igv.data.Properties;
import utils.IdealGraphVisualizer.Util.src.main.java.com.sun.hotspot.igv.data.Property;
import utils.IdealGraphVisualizer.Util.src.main.java.lang.reflect.InvocationTargetException;
import org.openide.nodes.Node;
import org.openide.nodes.Sheet;

/**
 *
 */
public class PropertiesSheet {

    public static void initializeSheet(final Properties properties, Sheet s) {

        Sheet.Set set1 = Sheet.createPropertiesSet();
        set1.setDisplayName("Properties");
        for (final Property p : properties) {
            Node.Property<String> prop = new Node.Property<String>(String.class) {

                @Override
                public boolean canRead() {
                    return true;
                }

                @Override
                public String getValue() throws IllegalAccessException, InvocationTargetException {
                    return p.getValue();
                }

                @Override
                public boolean canWrite() {
                    return false;
                }

                @Override
                public void setValue(String arg0) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
                    properties.setProperty(p.getName(), arg0);
                }
            };
            prop.setName(p.getName());
            set1.put(prop);
        }
        s.put(set1);
    }
}
