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

/**
 * @test
 * @bug 8019381
 * @summary Verify that we do not get exception when we override isEmpty()
 *          in a subclass of HashMap
 * @author zhangshj@linux.vnet.ibm.com
 */

import java.util.function.BiFunction;
import java.util.HashMap;

public class OverrideIsEmpty {
    public static class NotEmptyHashMap<K,V> extends HashMap<K,V> {
        private K alwaysExistingKey;
        private V alwaysExistingValue;

        @Override
        public V get(Object key) {
            if (key == alwaysExistingKey) {
                return alwaysExistingValue;
            }
            return super.get(key);
        }

        @Override
        public int size() {
            return super.size() + 1;
        }

        @Override
        public boolean isEmpty() {
            return size() == 0;
        }
    }

    public static void main(String[] args) {
        NotEmptyHashMap<Object, Object> map = new NotEmptyHashMap<>();
        Object key = new Object();
        Object value = new Object();
        map.get(key);
        map.remove(key);
        map.replace(key, value, null);
        map.replace(key, value);
        map.computeIfPresent(key, new BiFunction<Object, Object, Object>() {
            public Object apply(Object key, Object oldValue) {
                return oldValue;
            }
        });
    }
}

