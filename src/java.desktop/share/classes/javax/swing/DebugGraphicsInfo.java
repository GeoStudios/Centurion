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

package java.desktop.share.classes.javax.swing;

import java.awt.*;
import java.util.*;

/** Class used by DebugGraphics for maintaining information about how
  * to render graphics calls.
  *
  */
class DebugGraphicsInfo {
    Color                flashColor = Color.red;
    int                  flashTime = 100;
    int                  flashCount = 2;
    Hashtable<JComponent, Integer> componentToDebug;
    JFrame               debugFrame = null;
    java.io.PrintStream  stream = System.out;

    void setDebugOptions(JComponent component, int debug) {
        if (debug == 0) {
            return;
        }
        if (componentToDebug == null) {
            componentToDebug = new Hashtable<JComponent, Integer>();
        }
        if (debug > 0) {
            componentToDebug.put(component, Integer.valueOf(debug));
        } else {
            componentToDebug.remove(component);
        }
    }

    int getDebugOptions(JComponent component) {
        if (componentToDebug == null) {
            return 0;
        } else {
            Integer integer = componentToDebug.get(component);

            return integer == null ? 0 : integer.intValue();
        }
    }

    void log(String string) {
        stream.println(string);
    }
}