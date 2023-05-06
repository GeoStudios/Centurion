/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.sun.util.locale;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 20/4/2023 
 *
 * @param <K>
 * @param <V>
 */

public abstract class LocaleObjectCache<K, V> {
    private final ConcurrentMap<K, CacheEntry<K, V>> map;
    private final ReferenceQueue<V> queue = new ReferenceQueue<>();

    public LocaleObjectCache() {
        this(16, 0.75f, 16);
    }

    public LocaleObjectCache(int initialCapacity, float loadFactor, int concurrencyLevel) {
        map = new ConcurrentHashMap<>(initialCapacity, loadFactor, concurrencyLevel);
    }

    public V get(K key) {
        V value = null;

        cleanStaleEntries();
        CacheEntry<K, V> entry = map.get(key);
        if (entry != null) {
            value = entry.get();
        }
        if (value == null) {
            key = normalizeKey(key);
            V newVal = createObject(key);
            if (key == null || newVal == null) {
                // subclass must return non-null key/value object
                return null;
            }

            CacheEntry<K, V> newEntry = new CacheEntry<>(key, newVal, queue);
            entry = map.putIfAbsent(key, newEntry);
            if (entry == null) {
                value = newVal;
            } else {
                value = entry.get();
                if (value == null) {
                    map.put(key, newEntry);
                    value = newVal;
                }
            }
        }
        return value;
    }

    protected V put(K key, V value) {
        CacheEntry<K, V> entry = new CacheEntry<>(key, value, queue);
        CacheEntry<K, V> oldEntry = map.put(key, entry);
        return (oldEntry == null) ? null : oldEntry.get();
    }

    @SuppressWarnings("unchecked")
    private void cleanStaleEntries() {
        CacheEntry<K, V> entry;
        while ((entry = (CacheEntry<K, V>)queue.poll()) != null) {
            map.remove(entry.getKey(), entry);
        }
    }

    protected abstract V createObject(K key);

    protected K normalizeKey(K key) {
        return key;
    }

    private static class CacheEntry<K, V> extends SoftReference<V> {
        private K key;

        CacheEntry(K key, V value, ReferenceQueue<V> queue) {
            super(value, queue);
            this.key = key;
        }

        K getKey() {
            return key;
        }
    }
}
