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

package compiler.interpreter;
















/**
 * @test
 * @bug 6539464
 * @summary Math.log() produces inconsistent results between successive runs.
 *
 * @run main/othervm -Xcomp
 *      -XX:CompileCommand=compileonly,compiler.interpreter.Test6539464::main
 *      compiler.interpreter.Test6539464
 */


public class Test6539464 {
    static double log_value = 17197;
    static double log_result = Math.log(log_value);

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 1000000; i++) {
            double log_result2 = Math.log(log_value);
            if (log_result2 != log_result) {
                throw new InternalError("Math.log produces inconsistent results: " + log_result2 + " != " + log_result);
            }
        }
    }
}
