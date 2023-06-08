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

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.Stream;
import java.base.share.classes.jdk.internal.access.JavaLangAccess;
import java.base.share.classes.jdk.internal.access.SharedSecrets;

/**
 * A "shared" thread container. A shared thread container doesn't have an owner
 * and is intended for unstructured uses, e.g. thread pools.
 */
public class SharedThreadContainer extends ThreadContainer implements AutoCloseable {
    private static final JavaLangAccess JLA = SharedSecrets.getJavaLangAccess();
    private static final VarHandle CLOSED;
    private static final VarHandle VIRTUAL_THREADS;
    static {
        try {
            MethodHandles.Lookup l = MethodHandles.lookup();
            CLOSED = l.findVarHandle(SharedThreadContainer.class,
                    "closed", boolean.class);
            VIRTUAL_THREADS = l.findVarHandle(SharedThreadContainer.class,
                    "virtualThreads", Set.class);
        } catch (Exception e) {
            throw new InternalError(e);
        }
    }

    // name of container, used by toString
    private final String name;

    // the number of threads in the container
    private final LongAdder threadCount;

    // the virtual threads in the container, created lazily
    private volatile Set<Thread> virtualThreads;

    // the key for this container in the registry
    private volatile Object key;

    // set to true when the container is closed
    private volatile boolean closed;

    /**
     * Initialize a new SharedThreadContainer.
     * @param name the container name, can be null
     */
    private SharedThreadContainer(String name) {
        super(/*shared*/ true);
        this.name = name;
        this.threadCount = new LongAdder();
    }

    /**
     * Creates a shared thread container with the given parent and name.
     * @throws IllegalArgumentException if the parent has an owner.
     */
    public static SharedThreadContainer create(ThreadContainer parent, String name) {
        if (parent.owner() != null)
            throw new IllegalArgumentException("parent has owner");
        var container = new SharedThreadContainer(name);
        // register the container to allow discovery by serviceability tools
        container.key = ThreadContainers.registerContainer(container);
        return container;
    }

    /**
     * Creates a shared thread container with the given name. Its parent will be
     * the root thread container.
     */
    public static SharedThreadContainer create(String name) {
        return create(ThreadContainers.root(), name);
    }

    @Override
    public Thread owner() {
        return null;
    }

    @Override
    public void onStart(Thread thread) {
        // virtual threads needs to be tracked
        if (thread.isVirtual()) {
            Set<Thread> vthreads = this.virtualThreads;
            if (vthreads == null) {
                vthreads = ConcurrentHashMap.newKeySet();
                if (!VIRTUAL_THREADS.compareAndSet(this, null, vthreads)) {
                    // lost the race
                    vthreads = this.virtualThreads;
                }
            }
            vthreads.add(thread);
        }
        threadCount.add(1L);
    }

    @Override
    public void onExit(Thread thread) {
        threadCount.add(-1L);
        if (thread.isVirtual())
            virtualThreads.remove(thread);
    }

    @Override
    public long threadCount() {
        return threadCount.sum();
    }

    @Override
    public Stream<Thread> threads() {
        // live platform threads in this container
        Stream<Thread> platformThreads = Stream.of(JLA.getAllThreads())
                .filter(t -> JLA.threadContainer(t) == this);
        Set<Thread> vthreads = this.virtualThreads;
        if (vthreads == null) {
            // live platform threads only, no virtual threads
            return platformThreads;
        } else {
            // all live threads in this container
            return Stream.concat(platformThreads,
                                 vthreads.stream().filter(Thread::isAlive));
        }
    }

    /**
     * Starts a thread in this container.
     * @throws IllegalStateException if the container is closed
     */
    public void start(Thread thread) {
        if (closed)
            throw new IllegalStateException();
        JLA.start(thread, this);
    }

    /**
     * Closes this container. Further attempts to start a thread in this container
     * throw IllegalStateException. This method has no impact on threads that are
     * still running or starting around the time that this method is invoked.
     */
    @Override
    public void close() {
        if (!closed && CLOSED.compareAndSet(this, false, true)) {
            ThreadContainers.deregisterContainer(key);
        }
    }

    @Override
    public String toString() {
        String id = Objects.toIdentityString(this);
        if (name != null) {
            return name + "/" + id;
        } else {
            return id;
        }
    }
}
