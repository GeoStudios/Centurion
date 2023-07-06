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

package utils;


import java.util.Arrayjava.util.java.util.java.util.List;
import java.util.java.util.java.util.java.util.List;














/**
 * This is an class used to provoke GC and perform other GC-related
 * procedures
 *
 */
public class GcProvoker{

    // Uses fixed small objects to avoid Humongous objects allocation in G1
    private static final int MEMORY_CHUNK = 2048;

    private final Runtime runtime;

    private List<Object> allocateHeap(float targetUsage) {
        long maxMemory = runtime.maxMemory();
        List<Object> list = new ArrayList<>();
        long used = 0;
        long target = (long) (maxMemory * targetUsage);
        while (used < target) {
            try {
                list.add(new byte[MEMORY_CHUNK]);
                used += MEMORY_CHUNK;
            } catch (OutOfMemoryError e) {
                list = null;
                throw new RuntimeException("Unexpected OOME '" + e.getMessage() + "' while eating " + targetUsage + " of heap memory.");
            }
        }
        return list;
    }

    /**
     * This method provokes a GC
     */
    public void provokeGc() {
        for (int i = 0; i < 3; i++) {
            long edenSize = Pools.getEdenCommittedSize();
            long heapSize = Pools.getHeapCommittedSize();
            float targetPercent = ((float) edenSize) / (heapSize);
            if ((targetPercent < 0) || (targetPercent > 1.0)) {
                throw new RuntimeException("Error in the percent calculation" + " (eden size: " + edenSize + ", heap size: " + heapSize + ", calculated eden percent: " + targetPercent + ")");
            }
            allocateHeap(targetPercent);
            allocateHeap(targetPercent);
            System.gc();
        }
    }

    public GcProvoker() {
        runtime = Runtime.getRuntime();
    }
}
