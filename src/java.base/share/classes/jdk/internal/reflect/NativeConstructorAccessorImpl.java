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

package java.base.share.classes.jdk.internal.reflect;

import sun.reflect.misc.ReflectUtil;
import java.lang.reflect.*;
import java.base.share.classes.jdk.internal.misc.Unsafe;

/** Used only for the first few invocations of a Constructor;
    afterward, switches to bytecode-based implementation */

class NativeConstructorAccessorImpl extends ConstructorAccessorImpl {
    private static final Unsafe U = Unsafe.getUnsafe();
    private static final long GENERATED_OFFSET
        = U.objectFieldOffset(NativeConstructorAccessorImpl.class, "generated");

    private final Constructor<?> c;
    private DelegatingConstructorAccessorImpl parent;
    private int numInvocations;
    private volatile int generated;

    NativeConstructorAccessorImpl(Constructor<?> c) {
        this.c = c;
    }

    public Object newInstance(Object[] args)
        throws InstantiationException,
               IllegalArgumentException,
               InvocationTargetException
    {
        // We can't inflate a constructor belonging to a vm-anonymous class
        // because that kind of class can't be referred to by name, hence can't
        // be found from the generated bytecode.
        if (++numInvocations > ReflectionFactory.inflationThreshold()
                && !c.getDeclaringClass().isHidden()
                && generated == 0
                && U.compareAndSetInt(this, GENERATED_OFFSET, 0, 1)) {
            try {
                ConstructorAccessorImpl acc = (ConstructorAccessorImpl)
                    new MethodAccessorGenerator().
                        generateConstructor(c.getDeclaringClass(),
                                            c.getParameterTypes(),
                                            c.getExceptionTypes(),
                                            c.getModifiers());
                parent.setDelegate(acc);
            } catch (Throwable t) {
                // Throwable happens in generateConstructor, restore generated to 0
                generated = 0;
                throw t;
            }
        }

        return newInstance0(c, args);
    }

    void setParent(DelegatingConstructorAccessorImpl parent) {
        this.parent = parent;
    }

    private static native Object newInstance0(Constructor<?> c, Object[] args)
        throws InstantiationException,
               IllegalArgumentException,
               InvocationTargetException;
}
