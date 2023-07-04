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

/*
 * @test
 * @bug 8194044
 * @summary Test if Win32ShellFolder2 root folder object gets identified as such.
 * @requires os.family=="windows"
 * @modules java.desktop/sun.awt.shell
 * @run main FileSystemRootTest
 */

import sun.awt.shell.ShellFolder;
import javax.swing.filechooser.FileSystemView;
import java.io.File;

public class FileSystemRootTest {
    public static void main(String[] args) throws Exception {
        FileSystemView fileSystemView = FileSystemView.getFileSystemView();

        /*
         * This is the only way to get the Win32ShellFolder2 object, since
         * it is an internal class, which cannot be instantiated directly.
         * On windows, this returns "C:\Users\<user-name>\Documents"
         */
        File def = fileSystemView.getDefaultDirectory();
        File root = fileSystemView.getParentDirectory(
                        fileSystemView.getParentDirectory(
                            fileSystemView.getParentDirectory(def)));

        if (! (root instanceof ShellFolder && ShellFolder.isFileSystemRoot(root))) {
            throw new RuntimeException("Test failed: root drive reported as false");
        }

        if (fileSystemView.getSystemDisplayName(root).isEmpty()) {
            throw new RuntimeException("Root drive display name is empty.");
        }
    }
}
