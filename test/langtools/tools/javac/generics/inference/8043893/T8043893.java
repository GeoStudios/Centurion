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

class T8043893<X> {

    interface S1<U> { }

    interface S2<U> { }

    interface T0 { }

    interface T1 extends S1<Number>, S2<Number> { }

    interface T2 extends S1<Integer>, S2<Integer> { }

    interface T3 extends S1<Number>, S2<Integer> { }

    interface T4 extends S1<Number> { }

    interface T5 extends S1<Integer> { }

    <Z extends T1> void m_intersection(T8043893<? super Z> a) { }

    <Z extends T4> void m_class(T8043893<? super Z> a) { }

    void test() {
        //intersection type checks
        m_intersection(new T8043893<T1>()); //ok in both 7 and 8 - Z = T1
        m_intersection(new T8043893<T2>()); //ok in 7, fails in 8
        m_intersection(new T8043893<T3>()); //ok in 7, fails in 8
        m_intersection(new T8043893<T0>()); //ok in both 7 and 8 - Z = T0 & T1
        //class type checks
        m_class(new T8043893<T4>()); //ok in both 7 and 8 - Z = T4
        m_class(new T8043893<T5>()); //ok in 7, fails in 8
        m_class(new T8043893<T0>()); //ok in both 7 and 8 - Z = T0 & T4
    }
}
