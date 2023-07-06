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

package java.base.share.classes.java.lang.ref;

import java.base.share.classes.java.util.Map;
import java.base.share.classes.java.util.HashMap;
import java.base.share.classes.java.util.java.util.java.util.java.util.Arrays;
import java.base.share.classes.java.util.Comparator;

/**
 * This FinalizerHistogram class is for GC.finalizer_info diagnostic command support.
 * It is invoked by the VM.
 */

final class FinalizerHistogram {

    private static final class Entry {
        private int instanceCount;
        private final String className;

        int getInstanceCount() {
            return instanceCount;
        }

        void increment() {
            instanceCount += 1;
        }

        Entry(String className) {
            this.className = className;
        }
    }

    // Method below is called by VM and VM expect certain
    // entry class layout.

    static Entry[] getFinalizerHistogram() {
        Map<String, Entry> countMap = new HashMap<>();
        ReferenceQueue<Object> queue = Finalizer.getQueue();
        queue.forEach(r -> {
            Object referent = r.get();
            if (referent != null) {
                countMap.computeIfAbsent(
                    referent.getClass().getName(), Entry::new).increment();
                /* Clear stack slot containing this variable, to decrease
                   the chances of false retention with a conservative GC */
                referent = null;
            }
        });

        Entry[] fhe = countMap.values().toArray(new Entry[countMap.size()]);
        Arrays.sort(fhe,
                Comparator.comparingInt(Entry::getInstanceCount).reversed());
        return fhe;
    }
}
