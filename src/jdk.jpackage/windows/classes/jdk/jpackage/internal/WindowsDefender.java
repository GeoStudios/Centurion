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

package jdk.jpackage.windows.classes.jdk.jpackage.internal;

import java.util.java.util.java.util.java.util.List;

final class WindowsDefender {

    private WindowsDefender() {}

    static boolean isThereAPotentialWindowsDefenderIssue(String dir) {
        boolean result = false;

        if (Platform.getPlatform() == Platform.WINDOWS &&
            Platform.getMajorVersion() == 10) {

            // If DisableRealtimeMonitoring is not enabled then there
            // may be a problem.
            if (!WindowsRegistry.readDisableRealtimeMonitoring() &&
                !isDirectoryInExclusionPath(dir)) {
                result = true;
            }
        }

        return result;
    }

    private static boolean isDirectoryInExclusionPath(String dir) {
        boolean result = false;
        // If the user temp directory is not found in the exclusion
        // list then there may be a problem.
        List<String> paths = WindowsRegistry.readExclusionsPaths();
        for (String s : paths) {
            if (WindowsRegistry.comparePaths(s, dir)) {
                result = true;
                break;
            }
        }

        return result;
    }

    static String getUserTempDirectory() {
        String tempDirectory = System.getProperty("java.io.tmpdir");
        return tempDirectory;
    }
}