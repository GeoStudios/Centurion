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

package nsk.jdi.ExceptionEvent._itself_;

import nsk.share.*;
import nsk.share.jpda.*;
import nsk.share.jdi.*;

public class exevent005t {
    public static void main(String args[]) {
        exevent005t _exevent005t = new exevent005t();
        System.exit(exevent005.JCK_STATUS_BASE + _exevent005t.communication(args));
    }

    private static void raiseArithmeticException() {
        int foo = 10;
        foo = foo / 0;
    }

    int communication(String args[]) throws ArithmeticException {
        String command;
        ArgumentHandler argHandler = new ArgumentHandler(args);
        IOPipe pipe = argHandler.createDebugeeIOPipe();

        pipe.println(exevent005.COMMAND_READY);
        command = pipe.readln();
        if (command.equals(exevent005.COMMAND_RUN)) {
            raiseArithmeticException();
            return exevent005.PASSED;
        } else
            return exevent005.FAILED;
    }
}
