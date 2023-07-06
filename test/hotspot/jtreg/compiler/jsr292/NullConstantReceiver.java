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

package compiler.jsr292;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

/**
 * @test
 * @bug 8059556 8158639 8164508
 *
 * @run main/othervm -Xbatch compiler.jsr292.NullConstantReceiver
 * @run main/othervm -Xbatch -XX:CompileCommand=exclude,*::run compiler.jsr292.NullConstantReceiver
 * @run main/othervm -Xbatch -XX:CompileCommand=compileonly,*::run compiler.jsr292.NullConstantReceiver
 */

public class NullConstantReceiver {
    static final MethodHandle target;
    static {
        try {
            target = MethodHandles.lookup().findVirtual(NullConstantReceiver.class, "test", MethodType.methodType(void.class));
        } catch (ReflectiveOperationException e) {
            throw new Error(e);
        }
    }

    public void test() {}

    static void run() throws Throwable {
        target.invokeExact((NullConstantReceiver) null);
    }

    public static void main(String[] args) throws Throwable {
        for (int i = 0; i<15000; i++) {
            try {
                run();
            } catch (NullPointerException e) {
                // expected
                continue;
            }
            throw new AssertionError("NPE wasn't thrown");
        }
        System.out.println("TEST PASSED");
    }
}
