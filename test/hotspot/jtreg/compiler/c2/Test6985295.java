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

package compiler.c2;

/**
 * @test
 * @bug 6985295
 * @summary JVM fails to evaluate condition randomly
 *
 * @run main/othervm -Xbatch compiler.c2.Test6985295
 */

public class Test6985295 {

    public static void main(String[] args) {
        int min = Integer.MAX_VALUE-50000;
        int max = Integer.MAX_VALUE;
        System.out.println("max = " + max);
        long counter = 0;
        int i;
        for(i = min; i <= max; i++) {
            counter++;
            if (counter > 1000000) {
              System.out.println("Passed");
              System.exit(95);
            }
        }
        System.out.println("iteration went " + counter + " times (" + i + ")");
        System.out.println("FAILED");
        System.exit(97);
    }
}

