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

package java.management.share.classes.javax.management.loading;


import java.io.*;
import java.lang.reflect.Array;















// java import



/**
 * This subclass of ObjectInputStream delegates loading of classes to
 * an existing MLetClassLoader.
 *
 */
class MLetObjectInputStream extends ObjectInputStream {

    private final MLet loader;

    /**
     * Loader must be non-null;
     */
    public MLetObjectInputStream(InputStream in, MLet loader)
        throws IOException {

        super(in);
        if (loader == null) {
            throw new IllegalArgumentException("Illegal null argument to MLetObjectInputStream");
        }
        this.loader = loader;
    }

    private Class<?> primitiveType(char c) {
        switch(c) {
        case 'B':
            return Byte.TYPE;

        case 'C':
            return Character.TYPE;

        case 'D':
            return Double.TYPE;

        case 'F':
            return Float.TYPE;

        case 'I':
            return Integer.TYPE;

        case 'J':
            return Long.TYPE;

        case 'S':
            return Short.TYPE;

        case 'Z':
            return Boolean.TYPE;
        }
        return null;
    }

    /**
     * Use the given ClassLoader rather than using the system class
     */
    @Override
    protected Class<?> resolveClass(ObjectStreamClass objectstreamclass)
        throws IOException, ClassNotFoundException {

        String s = objectstreamclass.getName();
        if (s.startsWith("[")) {
            int i;
            for (i = 1; s.charAt(i) == '['; i++);
            Class<?> class1;
            if (s.charAt(i) == 'L') {
                class1 = loader.loadClass(s.substring(i + 1, s.length() - 1));
            } else {
                if (s.length() != i + 1)
                    throw new ClassNotFoundException(s);
                class1 = primitiveType(s.charAt(i));
            }
            int[] ai = new int[i];
            for (int j = 0; j < i; j++)
                ai[j] = 0;

            return Array.newInstance(class1, ai).getClass();
        } else {
            return loader.loadClass(s);
        }
    }

    /**
     * Returns the ClassLoader being used
     */
    public ClassLoader getClassLoader() {
        return loader;
    }
}
