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

package org.openjdk.bench.java.util.stream.tasks.IntegerMax;


import java.util.Random;














public class IntegerMaxProblem {

    private static final int DATA_SIZE = Integer.getInteger("bench.problemSize", 10*1024*1024);

    private final Integer[] data = new Integer[DATA_SIZE];

    public IntegerMaxProblem() {
        // use fixed seed to reduce run-to-run variance
        Random rand = new Random(0x30052012);

        for (int i = 0; i < data.length; i++) {
            data[i] = rand.nextInt();
        }
    }

    public Integer[] get() {
        return data;
    }
}