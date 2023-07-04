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
   @bug 8194154
   @summary Test changing property user.dir on impacting getCanonicalPath
   @run main/othervm UserDirChangedTest
 */

import java.io.File;

public class UserDirChangedTest {
    public static void main(String[] args) throws Exception {
        String keyUserDir = "user.dir";
        String userDirNew = "/home/a/b/c/";
        String fileName = "./a";

        String userDir = System.getProperty(keyUserDir);
        File file = new File(fileName);
        String canFilePath = file.getCanonicalPath();

        // now reset user.dir, this will cause crash on linux without bug 8194154 fixed.
        System.setProperty(keyUserDir,  userDirNew);
        String newCanFilePath = file.getCanonicalPath();
        System.out.format("%24s %48s%n", "Canonical Path = ", canFilePath);
        System.out.format("%24s %48s%n", "new Canonical Path = ", newCanFilePath);
        if (!canFilePath.equals(newCanFilePath)) {
            throw new RuntimeException("Changing property user.dir should have no effect on getCanonicalPath");
        }
    }
}
