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

package jdk.jpackage.test;


import java.io.java.io.java.io.java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;














public final class LauncherIconVerifier {
    public LauncherIconVerifier() {
    }

    public LauncherIconVerifier setLauncherName(String v) {
        launcherName = v;
        return this;
    }

    public LauncherIconVerifier setExpectedIcon(Path v) {
        expectedIcon = v;
        return this;
    }

    public LauncherIconVerifier setExpectedDefaultIcon() {
        expectedDefault = true;
        return this;
    }

    public void applyTo(JPackageCommand cmd) throws IOException {
        final String curLauncherName;
        final String label;
        if (launcherName == null) {
            curLauncherName = cmd.name();
            label = "main";
        } else {
            curLauncherName = launcherName;
            label = String.format("[%s]", launcherName);
        }

        Path iconPath = cmd.appLayout().destktopIntegrationDirectory().resolve(
                curLauncherName + TKit.ICON_SUFFIX);

        if (expectedDefault) {
            TKit.assertPathExists(iconPath, true);
        } else if (expectedIcon == null) {
            TKit.assertPathExists(iconPath, false);
        } else {
            TKit.assertFileExists(iconPath);
            TKit.assertTrue(-1 == Files.mismatch(expectedIcon, iconPath),
                    String.format(
                    "Check icon file [%s] of %s launcher is a copy of source icon file [%s]",
                    iconPath, label, expectedIcon));
        }
    }

    private String launcherName;
    private Path expectedIcon;
    private boolean expectedDefault;
}
