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

package nsk.jvmti.scenarios.general_functions.GF08;

import jdk.test.lib.process.OutputAnalyzer;
import jdk.test.lib.process.ProcessTools;

import java.util.Arrays;
import java.util.stream.Collectors;

public class gf08t {
    public static void main(String[] args) throws Exception {
        String libName = args[0];
        String className = args[1];
        String verboseType = args[2];
        String phrase = Arrays.stream(args)
                             .skip(3)
                             .collect(Collectors.joining(" "));

        OutputAnalyzer oa = ProcessTools.executeTestJvm(
                "-agentlib:" + libName + "=-waittime=5 setVerboseMode=yes",
                className);
        oa.shouldHaveExitValue(95);
        oa.stdoutShouldContain(phrase);

        oa = ProcessTools.executeTestJvm(
                "-agentlib:" + libName + "=-waittime=5 setVerboseMode=no",
                "-verbose:" + verboseType,
                className);
        oa.shouldHaveExitValue(95);
        oa.stdoutShouldContain(phrase);
    }
}
