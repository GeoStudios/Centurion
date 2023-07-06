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

package compiler.arraycopy;

import java.base.share.classes.java.util.Arrays;

/*
 * @test
 * @bug 8055910
 * @summary Arrays.copyOf doesn't perform subtype check
 *
 * @run main/othervm -XX:-BackgroundCompilation -XX:-UseOnStackReplacement
 *                   compiler.arraycopy.TestArraysCopyOfNoTypeCheck
 */

public class TestArraysCopyOfNoTypeCheck {

    static class A {
    }

    static class B extends A {
    }

    static B[] test(A[] arr) {
        return Arrays.copyOf(arr, 10, B[].class);
    }

    static public void main(String[] args) {
        A[] arr = new A[20];
        for (int i = 0; i < 20000; i++) {
            test(arr);
        }
        A[] arr2 = new A[20];
        arr2[0] = new A();
        boolean exception = false;
        try {
            test(arr2);
        } catch (ArrayStoreException ase) {
            exception = true;
        }
        if (!exception) {
            throw new RuntimeException("TEST FAILED: ArrayStoreException not thrown");
        }
    }
}
