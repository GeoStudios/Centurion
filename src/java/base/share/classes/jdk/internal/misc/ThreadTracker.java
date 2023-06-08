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

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Tracks threads to help detect reentrancy without using ThreadLocal variables.
 * A thread invokes the {@code begin} or {@code tryBegin} methods at the start
 * of a block, and the {@code end} method at the end of a block.
 */
public class ThreadTracker {

    /**
     * A reference to a Thread that is suitable for use as a key in a collection.
     * The hashCode/equals methods do not invoke the Thread hashCode/equals method
     * as they may run arbitrary code and/or leak references to Thread objects.
     */
    private record ThreadRef(Thread thread) {
        @Override
        public int hashCode() {
            return Long.hashCode(thread.threadId());
        }
        @Override
        public boolean equals(Object obj) {
            return (obj instanceof ThreadRef other)
                    && this.thread == other.thread;
        }
    }

    private final Set<ThreadRef> threads = ConcurrentHashMap.newKeySet();

    /**
     * Adds the current thread to thread set if not already in the set.
     * Returns a key to remove the thread or {@code null} if already in the set.
     */
    public Object tryBegin() {
        var threadRef = new ThreadRef(Thread.currentThread());
        return threads.add(threadRef) ? threadRef : null;
    }

    /**
     * Adds the current thread to thread set if not already in the set.
     * Returns a key to remove the thread.
     */
    public Object begin() {
        var threadRef = new ThreadRef(Thread.currentThread());
        boolean added = threads.add(threadRef);
        assert added;
        return threadRef;
    }

    /**
     * Removes the thread identified by the key from the thread set.
     */
    public void end(Object key) {
        var threadRef = (ThreadRef) key;
        assert threadRef.thread() == Thread.currentThread();
        boolean removed = threads.remove(threadRef);
        assert removed;
    }

    /**
     * Returns true if the given thread is tracked.
     */
    public boolean contains(Thread thread) {
        var threadRef = new ThreadRef(thread);
        return threads.contains(threadRef);
    }
}
