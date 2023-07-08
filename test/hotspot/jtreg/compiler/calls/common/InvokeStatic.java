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

package compiler.calls.common;


import jdk.test.lib.Asserts;














/**
 * A test class checking InvokeStatic instruction
 */
public class InvokeStatic extends CallsBase {
    private static final Object LOCK = new Object();

    public static void main(String args[]) {
        new InvokeStatic().runTest(args);
    }

    /**
     * A native caller method, assumed to called "callee"/"calleeNative"
     */
    @Override
    public native void callerNative();

    /**
     * A caller method, assumed to called "callee"/"calleeNative"
     */
    @Override
    public void caller() {
        if (nativeCallee) {
            Asserts.assertTrue(calleeNative(this, 1, 2L, 3.0f, 4.0d, "5"),
                    CALL_ERR_MSG);
        } else {
            Asserts.assertTrue(callee(this, 1, 2L, 3.0f, 4.0d, "5"),
                    CALL_ERR_MSG);
        }
    }

    /**
     * A callee method, assumed to be called by "caller"/"callerNative"
     */
    public static boolean callee(InvokeStatic instance, int param1,
            long param2, float param3, double param4, String param5) {
        instance.calleeVisited = true;
        CallsBase.checkValues(param1, param2, param3, param4, param5);
        return true;
    }

    /**
     * A native callee method, assumed to be called by "caller"/"callerNative"
     */
    public static native boolean calleeNative(InvokeStatic instance,
            int param1, long param2, float param3, double param4, String param5);

    /**
     * Returns object to lock execution on
     * @return lock object
     */
    @Override
    protected Object getLockObject() {
        return LOCK;
    }

    /**
     * Provides callee parameters types to search method
     * @return array of types
     */
    protected Class[] getCalleeParametersTypes() {
        return new Class[]{InvokeStatic.class, int.class, long.class,
            float.class, double.class, String.class};
    }
}
