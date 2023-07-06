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

package nsk.jvmti.RedefineClasses;

import java.io.*;

public class redefclass021 {

    final static int FAILED = 2;
    final static int JCK_STATUS_BASE = 95;

    static String fileDir = ".";

    static {
        try {
            System.loadLibrary("redefclass021");
        } catch (UnsatisfiedLinkError ule) {
            System.err.println("Could not load redefclass021 library");
            System.err.println("java.library.path:"
                + System.getProperty("java.library.path"));
            throw ule;
        }
    }

    native static int check(Class cls, byte bytes[]);

    public static void main(String args[]) {
        args = nsk.share.jvmti.JVMTITest.commonInit(args);

        // produce JCK-like exit status.
        System.exit(run(args, System.out) + JCK_STATUS_BASE);
    }

    public static int run(String args[], PrintStream out) {
        if (args.length > 0) {
            fileDir = args[0];
        }

        // Read data from class
        byte[] bytes;
        String fileName = fileDir + File.separator + "newclass" + File.separator +
            redefclass021a.class.getName().replace('.', File.separatorChar) +
            ".class";
        try {
            FileInputStream in = new FileInputStream(fileName);
            bytes = new byte[in.available()];
            in.read(bytes);
            in.close();
        } catch (Exception ex) {
            out.println("# Unexpected exception while reading class file:");
            out.println("# " + ex);
            return FAILED;
        }

        redefclass021a tc = new redefclass021a();
        return check(redefclass021a.class, bytes);
    }
}

class redefclass021a {
    int intValue;

    public redefclass021a() {
        intValue = 0;
    }

    public void setValue(int i) {
        intValue = i;
    }

    public int getValue() {
        return intValue;
    }
}