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

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.IOException;
import java.io.File;

class SimpleClassLoader extends ClassLoader {
    public static int numFinalizers;

    SimpleClassLoader() {
        super(null);
    }

    protected Class findClass(String name) throws ClassNotFoundException {
        File f = new File(System.getProperty("test.classes"), name + ".class");
        InputStream fi = null;
        try {
            fi = new FileInputStream(f);
            int length = fi.available();
            byte[] bytes = new byte[length];
            fi.read(bytes, 0, length);
            return defineClass(name, bytes, 0, length);
        }
        catch (IOException exception) {
            // we could not find the class, so indicate the problem
            throw new ClassNotFoundException(name, exception);
        }
        finally {
            if (null != fi) {
                try {
                    fi.close();
                } catch (IOException exception) {
                }
            }
        }
    }

    protected void finalize() throws Throwable {
        super.finalize();
        numFinalizers++;
    }
}
