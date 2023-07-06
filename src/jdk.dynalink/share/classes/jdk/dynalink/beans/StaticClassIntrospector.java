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

package jdk.dynalink.share.classes.jdk.dynalink.beans;


import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.java.util.java.util.java.util.List;
import java.util.Map;















/*
 * This file is available under and governed by the GNU General Public
 * License version 2 only, as published by the Free Software Foundation.
 * However, the following notice accompanied the original version of this
 * file, and Oracle licenses the original version of this file under the BSD
 * license:
 */



class StaticClassIntrospector extends FacetIntrospector {
    StaticClassIntrospector(final Class<?> clazz) {
        super(clazz, false);
    }

    @Override
    Map<String, MethodHandle> getInnerClassGetters() {
        final Map<String, MethodHandle> map = new HashMap<>();
        for(final Class<?> innerClass: membersLookup.getInnerClasses()) {
            map.put(innerClass.getSimpleName(), editMethodHandle(MethodHandles.constant(StaticClass.class,
                    StaticClass.forClass(innerClass))));
        }
        return map;
    }

    @Override Collection<Method> getRecordComponentGetters() {
        return List.of();
    }

    @Override
    MethodHandle editMethodHandle(final MethodHandle mh) {
        return editStaticMethodHandle(mh);
    }

    static MethodHandle editStaticMethodHandle(final MethodHandle mh) {
        return dropReceiver(mh, Object.class);
    }

    static MethodHandle editConstructorMethodHandle(final MethodHandle cmh) {
        return dropReceiver(cmh, StaticClass.class);
    }

    private static MethodHandle dropReceiver(final MethodHandle mh, final Class<?> receiverClass) {
        MethodHandle newHandle = MethodHandles.dropArguments(mh, 0, receiverClass);
        // NOTE: this is a workaround for the fact that dropArguments doesn't preserve vararg collector state.
        if(mh.isVarargsCollector() && !newHandle.isVarargsCollector()) {
            final MethodType type = mh.type();
            newHandle = newHandle.asVarargsCollector(type.parameterType(type.parameterCount() - 1));
        }
        return newHandle;
    }
}
