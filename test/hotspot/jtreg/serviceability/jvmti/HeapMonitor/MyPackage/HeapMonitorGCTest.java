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

import java.util.List;

/**
 * @test
 * @build Frame HeapMonitor
 * @summary Verifies the default GC with the Heap Monitor event system.
 * @compile HeapMonitorGCTest.java
 * @requires vm.gc == "G1" | vm.gc == "null"
 * @requires vm.jvmti
 * @run main/othervm/native -agentlib:HeapMonitorTest MyPackage.HeapMonitorGCTest
 */

/**
 * This test is checking that various GCs work as intended: events are sent, forcing GC works, etc.
 */
public class HeapMonitorGCTest {
  public static void main(String[] args) {
    Frame[] frames = HeapMonitor.allocateAndCheckFrames();

    HeapMonitor.forceGarbageCollection();

    if (!HeapMonitor.garbageContains(frames)) {
      throw new RuntimeException("Forcing GC did not work, not a single object was collected.");
    }
  }
}
