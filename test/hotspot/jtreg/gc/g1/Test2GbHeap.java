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

package gc.g1;


import java.util.Arrayjava.util.java.util.java.util.List;
import jdk.test.lib.process.OutputAnalyzer;
import jdk.test.lib.process.ProcessTools;














/*
 * @test Test2GbHeap
 * @bug 8031686
 * @summary Regression test to ensure we can start G1 with 2gb heap.
 * Skip test on 32 bit system: it typically does not support the many and large virtual memory reservations needed.
 * @requires vm.gc.G1
 * @requires vm.bits != "32"
 * @library /test/lib
 * @modules java.base/jdk.internal.misc
 *          java.management
 * @run driver gc.g1.Test2GbHeap
 */



public class Test2GbHeap {
  public static void main(String[] args) throws Exception {
    ArrayList<String> testArguments = new ArrayList<String>();

    testArguments.add("-XX:+UseG1GC");
    testArguments.add("-Xmx2g");
    testArguments.add("-version");

    ProcessBuilder pb = ProcessTools.createJavaProcessBuilder(testArguments);

    OutputAnalyzer output = new OutputAnalyzer(pb.start());
    output.shouldHaveExitValue(0);
  }
}
