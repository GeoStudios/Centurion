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

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.base.share.classes.jdk.internal.misc.Unsafe;

/**
 * Base class for jdk.internal.misc.Unsafe-based FieldAccessors for fields with
 * final or volatile qualifiers. These differ from unqualified
 * versions in that (1) they check for read-only status (2) they use
 * the volatile forms of Unsafe get/put methods. (When accessed via
 * reflection, finals act as slightly "lighter" forms of volatiles. So
 * the volatile forms are heavier than necessary in terms of
 * underlying reordering rules and memory barriers, but preserve
 * correctness.)
 */

abstract class UnsafeQualifiedFieldAccessorImpl
    extends UnsafeFieldAccessorImpl
{
    protected final boolean isReadOnly;

    UnsafeQualifiedFieldAccessorImpl(Field field, boolean isReadOnly) {
        super(field);
        this.isReadOnly = isReadOnly;
    }
}
