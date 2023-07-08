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

package compiler.rtm.cli;

/**
 * @test
 * @bug 8031320
 * @summary Verify processing of RTMSpinLoopCount option.
 * @library /test/lib /
 * @modules java.base/jdk.internal.misc
 *          java.management
 * @requires vm.flagless
 * @requires vm.rtm.compiler
 * @run driver compiler.rtm.cli.TestRTMSpinLoopCountOption
 */

public class TestRTMSpinLoopCountOption
        extends RTMGenericCommandLineOptionTest {
    private static final String DEFAULT_VALUE = "100";

    private TestRTMSpinLoopCountOption() {
        super("RTMSpinLoopCount", false, true,
                TestRTMSpinLoopCountOption.DEFAULT_VALUE,
                "0", "10", "42", "1000");
    }

    public static void main(String args[]) throws Throwable {
        new TestRTMSpinLoopCountOption().runTestCases();
    }
}
