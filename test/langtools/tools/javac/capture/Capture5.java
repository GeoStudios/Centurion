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
 * @bug 4916650
 * @summary wildcards versus recursive F-bounds
 * @author Peter von der Ahe
 *
 * @compile Capture5.java
 */

package capture5;

interface R<T extends R<T>> {}
class A implements R<A> {}
class B<T> implements R<B<T>> {}
class C implements R<C> {}

class Recursive {
    <t> t choose(t x, t y) {
        return x;
    }
    void f(boolean b1, boolean b2, boolean b3, A a, B<String> b, C c) {
        R<? extends R<? extends R<? extends R<? extends R<?>>>>> r = null;
        r = b2 ? a : b;
        r = b1 ? (b2 ? a : b) : (b3 ? b : c);
        r = choose(a,c);
        r = choose(choose(a,b), choose(b,c));
    }
}
