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


import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.classfile.JavaClass;















/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */




/**
 * Utility class implementing a (typesafe) set of JavaClass objects.
 * Since JavaClass has no equals() method, the name of the class is
 * used for comparison.
 *
 * @see ClassStack
 */
public class ClassSet {

    private final Map<String, JavaClass> map = new HashMap<>();


    public boolean add( final JavaClass clazz ) {
        boolean result = false;
        if (!map.containsKey(clazz.getClassName())) {
            result = true;
            map.put(clazz.getClassName(), clazz);
        }
        return result;
    }


    public void remove( final JavaClass clazz ) {
        map.remove(clazz.getClassName());
    }


    public boolean empty() {
        return map.isEmpty();
    }


    public JavaClass[] toArray() {
        final Collection<JavaClass> values = map.values();
        final JavaClass[] classes = new JavaClass[values.size()];
        values.toArray(classes);
        return classes;
    }


    public String[] getClassNames() {
        return map.keySet().toArray(new String[map.size()]);
    }
}
