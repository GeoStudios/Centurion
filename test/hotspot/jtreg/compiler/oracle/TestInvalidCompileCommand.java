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

package compiler.oracle;

import jdk.test.lib.process.OutputAnalyzer;
import jdk.test.lib.process.ProcessTools;

/*
 * @test TestInvalidCompileCommand
 * @bug 8263206 8263353
 * @summary Regression tests of -XX:CompileCommand
 * @library /test/lib
 * @requires vm.flagless
 * @run driver compiler.oracle.TestInvalidCompileCommand
 */

public class TestInvalidCompileCommand {

    private static final String[][] ARGUMENTS = {
        {
            "-XX:CompileCommand=unknown",
            "-version"
        },
        {
            "-XX:CompileCommand=option,Test::test,CompileThresholdScaling,3.14",
            "-version"
        },
        {
            "-XX:CompileCommand=option,Test::test,RepeatCompilation,3",
            "-version"
        },
        {
            "-XX:CompileCommand=option,Test::test,VectorizeDebug,3",
            "-version"
        },
        {
            "-XX:CompileCommand=option,Test::test,ControlIntrinsic,-_maxD,-_minD",
            "-version"
        }
    };

    private static final String[][] OUTPUTS = {
        {
            "Unrecognized option 'unknown'"
        },
        {
            "Missing type 'double' before option 'CompileThresholdScaling'"
        },
        {
            "Missing type 'intx' before option 'RepeatCompilation'"
        },
        {
            "Missing type 'uintx' before option 'VectorizeDebug'"
        },
        {
            "Missing type 'ccstrlist' before option 'ControlIntrinsic'"
        }
    };

    private static void verifyInvalidOption(String[] arguments, String[] expected_outputs) throws Exception {
        ProcessBuilder pb;
        OutputAnalyzer out;

        pb = ProcessTools.createJavaProcessBuilder(arguments);
        out = new OutputAnalyzer(pb.start());

        for (String expected_output : expected_outputs) {
            out.shouldContain(expected_output);
        }

        out.shouldContain("CompileCommand: An error occurred during parsing");
        out.shouldHaveExitValue(0);
    }

    public static void main(String[] args) throws Exception {

        if (ARGUMENTS.length != OUTPUTS.length) {
            throw new RuntimeException("Test is set up incorrectly: length of arguments and expected outputs does not match.");
        }

        for (int i = 0; i < ARGUMENTS.length; i++) {
            verifyInvalidOption(ARGUMENTS[i], OUTPUTS[i]);
        }
    }
}
