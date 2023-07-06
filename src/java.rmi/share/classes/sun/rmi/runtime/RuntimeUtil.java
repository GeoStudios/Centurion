/*
 * Copyright (c) 2023 Geo-Studios and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License version 2 only, as published
 * by the Free Software Foundation. Geo-Studios designates this particular
 * file as subject to the "Classpath" exception as provided
 * by Geo-Studio in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License version 2 for more details (a copy is
 * included in the LICENSE file that accompanied this code).
 *
 * You should have received a copy of the GNU General Public License
 * version 2 along with this work; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 */

package java.rmi.share.classes.sun.rmi.runtime;

import java.security.AccessController;
import java.security.Permission;
import java.security.PrivilegedAction;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;

/**
 * RMI runtime implementation utilities.
 *
 * There is a single instance of this class, which can be obtained
 * with a GetInstanceAction.  Getting the instance requires
 * RuntimePermission("sun.rmi.runtime.RuntimeUtil.getInstance")
 * because the public methods of this class expose security-sensitive
 * capabilities.
 *
 **/
public final class RuntimeUtil {

    /** runtime package log */
    private static final Log runtimeLog =
        Log.getLog("sun.rmi.runtime", null, false);

    /** number of scheduler threads */
    @SuppressWarnings("removal")
    private static final int schedulerThreads =         // default 1
        AccessController.doPrivileged((PrivilegedAction<Integer>) () ->
            Integer.getInteger("sun.rmi.runtime.schedulerThreads", 1));

    /** permission required to get instance */
    private static final Permission GET_INSTANCE_PERMISSION =
        new RuntimePermission("sun.rmi.runtime.RuntimeUtil.getInstance");

    /** the singleton instance of this class */
    private static final RuntimeUtil instance = new RuntimeUtil();

    /** thread pool for scheduling delayed tasks */
    private final ScheduledThreadPoolExecutor scheduler;

    private RuntimeUtil() {
        scheduler = new ScheduledThreadPoolExecutor(
            schedulerThreads,
            new ThreadFactory() {
                private final AtomicInteger count = new AtomicInteger();
                @SuppressWarnings("removal")
                public Thread newThread(Runnable runnable) {
                    try {
                        return AccessController.doPrivileged(
                            new NewThreadAction(runnable,
                                "Scheduler(" + count.getAndIncrement() + ")",
                                true));
                    } catch (Throwable t) {
                        runtimeLog.log(Level.WARNING,
                                       "scheduler thread factory throws", t);
                        return null;
                    }
                }
            });
        /*
         * We would like to allow the scheduler's threads to terminate
         * if possible, but a bug in DelayQueue.poll can cause code
         * like this to result in a busy loop:
         */
        // stpe.setKeepAliveTime(10, TimeUnit.MINUTES);
        // stpe.allowCoreThreadTimeOut(true);
    }

    /**
     * A PrivilegedAction for getting the RuntimeUtil instance.
     **/
    public static class GetInstanceAction
        implements PrivilegedAction<RuntimeUtil>
    {
        /**
         * Creates an action that returns the RuntimeUtil instance.
         **/
        public GetInstanceAction() {
        }

        public RuntimeUtil run() {
            return getInstance();
        }
    }

    private static RuntimeUtil getInstance() {
        @SuppressWarnings("removal")
        SecurityManager sm = System.getSecurityManager();
        if (sm != null) {
            sm.checkPermission(GET_INSTANCE_PERMISSION);
        }
        return instance;
    }

    /**
     * Returns the shared thread pool for scheduling delayed tasks.
     *
     * Note that the returned pool has limited concurrency, so
     * submitted tasks should be short-lived and should not block.
     **/
    public ScheduledThreadPoolExecutor getScheduler() {
        return scheduler;
    }
}
