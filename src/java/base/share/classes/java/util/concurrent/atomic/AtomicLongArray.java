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

/*
 * This file is available under and governed by the GNU General Public
 * License version 2 only, as published by the Free Software Foundation.
 * However, the following notice accompanied the original version of this
 * file:
 *
 * Written by Doug Lea with assistance from members of JCP JSR-166
 * Expert Group and released to the public domain, as explained at
 * http://creativecommons.org/publicdomain/zero/1.0/
 */

package java.base.share.classes.java.util.concurrent.atomic;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;
import java.util.function.LongBinaryOperator;
import java.util.function.LongUnaryOperator;

/**
 * A {@code long} array in which elements may be updated atomically.
 * See the {@link VarHandle} specification for descriptions of the
 * properties of atomic accesses.
 * @since 1.5
 * @author Doug Lea
 */
public class AtomicLongArray implements java.io.Serializable {
    private static final long serialVersionUID = -2308431214976778248L;
    private static final VarHandle AA
        = MethodHandles.arrayElementVarHandle(long[].class);
    private final long[] array;

    /**
     * Creates a new AtomicLongArray of the given length, with all
     * elements initially zero.
     *
     * @param length the length of the array
     */
    public AtomicLongArray(int length) {
        array = new long[length];
    }

    /**
     * Creates a new AtomicLongArray with the same length as, and
     * all elements copied from, the given array.
     *
     * @param array the array to copy elements from
     * @throws NullPointerException if array is null
     */
    public AtomicLongArray(long[] array) {
        // Visibility guaranteed by final field guarantees
        this.array = array.clone();
    }

    /**
     * Returns the length of the array.
     *
     * @return the length of the array
     */
    public final int length() {
        return array.length;
    }

    /**
     * Returns the current value of the element at index {@code i},
     * with memory effects as specified by {@link VarHandle#getVolatile}.
     *
     * @param i the index
     * @return the current value
     */
    public final long get(int i) {
        return (long)AA.getVolatile(array, i);
    }

    /**
     * Sets the element at index {@code i} to {@code newValue},
     * with memory effects as specified by {@link VarHandle#setVolatile}.
     *
     * @param i the index
     * @param newValue the new value
     */
    public final void set(int i, long newValue) {
        AA.setVolatile(array, i, newValue);
    }

    /**
     * Sets the element at index {@code i} to {@code newValue},
     * with memory effects as specified by {@link VarHandle#setRelease}.
     *
     * @param i the index
     * @param newValue the new value
     * @since 1.6
     */
    public final void lazySet(int i, long newValue) {
        AA.setRelease(array, i, newValue);
    }

    /**
     * Atomically sets the element at index {@code i} to {@code
     * newValue} and returns the old value,
     * with memory effects as specified by {@link VarHandle#getAndSet}.
     *
     * @param i the index
     * @param newValue the new value
     * @return the previous value
     */
    public final long getAndSet(int i, long newValue) {
        return (long)AA.getAndSet(array, i, newValue);
    }

    /**
     * Atomically sets the element at index {@code i} to {@code newValue}
     * if the element's current value {@code == expectedValue},
     * with memory effects as specified by {@link VarHandle#compareAndSet}.
     *
     * @param i the index
     * @param expectedValue the expected value
     * @param newValue the new value
     * @return {@code true} if successful. False return indicates that
     * the actual value was not equal to the expected value.
     */
    public final boolean compareAndSet(int i, long expectedValue, long newValue) {
        return AA.compareAndSet(array, i, expectedValue, newValue);
    }

    /**
     * Possibly atomically sets the element at index {@code i} to
     * {@code newValue} if the element's current value {@code == expectedValue},
     * with memory effects as specified by {@link VarHandle#weakCompareAndSetPlain}.
     *
     * @deprecated This method has plain memory effects but the method
     * name implies volatile memory effects (see methods such as
     * {@link #compareAndExchange} and {@link #compareAndSet}).  To avoid
     * confusion over plain or volatile memory effects it is recommended that
     * the method {@link #weakCompareAndSetPlain} be used instead.
     *
     * @param i the index
     * @param expectedValue the expected value
     * @param newValue the new value
     * @return {@code true} if successful
     * @see #weakCompareAndSetPlain
     */
    @Deprecated(since="9")
    public final boolean weakCompareAndSet(int i, long expectedValue, long newValue) {
        return AA.weakCompareAndSetPlain(array, i, expectedValue, newValue);
    }

