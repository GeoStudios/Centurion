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

import jdk.test.lib.process.ProcessTools;
import jdk.test.lib.process.OutputAnalyzer;

/*
 * @test MaxMetaspaceSizeTest
 * @requires vm.bits == 64 & vm.opt.final.UseCompressedOops == true
 * @bug 8087291
 * @library /test/lib
 * @run driver MaxMetaspaceSizeTest
 */

public class MaxMetaspaceSizeTest {
    public static void main(String... args) throws Exception {
        ProcessBuilder pb = ProcessTools.createJavaProcessBuilder(
            "-Xmx1g",
            "-XX:MaxMetaspaceSize=4K",
            "-XX:+UseCompressedClassPointers",
            "-XX:CompressedClassSpaceSize=1g",
            "--version");
        OutputAnalyzer output = new OutputAnalyzer(pb.start());
        // We do not explicitly limit MaxMetaspaceSize to a lower minimum. User can get as low as he wants.
        // However, you most certainly will hit either one of
        // "OutOfMemoryError: Metaspace" or
        // "OutOfMemoryError: Compressed class space"
        output.shouldMatch("OutOfMemoryError.*(Compressed class space|Metaspace)");
        output.shouldNotHaveExitValue(0);
    }
}
