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

package nsk.jdi.BScenarios.hotswap;


import nsk.share.*;
import nsk.share.jpda.*;
import nsk.share.jdi.*;














//    THIS TEST IS LINE NUMBER SENSITIVE

/**
 *  <code>tc10x002a</code> is deugee's part of the tc10x002.
 */
public class tc10x002a {

    public final static String brkpMethodName = "runIt";
    public final static int brkpLineNumber1 = 61;
    public final static int brkpLineNumber2 = 58;
    public final static int checkLastLine1 = 61;
    public final static int checkLastLine2 = 58;
    public final static int INITIAL_INT_VALUE = 42;
    public final static boolean INITIAL_BOOL_VALUE = true;

    private static Log log;

    public static void main (String argv[]) {
        ArgumentHandler argHandler = new ArgumentHandler(argv);
        log = new Log(System.err, argHandler);

        runIt();
        System.exit(Consts.TEST_PASSED + Consts.JCK_STATUS_BASE);
    }

    public static void runIt() {
        int i = INITIAL_INT_VALUE;
//      ^^^     ^^^^^^^^^^^^^^^^^ will be redefined
        tc10x002a obj = new tc10x002a(); // brkpLineNumber2 // checkLastLine2
        obj.method_A();

        System.err.println("i = " + i); // brkpLineNumber1 // checkLastLine1
    }

    public void method_A() {
        method_B();
    }

    public void method_B() {
        method_C();
    }

    public void method_C() {
        System.err.println("method_C:: line 1");
        System.err.println("method_C:: line 2");
        System.err.println("method_C:: line 3");
        System.err.println("method_C:: line 4");
    }
}
