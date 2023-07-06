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

package jdk.experimental.bytecode;
















public enum PoolTag {
    CONSTANT_UTF8(1),
    CONSTANT_UNICODE(2),
    CONSTANT_INTEGER(3),
    CONSTANT_FLOAT(4),
    CONSTANT_LONG(5),
    CONSTANT_DOUBLE(6),
    CONSTANT_CLASS(7),
    CONSTANT_STRING(8),
    CONSTANT_FIELDREF(9),
    CONSTANT_METHODREF(10),
    CONSTANT_INTERFACEMETHODREF(11),
    CONSTANT_NAMEANDTYPE(12),
    CONSTANT_METHODHANDLE(15),
    CONSTANT_METHODTYPE(16),
    CONSTANT_DYNAMIC(17),
    CONSTANT_INVOKEDYNAMIC(18);

    public final int tag;

    PoolTag(int tag) {
        this.tag = tag;
    }

    static PoolTag from(int tag) {
        return values()[tag - 1];
    }
}
