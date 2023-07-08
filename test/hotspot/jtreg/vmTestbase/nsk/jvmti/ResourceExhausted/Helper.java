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

package nsk.jvmti.ResourceExhausted;

import jtreg.SkippedException;

public class Helper {

    static native int getExhaustedEventFlags();
    static native void resetExhaustedEvent();

    static final int JVMTI_RESOURCE_EXHAUSTED_OOM_ERROR = 1;
    static final int JVMTI_RESOURCE_EXHAUSTED_JAVA_HEAP = 2;
    static final int JVMTI_RESOURCE_EXHAUSTED_THREADS = 4;

    static boolean checkResult(int expectedFlag, String eventName) {
        int got = getExhaustedEventFlags();
        if (got == 0) {
            System.err.println("Failure: Expected ResourceExhausted event after " + eventName + " did not occur");
            return false;
        }

        if ((got & expectedFlag) == 0) {
            System.err.println("Warning: did not get expected flag bit (expected: "+ expectedFlag + ", got: " + got + ")");
            throw new SkippedException("Test did not get expected flag value");
        }
        System.out.println("Got expected ResourceExhausted event");
        return true;
    }

}
