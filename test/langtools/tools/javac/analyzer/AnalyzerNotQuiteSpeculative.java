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
public class AnalyzerNotQuiteSpeculative {
    private void test() {
        Subclass1 c1 = null;
        Subclass2 c2 = null;
        Base b = null;

        t(new C<Base>(c1).set(c2));
        t(new C<Base>(b).set(c2));
    }

    public static class Base {}
    public static class Subclass1 extends Base {}
    public static class Subclass2 extends Base {}
    public class C<T extends Base> {
        public C(T t) {}
        public C<T> set(T t) { return this; }
    }
    <T extends Base> void t(C<? extends Base> l) {}
}
