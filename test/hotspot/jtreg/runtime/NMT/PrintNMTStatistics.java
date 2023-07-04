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

/*
 * @test
 * @bug 8005936 8058606
 * @summary Verify PrintNMTStatistics on normal JVM exit for detail and summary tracking level
 * @modules java.base/jdk.internal.misc
 * @library /test/lib
 * @run driver PrintNMTStatistics
 */

import jdk.test.lib.process.ProcessTools;
import jdk.test.lib.process.OutputAnalyzer;

public class PrintNMTStatistics {

    public static void main(String args[]) throws Exception {

    ProcessBuilder pb = ProcessTools.createJavaProcessBuilder(
      "-XX:+UnlockDiagnosticVMOptions",
      "-XX:+PrintNMTStatistics",
      "-XX:NativeMemoryTracking=detail",
      "-version");

    OutputAnalyzer output_detail = new OutputAnalyzer(pb.start());
    output_detail.shouldContain("Virtual memory map:");
    output_detail.shouldContain("Details:");

    // PrintNMTStatistics also prints out metaspace statistics as a convenience.
    output_detail.shouldContain("Metaspace:");

    output_detail.shouldHaveExitValue(0);

    // Make sure memory reserved for Module processing is recorded.
    output_detail.shouldContain(" Module (reserved=");

    ProcessBuilder pb1 = ProcessTools.createJavaProcessBuilder(
      "-XX:+UnlockDiagnosticVMOptions",
      "-XX:+PrintNMTStatistics",
      "-XX:NativeMemoryTracking=summary",
      "-version");

    OutputAnalyzer output_summary = new OutputAnalyzer(pb1.start());
    output_summary.shouldContain("Java Heap (reserved=");
    output_summary.shouldNotContain("Virtual memory map:");
    output_summary.shouldNotContain("Details:");
    output_summary.shouldHaveExitValue(0);
    }
}
