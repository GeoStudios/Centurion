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

import javax.sound.sampled.AudioFileFormat;

/**
 * @test
 * @bug 4300529
 * @summary Filename extension test. The filename extensions for file types
 *          AIFF-C, SND, and WAVE should not include a ".".
 */
public class FileTypeExtensionTest {

    public static void main(String[] args) throws Exception {

        AudioFileFormat.Type[] types = { AudioFileFormat.Type.AIFC,
                                         AudioFileFormat.Type.AIFF,
                                         AudioFileFormat.Type.AU,
                                         AudioFileFormat.Type.SND,
                                         AudioFileFormat.Type.WAVE };

        boolean failed = false;

        System.out.println("\nDefined file types and extensions:");

        for (int i = 0; i < types.length; i++) {
            System.out.println("\n");
            System.out.println("  file type: " + types[i]);
            System.out.println("  extension: " + types[i].getExtension());
            if( types[i].getExtension().charAt(0) == '.' ) {
                failed = true;
            }
        }

        if (failed) {
            System.err.println("Failed!");
            throw new Exception("File type extensions begin with .");
        } else {
            System.err.println("Passed!");
        }
    }
}
