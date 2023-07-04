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

import static jdk.test.lib.Asserts.assertFalse;
import jdk.test.lib.process.OutputAnalyzer;
import jdk.test.lib.process.ProcessTools;

/*
 * @test       NoLaunchOptionTest.java
 * @bug        4554734 4724714
 * @summary    Test for -Xrunjdwp:[onthrow,onuncaught] suboptions require launch suboption
 * @author     Tim Bell
 *
 * @library /test/lib
 *
 * @run compile -g NoLaunchOptionTest.java
 * @build VMConnection
 * @run driver NoLaunchOptionTest
 */
public class NoLaunchOptionTest extends Object {

    public static void main(String[] args) throws Exception {
        String[] cmd = VMConnection.insertDebuggeeVMOptions(new String[] {
                "-agentlib:jdwp=transport=dt_socket,address=5555," +
                "onthrow=java.lang.ClassNotFoundException,suspend=n",
                "NotAClass" });

        ProcessBuilder pb = ProcessTools.createJavaProcessBuilder(cmd);
        OutputAnalyzer output = ProcessTools.executeProcess(pb);
        System.out.println(output.getOutput());

        assertFalse(output.getExitValue() == 0, "Exit code should not be 0");
        output.shouldContain("ERROR: JDWP Specify launch=<command line> when using onthrow or onuncaught suboption");
    }

}
