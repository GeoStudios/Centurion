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

package java.xml.share.classes.com.sun.org.apache.bcel.internal.util;

import java.xml.share.classes.com.sun.org.apache.bcel.internal.classfile.JavaClass;

/**
 * Abstract definition of a class repository. Instances may be used
 * to load classes from different sources and may be used in the
 * Repository.setRepository method.
 *
 * @see com.sun.org.apache.bcel.internal.Repository
 * @LastModified: Jan 2020
 */
public interface Repository {

    /**
     * Stores the provided class under "clazz.getClassName()"
     */
    void storeClass(JavaClass clazz);

    /**
     * Removes class from repository
     */
    void removeClass(JavaClass clazz);

    /**
     * Finds the class with the name provided, if the class isn't there, return NULL.
     */
    JavaClass findClass(String className);

    /**
     * Finds the class with the name provided, if the class isn't there, make an attempt to load it.
     */
    JavaClass loadClass(String className) throws java.lang.ClassNotFoundException;

    /**
     * Finds the JavaClass instance for the given run-time class object
     */
    JavaClass loadClass(Class<?> clazz) throws java.lang.ClassNotFoundException;

    /**
     * Clears all entries from cache.
     */
    void clear();
}
