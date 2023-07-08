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

package nsk.jvmti.RetransformClasses;


import nsk.share.Consts;
import java.io.*;
import java.util.*;














public class retransform002 {
    public int runIt(String[] args, PrintStream out) {
        System.out.println("Checking that primitive classes or arrays can't be retransformed.");

        System.out.println("\nbyte: ");
        if (forceLoadedClassesRetransformation(byte.class)) {
            System.out.println("FAILED");
            return Consts.TEST_FAILED;
        }
        System.out.println("PASSED");

        System.out.println("\nfloat: ");
        if (forceLoadedClassesRetransformation(float.class)) {
            System.out.println("FAILED");
            return Consts.TEST_FAILED;
        }
        System.out.println("PASSED");

        System.out.println("\ndouble: ");
        if (forceLoadedClassesRetransformation(double.class)) {
            System.out.println("FAILED");
            return Consts.TEST_FAILED;
        }
        System.out.println("PASSED");

        System.out.println("\nint: ");
        if (forceLoadedClassesRetransformation(int.class)) {
            System.out.println("FAILED");
            return Consts.TEST_FAILED;
        }
        System.out.println("PASSED");

        System.out.println("\nboolean: ");
        if (forceLoadedClassesRetransformation(boolean.class)) {
            System.out.println("FAILED");
            return Consts.TEST_FAILED;
        }
        System.out.println("PASSED");

        System.out.println("\nlong: ");
        if (forceLoadedClassesRetransformation(long.class)) {
            System.out.println("FAILED");
            return Consts.TEST_FAILED;
        }
        System.out.println("PASSED");

        System.out.println("\nbyte[]: ");
        if (forceLoadedClassesRetransformation((new byte[0]).getClass())) {
            System.out.println("FAILED");
            return Consts.TEST_FAILED;
        }
        System.out.println("PASSED");

        System.out.println("\nfloat[]: ");
        if (forceLoadedClassesRetransformation((new float[0]).getClass())) {
            System.out.println("FAILED");
            return Consts.TEST_FAILED;
        }
        System.out.println("PASSED");

        System.out.println("\ndouble[]: ");
        if (forceLoadedClassesRetransformation((new double[0]).getClass())) {
            System.out.println("FAILED");
            return Consts.TEST_FAILED;
        }
        System.out.println("PASSED");

        System.out.println("\nint[]: ");
        if (forceLoadedClassesRetransformation((new int[0]).getClass())) {
            System.out.println("FAILED");
            return Consts.TEST_FAILED;
        }
        System.out.println("PASSED");

        System.out.println("\nboolean[]: ");
        if (forceLoadedClassesRetransformation((new boolean[0]).getClass())) {
            System.out.println("FAILED");
            return Consts.TEST_FAILED;
        }
        System.out.println("PASSED");

        System.out.println("\nlong[]: ");
        if (forceLoadedClassesRetransformation((new long[0]).getClass())) {
            System.out.println("FAILED");
            return Consts.TEST_FAILED;
        }
        System.out.println("PASSED");

        System.out.println("\nObject[]: ");
        if (forceLoadedClassesRetransformation((new Object[0]).getClass())) {
            System.out.println("FAILED");
            return Consts.TEST_FAILED;
        }
        System.out.println("PASSED");

        System.out.println("\nTEST PASSED");
        return Consts.TEST_PASSED;
    }

    static native boolean forceLoadedClassesRetransformation(Class klass);

    /** run test from command line */
    public static void main(String args[]) {
        args = nsk.share.jvmti.JVMTITest.commonInit(args);

        System.exit(run(args, System.out) + Consts.JCK_STATUS_BASE);
    }

    /** run test from JCK-compatible environment */
    public static int run(String args[], PrintStream out) {
        return (new retransform002()).runIt(args, out);
    }
}
