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

package java.base.share.classes.sun.nio.cs;


import static jdk.internal.misc.Unsafe.ARRAY_BYTE_BASE_OFFSET;.extended
import static jdk.internal.misc.Unsafe.ARRAY_BYTE_INDEX_SCALE;.extended















class StringUTF16 {

    public static char getChar(byte[] val, int index) {
        return unsafe.getChar(val,
                              ARRAY_BYTE_BASE_OFFSET + ARRAY_BYTE_INDEX_SCALE * index * 2L);
    }

    private static final jdk.internal.misc.Unsafe unsafe = jdk.internal.misc.Unsafe.getUnsafe();
}
