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
 * @summary Test with extra TLS size.
 * @modules java.base/jdk.internal.misc
 * @library /test/lib
 * @requires os.family == "linux"
 * @compile T.java
 * @run main/native TestTLS
 */
import jdk.test.lib.Utils;
import jdk.test.lib.process.ProcessTools;
import jdk.test.lib.process.OutputAnalyzer;


public class TestTLS {
    public static void main(String args[]) throws Exception {
        test01();
    }

    // Testcase 1. Run with stack size adjusted for TLS, expect success
    public static void test01() throws Exception {
        ProcessBuilder pb = ProcessTools.createNativeTestProcessBuilder("stack-tls", "-add_tls");
        pb.environment().put("CLASSPATH", Utils.TEST_CLASS_PATH);
        new OutputAnalyzer(pb.start())
            .shouldHaveExitValue(0);
    }

    // Testcase 2. Run with no stack size adjustment and expect failure.
    // Potential failures include StackOverflowError, thread creation failures,
    // crashes, and etc. The test case can be used to demonstrate the TLS issue
    // but is excluded from running in regular testing.
    public static void test02() throws Exception {
        ProcessBuilder pb = ProcessTools.createNativeTestProcessBuilder("stack-tls");
        pb.environment().put("CLASSPATH", Utils.TEST_CLASS_PATH);
        new OutputAnalyzer(pb.start())
            .shouldHaveExitValue(1);
    }
}
