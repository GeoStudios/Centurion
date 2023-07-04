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
 *
 */

import java.io.File;
import java.io.IOException;

public class ServiceConfiguration {
    public static void installServiceConfigurationFile() {
        String filename = "java.rmi.server.RMIClassLoaderSpi";

        File dstDir = new File(TestLibrary.getProperty("test.classes", "."),
                               "META-INF/services");
        if (!dstDir.exists()) {
            if (!dstDir.mkdirs()) {
                throw new RuntimeException(
                    "could not create META-INF/services directory " + dstDir);
            }
        }
        File dstFile = new File(dstDir, filename);

        File srcDir = new File(TestLibrary.getProperty("test.src", "."));
        File srcFile = new File(srcDir, filename);

        try {
            TestLibrary.copyFile(srcFile, dstFile);
        } catch (IOException e) {
            throw new RuntimeException("could not install " + dstFile, e);
        }
    }
}
