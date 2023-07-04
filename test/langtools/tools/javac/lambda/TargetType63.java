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
class TargetType63 {

    interface F<T extends Throwable> {
        void m() throws T;
    }

    void g1() { }
    void g2() throws ClassNotFoundException { }
    void g3() throws Exception { }

    <Z extends Throwable> void m1(F<Z> fz) throws Z { }
    <Z extends ClassNotFoundException> void m2(F<Z> fz) throws Z { }

    void test1() {
        m1(()->{ }); //ok (Z = RuntimeException)
        m1(this::g1); //ok (Z = RuntimeException)
    }

    void test2() {
        m2(()->{ }); //fail (Z = ClassNotFoundException)
        m2(this::g1); //fail (Z = ClassNotFoundException)
    }

    void test3() {
        m1(()->{ throw new ClassNotFoundException(); }); //fail (Z = ClassNotFoundException)
        m1(this::g2); //fail (Z = ClassNotFoundException)
        m2(()->{ throw new ClassNotFoundException(); }); //fail (Z = ClassNotFoundException)
        m2(this::g2); //fail (Z = ClassNotFoundException)
    }

    void test4() {
        m1(()->{ throw new Exception(); }); //fail (Z = Exception)
        m1(this::g3); //fail (Z = Exception)
    }
}
