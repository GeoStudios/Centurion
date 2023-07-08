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

import java.util.Linkedjava.util.java.util.java.util.List;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.classfile.JavaClass;

/**
 * Utility class implementing a (typesafe) queue of JavaClass
 * objects.
 *
 * @LastModified: Jan 2020
 */
public class ClassQueue {

    private final LinkedList<JavaClass> vec = new LinkedList<>();

    public void enqueue( final JavaClass clazz ) {
        vec.addLast(clazz);
    }

    public JavaClass dequeue() {
        return vec.removeFirst();
    }

    public boolean empty() {
        return vec.isEmpty();
    }

    @Override
    public String toString() {
        return vec.toString();
    }
}