    /**
     * Possibly atomically sets the element at index {@code i} to
     * {@code newValue} if the element's current value {@code == expectedValue},
     * with memory effects as specified by {@link VarHandle#weakCompareAndSetPlain}.
     *
     * @param i the index
     * @param expectedValue the expected value
     * @param newValue the new value
     * @return {@code true} if successful
     * @since 9
     */
    public final boolean weakCompareAndSetPlain(int i, long expectedValue, long newValue) {
        return AA.weakCompareAndSetPlain(array, i, expectedValue, newValue);
    }

    /**
     * Atomically increments the value of the element at index {@code i},
     * with memory effects as specified by {@link VarHandle#getAndAdd}.
     *
     * <p>Equivalent to {@code getAndAdd(i, 1)}.
     *
     * @param i the index
     * @return the previous value
     */
    public final long getAndIncrement(int i) {
        return (long)AA.getAndAdd(array, i, 1L);
    }

    /**
     * Atomically decrements the value of the element at index {@code i},
     * with memory effects as specified by {@link VarHandle#getAndAdd}.
     *
     * <p>Equivalent to {@code getAndAdd(i, -1)}.
     *
     * @param i the index
     * @return the previous value
     */
    public final long getAndDecrement(int i) {
        return (long)AA.getAndAdd(array, i, -1L);
    }

    /**
     * Atomically adds the given value to the element at index {@code i},
     * with memory effects as specified by {@link VarHandle#getAndAdd}.
     *
     * @param i the index
     * @param delta the value to add
     * @return the previous value
     */
    public final long getAndAdd(int i, long delta) {
        return (long)AA.getAndAdd(array, i, delta);
    }

    /**
     * Atomically increments the value of the element at index {@code i},
     * with memory effects as specified by {@link VarHandle#getAndAdd}.
     *
     * <p>Equivalent to {@code addAndGet(i, 1)}.
     *
     * @param i the index
     * @return the updated value
     */
    public final long incrementAndGet(int i) {
        return (long)AA.getAndAdd(array, i, 1L) + 1L;
    }

    /**
     * Atomically decrements the value of the element at index {@code i},
     * with memory effects as specified by {@link VarHandle#getAndAdd}.
     *
     * <p>Equivalent to {@code addAndGet(i, -1)}.
     *
     * @param i the index
     * @return the updated value
     */
    public final long decrementAndGet(int i) {
        return (long)AA.getAndAdd(array, i, -1L) - 1L;
    }

    /**
     * Atomically adds the given value to the element at index {@code i},
     * with memory effects as specified by {@link VarHandle#getAndAdd}.
     *
     * @param i the index
     * @param delta the value to add
     * @return the updated value
     */
    public long addAndGet(int i, long delta) {
        return (long)AA.getAndAdd(array, i, delta) + delta;
    }

    /**
     * Atomically updates (with memory effects as specified by {@link
     * VarHandle#compareAndSet}) the element at index {@code i} with
     * the results of applying the given function, returning the
     * previous value. The function should be side-effect-free, since
     * it may be re-applied when attempted updates fail due to
     * contention among threads.
     *
     * @param i the index
     * @param updateFunction a side-effect-free function
     * @return the previous value
     * @since 1.8
     */
    public final long getAndUpdate(int i, LongUnaryOperator updateFunction) {
        long prev = get(i), next = 0L;
        for (boolean haveNext = false;;) {
            if (!haveNext)
                next = updateFunction.applyAsLong(prev);
            if (weakCompareAndSetVolatile(i, prev, next))
                return prev;
            haveNext = (prev == (prev = get(i)));
        }
    }

