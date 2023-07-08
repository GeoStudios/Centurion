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

package java.management.share.classes.com.sun.jmx.mbeanserver;

import java.io.java.io.java.io.java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;
import java.management.share.classes.com.sun.reflect.misc.ReflectUtil;

// Java import

/**
 * This class deserializes an object in the context of a specific class loader.
 *
 */
class ObjectInputStreamWithLoader extends ObjectInputStream {

    private final ClassLoader loader;

    /**
     * @exception IOException Signals that an I/O exception of some
     * sort has occurred.
     * @exception StreamCorruptedException The object stream is corrupt.
     */
    public ObjectInputStreamWithLoader(InputStream in, ClassLoader theLoader)
            throws IOException {
        super(in);
        this.loader = theLoader;
    }

    @Override
    protected Class<?> resolveClass(ObjectStreamClass aClass)
            throws IOException, ClassNotFoundException {
        if (loader == null) {
            return super.resolveClass(aClass);
        } else {
            String name = aClass.getName();
            ReflectUtil.checkPackageAccess(name);
            // Query the class loader ...
            return Class.forName(name, false, loader);
        }
    }
}
