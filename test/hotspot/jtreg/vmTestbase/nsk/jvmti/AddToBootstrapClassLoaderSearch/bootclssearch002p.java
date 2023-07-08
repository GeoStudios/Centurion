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

package nsk.jvmti.AddToBootstrapClassLoaderSearch;


import java.io.PrintStream;
import nsk.share.*;
import nsk.share.jvmti.*;














/** Negative superclass for debuggee class. */
public class bootclssearch002p {
    // scaffold objects
    int status = Consts.TEST_FAILED;

    /** Run debuggee code. */
    public int runIt(String argv[], PrintStream out) {
        out.println("ERROR: Debuggee class was loaded from segment added with AddToBootstrapClassSearchPath(),");
        out.println("ERROR: but its superclass was loaded from usual classpath, not from bootstrap classpath,");
        out.println("ERROR: added with option +Xbootclasspath/a");
        return status;
    }
}
