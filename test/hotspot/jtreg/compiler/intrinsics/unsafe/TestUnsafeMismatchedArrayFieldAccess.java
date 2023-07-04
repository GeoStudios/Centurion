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

/**
 * @test
 * @bug 8142386
 * @summary Unsafe access to an array is wrongly marked as mismatched
 * @modules java.base/jdk.internal.misc
 *
 * @run main/othervm -XX:-UseOnStackReplacement -XX:-BackgroundCompilation -XX:-TieredCompilation
 *      compiler.intrinsics.unsafe.TestUnsafeMismatchedArrayFieldAccess
 */

package compiler.intrinsics.unsafe;

import jdk.internal.misc.Unsafe;

public class TestUnsafeMismatchedArrayFieldAccess {

    private static final Unsafe UNSAFE = Unsafe.getUnsafe();

    static {
        try {
            array_offset = UNSAFE.objectFieldOffset(TestUnsafeMismatchedArrayFieldAccess.class.getDeclaredField("array"));
        }
        catch (Exception e) {
            throw new AssertionError(e);
        }
    }

    int[] array;
    static final long array_offset;

    void m() {
        UNSAFE.getReference(this, array_offset);
    }

    static public void main(String[] args) {
        TestUnsafeMismatchedArrayFieldAccess test = new TestUnsafeMismatchedArrayFieldAccess();

        for (int i = 0; i < 20000; i++) {
            test.m();
        }
    }
}
