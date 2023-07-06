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

package jdk.jfr.share.classes.jdk.jfr.internal.jfc.model;

import java.util.Map;
import java.util.TreeMap;

public final class SettingsLog {
    private static final Map<String, String> settings = new TreeMap<>();
    private static boolean enabled;

    public static void enable() {
        enabled = true;
    }

    public static void flush() {
        if (!settings.isEmpty()) {
            System.out.println();
            System.out.println("Setting:");
            for (var s : settings.entrySet()) {
                System.out.println("\"" + s.getKey() + "=" + s.getValue() + "\"");
            }
            settings.clear();
        }
    }

    static void log(XmlSetting setting, String value) {
        if (enabled) {
            settings.put(setting.getFullName(), value);
        }
    }
}