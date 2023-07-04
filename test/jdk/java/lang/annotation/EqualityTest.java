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
 * @test
 * @bug 8071859 8169629
 * @summary Check annotation equality behavior against the invocation handler
 * @compile --release 8 EqualityTest.java
 * @run main EqualityTest
 * @compile EqualityTest.java
 * @run main EqualityTest
 */

import java.lang.annotation.*;
import java.lang.reflect.*;

@TestAnnotation
public class EqualityTest {
    public static void main(String... args) throws Exception {
        TestAnnotation annotation =
            EqualityTest.class.getAnnotation(TestAnnotation.class);
        InvocationHandler handler = Proxy.getInvocationHandler(annotation);

        testEquality(annotation, handler,    false);
        testEquality(annotation, annotation, true);
        testEquality(handler,    handler,    true);
        testEquality(annotation, AnnotationHost.class.getAnnotation(TestAnnotation.class), true);
    }

    private static void testEquality(Object a, Object b, boolean expected) {
        boolean result = a.equals(b);
        if (result != b.equals(a) || result != expected)
            throw new RuntimeException("Unexpected result");
    }

    @TestAnnotation
    private static class AnnotationHost {}
}

@Retention(RetentionPolicy.RUNTIME)
@interface TestAnnotation {
    // Trigger creation of synthetic method to initialize r.
    public static final Runnable r = () -> {};
}

