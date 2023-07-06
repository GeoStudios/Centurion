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

package compiler.startup;

import jdk.test.lib.process.OutputAnalyzer;
import jdk.test.lib.Platform;
import jdk.test.lib.process.ProcessTools;

/*
 * @test
 * @bug 8034775
 * @summary Ensures correct minimal number of compiler threads (provided by -XX:CICompilerCount=)
 * @library /test/lib
 * @requires vm.flagless
 * @modules java.base/jdk.internal.misc
 *          java.management
 *
 * @run driver compiler.startup.NumCompilerThreadsCheck
 */

public class NumCompilerThreadsCheck {

  public static void main(String[] args) throws Exception {
    ProcessBuilder pb = ProcessTools.createJavaProcessBuilder("-XX:CICompilerCount=-1");
    OutputAnalyzer out = new OutputAnalyzer(pb.start());

    String expectedOutput = "outside the allowed range";
    out.shouldContain(expectedOutput);

    if (Platform.isZero()) {
      String expectedLowWaterMarkText = "must be at least 0";
      out.shouldContain(expectedLowWaterMarkText);
    }
  }

}