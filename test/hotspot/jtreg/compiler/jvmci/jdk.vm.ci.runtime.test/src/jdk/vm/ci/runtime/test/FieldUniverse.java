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

package jdk.vm.ci.runtime.test;

import jdk.vm.ci.meta.ResolvedJavaField;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Context for field related tests.
 */
public class FieldUniverse extends TypeUniverse {

    public static final Map<Field, ResolvedJavaField> fields = new HashMap<>();

    {
        for (Class<?> c : classes) {
            for (Field f : c.getDeclaredFields()) {
                ResolvedJavaField field = metaAccess.lookupJavaField(f);
                fields.put(f, field);
            }
        }
    }
}