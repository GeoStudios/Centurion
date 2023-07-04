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
 * @bug 6484091
 * @summary FileSystemView leaks directory info
 * @author Pavel Porvatov
   @run main/othervm -Djava.security.manager=allow bug6484091
 */

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.security.AccessControlException;

public class bug6484091 {
    public static void main(String[] args) {
        File dir = FileSystemView.getFileSystemView().getDefaultDirectory();

        printDirContent(dir);

        System.setSecurityManager(new SecurityManager());

        // The next test cases use 'dir' obtained without SecurityManager

        try {
            printDirContent(dir);

            throw new RuntimeException("Dir content was derived bypass SecurityManager");
        } catch (AccessControlException e) {
            // It's a successful situation
        }
    }

    private static void printDirContent(File dir) {
        System.out.println("Files in " + dir.getAbsolutePath() + ":");

        for (File file : dir.listFiles()) {
            System.out.println(file.getName());
        }
    }
}
