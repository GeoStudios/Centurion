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
class T7151802 {
    static class Foo<X> { }

    static class SubFoo<X> extends Foo<X> { }

    //generic - bound - arg - non-slilent
    <Z extends Foo<String>> void get1(Z fz) { }
    void test1(Foo foo) { get1(foo); }

    //generic - bound - arg - silent
    <Z extends Foo<?>> void get2(Z fz) { }
    void test2(Foo foo) { get2(foo); }

    //generic - nobound - arg - non-slilent
    <Z> void get3(Foo<Z> fz) { }
    void test(Foo foo) { get3(foo); }

    //generic - nobound - arg - slilent
    <Z> void get4(Foo<?> fz) { }
    void test4(Foo foo) { get4(foo); }

    //generic - bound - ret - non-slilent
    <Z extends Foo<String>> Z get5() { return null; }
    void test5() { SubFoo sf = get5(); }

    //generic - bound - ret - slilent
    static <Z extends Foo<?>> Z get6() { return null; }
    void test6() { SubFoo sf = get6(); }

    //nogeneric - nobound - arg - non-slilent
    void get7(Foo<String> fz) { }
    void test7(Foo foo) { get7(foo); }

    //nogeneric - nobound - arg - slilent
    static void get8(Foo<?> fz) { }
    void test8(Foo foo) { get8(foo); }
}
