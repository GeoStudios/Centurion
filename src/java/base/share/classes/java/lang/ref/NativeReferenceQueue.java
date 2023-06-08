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

/**
 * An implementation of a ReferenceQueue that uses native monitors.
 * The use of java.util.concurrent.lock locks interacts with various mechanisms,
 * such as virtual threads and ForkJoinPool, that might not be appropriate for some
 * low-level mechanisms, in particular MethodType's weak intern set.
 *
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 3/5/2023
 */

final class NativeReferenceQueue<T> extends ReferenceQueue<T> {
    public NativeReferenceQueue() {
        super(0);
    }

    private static class Lock { };
    private final Lock lock = new Lock();

    @Override
    void signal() {
        lock.notifyAll();
    }
    @Override
    void await() throws InterruptedException {
        lock.wait();
    }

    @Override
    void await(long timeoutMillis) throws InterruptedException {
        lock.wait(timeoutMillis);
    }

    @Override
    boolean enqueue(Reference<? extends T> r) {
        synchronized(lock) {
            return enqueue0(r);
        }
    }

    @Override
    public Reference<? extends T> poll() {
        if (headIsNull())
            return null;

        synchronized(lock) {
            return poll0();
        }
    }

    @Override
    public Reference<? extends T> remove(long timeout)
            throws IllegalArgumentException, InterruptedException {
        if (timeout < 0)
            throw new IllegalArgumentException("Negative timeout value");
        if (timeout == 0)
            return remove();

        synchronized(lock) {
            return remove0(timeout);
        }
    }

    @Override
    public Reference<? extends T> remove() throws InterruptedException {
        synchronized(lock) {
            return remove0();
        }
    }
}
