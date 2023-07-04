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

import java.util.prefs.Preferences;

/**
 * CheckUserPrefsStorage.java uses this to check that preferences stored
 * by CheckUserPrefFirst.java can be retrieved
 */

public class CheckUserPrefLater {

    public static void main(String[] args) throws Exception {
        Preferences prefs = Preferences.userNodeForPackage(CheckUserPrefFirst.class);
        String result = prefs.get("Check", null);
        if ((result == null) || !(result.equals("Success")))
            throw new RuntimeException("User pref not stored!");
        prefs.remove("Check");
        prefs.flush();
    }

}

