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
class T8164399b<X extends Throwable> {
    <T extends Throwable> void m(Class<? super T> arg) throws T {}
    <T extends X> void g() throws T {}

    void test() {
        m(RuntimeException.class); // ok
        m(Exception.class); // error
        m(Throwable.class); // ok
        m(java.io.Serializable.class); // error
        m(Object.class); // error
        m(Runnable.class); // error
        T8164399b<? super Exception> x = null;
        x.g(); // expected: ok; actual: error
    }
}
