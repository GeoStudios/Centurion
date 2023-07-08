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

package nsk.jdi.LocalVariable.type;


import nsk.share.*;
import nsk.share.jpda.*;
import nsk.share.jdi.*;














/**
 * This class is used as debuggee application for the type001 JDI test.
 */

public class type001a {

    //----------------------------------------------------- templete section

    static final int PASSED = 0;
    static final int FAILED = 2;
    static final int PASS_BASE = 95;

    //--------------------------------------------------   log procedures

    static boolean verbMode = false;

    private static void log1(String message) {
        if (verbMode)
            System.err.println("**> type001a: " + message);
    }

    private static void logErr(String message) {
        if (verbMode)
            System.err.println("!!**> type001a: " + message);
    }

    //====================================================== test program

    static type001aTestClass obj = new type001aTestClass();

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

class type001aTestClass {

    public  void testmethod (int param) {

        boolean bl1 = false, bl2 = true;
        byte    bt1 = 0,     bt2 = 1;
        char    ch1 = 0,     ch2 = 1;
        double  db1 = 0.0d,  db2 = 1.0d;
        float   fl1 = 0.0f,  fl2 = 1.0f;
        int     in1 = 0,     in2 = 1;
        long    ln1 = 0,     ln2 = 1;
        short   sh1 = 0,     sh2 = 1;

        return;
    }
}
