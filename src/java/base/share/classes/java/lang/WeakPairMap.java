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
package java.base.share.classes.java.lang;

import java.base.share.classes.java.lang.ref.Reference;
import java.base.share.classes.java.lang.ref.ReferenceQueue;
import java.base.share.classes.java.lang.ref.WeakReference;
import java.util.Collection;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;

/**
 * A WeakHashMap-like data structure that uses a pair of weakly-referenced keys
 * with identity equality semantics to associate a strongly-referenced value.
 * Unlike WeakHashMap, this data structure is thread-safe.
 *
 * @param <K1> the type of 1st key in key pair
 * @param <K2> the type of 2nd key in key pair
 * @param <V>  the type of value
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 24/4/2023
 */

final class WeakPairMap<K1, K2, V> {

    private final ConcurrentHashMap<Pair<K1, K2>, V> map = new ConcurrentHashMap<>();
    private final ReferenceQueue<Object> queue = new ReferenceQueue<>();

    /**
     * Tests if the specified pair of keys are associated with a value
     * in the WeakPairMap.
     *
     * @param k1 the 1st of the pair of keys
     * @param k2 the 2nd of the pair of keys
     * @return true if and only if the specified key pair is in this WeakPairMap,
     * as determined by the identity comparison; false otherwise
     * @throws NullPointerException if any of the specified keys is null
     */
    public boolean containsKeyPair(K1 k1, K2 k2) {
        expungeStaleAssociations();
        return map.containsKey(Pair.lookup(k1, k2));
    }

    /**
     * Returns the value to which the specified pair of keys is mapped, or null
     * if this WeakPairMap contains no mapping for the key pair.
     * <p>More formally, if this WeakPairMap contains a mapping from a key pair
     * {@code (_k1, _k2)} to a value {@code v} such that
     * {@code k1 == _k1 && k2 == _k2}, then this method returns {@code v};
     * otherwise it returns {@code null}.
     * (There can be at most one such mapping.)
     *
     * @param k1 the 1st of the pair of keys for which the mapped value is to
     *           be returned
     * @param k2 the 2nd of the pair of keys for which the mapped value is to
     *           be returned
     * @return the value to which the specified key pair is mapped, or null if
     * this map contains no mapping for the key pair
     * @throws NullPointerException if any of the specified keys is null
     */
    public V get(K1 k1, K2 k2) {
        expungeStaleAssociations();
        return map.get(Pair.lookup(k1, k2));
    }

    /**
     * Maps the specified key pair to the specified value in this WeakPairMap.
     * Neither the keys nor the value can be null.
     * <p>The value can be retrieved by calling the {@link #get} method
     * with the same keys (compared by identity).
     *
     * @param k1 the 1st of the pair of keys with which the specified value is to
     *           be associated
     * @param k2 the 2nd of the pair of keys with which the specified value is to
     *           be associated
     * @param v  value to be associated with the specified key pair
     * @return the previous value associated with key pair, or {@code null} if
     * there was no mapping for key pair
     * @throws NullPointerException if any of the specified keys or value is null
     */
    public V put(K1 k1, K2 k2, V v) {
        expungeStaleAssociations();
        return map.put(Pair.weak(k1, k2, queue), v);
    }

    /**
     * If the specified key pair is not already associated with a value,
     * associates it with the given value and returns {@code null}, else does
     * nothing and returns the currently associated value.
     *
     * @param k1 the 1st of the pair of keys with which the specified value is to
     *           be associated
     * @param k2 the 2nd of the pair of keys with which the specified value is to
     *           be associated
     * @param v  value to be associated with the specified key pair
     * @return the previous value associated with key pair, or {@code null} if
     * there was no mapping for key pair
     * @throws NullPointerException if any of the specified keys or value is null
     */
    public V putIfAbsent(K1 k1, K2 k2, V v) {
        expungeStaleAssociations();
        return map.putIfAbsent(Pair.weak(k1, k2, queue), v);
    }

    /**
     * If the specified key pair is not already associated with a value,
     * attempts to compute its value using the given mapping function
     * and enters it into this WeakPairMap unless {@code null}. The entire
     * method invocation is performed atomically, so the function is
     * applied at most once per key pair. Some attempted update operations
     * on this WeakPairMap by other threads may be blocked while computation
     * is in progress, so the computation should be short and simple,
     * and must not attempt to update any other mappings of this WeakPairMap.
     *
     * @param k1              the 1st of the pair of keys with which the
     *                        computed value is to be associated
     * @param k2              the 2nd of the pair of keys with which the
     *                        computed value is to be associated
     * @param mappingFunction the function to compute a value
     * @return the current (existing or computed) value associated with
     * the specified key pair, or null if the computed value is null
     * @throws NullPointerException  if any of the specified keys or
     *                               mappingFunction is null
     * @throws IllegalStateException if the computation detectably
     *                               attempts a recursive update to this map
     *                               that would otherwise never complete
     * @throws RuntimeException      or Error if the mappingFunction does so, in
     *                               which case the mapping is left unestablished
     */
    public V computeIfAbsent(K1 k1, K2 k2,
                             BiFunction<? super K1, ? super K2, ? extends V>
                                 mappingFunction) {
        expungeStaleAssociations();
        try {
            return map.computeIfAbsent(
                Pair.weak(k1, k2, queue),
                pair -> mappingFunction.apply(pair.first(), pair.second()));
        } finally {
            Reference.reachabilityFence(k1);
            Reference.reachabilityFence(k2);
        }
    }

