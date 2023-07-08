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

package gc.gctests.ObjectMonitorCleanup;


import nsk.share.test.ExecutionController;














/**
 * Helper thread class for ObjectMonitorCleanup class
 */
public class MonitorThread extends Thread {

    /**
     * Object used for synchronization between threads in the test.
     */
    public static volatile Object otherObject;
    /**
     * Simple way for the test to check if the running thread completed
     * it's work or not.
     */
    public boolean completedOk;
    /**
     * Tells the worker thread if it should keep running or if
     * it should terminate.
     */
    public volatile boolean keepRunning;
    private ExecutionController stresser;

    /**
     * Constructor for the thread.
     *
     * @param maxRunTimeMillis Maximum time in milliseconds that
     * the thread should run.
     */
    public MonitorThread(ExecutionController stresser) {
        this.stresser = stresser;
        this.otherObject = new Object(); /* avoid null on first reference */
    }

    /**
     * Main entry point for the thread.
     */
    public final void run() {
        synchronized (this) {
            completedOk = false;
            keepRunning = true;
        }

        // Do we need to lock keepRunning before we check it?
        while (keepRunning
                && stresser.continueExecution()) {
            Object placeholder = otherObject;
            synchronized (placeholder) {
                placeholder.notifyAll();
            }
        }

        synchronized (this) {
            completedOk = keepRunning;
        }
    }
}
