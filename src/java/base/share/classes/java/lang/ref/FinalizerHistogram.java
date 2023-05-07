/*
 * Geo Studios Protective License
 *
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 *
 * Whoever collects this software or tool may not distribute the copy that has been obtained.
 *
 * This software or tool may not be used to gain a commercial or monetary advantage.
 *
 * Copyright will be included in any software or tool using this license, no matter the size or type of software or tool.
 *
 * This software or tool is not under any patent, but the software or tool shall not be
 * sold or uploaded as some other product or without the original creators consent and
 * permission. If the following happens, consequences will occur due to following
 * instructions or not following the rules written in this document.
 */

package java.base.share.classes.java.lang.ref;

import java.base.share.classes.java.util.Map;
import java.base.share.classes.java.util.HashMap;
import java.base.share.classes.java.util.Arrays;
import java.base.share.classes.java.util.Comparator;

/**
 * This FinalizerHistogram class is for GC.finalizer_info diagnostic command support.
 * It is invoked by the VM.
 *
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 3/5/2023
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

        Entry fhe[] = countMap.values().toArray(new Entry[countMap.size()]);
        Arrays.sort(fhe,
                Comparator.comparingInt(Entry::getInstanceCount).reversed());
        return fhe;
    }
}
