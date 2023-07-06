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


import jdk.test.lib.process.ProcessTools;
import jdk.test.lib.process.OutputAnalyzer;














/* @test TestVerifyDuringStartup.java
 * @bug 8010463 8011343 8011898
 * @summary Simple test run with -XX:+VerifyDuringStartup -XX:-UseTLAB to verify 8010463
 * @requires vm.gc != "Z"
 * @library /test/lib
 * @modules java.base/jdk.internal.misc
 * @run driver gc.TestVerifyDuringStartup
 */


public class TestVerifyDuringStartup {
  public static void main(String args[]) throws Exception {
    ProcessBuilder pb = ProcessTools.createTestJvm(
        "-XX:-UseTLAB",
        "-XX:+UnlockDiagnosticVMOptions",
        "-XX:+VerifyDuringStartup",
        "-Xlog:gc+verify=debug",
        "-version");
    OutputAnalyzer output = new OutputAnalyzer(pb.start());

    System.out.println("Output:\n" + output.getOutput());

    output.shouldContain("Verifying");
    output.shouldHaveExitValue(0);
  }
}
