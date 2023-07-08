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

package java.xml.share.classes.com.sun.org.apache.xml.internal.utils;

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */

/**
 * A utility class that wraps the ThreadController, which is used by
 * IncrementalSAXSource for the incremental building of DTM.
 */
public class ThreadControllerWrapper {

    /**
     * The ThreadController pool
     */
    private static final ThreadController m_tpool = new ThreadController();

    public static Thread runThread(Runnable runnable, int priority) {
        return m_tpool.run(runnable, priority);
    }

    public static void waitThread(Thread worker, Runnable task)
            throws InterruptedException {
        m_tpool.waitThread(worker, task);
    }

    /**
     * Thread controller utility class for incremental SAX source. Must be
     * overridden with a derived class to support thread pooling.
     *
     * All thread-related stuff is in this class.
     */
    public static class ThreadController {

        /**
         * Will get a thread from the pool, execute the task and return the
         * thread to the pool.
         *
         * The return value is used only to wait for completion
         *
         *
         * @param task the Runnable
         *
         * @param priority if >0 the task will run with the given priority (
         * doesn't seem to be used in xalan, since it's always the default )
         * @return The thread that is running the task, can be used to wait for
         * completion
         */
        public Thread run(Runnable task, int priority) {

            Thread t = new SafeThread(task);
            t.start();

            //if( priority > 0 )
            //    t.setPriority( priority );
            return t;
        }

        /**
         * Wait until the task is completed on the worker thread.
         *
         * @param worker worker thread
         * @param task the Runnable
         *
         * @throws InterruptedException
         */
        public void waitThread(Thread worker, Runnable task)
                throws InterruptedException {

            // This should wait until the transformThread is considered not alive.
            worker.join();
        }
    }
}
