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

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;

/**
 * @test
 * @bug 7131400
 * @key headful
 * @summary IOException should be thrown if folder is passed to print()/edit()
 */
public final class EditPrintFolder {

    public static void main(String[] args) throws IOException {
        if (!Desktop.isDesktopSupported()) {
            return;
        }

        File file = FileSystems.getDefault().getPath(".").toFile();
        if (file.isDirectory()) {
            if (Desktop.getDesktop().isSupported(Desktop.Action.EDIT)) {
                try {
                    Desktop.getDesktop().edit(file);
                    throw new RuntimeException("IOException was not thrown");
                } catch (IOException ignored) {
                    // ok
                }
            }
            if (Desktop.getDesktop().isSupported(Desktop.Action.PRINT)) {
                try {
                    Desktop.getDesktop().print(file);
                    throw new RuntimeException("IOException was not thrown");
                } catch (IOException ignored) {
                    // ok
                }
            }
        }
    }
}
