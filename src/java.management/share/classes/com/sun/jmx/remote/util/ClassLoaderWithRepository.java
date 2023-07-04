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

package com.sun.jmx.remote.util;

import javax.management.loading.ClassLoaderRepository;

public class ClassLoaderWithRepository extends ClassLoader {
    public ClassLoaderWithRepository(ClassLoaderRepository clr,
                                     ClassLoader cl2) {

        if (clr == null) throw new
            IllegalArgumentException("Null ClassLoaderRepository object.");

        repository = clr;
        this.cl2 = cl2;
   }

    protected Class<?> findClass(String name) throws ClassNotFoundException {
        Class<?> cls;
        try {
            cls = repository.loadClass(name);
        } catch (ClassNotFoundException cne) {
            if (cl2 != null) {
                return cl2.loadClass(name);
            } else {
                throw cne;
            }
        }

        if(!cls.getName().equals(name)){
            if (cl2 != null) {
                return cl2.loadClass(name);
            } else {
                throw new ClassNotFoundException(name);
            }
        }
        return cls;
    }

    private final ClassLoaderRepository repository;
    private final ClassLoader cl2;
}
