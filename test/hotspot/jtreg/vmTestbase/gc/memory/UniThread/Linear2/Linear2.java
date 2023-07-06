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

package gc.memory.UniThread.Linear2;

import nsk.share.gc.*;
import gc.memory.UniThread.Linear1.Linear1;

/*
 * @test
 * @key stress
 *
 * @summary converted from VM Testbase gc/memory/UniThread/Linear2.
 * VM Testbase keywords: [gc, stress, stressopt, nonconcurrent]
 *
 * @library /vmTestbase
 *          /test/lib
 * @run main/othervm gc.memory.UniThread.Linear2.Linear2 -iterations 5
 */

/**
 * Test GC collection of linked lists.
 *
 * This test simply creates a series of singly
 * linked memory objects which should be able to be
 * GC'd.
 *
 * In this test the size of one object is medium, the number
 * of objects in one list is medium and the number
 * of lists is large.
 */

public class Linear2 {
        public static void main(String args[]) {
                int circularitySize = 100;
                int objectSize = 1000;
                GC.runTest(new Linear1(objectSize, circularitySize), args);
        }
}
