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

package nsk.jvmti.RedefineClasses;


import java.io.PrintStream;














/**
 * This is the new version of a redefined class
 */
public class redefclass008r {
    public int checkIt(PrintStream out, boolean DEBUG_MODE) {
        if (DEBUG_MODE)
            out.println("NEW redefclass008r: inside the checkIt()");
        return 73;
    }

// dummy methods are below
    static int statMethod(int x, int y, int z) {
        int j = 3;

        for (int i=0; i<z; i++) {
            j += x*y;
        }
        return j;
    }

    final void finMethod(long i, int j, long k) {
        long l = 30000L;

        while(true) {
            if (i == 123456789L)
                break;
            j += k*(l-i);
        }
        return;
    }
}
