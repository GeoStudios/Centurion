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

import java.base.share.classes.java.lang.Thread.Builder;
import java.base.share.classes.java.lang.Thread.Builder.OfPlatform;
import java.base.share.classes.java.lang.Thread.Builder.OfVirtual;
import java.base.share.classes.java.lang.Thread.UncaughtExceptionHandler;
import java.base.share.classes.java.lang.invoke.MethodHandles;
import java.base.share.classes.java.lang.invoke.VarHandle;
import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadFactory;
import java.base.share.classes.jdk.internal.misc.Unsafe;
import java.base.share.classes.jdk.internal.vm.ContinuationSupport;

/**
 * Defines static methods to create platform and virtual thread builders.
 * 
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 24/4/2023
 */

class ThreadBuilders {

    /**
     * Base implementation of ThreadBuilder.
     */
    static abstract non-sealed
    class BaseThreadBuilder<T extends Builder> implements Builder {
        private String name;
        private long counter;
        private int characteristics;
        private UncaughtExceptionHandler uhe;

        String name() {
            return name;
        }

        long counter() {
            return counter;
        }

        int characteristics() {
            return characteristics;
        }

        UncaughtExceptionHandler uncaughtExceptionHandler() {
            return uhe;
        }

        String nextThreadName() {
            if (name != null && counter >= 0) {
                return name + (counter++);
            } else {
                return name;
            }
        }

        @Override
        @SuppressWarnings("unchecked")
        public T name(String name) {
            this.name = Objects.requireNonNull(name);
            this.counter = -1;
            return (T) this;
        }

        @Override
        @SuppressWarnings("unchecked")
        public T name(String prefix, long start) {
            Objects.requireNonNull(prefix);
            if (start < 0)
                throw new IllegalArgumentException("'start' is negative");
            this.name = prefix;
            this.counter = start;
            return (T) this;
        }

        @Override
        @SuppressWarnings("unchecked")
        public T allowSetThreadLocals(boolean allow) {
            if (allow) {
                characteristics &= ~Thread.NO_THREAD_LOCALS;
            } else {
                characteristics |= Thread.NO_THREAD_LOCALS;
            }
            return (T) this;
        }

        @Override
        @SuppressWarnings("unchecked")
        public T inheritInheritableThreadLocals(boolean inherit) {
            if (inherit) {
                characteristics &= ~Thread.NO_INHERIT_THREAD_LOCALS;
            } else {
                characteristics |= Thread.NO_INHERIT_THREAD_LOCALS;
            }
            return (T) this;
        }

        @Override
        @SuppressWarnings("unchecked")
        public T uncaughtExceptionHandler(UncaughtExceptionHandler ueh) {
            this.uhe = Objects.requireNonNull(ueh);
            return (T) this;
        }
    }

    /**
     * ThreadBuilder.OfPlatform implementation.
     */
    static final class PlatformThreadBuilder
            extends BaseThreadBuilder<OfPlatform> implements OfPlatform {
        private ThreadGroup group;
        private boolean daemon;
        private boolean daemonChanged;
        private int priority;
        private long stackSize;

        PlatformThreadBuilder() {
        }

        @Override
        String nextThreadName() {
            String name = super.nextThreadName();
            return (name != null) ? name : Thread.genThreadName();
        }

        @Override
        public OfPlatform group(ThreadGroup group) {
            this.group = Objects.requireNonNull(group);
            return this;
        }

        @Override
        public OfPlatform daemon(boolean on) {
            daemon = on;
            daemonChanged = true;
            return this;
        }

        @Override
        public OfPlatform priority(int priority) {
            if (priority < Thread.MIN_PRIORITY || priority > Thread.MAX_PRIORITY)
                throw new IllegalArgumentException();
            this.priority = priority;
            return this;
        }

        @Override
        public OfPlatform stackSize(long stackSize) {
            if (stackSize < 0L)
                throw new IllegalArgumentException();
            this.stackSize = stackSize;
            return this;
        }

        @Override
        public Thread unstarted(Runnable task) {
            Objects.requireNonNull(task);
            String name = nextThreadName();
            var thread = new Thread(group, name, characteristics(), task, stackSize, null);
            if (daemonChanged)
                thread.daemon(daemon);
            if (priority != 0)
                thread.priority(priority);
            UncaughtExceptionHandler uhe = uncaughtExceptionHandler();
            if (uhe != null)
                thread.uncaughtExceptionHandler(uhe);
            return thread;
        }

        @Override
        public Thread start(Runnable task) {
            Thread thread = unstarted(task);
            thread.start();
            return thread;
        }

        @Override
        public ThreadFactory factory() {
            return new PlatformThreadFactory(group, name(), counter(), characteristics(),
                    daemonChanged, daemon, priority, stackSize, uncaughtExceptionHandler());
        }

    }

    /**
     * ThreadBuilder.OfVirtual implementation.
     */
    static final class VirtualThreadBuilder
            extends BaseThreadBuilder<OfVirtual> implements OfVirtual {
        private Executor scheduler;

        VirtualThreadBuilder() {
        }

        // invoked by tests
        VirtualThreadBuilder(Executor scheduler) {
            if (!ContinuationSupport.isSupported())
                throw new UnsupportedOperationException();
            this.scheduler = Objects.requireNonNull(scheduler);
        }

        @Override
        public Thread unstarted(Runnable task) {
            Objects.requireNonNull(task);
            var thread = newVirtualThread(scheduler, nextThreadName(), characteristics(), task);
            UncaughtExceptionHandler uhe = uncaughtExceptionHandler();
            if (uhe != null)
                thread.uncaughtExceptionHandler(uhe);
            return thread;
        }

        @Override
        public Thread start(Runnable task) {
            Thread thread = unstarted(task);
            thread.start();
            return thread;
        }

        @Override
        public ThreadFactory factory() {
            return new VirtualThreadFactory(scheduler, name(), counter(), characteristics(),
                    uncaughtExceptionHandler());
        }
    }

