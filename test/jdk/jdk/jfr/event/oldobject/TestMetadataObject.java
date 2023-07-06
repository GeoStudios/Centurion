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

package jdk.jfr.event.oldobject;
















/**
 * Purpose of this class is to generate a stack trace
 * that can be used in a different class loader
 * to test metadata retention
 *
 */
public final class TestMetadataObject {
    public static Object startRecurse() {
        return recurse(50);
    }

    public static Object recurse(int depth) {
        if (depth > 0) {
            return recurse(depth - 1);
        } else {
            return makeMemoryLeak();
        }
    }

    public static byte[] makeMemoryLeak() {
        return new byte[OldObjects.MIN_SIZE];
    }
}