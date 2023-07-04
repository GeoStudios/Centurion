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
 * @bug 8228485
 * @summary Correctly handle initialization error for Condy BSM.
 * @modules java.base/jdk.internal.misc
 * @library /test/lib
 * @compile Example.jasm
 * @compile StaticInit.java
 * @run driver TestInitException
 */

import jdk.test.lib.process.ProcessTools;
import jdk.test.lib.process.OutputAnalyzer;

public class TestInitException {
    public static void main(java.lang.String[] unused) throws Exception {
        ProcessBuilder pb = ProcessTools.createJavaProcessBuilder("Example");
        OutputAnalyzer oa = new OutputAnalyzer(pb.start());
        // First call stack trace
        // shouldMatch is used to workaround CODETOOLS-7902686
        oa.shouldMatch("^\tat Example\\.\\$jacocoInit\\(.*Example\\.jasm\\)$");
        oa.shouldContain("Caused by: java.lang.RuntimeException");
        oa.shouldContain("at StaticInit.<clinit>(StaticInit.java:27)");
        // Second call stack trace, with the message
        oa.shouldContain("java.lang.ExceptionInInitializerError: $jacocoData");
        oa.shouldMatch("^\tat Example\\.foo\\(.*Example\\.jasm\\)$");
        oa.shouldMatch("^\tat Example\\.main\\(.*Example\\.jasm\\)$");
        oa.shouldHaveExitValue(1);
    }
}

