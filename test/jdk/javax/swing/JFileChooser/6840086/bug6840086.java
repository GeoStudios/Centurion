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

/* @test
   @bug 6840086
   @summary JFileChooser lacks icons on top right when running on Windows 7
   @author Pavel Porvatov
   @library /test/lib
   @modules java.desktop/sun.awt.shell
   @build jdk.test.lib.Platform
   @run main bug6840086
*/

import jdk.test.lib.Platform;
import sun.awt.shell.ShellFolder;

import java.awt.*;

public class bug6840086 {
    private static final String[] KEYS = {
        "fileChooserIcon ListView",
        "fileChooserIcon ViewMenu",
        "fileChooserIcon DetailsView",
        "fileChooserIcon UpFolder",
        "fileChooserIcon NewFolder",
    };

    public static void main(String[] args) {
        if (!Platform.isWindows()) {
            System.out.println("The test was skipped because it is sensible only for Windows.");

            return;
        }

        for (String key : KEYS) {
            Image image = (Image) ShellFolder.get(key);

            if (image == null) {
                throw new RuntimeException("The image '" + key + "' not found.");
            }

            if (image != ShellFolder.get(key)) {
                throw new RuntimeException("The image '" + key + "' is not cached.");
            }
        }

        System.out.println("The test passed.");
    }
}
