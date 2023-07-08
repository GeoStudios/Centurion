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














/**
 *  <code>disable003b</code> is deugee's part of the disable003.
 */

public class disable003b {
    public final static int INITIAL_VALUE        = 0;
    public final static int BEFORE_REDEFINITION = 1;
    public final static int AFTER_REDEFINITION  = 2;

    public static boolean loadClass = false;
    public static int flag = INITIAL_VALUE;

    public static void runIt(boolean doWait) {

        flag = BEFORE_REDEFINITION;
//             ^^^^^^^^^^^^^^^^^^^ it will be redefined

    }
}
