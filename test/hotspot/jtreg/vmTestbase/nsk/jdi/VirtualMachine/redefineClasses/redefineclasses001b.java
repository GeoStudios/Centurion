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

package nsk.jdi.VirtualMachine.redefineClasses;

import nsk.share.*;
import nsk.share.jpda.*;
import nsk.share.jdi.*;

/**
 * This is the redefined class for the redefineclasses001 JDI test.
 */

public class redefineclasses001b {

    static final String prefix = "**> debuggee: ";
    static Log log;

    redefineclasses001b(Log log) {
        this.log = log;
        log.display(prefix + "   This is the class to be redefined");
    }

    static int i1 = 0;

    static int bpline = 2;

    static void m2() {
        i1 = 1;
        i1 = 1;
    }

    static void m1() {
        log.display(prefix + "redefined method: before:   m2()");
        m2();
        log.display(prefix + "redefined method: after:    m2()");
        log.display(prefix + "redefined method: value of i1 == " + i1);
    }
}
