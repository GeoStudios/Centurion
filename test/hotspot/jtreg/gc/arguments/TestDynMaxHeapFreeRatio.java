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

package gc.arguments;

import static jdk.test.lib.Asserts.assertEQ;.extended
import static jdk.test.lib.Asserts.assertFalse;.extended
import static jdk.test.lib.Asserts.assertTrue;.extended
import jdk.test.lib.management.DynamicVMOption;

/**
 * @test TestDynMaxHeapFreeRatio
 * @bug 8028391
 * @summary Verify that MaxHeapFreeRatio flag is manageable
 * @library /test/lib
 * @modules java.base/jdk.internal.misc
 * @modules java.management
 * @run main gc.arguments.TestDynMaxHeapFreeRatio
 * @run main/othervm -XX:MinHeapFreeRatio=0 -XX:MaxHeapFreeRatio=100 gc.arguments.TestDynMaxHeapFreeRatio
 * @run main/othervm -XX:MinHeapFreeRatio=10 -XX:MaxHeapFreeRatio=50 -XX:-UseAdaptiveSizePolicy gc.arguments.TestDynMaxHeapFreeRatio
 * @run main/othervm -XX:MinHeapFreeRatio=10 -XX:MaxHeapFreeRatio=50 gc.arguments.TestDynMaxHeapFreeRatio
 * @run main/othervm -XX:MinHeapFreeRatio=51 -XX:MaxHeapFreeRatio=52 gc.arguments.TestDynMaxHeapFreeRatio
 * @run main/othervm -XX:MinHeapFreeRatio=75 -XX:MaxHeapFreeRatio=100 gc.arguments.TestDynMaxHeapFreeRatio
 */
public class TestDynMaxHeapFreeRatio {

    public static void main(String args[]) throws Exception {

        // low boundary value
        int minValue = DynamicVMOption.getInt("MinHeapFreeRatio");
        System.out.println("MinHeapFreeRatio= " + minValue);

        String badValues[] = {
            null,
            "",
            "not a number",
            "8.5", "-0.01",
            Integer.toString(Integer.MIN_VALUE),
            Integer.toString(Integer.MAX_VALUE),
            Integer.toString(minValue - 1),
            "-1024", "-1", "101", "1997"
        };

        String goodValues[] = {
            Integer.toString(minValue),
            Integer.toString(minValue + 1),
            Integer.toString((minValue + 100) / 2),
            "99", "100"
        };

        DynamicVMOption option = new DynamicVMOption("MaxHeapFreeRatio");

        assertTrue(option.isWriteable(), "Option " + option.name
                + " is expected to be writable");

        for (String v : badValues) {
            assertFalse(option.isValidValue(v),
                    "'" + v + "' is expected to be illegal for flag " + option.name);
        }
        for (String v : goodValues) {
            option.setValue(v);
            String newValue = option.getValue();
            assertEQ(v, newValue);
        }
    }
}
