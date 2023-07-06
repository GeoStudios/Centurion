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

package nsk.jdi.EventRequest.disable;


import nsk.share.*;
import nsk.share.jpda.*;
import nsk.share.jdi.*;
import java.io.*;














//    THIS TEST IS LINE NUMBER SENSITIVE

/**
 *  <code>disable003a</code> is deugee's part of the disable003.
 */
public class disable003a {
    public final static String brkpMethodName = "main";
    public final static int brkpLineNumber = 49;

    private static Log log = null;

    public static void main (String argv[]) {
        ArgumentHandler argHandler = new ArgumentHandler(argv);
        log = new Log(System.out, argHandler);

        disable003b.loadClass = true;

        log.display("completed succesfully."); // brkpLineNumber
        System.exit(Consts.TEST_PASSED + Consts.JCK_STATUS_BASE);
    }
}
