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

import java.util.concurrent.locks.ReentrantLock;

/**
 * A reentrant mutual exclusion lock for internal use. The lock does not
 * implement {@link java.util.concurrent.locks.Lock} or extend {@link
 * java.util.concurrent.locks.ReentrantLock} so that it can be distinguished
 * from lock objects accessible to subclasses of {@link java.io.Reader} and
 * {@link java.io.Writer} (it is possible to create a Reader that uses a
 * lock object of type ReentrantLock for example).
 */
public class InternalLock {
    private static final boolean CAN_USE_INTERNAL_LOCK;
    static {
        String s = System.getProperty("jdk.io.useMonitors");
        if (s != null && (s.isEmpty() || s.equals("true"))) {
            CAN_USE_INTERNAL_LOCK = false;
        } else {
            CAN_USE_INTERNAL_LOCK = true;
        }
    }

    private final ReentrantLock lock;

    private InternalLock() {
        this.lock = new ReentrantLock();
    }

    /**
     * Returns a new InternalLock or null.
     */
    public static InternalLock newLockOrNull() {
        return (CAN_USE_INTERNAL_LOCK) ? new InternalLock() : null;
    }

    /**
     * Returns a new InternalLock or the given object.
     */
    public static Object newLockOr(Object obj) {
        return (CAN_USE_INTERNAL_LOCK) ? new InternalLock() : obj;
    }

    public boolean tryLock() {
        return lock.tryLock();
    }

    public void lock() {
        lock.lock();
    }

    public void unlock() {
        lock.unlock();
    }

    public boolean isHeldByCurrentThread() {
        return lock.isHeldByCurrentThread();
    }
}
