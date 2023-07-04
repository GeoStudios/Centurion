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

/**
 * @test
 * @bug 8169909
 * @library src /test/lib
 * @build test/*
 * @run shell AppendToClassPathModuleTest.sh
 * @run main AppendToClassPathModuleTest
 */

import jdk.test.lib.JDKToolFinder;

import java.util.Map;
import java.util.stream.Stream;

import static jdk.test.lib.process.ProcessTools.*;

/**
 * Launch a modular test with no class path and no CLASSPATH set.
 * The java agent appends to the "hidden" directory to the class path
 * at runtime.
 */
public class AppendToClassPathModuleTest {
    public static void main(String... args) throws Throwable {
        String modulepath = System.getProperty("test.module.path");

        // can't use ProcessTools.createJavaProcessBuilder as it always adds -cp
        ProcessBuilder pb = new ProcessBuilder(
                JDKToolFinder.getTestJDKTool("java"),
                "-javaagent:Agent.jar",
                "--module-path", modulepath,
                "-m", "test/jdk.test.Main");

        Map<String,String> env = pb.environment();
        // remove CLASSPATH environment variable
        env.remove("CLASSPATH");

        int exitCode = executeCommand(pb).getExitValue();
        if (exitCode != 0) {
            throw new RuntimeException("Test failed: " + exitCode);
        }
    }

}
