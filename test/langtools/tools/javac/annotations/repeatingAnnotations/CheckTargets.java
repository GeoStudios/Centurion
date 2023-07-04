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
 * @summary Smoke test for repeating annotations
 * @bug 7151010
 *
 * @run clean Foos Foo Bars Bar Baz Bazs CheckTargets
 * @run compile CheckTargets.java
 */

import java.lang.annotation.*;

@Repeatable(Foos.class)
@Target(ElementType.TYPE)
@interface Foo {}

@Target(ElementType.ANNOTATION_TYPE)
@interface Foos {
    Foo[] value();
}

@Repeatable(Bars.class)
@Target(ElementType.TYPE)
@interface Bar {}

@Target({ ElementType.ANNOTATION_TYPE, ElementType.TYPE })
@interface Bars {
    Bar[] value();
}


@Repeatable(Bazs.class)
@Target({ ElementType.TYPE, ElementType.ANNOTATION_TYPE })
@interface Baz {}

@Target({ ElementType.ANNOTATION_TYPE, ElementType.TYPE })
@interface Bazs {
    Baz[] value();
}


public class CheckTargets {}
