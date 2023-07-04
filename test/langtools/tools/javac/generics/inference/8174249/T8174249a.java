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
 * @bug 8174249
 * @summary Regression in generic method unchecked calls
 * @compile T8174249a.java
 */

import java.util.ArrayList;
import java.util.Collection;

class T8174249a {
    static <T> T foo(Class<T> c, Collection<? super T> baz) {
return null;
    }

    static void bar(String c) { }

    void test() {
        // this works
        bar(foo(String.class, new ArrayList<String>()));

        // this works with a warning
        String s = foo(String.class, new ArrayList());
        bar(s);

        // this causes an error on JDK9 but should work
        bar(foo(String.class, new ArrayList()));
    }
}
