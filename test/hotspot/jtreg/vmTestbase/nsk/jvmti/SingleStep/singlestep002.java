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

package nsk.jvmti.SingleStep;


import java.io.*;
import nsk.share.*;
import nsk.share.jvmti.*;














/**
 * This test exercises the JVMTI event <code>SingleStep</code>.
 * <br>It verifies that this event is sent only during the live
 * phase of VM execution.<br>
 * The test works as follows. The tested event is enabled in the
 * <code>OnLoad</code> phase. Then all received <code>SingleStep</code>
 * events is checked to be sent only during the live phase via
 * the <code>GetPhase()</code> call.
 */
public class singlestep002 {
    private Log log;

    static {
        try {
            System.loadLibrary("singlestep002");
        } catch (UnsatisfiedLinkError ule) {
            System.err.println("Could not load \"singlestep002\" library");
            System.err.println("java.library.path:"
                + System.getProperty("java.library.path"));
            throw ule;
        }
    }

    public static void main(String[] argv) {
        argv = nsk.share.jvmti.JVMTITest.commonInit(argv);

        // produce JCK-like exit status
        System.exit(run(argv, System.out) + Consts.JCK_STATUS_BASE);
    }

    public static int run(String argv[], PrintStream out) {
        return new singlestep002().runThis(argv, out);
    }

    private int runThis(String argv[], PrintStream out) {
        return Consts.TEST_PASSED;
    }
}