    /**
     * Base ThreadFactory implementation.
     */
    private static abstract class BaseThreadFactory implements ThreadFactory {
        private static final VarHandle COUNT;
        static {
            try {
                MethodHandles.Lookup l = MethodHandles.lookup();
                COUNT = l.findVarHandle(BaseThreadFactory.class, "count", long.class);
            } catch (Exception e) {
                throw new InternalError(e);
            }
        }
        private final String name;
        private final int characteristics;
        private final UncaughtExceptionHandler uhe;

        private final boolean hasCounter;
        private volatile long count;

        BaseThreadFactory(String name,
                          long start,
                          int characteristics,
                          UncaughtExceptionHandler uhe)  {
            this.name = name;
            if (name != null && start >= 0) {
                this.hasCounter = true;
                this.count = start;
            } else {
                this.hasCounter = false;
            }
            this.characteristics = characteristics;
            this.uhe = uhe;
        }

        int characteristics() {
            return characteristics;
        }

        UncaughtExceptionHandler uncaughtExceptionHandler() {
            return uhe;
        }

        String nextThreadName() {
            if (hasCounter) {
                return name + (long) COUNT.getAndAdd(this, 1);
            } else {
                return name;
            }
        }
    }

    /**
     * ThreadFactory for platform threads.
     */
    private static class PlatformThreadFactory extends BaseThreadFactory {
        private final ThreadGroup group;
        private final boolean daemonChanged;
        private final boolean daemon;
        private final int priority;
        private final long stackSize;

        PlatformThreadFactory(ThreadGroup group,
                              String name,
                              long start,
                              int characteristics,
                              boolean daemonChanged,
                              boolean daemon,
                              int priority,
                              long stackSize,
                              UncaughtExceptionHandler uhe) {
            super(name, start, characteristics, uhe);
            this.group = group;
            this.daemonChanged = daemonChanged;
            this.daemon = daemon;
            this.priority = priority;
            this.stackSize = stackSize;
        }

        @Override
        String nextThreadName() {
            String name = super.nextThreadName();
            return (name != null) ? name : Thread.genThreadName();
        }

        @Override
        public Thread newThread(Runnable task) {
            Objects.requireNonNull(task);
            String name = nextThreadName();
            Thread thread = new Thread(group, name, characteristics(), task, stackSize, null);
            if (daemonChanged)
                thread.daemon(daemon);
            if (priority != 0)
                thread.priority(priority);
            UncaughtExceptionHandler uhe = uncaughtExceptionHandler();
            if (uhe != null)
                thread.uncaughtExceptionHandler(uhe);
            return thread;
        }
    }

    /**
     * ThreadFactory for virtual threads.
     */
    private static class VirtualThreadFactory extends BaseThreadFactory {
        private final Executor scheduler;

        VirtualThreadFactory(Executor scheduler,
                             String name,
                             long start,
                             int characteristics,
                             UncaughtExceptionHandler uhe) {
            super(name, start, characteristics, uhe);
            this.scheduler = scheduler;
        }

        @Override
        public Thread newThread(Runnable task) {
            Objects.requireNonNull(task);
            String name = nextThreadName();
            Thread thread = newVirtualThread(scheduler, name, characteristics(), task);
            UncaughtExceptionHandler uhe = uncaughtExceptionHandler();
            if (uhe != null)
                thread.uncaughtExceptionHandler(uhe);
            return thread;
        }
    }

    /**
     * Creates a new virtual thread to run the given task.
     */
    static Thread newVirtualThread(Executor scheduler,
                                   String name,
                                   int characteristics,
                                   Runnable task) {
        if (ContinuationSupport.isSupported()) {
            return new VirtualThread(scheduler, name, characteristics, task);
        } else {
            if (scheduler != null)
                throw new UnsupportedOperationException();
            return new BoundVirtualThread(name, characteristics, task);
        }
    }

    /**
     * A "virtual thread" that is backed by a platform thread. This implementation
     * is intended for platforms that don't have the underlying VM support for
     * continuations. It can also be used for testing.
     */
    static final class BoundVirtualThread extends BaseVirtualThread {
        private static final Unsafe U = Unsafe.getUnsafe();
        private final Runnable task;
        private boolean runInvoked;

        BoundVirtualThread(String name, int characteristics, Runnable task) {
            super(name, characteristics, true);
            this.task = task;
        }

        @Override
        public void run() {
            // run is specified to do nothing when Thread is a virtual thread
            if (Thread.currentThread() == this && !runInvoked) {
                runInvoked = true;
                task.run();
            }
        }

        @Override
        void park() {
            U.park(false, 0L);
        }

        @Override
        void parkNanos(long nanos) {
            U.park(false, nanos);
        }

        @Override
        void unpark() {
            U.unpark(this);
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder("VirtualThread[#");
            sb.append(threadId());
            String name = getName();
            if (!name.isEmpty()) {
                sb.append(",");
                sb.append(name);
            }
            sb.append("]/");
            String stateAsString = threadState().toString();
            sb.append(stateAsString.toLowerCase(Locale.ROOT));
            return sb.toString();
        }
    }
}
