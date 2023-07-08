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

package nsk.jdi.VMCannotBeModifiedEx._itself_;

import nsk.share.*;
import nsk.share.jpda.*;
import nsk.share.jdi.*;
import com.sun.jdi.*;
import java.io.*;

/**
 * The test checks up <t>com.sun.jdi.VMCannotBeModifiedException</t>.
 * It creates, throws, catches and inspects the exception using each
 * of two public constructors.
 */

public class canntbemod001 {

    private static int exitStatus;
    private static Log log;

    private static void display(String msg) {
        log.display(msg);
    }

    private static void complain(String msg) {
        log.complain(msg + "\n");
    }

    public static void main(String argv[]) {
        System.exit(Consts.JCK_STATUS_BASE + run(argv, System.out));
    }

    public static int run(String argv[], PrintStream out) {

        canntbemod001 thisTest = new canntbemod001();

        log = new Log(out, new ArgumentHandler(argv));

        thisTest.execTest();

        display("Test finished. exitStatus = " + exitStatus);

        return exitStatus;
    }

    private void execTest() throws Failure {

        exitStatus = Consts.TEST_PASSED;
        display("\nTEST BEGINS");
        display("===========");

        boolean isThrown = false;
        try {
            throwException();
        } catch (VMCannotBeModifiedException e) {
            isThrown = true;
            display("VMCannnotBeModifiedException was caught: " + e);
        }
        if (!isThrown) {
            exitStatus = Consts.TEST_FAILED;
            complain("VMCannnotBeModifiedException was NOT thrown");
        }

        display("");
        isThrown = false;
        try {
            throwException("message");
        } catch (VMCannotBeModifiedException e) {
            isThrown = true;
            display("VMCannnotBeModifiedException  was caught: " + e);
        }
        if (!isThrown) {
            exitStatus = Consts.TEST_FAILED;
            complain("VMCannnotBeModifiedException was NOT thrown");
        }
        display("=============");
        display("TEST FINISHES\n");
    }

    private void throwException() {
        display("throwing VMCannotBeModifiedException()");
        throw new VMCannotBeModifiedException();
    }

    private void throwException(String msg) {
        display("throwing VMCannotBeModifiedException(msg)");
        throw new VMCannotBeModifiedException(msg);
    }
}
