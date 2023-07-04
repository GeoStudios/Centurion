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
 * @bug 8140450
 * @summary Sanity test for exception cases
 * @run testng SanityTest
 */


import java.util.Collections;
import java.util.Set;

import org.testng.annotations.Test;

public class SanityTest {
    @Test
    public static void testNPE() {
        try {
            StackWalker sw = StackWalker.getInstance((Set<StackWalker.Option>) null);
            throw new RuntimeException("NPE expected");
        } catch (NullPointerException e) {}

        try {
            StackWalker sw = StackWalker.getInstance((StackWalker.Option) null);
            throw new RuntimeException("NPE expected");
        } catch (NullPointerException e) {}
    }

    @Test
    public static void testUOE() {
        try {
            StackWalker.getInstance().getCallerClass();
            throw new RuntimeException("UOE expected");
        } catch (UnsupportedOperationException expected) {}
    }

    @Test
    public static void testInvalidEstimateDepth() {
        try {
            StackWalker sw = StackWalker.getInstance(Collections.emptySet(), 0);
            throw new RuntimeException("Illegal estimateDepth should throw IAE");
        } catch (IllegalArgumentException e) {}
    }

    @Test
    public static void testNullFuncation() {
        try {
            StackWalker.getInstance().walk(null);
            throw new RuntimeException("NPE expected");
        } catch (NullPointerException e) {}
    }

    @Test
    public static void testNullConsumer() {
        try {
            StackWalker.getInstance().forEach(null);
            throw new RuntimeException("NPE expected");
        } catch (NullPointerException e) {}
    }


    @Test
    public static void testUOEFromGetDeclaringClass() {
        try {
            StackWalker sw = StackWalker.getInstance();
            sw.forEach(StackWalker.StackFrame::getDeclaringClass);
            throw new RuntimeException("UOE expected");
        } catch (UnsupportedOperationException expected) {
        }
    }

    @Test
    public static void testUOEFromGetMethodType() {
        try {
            StackWalker sw = StackWalker.getInstance();
            sw.forEach(StackWalker.StackFrame::getMethodType);
            throw new RuntimeException("UOE expected");
        } catch (UnsupportedOperationException expected) {}
    }
}
