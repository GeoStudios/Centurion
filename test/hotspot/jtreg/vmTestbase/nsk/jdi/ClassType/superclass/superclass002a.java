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

package nsk.jdi.ClassType.superclass;

import nsk.share.*;
import nsk.share.jpda.*;
import nsk.share.jdi.*;

/**
 * The debugged application of the test.
 */
public class superclass002a {

    //------------------------------------------------------- immutable common fields

    private static int exitStatus;
    private static ArgumentHandler argHandler;
    private static Log log;
    private static IOPipe pipe;

    //------------------------------------------------------- immutable common methods

    static void display(String msg) {
        log.display("debuggee > " + msg);
    }

    static void complain(String msg) {
        log.complain("debuggee FAILURE > " + msg);
    }

    public static void receiveSignal(String signal) {
        String line = pipe.readln();

        if ( !line.equals(signal) )
            throw new Failure("UNEXPECTED debugger's signal " + line);

        display("debugger's <" + signal + "> signal received.");
    }

    //------------------------------------------------------ mutable common fields

    //------------------------------------------------------ test specific fields

    static Enum1 f1 = Enum1.e1;
    static Enum2 f2 = Enum2.e2;
    static Enum3 f3 = Enum3.e1;
    static Enum4.Enum4_ f4 = Enum4.Enum4_.e1;
    static superclass002Enum5 f5 = superclass002Enum5.e2;
    static superclass002Enum6 f6 = superclass002Enum6.e1;
    static superclass002Enum7 f7 = superclass002Enum7.e2;
    static superclass002Enum8.Enum8_ f8 = superclass002Enum8.Enum8_.e1;

    //------------------------------------------------------ mutable common method

    public static void main (String argv[]) {
        exitStatus = Consts.TEST_FAILED;
        argHandler = new ArgumentHandler(argv);
        log = new Log(System.err, argHandler);
        pipe = argHandler.createDebugeeIOPipe(log);

        pipe.println(superclass002.SIGNAL_READY);

        //pipe.println(superclass002.SIGNAL_GO);
        receiveSignal(superclass002.SIGNAL_QUIT);

        display("completed succesfully.");
        System.exit(Consts.TEST_PASSED + Consts.JCK_STATUS_BASE);
    }

    //--------------------------------------------------------- test specific inner classes

    enum Enum1 {
        e1, e2;
    }

    enum Enum2 {
        e1 {
           int val() {return 1;}
        },

        e2 {
           int val() {return 2;}
        };
        abstract int val();
    }

    enum Enum3 implements superclass002i {
        e1 {
           int val() {return i+1;}
        },

        e2 {
           int val() {return i+2;}
        };
        abstract int val();
    }

    enum Enum4 {
       e1, e2;

       enum Enum4_ {
           e1, e2;
       }
    }

}

//--------------------------------------------------------- test specific classes

enum superclass002Enum5 {
    e1, e2;
}

enum superclass002Enum6 {
    e1 {
       int val() {return 1;}
    },

    e2 {
       int val() {return 2;}
    };
    abstract int val();
}

enum superclass002Enum7 implements superclass002i {
    e1 {
       int val() {return i+1;}
    },

    e2 {
       int val() {return i+2;}
    };
    abstract int val();
}

enum superclass002Enum8 {
   e1, e2;
   enum Enum8_ {
       e1, e2;
   }
}

interface superclass002i {
    int i = 1;
}