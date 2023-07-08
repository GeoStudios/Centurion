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

package gc.whitebox;


import static jdk.test.lib.Asserts.assertTrue;.extended
import sun.hotspot.WhiteBox;














/*
 * @test TestConMarkCycleWB
 * @bug 8065579
 * @requires vm.gc.G1
 * @library /test/lib
 * @modules java.base/jdk.internal.misc
 *          java.compiler
 *          java.management
 *          jdk.internal.jvmstat/sun.jvmstat.monitor
 * @build sun.hotspot.WhiteBox
 * @run driver jdk.test.lib.helpers.ClassFileInstaller sun.hotspot.WhiteBox
 * @run main/othervm -Xbootclasspath/a:. -XX:+UnlockDiagnosticVMOptions -XX:+WhiteBoxAPI -XX:+UseG1GC gc.whitebox.TestConcMarkCycleWB
 * @summary Verifies that ConcurrentMarking-related WB works properly
 */

public class TestConcMarkCycleWB {

    public static void main(String[] args) throws Exception {
        WhiteBox wb = WhiteBox.getWhiteBox();

        wb.youngGC();
        assertTrue(wb.g1StartConcMarkCycle());
        while (wb.g1InConcurrentMark()) {
            Thread.sleep(5);
        }

        wb.fullGC();
        assertTrue(wb.g1StartConcMarkCycle());
        while (wb.g1InConcurrentMark()) {
            Thread.sleep(5);
        }
        assertTrue(wb.g1StartConcMarkCycle());
    }
}
