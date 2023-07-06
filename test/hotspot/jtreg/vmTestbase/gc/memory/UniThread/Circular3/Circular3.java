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

package gc.memory.UniThread.Circular3;

import nsk.share.test.*;
import nsk.share.gc.*;

/*
 * @test
 * @key stress randomness
 *
 * @summary converted from VM Testbase gc/memory/UniThread/Circular3.
 * VM Testbase keywords: [gc, stress, stressopt, nonconcurrent]
 *
 * @library /vmTestbase
 *          /test/lib
 * @run main/othervm gc.memory.UniThread.Circular3.Circular3 -iterations 5
 */

/**
 * Test GC collection of circular linked lists.
 *
 * This test simply creates a series of circulary
 * linked memory objects which should be able to be
 * GC'd.
 *
 * In this test the size of one object is small, the number
 * of objects in one list is small and the number
 * of lists is large. Also, the order in which references
 * are cleared is randomized.
 */
public class Circular3 extends GCTestBase {
        private int arraySize;
        private int objectSize;
        private int circularitySize;
        private MemoryObject memoryArray[];

        public Circular3() {
                this(100, 5);
        }

        public Circular3(int objectSize, int circularitySize) {
                this.objectSize = objectSize;
                this.circularitySize = circularitySize;
        }

        public void run() {
                arraySize = Memory.getArrayLength(runParams.getTestMemory(), Memory.getListSize(circularitySize, objectSize));
                log.debug("Array size: " + arraySize);
                Stresser stresser = new Stresser(runParams.getStressOptions());
                memoryArray = new MemoryObject[arraySize];
                stresser.start(runParams.getIterations());
                try {
                        while (stresser.iteration()) {
                                log.debug(Runtime.getRuntime().freeMemory());
                                for (int i = 0; i < arraySize && stresser.continueExecution(); i ++) {
                                        int index = LocalRandom.nextInt(arraySize);
                                        memoryArray[index] = Memory.makeCircularList(circularitySize, objectSize);
                                }
                        }
                } finally {
                        stresser.finish();
                }
                System.out.println("Test passed.");
        }

        public static void main(String args[]) {
                GC.runTest(new Circular3(), args);
        }
}
