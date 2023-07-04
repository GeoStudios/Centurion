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
 * @bug 5050487
 * @summary Check that Instrumentation methods can execute from a runtime
 *          shutdown hook
 * @run build FromShutdownHook
 * @run shell MakeJAR.sh basicAgent
 * @run main/othervm -javaagent:basicAgent.jar FromShutdownHook FromShutdownHook
 */
import java.lang.instrument.Instrumentation;

public class FromShutdownHook
    extends ASimpleInstrumentationTestCase
{
    public FromShutdownHook(String name) {
        super(name);
    }
    public static void main(String args[] ) throws Throwable {
        FromShutdownHook fsh = new FromShutdownHook(args[0]);
        fsh.runTest();
    }

    Instrumentation ins;

    protected final void doRunTest() {
        // keep reference to Instrumentation
        ins = fInst;

        // install shutdown hook
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                System.err.println(ins.getAllLoadedClasses().length +
                        " classes loaded.");
            }
        });
    }
}
