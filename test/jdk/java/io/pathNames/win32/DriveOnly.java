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
   @bug 4096648
   @summary Make sure that isDirectory and lastModified work on "x:"
 */

import java.io.File;


public class DriveOnly {

    public static void main(String[] args) throws Exception {
        if (File.separatorChar != '\\') return;
        File f = new File("").getCanonicalFile();
        while (f.getParent() != null) f = f.getParentFile();
        String p = f.getPath().substring(0, 2);
        if (!(Character.isLetter(p.charAt(0)) && (p.charAt(1) == ':'))) {
            System.err.println("No current drive, cannot run test");
            return;
        }
        f = new File(p);
        if (!f.isDirectory())
            throw new Exception("\"" + f + "\" is not a directory");
        if (f.lastModified() == 0)
            throw new Exception("\"" + f + "\" has no last-modified time");
    }

}