    /**
     * Returns a {@link Collection} view of the values contained in this
     * WeakPairMap. The collection is backed by the WeakPairMap, so changes to
     * the map are reflected in the collection, and vice-versa.  The collection
     * supports element removal, which removes the corresponding
     * mapping from this map, via the {@code Iterator.remove},
     * {@code Collection.remove}, {@code removeAll},
     * {@code retainAll}, and {@code clear} operations.  It does not
     * support the {@code add} or {@code addAll} operations.
     *
     * @return the collection view
     */
    public Collection<V> values() {
        expungeStaleAssociations();
        return map.values();
    }

    /**
     * Removes associations from this WeakPairMap for which at least one of the
     * keys in key pair has been found weakly-reachable and corresponding
     * WeakRefPeer(s) enqueued. Called as part of each public operation.
     */
    private void expungeStaleAssociations() {
        WeakRefPeer<?> peer;
        while ((peer = (WeakRefPeer<?>) queue.poll()) != null) {
            map.remove(peer.weakPair());
        }
    }

    /**
     * Common interface of both {@link Weak} and {@link Lookup} key pairs.
     */
    private interface Pair<K1, K2> {

        static <K1, K2> Pair<K1, K2> weak(K1 k1, K2 k2,
                                          ReferenceQueue<Object> queue) {
            return new Weak<>(k1, k2, queue);
        }

        static <K1, K2> Pair<K1, K2> lookup(K1 k1, K2 k2) {
            return new Lookup<>(k1, k2);
        }

        /**
         * @return The 1st of the pair of keys (may be null for {@link Weak}
         * when it gets cleared)
         */
        K1 first();

        /**
         * @return The 2nd of the pair of keys (may be null for {@link Weak}
         * when it gets cleared)
         */
        K2 second();

        static int hashCode(Object first, Object second) {
            // assert first != null && second != null;
            return System.identityHashCode(first) ^
                   System.identityHashCode(second);
        }

        static boolean equals(Object first, Object second, Pair<?, ?> p) {
            return first != null && second != null &&
                   first == p.first() && second == p.second();
        }

        /**
         * A Pair where both keys are weakly-referenced.
         * It is composed of two instances of {@link WeakRefPeer}s:
         * <pre>{@code
         *
         *     +-referent-> [K1]                +-referent-> [K2]
         *     |                                |
         *   +----------------+               +----------------+
         *   | Pair.Weak <:   |-----peer----->| (anonymous) <: |
         *   | WeakRefPeer,   |               | WeakRefPeer    |
         *   | Pair           |<--weakPair()--|                |
         *   +----------------+               +----------------+
         *     |            ^
         *     |            |
         *     +-weakPair()-+
         *
         * }</pre>
         * <p>
         * Pair.Weak is used for CHM keys. Both peers are associated with the
         * same {@link ReferenceQueue} so when either of their referents
         * becomes weakly-reachable, the corresponding entries can be
         * {@link #expungeStaleAssociations() expunged} from the map.
         */
        final class Weak<K1, K2> extends WeakRefPeer<K1> implements Pair<K1, K2> {

            // saved hash so it can be retrieved after the reference is cleared
            private final int hash;
            // link to <K2> peer
            private final WeakRefPeer<K2> peer;

            Weak(K1 k1, K2 k2, ReferenceQueue<Object> queue) {
                super(k1, queue);
                hash = Pair.hashCode(k1, k2);
                peer = new WeakRefPeer<>(k2, queue) {
                    // link back to <K1> peer
                    @Override
                    Weak<?, ?> weakPair() { return Weak.this; }
                };
            }

            @Override
            Weak<?, ?> weakPair() {
                return this;
            }

            @Override
            public K1 first() {
                return get();
            }

            @Override
            public K2 second() {
                return peer.get();
            }

            @Override
            public int hashCode() {
                return hash;
            }

            @Override
            public boolean equals(Object obj) {
                return this == obj ||
                       (obj instanceof Pair &&
                        Pair.equals(first(), second(), (Pair<?, ?>) obj));
            }
        }

        /**
         * Optimized lookup Pair, used as lookup key in methods like
         * {@link java.util.Map#get(Object)} or
         * {@link java.util.Map#containsKey(Object)}) where
         * there is a great chance its allocation is eliminated
         * by escape analysis when such lookups are inlined by JIT.
         * All its methods are purposely designed so that 'this' is never
         * passed to any other method or used as identity.
         */
        final class Lookup<K1, K2> implements Pair<K1, K2> {
            private final K1 k1;
            private final K2 k2;

            Lookup(K1 k1, K2 k2) {
                this.k1 = Objects.requireNonNull(k1);
                this.k2 = Objects.requireNonNull(k2);
            }

            @Override
            public K1 first() {
                return k1;
            }

            @Override
            public K2 second() {
                return k2;
            }

            @Override
            public int hashCode() {
                return Pair.hashCode(k1, k2);
            }

            @Override
            public boolean equals(Object obj) {
                return obj instanceof Pair &&
                       Pair.equals(k1, k2, (Pair<?, ?>) obj);
            }
        }
    }

    /**
     * Common abstract supertype of a pair of WeakReference peers.
     */
    private abstract static class WeakRefPeer<K> extends WeakReference<K> {

        WeakRefPeer(K k, ReferenceQueue<Object> queue) {
            super(Objects.requireNonNull(k), queue);
        }

        /**
         * @return the {@link Pair.Weak} side of the pair of peers.
         */
        abstract Pair.Weak<?, ?> weakPair();
    }
}
