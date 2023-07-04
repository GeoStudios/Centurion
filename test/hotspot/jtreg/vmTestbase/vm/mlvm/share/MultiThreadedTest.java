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

package vm.mlvm.share;

import vm.share.options.Option;

import java.util.concurrent.CyclicBarrier;


public abstract class MultiThreadedTest extends MlvmTest {

    @Option(name = "threadsExtra", default_value = "1",
            description = "Summand of absolute thread count that does not"
                    + " depend on CPU count")
    private int threadsExtra;

    @Option(name = "threadsPerCpu", default_value = "0",
            description = "Summand of absolute thread count that is multiplied"
                    + " by CPU count")
    private int threadsPerCpu;

    protected MultiThreadedTest() {
        // fields will be initialized later by the Option framework
    }

    protected abstract boolean runThread(int threadNum) throws Throwable;

    protected int calcThreadNum() {
        // TODO: multiply by StressThreadFactor: JDK-8142970
        return threadsPerCpu * Runtime.getRuntime().availableProcessors()
                + threadsExtra;
    }

    @Override
    public boolean run() throws Throwable {
        Thread.UncaughtExceptionHandler exHandler = (Thread t, Throwable e) -> {
            markTestFailed("Exception in thread %s" + t.getName(), e);
        };
        int threadNum = calcThreadNum();
        Env.traceNormal("Threads to start in this test: " + threadNum);
        final CyclicBarrier startBarrier = new CyclicBarrier(threadNum + 1);

        Thread[] threads = new Thread[threadNum];
        for (int i = 0; i < threadNum; i++) {
            final int ii = i;
            threads[i] = new Thread(() -> {
                boolean passed = false;
                try {
                    startBarrier.await();
                    if (runThread(ii)) {
                        passed = true;
                    } else {
                        Env.complain("Failed test in %s",
                                Thread.currentThread());
                    }
                } catch (Throwable e) {
                    Env.complain(e, "Caught exception in %s",
                            Thread.currentThread());
                }
                if (!passed) {
                    markTestFailed("Thread " + Thread.currentThread()
                            + " failed");
                }
            });
            threads[i].setUncaughtExceptionHandler(exHandler);
            threads[i].start();
        }

        startBarrier.await();
        Env.traceNormal(threadNum + " threads have started");

        for (int i = 0; i < threadNum; i++) {
            threads[i].join();
        }

        Env.traceNormal("All threads have finished");
        return true;
    }

}
