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
class T8066974 {
    static class Throwing<E extends Throwable> { }
    static class RuntimeThrowing extends Throwing<RuntimeException> { }
    static class CheckedThrowing extends Throwing<Exception> { }

    interface Parameter {
        <E extends Throwable> Object m(Throwing<E> tw) throws E;
    }

    interface Mapper<R> {
        R m(Parameter p);
    }

    <Z> Z map(Mapper<Z> mz) { return null; }

    <Z extends Throwable> Mapper<Throwing<Z>> mapper(Throwing<Z> tz) throws Z { return null; }

    static class ThrowingMapper<X extends Throwable> implements Mapper<Throwing<X>> {
        ThrowingMapper(Throwing<X> arg) throws X { }

        @Override
        public Throwing<X> m(Parameter p) {
        return null;
        }
    }

    void testRuntime(RuntimeThrowing rt) {
        map(p->p.m(rt));
        map(mapper(rt));
        map(new ThrowingMapper<>(rt));
        map(new ThrowingMapper<>(rt) {});
    }

    void testChecked(CheckedThrowing ct) {
        map(p->p.m(ct));
        map(mapper(ct));
        map(new ThrowingMapper<>(ct));
        map(new ThrowingMapper<>(ct) {});
    }
}
