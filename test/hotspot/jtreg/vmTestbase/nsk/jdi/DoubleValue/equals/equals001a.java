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

package nsk.jdi.DoubleValue.equals;

import nsk.share.*;
import nsk.share.jpda.*;
import nsk.share.jdi.*;

/**
 * This class is used as debuggee application for the equals001a JDI test.

 */

public class equals001a {

    //----------------------------------------------------- templete section

    static final int PASSED = 0;
    static final int FAILED = 2;
    static final int PASS_BASE = 95;

    //--------------------------------------------------   log procedures

    static boolean verbMode = false;  // debugger may switch to true

    private static void log1(String message) {
        if (verbMode)
            System.err.println("**> equals001a: " + message);
    }

    private static void logErr(String message) {
        if (verbMode)
            System.err.println("!!**> equals001a: " + message);
    }

    //====================================================== test program
    //................................................... globals for a debugger

//      public static double pos_inf  =  Double.POSITIVE_INFINITY;
//      public static double pos_zero =  0.0d;
//      public static double neg_zero = -0.0d;
//      public static double neg_inf  =  Double.NEGATIVE_INFINITY;

//      public static double pos_largest  =  Double.MAX_VALUE;
//      public static double pos_smallest =  Double.MIN_VALUE;
//      public static double neg_largest  = -Double.MAX_VALUE;
//      public static double neg_smallest = -Double.MIN_VALUE;

//      public static double double_nan   =  Double.NaN;

        public static double plus1_1 =  +1.0d;
        public static double plus1_2 =  +1.0d;
        public static double minus1  =  -1.0d;

        public static float  float_plus1 = +1.0f;

    //....................................................
    //----------------------------------------------------   main method

    public static void main (String argv[]) {

        for (int i=0; i<argv.length; i++) {
            if ( argv[i].equals("-vbs") || argv[i].equals("-verbose") ) {
                verbMode = true;
                break;
            }
        }
        log1("debuggee started!");

        // informing a debugger of readyness
        ArgumentHandler argHandler = new ArgumentHandler(argv);
        IOPipe pipe = argHandler.createDebugeeIOPipe();
        pipe.println("ready");

        int exitCode = PASSED;
        for (int i = 0; ; i++) {

            String instruction;

            log1("waiting for an instruction from the debugger ...");
            instruction = pipe.readln();
            if (instruction.equals("quit")) {
                log1("'quit' recieved");
                break ;

            } else if (instruction.equals("newcheck")) {
                switch (i) {

    //------------------------------------------------------  section tested

                case 0:
                                pipe.println("checkready");
                                break ;

    //-------------------------------------------------    standard end section

                default:
                                pipe.println("checkend");
                                break ;
                }

            } else {
                logErr("ERRROR: unexpected instruction: " + instruction);
                exitCode = FAILED;
                break ;
            }
        }

        System.exit(exitCode + PASS_BASE);
    }
}