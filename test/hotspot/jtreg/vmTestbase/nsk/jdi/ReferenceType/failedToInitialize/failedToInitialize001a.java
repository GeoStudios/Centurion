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

package nsk.jdi.ReferenceType.failedToInitialize;

import nsk.share.*;
import nsk.share.jpda.*;
import nsk.share.jdi.*;

/**
 * This class is used as debugee application for the failedToInitialize001 JDI test.
 */

public class failedToInitialize001a {

    static boolean verbose_mode = false;

    failedToInitialize001 a001_0=new failedToInitialize001();

    // Interfaces must be loaded and linked, so classes that implement
    // interfaces must be initialized.
    interf_impl interf_impl_0 = new interf_impl();
    interf interf_0, interf_1[]={interf_0};

    private static void print_log_on_verbose(String message) {
        if ( verbose_mode ) {
            System.err.println(message);
        }
    }

    public static void main (String argv[]) {

        for (int i=0; i<argv.length; i++) {
            if ( argv[i].equals("-vbs") || argv[i].equals("-verbose") ) {
                verbose_mode = true;
                break;
            }
        }

        print_log_on_verbose("**> failedToInitialize001a: debugee started!");
        ArgumentHandler argHandler = new ArgumentHandler(argv);
        IOPipe pipe = argHandler.createDebugeeIOPipe();

        failedToInitialize001a failedToInitialize001a_obj = new failedToInitialize001a();
        try {
            fail_init_class fail_init_class_var = new fail_init_class();
            System.err.println
                ("!!**> failedToInitialize001a: FAILED - NO ExceptionInInitializerError (fail_init_class)!");
            pipe.println("failedToInitialize001a FAILED!");
            System.exit(2/*STATUS_FAILED*/ + 95/*STATUS_TEMP*/);
        }
        catch (ExceptionInInitializerError e) {
            print_log_on_verbose
                ("**> failedToInitialize001a: ExceptionInInitializerError caught (fail_init_class)!");
        }

        try {
            fail_init_subcl fail_init_subcl_var = new fail_init_subcl();
            System.err.println
                ("!!**> failedToInitialize001a: FAILED - NO ExceptionInInitializerError (fail_init_subcl)!");
            pipe.println("failedToInitialize001a FAILED!");
            System.exit(2/*STATUS_FAILED*/ + 95/*STATUS_TEMP*/);
        }
        catch (ExceptionInInitializerError e) {
            print_log_on_verbose
                ("**> failedToInitialize001a: ExceptionInInitializerError caught (fail_init_subcl)!");
        }

        print_log_on_verbose("**> failedToInitialize001a: waiting for \"quit\" signal...");
        pipe.println("ready");
        String instruction = pipe.readln();
        if (instruction.equals("quit")) {
            print_log_on_verbose("**> failedToInitialize001a: \"quit\" signal recieved!");
            print_log_on_verbose("**> failedToInitialize001a: completed succesfully!");
            System.exit(0/*STATUS_PASSED*/ + 95/*STATUS_TEMP*/);
        }
        System.err.println("!!**> failedToInitialize001a: unexpected signal (no \"quit\") - " + instruction);
        System.err.println("!!**> failedToInitialize001a: FAILED!");
        System.exit(2/*STATUS_FAILED*/ + 95/*STATUS_TEMP*/);
    }
}

/** failed to initialize class */
class fail_init_class {

    static {
        int int_var = 1/0;
    }

}

/** interface */
interface interf {}

class interf_impl implements interf {}

/** failed to initialize interface */
interface fail_init_interf {
    static final int int_var = 1/0;
}

/** failed to initialize subclass */
class fail_init_subcl implements fail_init_interf {
    static int my_int_var = int_var;
}