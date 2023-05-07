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

package java.base.share.classes.jdk.internal.vm;

import java.util.stream.Stream;

/**
 * A container of threads.
 */
public abstract class ThreadContainer extends StackableScope {

    /**
     * Creates a ThreadContainer.
     * @param shared true for a shared container, false for a container
     * owned by the current thread
     */
    protected ThreadContainer(boolean shared) {
        super(shared);
    }

    /**
     * Returns the parent of this container or null if this is the root container.
     */
    public ThreadContainer parent() {
        return ThreadContainers.parent(this);
    }

    /**
     * Return the stream of children of this container.
     */
    public final Stream<ThreadContainer> children() {
        return ThreadContainers.children(this);
    }

    /**
     * Return a count of the number of threads in this container.
     */
    public long threadCount() {
        return threads().mapToLong(e -> 1L).sum();
    }

    /**
     * Returns a stream of the live threads in this container.
     */
    public abstract Stream<Thread> threads();

    /**
     * Invoked by Thread::start before the given Thread is started.
     */
    public void onStart(Thread thread) {
        // do nothing
    }

    /**
     * Invoked when a Thread terminates or starting it fails.
     *
     * For a platform thread, this method is invoked by the thread itself when it
     * terminates. For a virtual thread, this method is invoked on its carrier
     * after the virtual thread has terminated.
     *
     * If starting the Thread failed then this method is invoked on the thread
     * that invoked onStart.
     */
    public void onExit(Thread thread) {
        // do nothing
    }

    /**
     * The scoped values captured when the thread container was created.
     */
    public ScopedValueContainer.BindingsSnapshot scopedValueBindings() {
        return null;
    }
}
