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
 *
 * @summary converted from VM Testbase nsk/jvmti/scenarios/multienv/MA02/ma02t001.
 * VM Testbase keywords: [quick, jpda, jvmti, onload_only_logic, noras]
 * VM Testbase readme:
 * DESCRIPTION
 *     This test is for MA02 scenario of "multiple environments support".
 *     VM starts with two different agents of different libraries.
 *     The agents set callbacks for VMInit and VMDeath events and enable
 *     them in their own environments. The test check if the events were
 *     received in both agents and both shutdown functions Agent_OnUnload
 *     were invoked on VM's exit.
 * COMMENTS
 *
 * @library /vmTestbase
 *          /test/lib
 * @run main/othervm/native TestDriver
 */

import jdk.test.lib.process.OutputAnalyzer;
import jdk.test.lib.process.ProcessTools;

public class TestDriver {
    public static void main(String[] args) throws Exception {
        OutputAnalyzer oa = ProcessTools.executeTestJvm(
                "-agentlib:ma02t001=-waittime=5",
                "-agentlib:ma02t001a=-waittime=5",
                nsk.jvmti.scenarios.multienv.MA02.ma02t001.class.getName());
        oa.shouldHaveExitValue(95);
        oa.stdoutShouldContain("Agent_OnUnload() of the 1st agent");
        oa.stdoutShouldContain("Agent_OnUnload() of the 2nd agent");
    }
}
