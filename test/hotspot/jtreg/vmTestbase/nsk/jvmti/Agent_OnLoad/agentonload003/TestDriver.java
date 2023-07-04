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
 * @summary converted from VM Testbase nsk/jvmti/Agent_OnLoad/agentonload003.
 * VM Testbase keywords: [quick, jpda, jvmti, noras]
 * VM Testbase readme:
 * DESCRIPTION
 *     This JVMTI test exercises JVMTI thread function Agent_OnLoad().
 *     This test checks that options string passed to Agent_OnLoad() is
 *     identical to specified in command line, event if it contains blanks.
 *     The test does not create JVMTI environment, neither use JVMTI framework.
 *     If options string passed to AgentOnLoad() is not equal to expected,
 *     then Agent_OnLoad() returns with JNI_ERR and test fails with
 *     exit status 1.
 *     Otherwise, the test executes trivial debuggee class and passes
 *     with exit status 95.
 * COMMENTS
 *
 * @library /vmTestbase
 *          /test/lib
 * @run main/othervm/native TestDriver
 */

import java.io.IOException;

public class TestDriver {
    public static void main(String[] args) throws IOException, InterruptedException {
        // TestDriver is needed b/c jtreg replaces \s\s* w/ \s
        ExecDriver.main(new String[] {
                        "--java",
                        "-agentlib:agentonload003=options string  with   blanks",
                        nsk.jvmti.Agent_OnLoad.agentonload003.class.getName()});
    }
}
