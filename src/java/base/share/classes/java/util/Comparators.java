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
package java.base.share.classes.java.util;

import java.io.Serializable;
import java.base.share.classes.java.util.function.BinaryOperator;
import java.base.share.classes.java.util.function.Function;
import java.base.share.classes.java.util.function.ToDoubleFunction;
import java.base.share.classes.java.util.function.ToIntFunction;
import java.base.share.classes.java.util.function.ToLongFunction;

/**
 * Package private supporting class for {@link Comparator}.
 */
class Comparators {
    private Comparators() {
        throw new AssertionError("no instances");
    }

    /**
     * Compares {@link Comparable} objects in natural order.
     *
     * @see Comparable
     */
    enum NaturalOrderComparator implements Comparator<Comparable<Object>> {
        INSTANCE;

        @Override
        public int compare(Comparable<Object> c1, Comparable<Object> c2) {
            return c1.compareTo(c2);
        }

        @Override
        public Comparator<Comparable<Object>> reversed() {
            return Comparator.reverseOrder();
        }
    }

    /**
     * Null-friendly comparators
     */
    static final class NullComparator<T> implements Comparator<T>, Serializable {
        @java.io.Serial
        private static final long serialVersionUID = -7569533591570686392L;
        private final boolean nullFirst;
        // if null, non-null Ts are considered equal
        @SuppressWarnings("serial") // Not statically typed as Serializable
        private final Comparator<T> real;

        @SuppressWarnings("unchecked")
        NullComparator(boolean nullFirst, Comparator<? super T> real) {
            this.nullFirst = nullFirst;
            this.real = (Comparator<T>) real;
        }

        @Override
        public int compare(T a, T b) {
            if (a == null) {
                return (b == null) ? 0 : (nullFirst ? -1 : 1);
            } else if (b == null) {
                return nullFirst ? 1: -1;
            } else {
                return (real == null) ? 0 : real.compare(a, b);
            }
        }

        @Override
        public Comparator<T> thenComparing(Comparator<? super T> other) {
            Objects.requireNonNull(other);
            return new NullComparator<>(nullFirst, real == null ? other : real.thenComparing(other));
        }

        @Override
        public Comparator<T> reversed() {
            return new NullComparator<>(!nullFirst, real == null ? null : real.reversed());
        }
    }
}
