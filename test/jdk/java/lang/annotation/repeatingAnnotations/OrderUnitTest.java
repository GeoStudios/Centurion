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
 * @bug     8004912
 * @summary Unit test for order of annotations returned by get[Declared]AnnotationsByType.
 *
 * @run main OrderUnitTest
 */

import java.lang.annotation.*;
import java.lang.reflect.*;

public class OrderUnitTest {

    public static void main(String[] args) {
        testOrder(Case1.class);
        testOrder(Case2.class);
    }

    private static void testOrder(AnnotatedElement e) {
        Annotation[] decl = e.getDeclaredAnnotations();
        Foo[] declByType = e.getDeclaredAnnotationsByType(Foo.class);

        if (decl[0] instanceof Foo != declByType[0].isDirect() ||
            decl[1] instanceof Foo != declByType[1].isDirect()) {
            throw new RuntimeException("Order of directly / indirectly present " +
                    "annotations from getDeclaredAnnotationsByType does not " +
                    "match order from getDeclaredAnnotations.");
        }
    }
}

@Retention(RetentionPolicy.RUNTIME)
@interface FooContainer {
    Foo[] value();
}

@Retention(RetentionPolicy.RUNTIME)
@Repeatable(FooContainer.class)
@interface Foo {
    boolean isDirect();
}


@Foo(isDirect = true) @FooContainer({@Foo(isDirect = false)})
class Case1 {
}


@FooContainer({@Foo(isDirect = false)}) @Foo(isDirect = true)
class Case2 {
}
