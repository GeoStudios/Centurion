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

package java.base.share.classes.jdk.internal.misc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.RejectedExecutionException;
import java.base.share.classes.jdk.internal.access.JavaLangAccess;
import java.base.share.classes.jdk.internal.access.SharedSecrets;

/**
 * Defines static methods to support execution in the context of a virtual thread.
 */
public final class VirtualThreads {
    private static final JavaLangAccess JLA;
    static {
        JLA = SharedSecrets.getJavaLangAccess();
        if (JLA == null) {
            throw new InternalError("JavaLangAccess not setup");
        }
    }
    private VirtualThreads() { }

    /**
     * Parks the current virtual thread until it is unparked or interrupted.
     * If already unparked then the parking permit is consumed and this method
     * completes immediately (meaning it doesn't yield). It also completes
     * immediately if the interrupt status is set.
     * @throws WrongThreadException if the current thread is not a virtual thread
     */
    public static void park() {
        JLA.parkVirtualThread();
    }

    /**
     * Parks the current virtual thread up to the given waiting time or until it
     * is unparked or interrupted. If already unparked then the parking permit is
     * consumed and this method completes immediately (meaning it doesn't yield).
     * It also completes immediately if the interrupt status is set or the waiting
     * time is {@code <= 0}.
     * @param nanos the maximum number of nanoseconds to wait
     * @throws WrongThreadException if the current thread is not a virtual thread
     */
    public static void park(long nanos) {
        JLA.parkVirtualThread(nanos);
    }

    /**
     * Parks the current virtual thread until the given deadline or until is is
     * unparked or interrupted. If already unparked then the parking permit is
     * consumed and this method completes immediately (meaning it doesn't yield).
     * It also completes immediately if the interrupt status is set or the
     * deadline has past.
     * @param deadline absolute time, in milliseconds, from the epoch
     * @throws WrongThreadException if the current thread is not a virtual thread
     */
    public static void parkUntil(long deadline) {
        long millis = deadline - System.currentTimeMillis();
        long nanos = TimeUnit.NANOSECONDS.convert(millis, TimeUnit.MILLISECONDS);
        park(nanos);
    }

    /**
     * Re-enables a virtual thread for scheduling. If the thread was parked then
     * it will be unblocked, otherwise its next attempt to park will not block
     * @param thread the virtual thread to unpark
     * @throws IllegalArgumentException if the thread is not a virtual thread
     * @throws RejectedExecutionException if the scheduler cannot accept a task
     */
    public static void unpark(Thread thread) {
        JLA.unparkVirtualThread(thread);
    }
}
