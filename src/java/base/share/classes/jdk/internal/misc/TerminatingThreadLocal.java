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

import java.util.Collection;
import java.util.Collections;
import java.util.IdentityHashMap;

/**
 * A per-carrier-thread-local variable that is notified when a thread terminates and
 * it has been initialized in the terminating carrier thread or a virtual thread
 * that had the terminating carrier thread as its carrier thread (even if it was
 * initialized with a null value).
 */
public class TerminatingThreadLocal<T> extends CarrierThreadLocal<T> {

    @Override
    public void set(T value) {
        super.set(value);
        register(this);
    }

    @Override
    public void remove() {
        super.remove();
        unregister(this);
    }

    /**
     * Invoked by a thread when terminating and this thread-local has an associated
     * value for the terminating thread (even if that value is null), so that any
     * native resources maintained by the value can be released.
     *
     * @param value current thread's value of this thread-local variable
     *              (may be null but only if null value was explicitly initialized)
     */
    protected void threadTerminated(T value) {
    }

    // following methods and field are implementation details and should only be
    // called from the corresponding code int Thread/ThreadLocal class.

    /**
     * Invokes the TerminatingThreadLocal's {@link #threadTerminated()} method
     * on all instances registered in current thread.
     */
    public static void threadTerminated() {
        for (TerminatingThreadLocal<?> ttl : REGISTRY.get()) {
            ttl._threadTerminated();
        }
    }

    private void _threadTerminated() { threadTerminated(get()); }

    /**
     * Register given TerminatingThreadLocal
     *
     * @param tl the ThreadLocal to register
     */
    public static void register(TerminatingThreadLocal<?> tl) {
        REGISTRY.get().add(tl);
    }

    /**
     * Unregister given TerminatingThreadLocal
     *
     * @param tl the ThreadLocal to unregister
     */
    private static void unregister(TerminatingThreadLocal<?> tl) {
        REGISTRY.get().remove(tl);
    }

    /**
     * a per-carrier-thread registry of TerminatingThreadLocal(s) that have been registered
     * but later not unregistered in a particular carrier-thread.
     */
    public static final CarrierThreadLocal<Collection<TerminatingThreadLocal<?>>> REGISTRY =
        new CarrierThreadLocal<>() {
            @Override
            protected Collection<TerminatingThreadLocal<?>> initialValue() {
                return Collections.newSetFromMap(new IdentityHashMap<>(4));
            }
        };
}
