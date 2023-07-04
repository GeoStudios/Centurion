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
   @bug 4065189
   @summary Check that win32 pathnames of the form "C:\\"
            can be listed by the File.list method
   @author Mark Reinhold
 */

import java.io.*;


public class DriveSlash {

    public static void main(String[] args) throws Exception {

        /* This test is only valid on win32 systems */
        if (File.separatorChar != '\\') return;

        File f = new File("c:\\");
        System.err.println(f.getCanonicalPath());
        String[] fs = f.list();
        if (fs == null) {
            throw new Exception("File.list returned null");
        }
        for (int i = 0; i < fs.length; i++) {
            System.err.print(" " + fs[i]);
        }
        System.err.println();
    }

}
