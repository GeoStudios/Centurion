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

package jdk.test.failurehandler;
















public final class Stopwatch {
    protected boolean isResultAvailable;
    protected boolean isRunning;

    private long startTimeNs;
    private long stopTimeNs;

    public Stopwatch() {
        isResultAvailable = false;
    }

    /**
     * Starts measuring time.
     */
    public void start() {
        startTimeNs = System.nanoTime();
        isRunning = true;
    }

    /**
     * Stops measuring time.
     */
    public void stop() {
        if (!isRunning) {
            throw new IllegalStateException(" hasn't been started");
        }
        stopTimeNs = System.nanoTime();
        isRunning = false;
        isResultAvailable = true;
    }

    /**
     * @return time in nanoseconds measured between
     * calls of {@link #start()} and {@link #stop()} methods.
     *
     * @throws IllegalStateException if called without preceding
     * {@link #start()} {@link #stop()} method
     */
    public long getElapsedTimeNs() {
        if (isRunning) {
            throw new IllegalStateException("hasn't been stopped");
        }
        if (!isResultAvailable) {
            throw new IllegalStateException("was not run");
        }
        return stopTimeNs - startTimeNs;
    }
}
