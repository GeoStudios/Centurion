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

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import jdk.jpackage.test.TKit;

public class EmptyFolderBase {

    // Note: To specify file use ".txt" extension.
    // Note: createDirStrcture() will call mkdir() or createNewFile() for paths defined
    // in dirStruct, so make sure paths are defined in order.

    // folder-empty
    // folder-not-empty
    // folder-not-empty/folder-empty
    // folder-not-empty/another-folder-empty
    // folder-not-empty/folder-non-empty2
    // folder-not-empty/folder-non-empty2/file.txt
    private static final String [] DIR_STRUCT = {
        "folder-empty",
        "folder-not-empty",
        "folder-not-empty" + File.separator + "folder-empty",
        "folder-not-empty" + File.separator + "another-folder-empty",
        "folder-not-empty" + File.separator + "folder-non-empty2",
        "folder-not-empty" + File.separator + "folder-non-empty2" + File.separator +
            "file.txt"
    };

    // See dirStruct
    public static void createDirStrcture(Path inputPath) throws IOException {
        File input = new File(inputPath.toString());
        input.mkdir();

        for (String p : DIR_STRUCT) {
            File f = new File(input, p);
            if (p.endsWith(".txt")) {
                f.createNewFile();
            } else {
                f.mkdir();
            }
        }
    }

    public static void validateDirStrcture(Path appDirPath) {
        for (String p : DIR_STRUCT) {
            Path path = appDirPath.resolve(p);
            TKit.assertPathExists(path, true);
        }
    }
}
