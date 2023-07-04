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

class FilterOutCandidatesForDiagnosticsTest {

    interface C<E> {
        boolean add(E e);
    }

    interface L<E> extends C<E> {
        @Override
        boolean add(E e);
        void add(int index, E element);
    }

    static abstract class AC<E> implements C<E> {
        @Override
        public boolean add(E e) {
            throw new UnsupportedOperationException();
        }
    }

    static abstract class AL<E> extends AC<E> implements L<E> {
        @Override
        public boolean add(E e) {
            return true;
        }
        @Override
        public void add(int index, E element) {
            throw new UnsupportedOperationException();
        }
    }

    static class ARL<E> extends AL<E> implements L<E> {
        @Override
        public boolean add(E e) {
            throw new UnsupportedOperationException();
        }
        @Override
        public void add(int index, E element) {
        }
        private void add(E e, Object[] elementData, int s) {
        }
    }

    void test() {
        make(new ARL<String>(), new ARL<Integer>()).add("");
    }

    <Z> Z make(Z z1, Z z2) {
        return null;
    }
}
