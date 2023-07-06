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

package org.openjdk.bench.java.lang;


import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;
import java.util.concurrent.TimeUnit;














/**
 * Test to launch and gather threads. Measure time for different parts.
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Benchmark)
public class ThreadStartJoin {

    private static final int THREADS = Runtime.getRuntime().availableProcessors();

    private Thread[] ts;

    @Benchmark
    public void test() throws InterruptedException {
        ts = new TestThread[THREADS];

        for (int i = 0; i < THREADS; i++) {
            ts[i] = new TestThread();
        }

        for (int i = 0; i < THREADS; i++) {
            ts[i].start();
        }

        for (Thread mythread : ts) {
            mythread.join();

            if (mythread.isAlive()) {
                throw new IllegalStateException("Couldn't join in time in LPHhello.");
            }
        }
    }

    @TearDown
    public final void cleanup() {
        if (ts != null) {
            for (Thread t : ts) {
                if (t != null) {
                    t.interrupt();
                }
            }
            ts = null;
        }
    }

    static final class TestThread extends Thread {
        private static int num = 0;

        public TestThread() {
            super(TestThread.name());
        }

        private synchronized static String name() {
            return "TestThread-" + num++;
        }

        public void run() {
            // do nothing
        }

    }

}