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

package nsk.jvmti.GetJNIFunctionTable;


import java.io.*;
import java.util.*;
import nsk.share.*;














/**
 * The test exercises the JVMTI function GetJNIFunctionTable().
 * It checks that the function returns the following errors properly:
 * <li> JVMTI_ERROR_NULL_POINTER with NULL parameter
 * <li> JVMTI_ERROR_UNATTACHED_THREAD if current thread is
 * unattached
 * <li> JVMTI_ERROR_INVALID_ENVIRONMENT if the JVMTI environment
 * is disposed
 */
public class getjniftab002 {
    static {
        try {
            System.loadLibrary("getjniftab002");
        } catch (UnsatisfiedLinkError ule) {
            System.err.println("Could not load \"getjniftab002\" library");
            System.err.println("java.library.path:"
                + System.getProperty("java.library.path"));
            throw ule;
        }
    }

    native int check();

    public static void main(String args[]) {
        args = nsk.share.jvmti.JVMTITest.commonInit(args);

        // produce JCK-like exit status.
        System.exit(run(args, System.out) + Consts.JCK_STATUS_BASE);
    }

    public static int run(String args[], PrintStream out) {
        return new getjniftab002().runIt(args, out);
    }

    private int runIt(String args[], PrintStream out) {
        return check();
    }
}
