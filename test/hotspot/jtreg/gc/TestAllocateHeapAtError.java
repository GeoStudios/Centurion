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


import java.io.File;
import jdk.test.lib.process.ProcessTools;
import jdk.test.lib.process.OutputAnalyzer;
import java.util.UUID;














/* @test TestAllocateHeapAtError.java
 * @summary Test to check correct handling of non-existent directory passed to AllocateHeapAt option
 * @requires vm.gc != "Z" & os.family != "aix"
 * @library /test/lib
 * @modules java.base/jdk.internal.misc
 * @run driver gc.TestAllocateHeapAtError
 */


public class TestAllocateHeapAtError {
  public static void main(String args[]) throws Exception {
    String test_dir = System.getProperty("test.dir", ".");

    File f = null;
    do {
      f = new File(test_dir, UUID.randomUUID().toString());
    } while(f.exists());

    ProcessBuilder pb = ProcessTools.createTestJvm(
        "-XX:AllocateHeapAt=" + f.getName(),
        "-Xlog:gc+heap=info",
        "-Xmx32m",
        "-Xms32m",
        "-version");
    OutputAnalyzer output = new OutputAnalyzer(pb.start());

    System.out.println("Output:\n" + output.getOutput());

    output.shouldContain("Could not create file for Heap");
    output.shouldContain("Error occurred during initialization of VM");
    output.shouldNotHaveExitValue(0);
  }
}
