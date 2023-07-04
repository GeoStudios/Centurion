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

class TargetType21 {
    interface SAM1 {
        String m1(Integer n) throws Exception;
    }

    interface SAM2 {
        void m2(Integer n);
    }

    interface SAM3<R,A> {
        R m3(A n);
    }

    void call(SAM1 sam) { }
    void call(SAM2 sam) { }
    <R,A> void call(SAM3<R,A> sam) { }

    void test() {
        call(x -> { throw new Exception(); }); //ambiguous
        call((Integer x) -> { System.out.println(""); }); //ok (only one is void)
        call((Integer x) -> { return (Object) null; }); //ok (only one returns Object)
        call(x -> { System.out.println(""); }); //ambiguous
        call(x -> { return (Object) null; }); //ambiguous
        call(x -> { return null; }); //ambiguous
    }
}
