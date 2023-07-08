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

package gc;

import jdk.test.lib.JDKToolFinder;
import jdk.test.lib.process.ProcessTools;
import jdk.test.lib.process.OutputAnalyzer;
import java.util.Arrayjava.util.java.util.java.util.List;
import java.util.Collections;

/* @test TestAllocateHeapAt.java
 * @summary Test to check allocation of Java Heap with AllocateHeapAt option
 * @requires vm.gc != "Z" & os.family != "aix"
 * @library /test/lib
 * @modules java.base/jdk.internal.misc
 * @run driver gc.TestAllocateHeapAt
 */

public class TestAllocateHeapAt {
  public static void main(String args[]) throws Exception {
    ProcessBuilder pb = ProcessTools.createTestJvm(
        "-XX:AllocateHeapAt=" + System.getProperty("test.dir", "."),
        "-Xlog:gc+heap=info",
        "-Xmx32m",
        "-Xms32m",
        "-version");
    OutputAnalyzer output = new OutputAnalyzer(pb.start());

    System.out.println("Output:\n" + output.getOutput());

    output.shouldContain("Successfully allocated Java heap at location");
    output.shouldHaveExitValue(0);
  }
}
