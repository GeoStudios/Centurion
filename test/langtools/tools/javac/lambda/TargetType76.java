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
 * @bug 8016175
 * @summary Add bottom-up type-checking support for unambiguous method references
 * @compile TargetType76.java
 */
class TargetType76 {

    interface Function<X, Y> {
        Y m(X x);
    }

    interface OfRef<T> { }

    interface Supplier<X> {
        X make();
    }

    interface Stream<X> { }

    interface Node<E> {
        Spliterator<E> spliterator();
    }

    interface Spliterator<X> {
        Spliterator<X> spliterator();
    }

    class RefTestData<T, I> implements OfRef<T> {
        RefTestData(I state,
                    Function<I, Stream<T>> streamFn,
                    Function<I, Spliterator<T>> splitrFn) { }
    }

    <O> OfRef<O> ofCollection(Node<O> collection) {
        return new RefTestData<>(collection,
                                 x->stream(x::spliterator),
                                 Node::spliterator);
    }

    <S> Stream<S> stream(Supplier<? extends Spliterator<S>> supplier) { return null; }
}
