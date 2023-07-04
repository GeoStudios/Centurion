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

import java.util.*;

public enum Sorter {
    TIMSORT {
        public void sort(Object[] array) {
            ComparableTimSort.sort(array);
        }
    },
    MERGESORT {
        public void sort(Object[] array) {
            Arrays.sort(array);
        }
    };

    public abstract void sort(Object[] array);

    public static void warmup() {
        System.out.println("start warm up");
        Integer[] gold = new Integer[10000];
        Random random = new java.util.Random();
        for (int i=0; i < gold.length; i++)
            gold[i] = random.nextInt();

        for (int i=0; i < 10000; i++) {
            for (Sorter s : values()) {
                Integer[] test = gold.clone();
                s.sort(test);
            }
        }
        System.out.println("  end warm up");
    }
}
