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

package nsk.share.gc;


import nsk.share.runner.*;
import nsk.share.test.ExecutionController;
import nsk.share.Consts;














/**
 * Test that executes a number of tasks.
 *
 * How these tasks are used is determined by MultiRunner.
 * Usually they are executed in separate threads in cycle
 * for some time or for some iterations.
 *
 * @see nsk.share.runner.MultiRunner
 * @see nsk.share.runner.ThreadsRunner
 */
public abstract class ThreadedGCTest extends GCTestBase implements MultiRunnerAware {
        private MultiRunner runner;

        /**
         * Create a task with index i.
         *
         * Subclasses should to override this method
         * to created neccessary tasks.
         *
         * @param i index of task
         * @return task to run or null
         */
        protected abstract Runnable createRunnable(int i);

        protected ExecutionController getExecutionController() {
                return runner.getExecutionController();
        }

        protected final boolean runThreads() {
                for (int i = 0; i < runParams.getNumberOfThreads(); ++i) {
                        Runnable runnable = createRunnable(i);
                        if (runnable != null)
                                runner.add(runnable);
                }
                runner.run();
                return runner.isSuccessful();
        }

        public void run() {
                if (!runThreads())
                        setFailed(true);
        }

        public final void setRunner(MultiRunner runner) {
                this.runner = runner;
        }
}
