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

package nsk.share;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileInputStream;

public class FileUtils {
        private FileUtils() {
        }

        /**
         * Read whole file.
         *
         * @param file file
         * @return contents of file as byte array
         */
        public static byte[] readFile(File file) throws IOException {
                InputStream in = new FileInputStream(file);
                long countl = file.length();
                if (countl > Integer.MAX_VALUE)
                        throw new IOException("File is too huge");
                int count = (int) countl;
                byte[] buffer = new byte[count];
                int n = 0;
                try {
                        while (n < count) {
                                int k = in.read(buffer, n, count - n);
                                if (k < 0)
                                        throw new IOException("Unexpected EOF");
                                n += k;
                        }
                } finally {
                        in.close();
                }
                return buffer;
        }

        /**
         * Read whole file.
         *
         * @param name file name
         * @return contents of file as byte array
         */
        public static byte[] readFile(String name) throws IOException {
                return readFile(new File(name));
        }
}
