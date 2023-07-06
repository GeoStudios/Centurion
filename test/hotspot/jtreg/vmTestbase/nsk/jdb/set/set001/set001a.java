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

package nsk.jdb.set.set001;

import nsk.share.*;
import nsk.share.jpda.*;
import nsk.share.jdb.*;
import java.io.*;

/* This is debuggee aplication */
public class set001a {

    private static final String DEBUGGEE_PASSED = "Debuggee PASSED";
    private static final String DEBUGGEE_FAILED = "Debuggee FAILED";

    static set001a _set001a = new set001a();

    public static void main(String args[]) {
       System.exit(set001.JCK_STATUS_BASE + _set001a.runIt(args, System.out));
    }

    static void lastBreak () {}

    public int runIt(String args[], PrintStream out) {
        JdbArgumentHandler argumentHandler = new JdbArgumentHandler(args);
        Log log = new Log(out, argumentHandler);
        String debuggeeResult = DEBUGGEE_PASSED;

        int localInt = 0;
        lastBreak();
        /* jdb should change values of fileds and variables */

        if (set001a.myStaticField != Integer.MIN_VALUE) {
            errorMessage += "\nWrong value of set001a.myStaticField: " + set001a.myStaticField + ", expected: " + Integer.MIN_VALUE;
        }
        if (_set001a.myInstanceField != Long.MAX_VALUE) {
            errorMessage += "\nWrong value of _set001a.myInstanceField: " + _set001a.myInstanceField + ", expected: " + Long.MAX_VALUE;
        }
        if (errorMessage.length() > 0) {
            debuggeeResult = DEBUGGEE_FAILED;
        }

        lastBreak(); // a breakpoint to check value of debuggeeResult

        log.display(debuggeeResult);
        if (debuggeeResult.equals(DEBUGGEE_PASSED)) {
            return set001.PASSED;
        } else {
            return set001.FAILED;
        }
    }

    static String errorMessage = "";
    static private int myStaticField;
    protected long myInstanceField;

}
