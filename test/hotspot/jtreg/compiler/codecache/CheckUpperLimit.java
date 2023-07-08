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

package compiler.codecache;

import jdk.test.lib.process.OutputAnalyzer;
import jdk.test.lib.process.ProcessTools;

/*
 * @test
 * @bug 8015635
 * @summary Test ensures that the ReservedCodeCacheSize is at most MAXINT
 * @library /test/lib
 * @requires vm.flagless
 *
 * @run driver compiler.codecache.CheckUpperLimit
 */

public class CheckUpperLimit {
    public static void main(String[] args) throws Exception {
        ProcessBuilder pb;
        OutputAnalyzer out;

        pb = ProcessTools.createJavaProcessBuilder("-XX:ReservedCodeCacheSize=2049m", "-version");
        out = new OutputAnalyzer(pb.start());
        out.shouldContain("Invalid ReservedCodeCacheSize=");
        out.shouldHaveExitValue(1);
    }
}
