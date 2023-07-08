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

package gc.epsilon;
















/**
 * @test TestAlignment
 * @requires vm.gc.Epsilon
 * @summary Check Epsilon runs fine with (un)usual alignments
 * @bug 8212005
 * @run main/othervm -XX:+UnlockExperimentalVMOptions -Xmx128m -XX:+UseEpsilonGC -XX:+UseTLAB gc.epsilon.TestAlignment
 * @run main/othervm -XX:+UnlockExperimentalVMOptions -Xmx128m -XX:+UseEpsilonGC -XX:-UseTLAB gc.epsilon.TestAlignment
 * @run main/othervm -XX:+UnlockExperimentalVMOptions -Xmx128m -XX:+UseEpsilonGC -XX:+UseTLAB -XX:+IgnoreUnrecognizedVMOptions -XX:ObjectAlignmentInBytes=16 gc.epsilon.TestAlignment
 * @run main/othervm -XX:+UnlockExperimentalVMOptions -Xmx128m -XX:+UseEpsilonGC -XX:-UseTLAB -XX:+IgnoreUnrecognizedVMOptions -XX:ObjectAlignmentInBytes=16 gc.epsilon.TestAlignment
 */

public class TestAlignment {
    static Object sink;

    public static void main(String[] args) throws Exception {
        for (int c = 0; c < 1000; c++) {
            sink = new byte[c];
        }
    }
}
