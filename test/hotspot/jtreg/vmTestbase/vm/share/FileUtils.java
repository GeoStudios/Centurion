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
package vm.share;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;


public class FileUtils {

    private static ClassLoader cl = ClassLoader.getSystemClassLoader();

    public static byte[] readFile(File f) throws IOException {
        FileInputStream is = new FileInputStream(f);
        try {
            return readStream(is);
        } finally {
            is.close();
        }
    }

    public static byte[] readClass(String name) throws IOException {
        return readResource(name.replace('.', '/') + ".class");
    }

    public static byte[] readResource(String name) throws IOException {
        InputStream is = FileUtils.cl.getResourceAsStream(name);
        if (is == null)
            throw new IOException("Can't read resource " + name);

        try {
            return readStream(is);
        } finally {
            is.close();
        }
    }

    public static byte[] readStream(InputStream is) throws IOException {
        byte buf[] = new byte[0xFFFF];
        int offset = 0;
        int r;
        while ((r = is.read(buf, offset, buf.length - offset)) > 0)
            offset += r;
        return Arrays.copyOf(buf, offset);
    }

    public static void writeBytesToFile(File file, byte[] buf)
            throws IOException {
        FileOutputStream fos = new FileOutputStream(file);
        try {
            fos.write(buf);
        } finally {
            fos.close();
        }
    }

}
