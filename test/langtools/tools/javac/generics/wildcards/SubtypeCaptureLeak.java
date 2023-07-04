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
 * @bug 8039214
 * @summary Capture variables used for subtyping should not leak out of inference
 * @compile SubtypeCaptureLeak.java
 */

public class SubtypeCaptureLeak {

    interface Parent<T> {}
    interface Child<T> extends Parent<T> {}
    interface Box<T> {}
    interface SubBox<T> extends Box<T> {}

    // applicability inference

    <T> void m1(Parent<? extends T> arg) {}

    void testApplicable(Child<?> arg) {
        m1(arg);
    }

    // applicability inference, nested

    <T> void m2(Box<? extends Parent<? extends T>> arg) {}

    void testApplicable(Box<Child<?>> arg) {
        m2(arg);
    }

    // most specific inference

    <T> void m3(Parent<? extends T> arg) {}
    void m3(Child<?> arg) {}

    void testMostSpecific(Child<?> arg) {
        m3(arg);
    }

    // most specific inference, nested

    <T> void m4(Box<? extends Parent<? extends T>> arg) {}
    void m4(SubBox<Child<?>> arg) {}

    void testMostSpecificNested(SubBox<Child<?>> arg) {
        m4(arg);
    }

}
