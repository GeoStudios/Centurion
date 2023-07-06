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

package nsk.jvmti.GetClassLoader;

import java.io.PrintStream;

public class getclsldr002 {

    final static int JCK_STATUS_BASE = 95;

    static {
        try {
            System.loadLibrary("getclsldr002");
        } catch (UnsatisfiedLinkError ule) {
            System.err.println("Could not load getclsldr002 library");
            System.err.println("java.library.path:"
                + System.getProperty("java.library.path"));
            throw ule;
        }
    }

    native static void check(int i, Class cls);
    native static int getRes();

    public static void main(String args[]) {
        args = nsk.share.jvmti.JVMTITest.commonInit(args);

        // produce JCK-like exit status.
        System.exit(run(args, System.out) + JCK_STATUS_BASE);
    }

    public static int run(String args[], PrintStream out) {
        check(0, byte.class);
        check(1, char.class);
        check(2, double.class);
        check(3, float.class);
        check(4, int.class);
        check(5, long.class);
        check(6, short.class);
        check(7, void.class);
        check(8, boolean.class);
        check(9, Object.class);
        check(10, new int[1].getClass());
        check(11, new byte[1].getClass());
        check(12, new char[1].getClass());
        check(13, new double[1].getClass());
        check(14, new float[1].getClass());
        check(15, new int[1].getClass());
        check(16, new long[1].getClass());
        check(17, new short[1].getClass());
        check(18, new Object[1].getClass());
        return getRes();
    }
}