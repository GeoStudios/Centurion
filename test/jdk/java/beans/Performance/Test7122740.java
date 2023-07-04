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
 * @bug 7187618 7122740
 * @summary Tests just a benchmark of PropertyDescriptor(String, Class) performance
 * @author Sergey Malenkov
 * @run main/manual Test7122740
 */

import java.beans.PropertyDescriptor;

public class Test7122740 {
    public static void main(String[] args) throws Exception {
        long time = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            new PropertyDescriptor("name", PropertyDescriptor.class);
            new PropertyDescriptor("value", Concrete.class);
        }
        time -= System.nanoTime();
        System.out.println("Time (ms): " + (-time / 1000000));
    }

    public static class Abstract<T> {
        private T value;
        public T getValue() {
            return this.value;
        }
        public void setValue(T value) {
            this.value = value;
        }
    }

    private static class Concrete extends Abstract<String> {
    }
}
