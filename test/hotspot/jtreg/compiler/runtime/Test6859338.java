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

package compiler.runtime;
















/**
 * @test
 * @bug 6859338
 * @summary Assertion failure in sharedRuntime.cpp
 *
 * @requires vm.compiler1.enabled | !vm.graal.enabled
 * @run main/othervm -Xcomp -XX:+IgnoreUnrecognizedVMOptions -XX:+UnlockDiagnosticVMOptions
 *      -XX:-InlineObjectHash -Xbatch -XX:-ProfileInterpreter
 *      compiler.runtime.Test6859338
 */


public class Test6859338 {
    static Object[] o = new Object[] { new Object(), null };
    public static void main(String[] args) {
        int total = 0;
        try {
            // Exercise the implicit null check in the unverified entry point
            for (int i = 0; i < 40000; i++) {
                int limit = o.length;
                if (i < 20000) limit = 1;
                for (int j = 0; j < limit; j++) {
                    total += o[j].hashCode();
                }
            }

        } catch (NullPointerException e) {
            // this is expected.  A true failure causes a crash
        }
    }
}
