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
package com.sun.hotspot.igv.settings;

import java.util.prefs.Preferences;

/**
 *
 */
public class Settings {

    public final static String NODE_TEXT = "nodeText";
    public final static String NODE_TEXT_DEFAULT = "[idx] [name]";
    public final static String NODE_SHORT_TEXT = "nodeShortText";
    public final static String NODE_SHORT_TEXT_DEFAULT = "[idx] [name]";
    public final static String NODE_WIDTH = "nodeWidth";
    public final static String NODE_WIDTH_DEFAULT = "100";
    public final static String PORT = "port";
    public final static String PORT_BINARY = "portBinary";
    public final static String PORT_DEFAULT = "4444";
    public final static String PORT_BINARY_DEFAULT = "4445";
    public final static String DIRECTORY = "directory";
    public final static String DIRECTORY_DEFAULT = System.getProperty("user.dir");

    public static Preferences get() {
        return Preferences.userNodeForPackage(Settings.class);
    }
}