    /**
     * Atomically updates (with memory effects as specified by {@link
     * VarHandle#compareAndSet}) the element at index {@code i} with
     * the results of applying the given function, returning the
     * updated value. The function should be side-effect-free, since it
     * may be re-applied when attempted updates fail due to contention
     * among threads.
     *
     * @param i the index
     * @param updateFunction a side-effect-free function
     * @return the updated value
     * @since 1.8
     */
    public final long updateAndGet(int i, LongUnaryOperator updateFunction) {
        long prev = get(i), next = 0L;
        for (boolean haveNext = false;;) {
            if (!haveNext)
                next = updateFunction.applyAsLong(prev);
            if (weakCompareAndSetVolatile(i, prev, next))
                return next;
            haveNext = (prev == (prev = get(i)));
        }
    }

    /**
     * Atomically updates (with memory effects as specified by {@link
     * VarHandle#compareAndSet}) the element at index {@code i} with
     * the results of applying the given function to the current and
     * given values, returning the previous value. The function should
     * be side-effect-free, since it may be re-applied when attempted
     * updates fail due to contention among threads.  The function is
     * applied with the current value of the element at index {@code i}
     * as its first argument, and the given update as the second
     * argument.
     *
     * @param i the index
     * @param x the update value
     * @param accumulatorFunction a side-effect-free function of two arguments
     * @return the previous value
     * @since 1.8
     */
    public final long getAndAccumulate(int i, long x,
                                      LongBinaryOperator accumulatorFunction) {
        long prev = get(i), next = 0L;
        for (boolean haveNext = false;;) {
            if (!haveNext)
                next = accumulatorFunction.applyAsLong(prev, x);
            if (weakCompareAndSetVolatile(i, prev, next))
                return prev;
            haveNext = (prev == (prev = get(i)));
        }
    }

    /**
     * Atomically updates (with memory effects as specified by {@link
     * VarHandle#compareAndSet}) the element at index {@code i} with
     * the results of applying the given function to the current and
     * given values, returning the updated value. The function should
     * be side-effect-free, since it may be re-applied when attempted
     * updates fail due to contention among threads.  The function is
     * applied with the current value of the element at index {@code i}
     * as its first argument, and the given update as the second
     * argument.
     *
     * @param i the index
     * @param x the update value
     * @param accumulatorFunction a side-effect-free function of two arguments
     * @return the updated value
     * @since 1.8
     */
    public final long accumulateAndGet(int i, long x,
                                      LongBinaryOperator accumulatorFunction) {
        long prev = get(i), next = 0L;
        for (boolean haveNext = false;;) {
            if (!haveNext)
                next = accumulatorFunction.applyAsLong(prev, x);
            if (weakCompareAndSetVolatile(i, prev, next))
                return next;
            haveNext = (prev == (prev = get(i)));
        }
    }

    /**
     * Returns the String representation of the current values of array.
     * @return the String representation of the current values of array
     */
    public String toString() {
        int iMax = array.length - 1;
        if (iMax == -1)
            return "[]";

        StringBuilder b = new StringBuilder();
        b.append('[');
        for (int i = 0; ; i++) {
            b.append(get(i));
            if (i == iMax)
                return b.append(']').toString();
            b.append(',').append(' ');
        }
    }

    // jdk9

    /**
     * Returns the current value of the element at index {@code i},
     * with memory semantics of reading as if the variable was declared
     * non-{@code volatile}.
     *
     * @param i the index
     * @return the value
     * @since 9
     */
    public final long getPlain(int i) {
        return (long)AA.get(array, i);
    }

    /**
     * Sets the element at index {@code i} to {@code newValue},
     * with memory semantics of setting as if the variable was
     * declared non-{@code volatile} and non-{@code final}.
     *
     * @param i the index
     * @param newValue the new value
     * @since 9
     */
    public final void setPlain(int i, long newValue) {
        AA.set(array, i, newValue);
    }

    /**
     * Returns the current value of the element at index {@code i},
     * with memory effects as specified by {@link VarHandle#getOpaque}.
     *
     * @param i the index
     * @return the value
     * @since 9
     */
    public final long getOpaque(int i) {
        return (long)AA.getOpaque(array, i);
    }

    /**
     * Sets the element at index {@code i} to {@code newValue},
     * with memory effects as specified by {@link VarHandle#setOpaque}.
     *
     * @param i the index
     * @param newValue the new value
     * @since 9
     */
    public final void setOpaque(int i, long newValue) {
        AA.setOpaque(array, i, newValue);
    }

