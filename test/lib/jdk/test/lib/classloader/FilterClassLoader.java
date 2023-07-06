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

package jdk.test.lib.classloader;


import java.util.function.Predicate;














/**
 * A classloader, which using target classloader in case provided condition
 * for class name is met, and using parent otherwise
 */
public class FilterClassLoader extends ClassLoader {

    private final ClassLoader target;
    private final Predicate<String> condition;

    public FilterClassLoader(ClassLoader target, ClassLoader parent,
            Predicate<String> condition) {
        super(parent);
        this.condition = condition;
        this.target = target;
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        if (condition.test(name)) {
            return target.loadClass(name);
        }
        return super.loadClass(name);
    }
}
