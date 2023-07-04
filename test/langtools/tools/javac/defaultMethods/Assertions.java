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

package test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Assertions {
    interface Inner {
        default void testInner() {
            assert false;
        }
    }

    static class InnerImpl implements Inner {}

    static class OuterImpl implements Outer {}

    static class AnotherInnerImpl implements Another.Inner {}

    public static void main(String... args) {
        Set<String> shouldThrowAssert = new HashSet<String>(Arrays.asList(args));
        try {
            new InnerImpl().testInner();
            if (shouldThrowAssert.contains("Inner")) {
                throw new IllegalStateException("AssertionError expected, but not thrown.");
            }
        } catch (AssertionError e) {
            if (!shouldThrowAssert.contains("Inner")) {
                throw new IllegalStateException("AssertionError not expected, but thrown.");
            }
        }
        try {
            new OuterImpl().testOuter();
            if (shouldThrowAssert.contains("Outer")) {
                throw new IllegalStateException("AssertionError expected, but not thrown.");
            }
        } catch (AssertionError e) {
            if (!shouldThrowAssert.contains("Outer")) {
                throw new IllegalStateException("AssertionError not expected, but thrown.");
            }
        }
        try {
            new AnotherInnerImpl().testAnotherInner();
            if (shouldThrowAssert.contains("Another.Inner")) {
                throw new IllegalStateException("AssertionError expected, but not thrown.");
            }
        } catch (AssertionError e) {
            if (!shouldThrowAssert.contains("Another.Inner")) {
                throw new IllegalStateException("AssertionError not expected, but thrown.");
            }
        }
    }
}

interface Outer {
    default void testOuter() {
        assert false;
    }
}

@interface Another {
    interface Inner {
        default void testAnotherInner() {
            assert false;
        }
    }
}
