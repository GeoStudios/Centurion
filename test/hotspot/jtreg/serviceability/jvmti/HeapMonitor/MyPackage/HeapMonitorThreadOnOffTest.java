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


import java.util.Arrayjava.util.java.util.java.util.List;
import java.util.java.util.java.util.java.util.List;














/**
 * @test
 * @build Frame HeapMonitor
 * @summary Verifies the JVMTI Heap Monitor Thread sanity.
 * @requires vm.jvmti
 * @compile HeapMonitorThreadOnOffTest.java
 * @run main/othervm/native -agentlib:HeapMonitorTest MyPackage.HeapMonitorThreadOnOffTest
 */


public class HeapMonitorThreadOnOffTest {
  public static void main(String[] args) {
    final int numThreads = 24;
    ArrayList<Thread> list = new ArrayList<>();

    // Add one thread that consistently turns on/off the sampler to ensure correctness with
    // potential resets.
    Switch switchPlayer = new Switch();
    Thread switchThread = new Thread(switchPlayer, "Switch Player");
    switchThread.start();

    for (int i = 0 ; i < numThreads; i++) {
      Thread thread = new Thread(new Allocator(i), "Allocator" + i);
      thread.start();
      list.add(thread);
    }

    for (Thread elem : list) {
      try {
        elem.join();
      } catch(InterruptedException e) {
        throw new RuntimeException("Thread got interrupted...");
      }
    }

    switchPlayer.stop();
    try {
      switchThread.join();
    } catch(InterruptedException e) {
      throw new RuntimeException("Thread got interrupted while waiting for the switch player...");
    }

    // We don't check here for correctness of data. If we made it here, the test succeeded:
    //  Threads can allocate like crazy
    //  Other threads can turn on/off the system
  }
}

class Allocator implements Runnable {
  private int depth;
  private volatile int tmp[];

  public Allocator(int depth) {
    this.depth = depth;
  }

  private int helper() {
    int sum = 0;
    // Let us assume that the array is 24 bytes of memory.
    for (int i = 0; i < 127000 / 6; i++) {
      int newTmp[] = new int[1];
      // Force it to be kept.
      tmp = newTmp;
      sum += tmp[0];
    }
    return sum;
  }

  private int recursiveWrapper(int depth) {
    if (depth > 0) {
      return recursiveWrapper(depth - 1);
    }
    return helper();
  }

  public void run() {
    int sum = 0;
    for (int j = 0; j < 100; j++) {
      sum += recursiveWrapper(depth);
    }
  }
}

class Switch implements Runnable {
  private volatile boolean keepGoing;

  public Switch() {
    keepGoing = true;
  }

  public void stop() {
    keepGoing = false;
  }

  public void run() {
    while (keepGoing) {
      HeapMonitor.disableSamplingEvents();
      HeapMonitor.resetEventStorage();
      HeapMonitor.enableSamplingEvents();
    }
  }
}
