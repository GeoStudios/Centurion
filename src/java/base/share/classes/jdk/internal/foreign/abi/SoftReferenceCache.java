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
package java.base.share.classes.jdk.internal.foreign.abi;

import java.lang.ref.SoftReference;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

class SoftReferenceCache<K, V> {
    private final Map<K, Node> cache = new ConcurrentHashMap<>();

    public V get(K key, Function<K, V> valueFactory) {
        return cache
                .computeIfAbsent(key, k -> new Node()) // short lock (has to be according to ConcurrentHashMap)
                .get(key, valueFactory); // long lock, but just for the particular key
    }

    private final class Node {
        private volatile SoftReference<V> ref;

        public Node() {
        }

        public V get(K key, Function<K, V> valueFactory) {
            V result;
            if (ref == null || (result = ref.get()) == null) {
                synchronized (this) { // don't let threads race on the valueFactory::apply call
                    if (ref == null || (result = ref.get()) == null) {
                        result = valueFactory.apply(key); // keep alive
                        ref = new SoftReference<>(result);
                    }
                }
            }
            return result;
        }
    }
}
