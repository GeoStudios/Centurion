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

package nsk.jdi.Method.isObsolete;


import nsk.share.*;
import nsk.share.jpda.*;
import nsk.share.jdi.*;














//    THIS TEST IS LINE NUMBER SENSITIVE
/**
 * This is the redefined class for the isobsolete002 JDI test.
 */
public class isobsolete002b {

    static final String prefix = "**> debuggee: ";
    static Log log;

    isobsolete002b(Log log) {
        this.log = log;
        log.display(prefix + "   This is the class to be redefined");
    }

    static int i1 = 0;
    static int i2 = 0;

    static void m2() {
        i1 = 1;
        i2 = 1; // isobsolete001.brkpLineNumber
    }

    static void m1() {
        log.display(prefix + "method m1: before   m2()");
        m2();
        log.display(prefix + "method m1: after    m2()");
    }
}
