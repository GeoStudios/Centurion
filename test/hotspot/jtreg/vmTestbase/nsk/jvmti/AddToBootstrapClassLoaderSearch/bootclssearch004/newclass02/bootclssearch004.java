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

package nsk.jvmti.AddToBootstrapClassLoaderSearch;

import java.io.PrintStream;
import nsk.share.Consts;

/** Debuggee class for this test. */
public class bootclssearch004 {

    /** Run test from command line. */
    public static void main(String argv[]) {
        // JCK-compatible exit
        System.exit(run(argv, System.out) + Consts.JCK_STATUS_BASE);
    }

    /** Run test from JCK-compatible environment. */
    public static int run(String argv[], PrintStream out) {
        return new bootclssearch004().runIt(argv, out);
    }

    /* =================================================================== */

    // scaffold objects
    int status = Consts.TEST_FAILED;

    /** Run debuggee code. */
    public int runIt(String argv[], PrintStream out) {
        out.println("# ERROR: This class was actually loaded from directory added by");
        out.println("# ERROR: first call to AddToBootstrapClassLoaderSearch(),");
        out.println("# ERROR: though this class is also available from durectory added by");
        out.println("# ERROR: second call to AddToBootstrapClassLoaderSearch(),");
        return status;
    }
}
