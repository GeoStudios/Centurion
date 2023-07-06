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

package gc.g1.unloading.keepref;


import gc.g1.unloading.bytecode.BytecodeMutatorFactory;
import gc.g1.unloading.bytecode.TemplateClassWithStaticField;
import gc.g1.unloading.classloaders.DoItYourselfClassLoader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.atomic.AtomicLong;














/**
 * This holder prevents class from being collected by keeping link in static field of another class.
 *
 */
public class InStaticFieldHolder implements RefHolder {

    private static BytecodeMutatorFactory bm = new BytecodeMutatorFactory(TemplateClassWithStaticField.class.getName());

    private static final String CLASS_NAME_BASE = "StaticFieldHolder_";

    private static AtomicLong counter = new AtomicLong(0);

    @Override
    public Object hold(Object object) {
        try {
            String className = bm.padName(CLASS_NAME_BASE + counter.getAndIncrement());
            byte[] bytecode = bm.getBytecode(className);
            DoItYourselfClassLoader loader = new DoItYourselfClassLoader();
            Class<?> clazz = loader.defineClass(className, bytecode);
            for (Method m : clazz.getMethods()) {
                if ("setField".equals(m.getName())) {
                    m.invoke(null, object);
                }
            }
            Object instance = clazz.newInstance();
            return instance;
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            throw new RuntimeException("Something went wrong in StaticFieldHolder ", e);
        }
    }

}
