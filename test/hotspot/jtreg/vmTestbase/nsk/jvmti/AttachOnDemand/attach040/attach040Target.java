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

package nsk.jvmti.AttachOnDemand.attach040;

import nsk.share.aod.TargetApplicationWaitingAgents;
import java.util.*;

public class attach040Target extends TargetApplicationWaitingAgents {

    static class TestThread extends Thread {
        public void run() {
            try {
                log.display(Thread.currentThread() + " is running");
            } catch (Throwable t) {
                setStatusFailed("Unexpected exception: " + t);
                t.printStackTrace(log.getOutStream());
            }
        }
    }

    /*
     * NOTE: constants values should be up-to-date with constants in the agent's code
     */

    static final int TEST_THREADS_NUMBER = 5;

    static final String TEST_THREAD_NAME_PREFIX = "attach040-TestThread-";

    protected void targetApplicationActions() throws InterruptedException {
        List<TestThread> threads = new ArrayList<TestThread>();
        for (int i = 0; i < TEST_THREADS_NUMBER; i++) {
            TestThread thread = new TestThread();
            thread.setName(TEST_THREAD_NAME_PREFIX + i);
            thread.start();
            threads.add(thread);
        }

        for (TestThread thread : threads) {
            thread.join();
        }
    }

    public static void main(String[] args) {
        new attach040Target().runTargetApplication(args);
    }
}
