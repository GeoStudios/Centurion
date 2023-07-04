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
 * @bug 8011028
 * @summary lang/INFR/infr001/infr00101md/infr00101md.java fails to compile after switch to JDK8-b82
 * @compile TargetType70.java
 */
class TargetType70  {

    static class Sup {}
    static class Sub extends Sup {}

    interface I<T extends GenSup<U>, U> {
        T m(U o);
    }

    static class GenSup<T> {
        GenSup(T f) { }
    }

    static class GenSub<T> extends GenSup<T> {
        GenSub(T f) { super(f); }
    }

    <T extends Sup> void m(I<? extends GenSup<T>, T> o1, T o2) { }

    void test(Sub sub) {
        m(GenSub::new, sub);
    }
}
