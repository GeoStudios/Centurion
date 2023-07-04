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
class MostSpecific08 {

    static class C {
        int getInt() { return -1; }
        Integer getInteger() { return -1; }
    }

    interface IntResult { }
    interface ReferenceResult<X> { }

    interface PrimitiveFunction {
        int f(C c);
    }

    interface ReferenceFunction<X> {
        X f(C c);
    }

    interface Tester {
        IntResult apply(PrimitiveFunction p);
        <Z> ReferenceResult<Z> apply(ReferenceFunction<Z> p);
    }

    void testMref(Tester t) {
        IntResult pr = t.apply(C::getInt); //ok - unoverloaded mref
        ReferenceResult<Integer> rr = t.apply(C::getInteger); //ok - unoverloaded mref
    }

    void testLambda(Tester t) {
        IntResult pr1 = t.apply(c->c.getInt()); //ambiguous - implicit
        IntResult pr2 = t.apply((C c)->c.getInt()); //ok
        ReferenceResult<Integer> rr1 = t.apply(c->c.getInteger()); //ambiguous - implicit
        ReferenceResult<Integer> rr2 = t.apply((C c)->c.getInteger()); //ok
    }
}
