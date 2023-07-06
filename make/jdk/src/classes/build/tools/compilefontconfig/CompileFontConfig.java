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

package build.tools.compilefontconfig;

public class CompileFontConfig {
    public static void main(String[] argv) {
        boolean verbose = false;
        if (argv.length != 0 && "-verbose".equals(argv[0])) {
            verbose = true;
        }
        if (verbose) {
            if (argv.length != 3)
                System.out.println("Usage: java CompileFontConfig [-verbose] propertiesFileName binaryFileName");
            else
                new CompileFontConfig(argv[1], argv[2], true);
        } else {
            if (argv.length != 2)
                System.out.println("Usage: java CompileFontConfig [-verbose] propertiesFileName binaryFileNme");
            else
                new CompileFontConfig(argv[0], argv[1], false);
        }
    }

    CompileFontConfig(String inFile, String outFile, boolean verbose) {
        try {
            FileInputStream in = new FileInputStream(inFile);
            FileOutputStream out = new FileOutputStream(outFile);
            FontConfiguration.verbose = verbose;
            FontConfiguration.loadProperties(in);
            FontConfiguration.saveBinary(out);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}