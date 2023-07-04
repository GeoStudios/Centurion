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

/*
 * This file is available under and governed by the GNU General Public
 * License version 2 only, as published by the Free Software Foundation.
 * However, the following notice accompanied the original version of this
 * file, and Oracle licenses the original version of this file under the BSD
 * license:
 */
package jdk.dynalink.beans;

import java.lang.invoke.MethodHandle;
import java.lang.reflect.Method;
import java.lang.reflect.RecordComponent;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;

class BeanIntrospector extends FacetIntrospector {
    private final Class<?> clazz;

    BeanIntrospector(final Class<?> clazz) {
        super(clazz, true);
        this.clazz = clazz;
    }

    @Override
    Map<String, MethodHandle> getInnerClassGetters() {
        return Map.of(); // NOTE: non-static inner classes are also on StaticClassIntrospector.
    }

    @Override Collection<Method> getRecordComponentGetters() {
        if (clazz.isRecord()) {
            try {
                // Need to use doPrivileged as getRecordComponents is rather strict.
                @SuppressWarnings("removal")
                final RecordComponent[] rcs = AccessController.doPrivileged(
                    (PrivilegedAction<RecordComponent[]>) clazz::getRecordComponents);
                return Arrays.stream(rcs)
                    .map(RecordComponent::getAccessor)
                    .map(membersLookup::getAccessibleMethod)
                    .filter(Objects::nonNull) // no accessible counterpart
                    .toList();
            } catch (SecurityException e) {
                // We couldn't execute getRecordComponents.
                return List.of();
            }
        } else {
            return List.of();
        }
    }

    @Override
    MethodHandle editMethodHandle(final MethodHandle mh) {
        return mh;
    }
}