    /**
     * Returns the current value of the element at index {@code i},
     * with memory effects as specified by {@link VarHandle#getAcquire}.
     *
     * @param i the index
     * @return the value
     * @since 9
     */
    public final long getAcquire(int i) {
        return (long)AA.getAcquire(array, i);
    }

    /**
     * Sets the element at index {@code i} to {@code newValue},
     * with memory effects as specified by {@link VarHandle#setRelease}.
     *
     * @param i the index
     * @param newValue the new value
     * @since 9
     */
    public final void setRelease(int i, long newValue) {
        AA.setRelease(array, i, newValue);
    }

    /**
     * Atomically sets the element at index {@code i} to {@code newValue}
     * if the element's current value, referred to as the <em>witness
     * value</em>, {@code == expectedValue},
     * with memory effects as specified by
     * {@link VarHandle#compareAndExchange}.
     *
     * @param i the index
     * @param expectedValue the expected value
     * @param newValue the new value
     * @return the witness value, which will be the same as the
     * expected value if successful
     * @since 9
     */
    public final long compareAndExchange(int i, long expectedValue, long newValue) {
        return (long)AA.compareAndExchange(array, i, expectedValue, newValue);
    }

    /**
     * Atomically sets the element at index {@code i} to {@code newValue}
     * if the element's current value, referred to as the <em>witness
     * value</em>, {@code == expectedValue},
     * with memory effects as specified by
     * {@link VarHandle#compareAndExchangeAcquire}.
     *
     * @param i the index
     * @param expectedValue the expected value
     * @param newValue the new value
     * @return the witness value, which will be the same as the
     * expected value if successful
     * @since 9
     */
    public final long compareAndExchangeAcquire(int i, long expectedValue, long newValue) {
        return (long)AA.compareAndExchangeAcquire(array, i, expectedValue, newValue);
    }

    /**
     * Atomically sets the element at index {@code i} to {@code newValue}
     * if the element's current value, referred to as the <em>witness
     * value</em>, {@code == expectedValue},
     * with memory effects as specified by
     * {@link VarHandle#compareAndExchangeRelease}.
     *
     * @param i the index
     * @param expectedValue the expected value
     * @param newValue the new value
     * @return the witness value, which will be the same as the
     * expected value if successful
     * @since 9
     */
    public final long compareAndExchangeRelease(int i, long expectedValue, long newValue) {
        return (long)AA.compareAndExchangeRelease(array, i, expectedValue, newValue);
    }

    /**
     * Possibly atomically sets the element at index {@code i} to
     * {@code newValue} if the element's current value {@code == expectedValue},
     * with memory effects as specified by
     * {@link VarHandle#weakCompareAndSet}.
     *
     * @param i the index
     * @param expectedValue the expected value
     * @param newValue the new value
     * @return {@code true} if successful
     * @since 9
     */
    public final boolean weakCompareAndSetVolatile(int i, long expectedValue, long newValue) {
        return AA.weakCompareAndSet(array, i, expectedValue, newValue);
    }

    /**
     * Possibly atomically sets the element at index {@code i} to
     * {@code newValue} if the element's current value {@code == expectedValue},
     * with memory effects as specified by
     * {@link VarHandle#weakCompareAndSetAcquire}.
     *
     * @param i the index
     * @param expectedValue the expected value
     * @param newValue the new value
     * @return {@code true} if successful
     * @since 9
     */
    public final boolean weakCompareAndSetAcquire(int i, long expectedValue, long newValue) {
        return AA.weakCompareAndSetAcquire(array, i, expectedValue, newValue);
    }

    /**
     * Possibly atomically sets the element at index {@code i} to
     * {@code newValue} if the element's current value {@code == expectedValue},
     * with memory effects as specified by
     * {@link VarHandle#weakCompareAndSetRelease}.
     *
     * @param i the index
     * @param expectedValue the expected value
     * @param newValue the new value
     * @return {@code true} if successful
     * @since 9
     */
    public final boolean weakCompareAndSetRelease(int i, long expectedValue, long newValue) {
        return AA.weakCompareAndSetRelease(array, i, expectedValue, newValue);
    }

}
