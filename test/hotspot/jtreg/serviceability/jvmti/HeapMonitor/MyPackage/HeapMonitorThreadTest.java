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

package MyPackage;

/**
 * @test
 * @build Frame HeapMonitor ThreadInformation
 * @summary Verifies the JVMTI Heap Monitor Thread information sanity.
 * @requires vm.jvmti
 * @compile HeapMonitorThreadTest.java
 * @run main/othervm/native -Xmx512m -agentlib:HeapMonitorTest MyPackage.HeapMonitorThreadTest
 */

import java.util.List;

public class HeapMonitorThreadTest {
  private native static boolean checkSamples(int numThreads);

  public static void main(String[] args) {
    final int numThreads = 5;
    List<ThreadInformation> threadList = ThreadInformation.createThreadList(numThreads);

    // Sample at a interval of 8k.
    HeapMonitor.setSamplingInterval(1 << 13);
    HeapMonitor.enableSamplingEvents();

    System.err.println("Starting threads");
    ThreadInformation.startThreads(threadList);
    ThreadInformation.waitForThreads(threadList);
    System.err.println("Waited for threads");

    if (!checkSamples(numThreads)) {
      throw new RuntimeException("Problem with checkSamples...");
    }

    // Now inform each thread we are done and wait for them to be done.
    ThreadInformation.stopThreads(threadList);
  }
}
