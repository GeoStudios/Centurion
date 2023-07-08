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

package nsk.share.gc;

import java.io.PrintStream;

/**
 * An object that occupies approximately given number of bytes in memory
 * and also records number of allocated instances.
 */
public class AllMemoryObject extends MemoryObject {
        private static int allocatedCount;

        public AllMemoryObject(int size) {
                super(size);
                synchronized (AllMemoryObject.class) {
                        ++allocatedCount;
                }
        }

        /**
         * Returns the number of allocated FinMemoryObjects.
         */
        public static int getAllocatedCount() {
                return allocatedCount;
        }

        public static void dumpStatistics(PrintStream out) {
                out.println("Object count: " + getAllocatedCount());
        }

        public static void dumpStatistics() {
                dumpStatistics(System.out);
        }
}
