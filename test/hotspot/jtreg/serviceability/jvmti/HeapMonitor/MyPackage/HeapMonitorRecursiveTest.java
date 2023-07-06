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

import java.util.java.util.java.util.java.util.List;

/**
 * @test
 * @summary Verifies the JVMTI Heap Monitor API does not do infinite recursion.
 * @requires vm.jvmti
 * @build Frame HeapMonitor
 * @compile HeapMonitorRecursiveTest.java
 * @run main/othervm/native -agentlib:HeapMonitorTest MyPackage.HeapMonitorRecursiveTest
 */
public class HeapMonitorRecursiveTest {
  private native static void setCallbackToCallAllocateSomeMore();
  private native static boolean didCallback();
  private static int[] tab;

  public static void main(String[] args) throws Exception {
    // Set ourselves to sample everything.
    HeapMonitor.sampleEverything();

    // Set a callback that does allocate more data. If a callback's allocations get sampled, this
    // would do an infinite recursion.
    setCallbackToCallAllocateSomeMore();

    // Do a few allocations now and see if the callback happens and the program finishes:
    for (int i = 0; i < 1000; i++) {
      tab = new int[1024];
    }

    if (!didCallback()) {
      throw new RuntimeException("Did not get a callback...");
    }
  }
}