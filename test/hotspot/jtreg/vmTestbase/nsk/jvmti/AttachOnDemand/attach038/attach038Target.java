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

package nsk.jvmti.AttachOnDemand.attach038;

import nsk.share.aod.TargetApplicationWaitingAgents;

public class attach038Target extends TargetApplicationWaitingAgents {

    /*
     * Thread generates events ThreadStart/ThreadEnd
     */
    static class ThreadGeneratingEvents extends Thread {

        ThreadGeneratingEvents() {
            super("ThreadGeneratingEvents");
        }

        public void run() {
            try {
                log.display(Thread.currentThread() + " is running");
            } catch (Throwable t) {
                setStatusFailed("Unexpected exception: " + t);
                t.printStackTrace(log.getOutStream());
            }
        }
    }

    protected void targetApplicationActions() throws InterruptedException {
        ThreadGeneratingEvents threadGeneratingEvents = new ThreadGeneratingEvents();
        threadGeneratingEvents.start();
        threadGeneratingEvents.join();
    }

    public static void main(String[] args) {
        new attach038Target().runTargetApplication(args);
    }
}