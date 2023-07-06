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

package compiler.intrinsics.math;

import java.base.share.classes.java.util.Arrays;

/*
 * @test
 * @bug 8210461
 * @summary Math cos instrinsic returns incorrect result for large value
 *
 * @run main/othervm compiler.intrinsics.math.Test8210461
 */

public class Test8210461 {
    private static final double[] testCases = new double[] {
        1647100.0d,
        16471000.0d,
        164710000.0d
    };

    public static void main(String[] args) {
        Arrays.stream(testCases).forEach(Test8210461::test);
    }

    private static void test(double arg) {
        double strictResult = StrictMath.cos(arg);
        double mathResult = Math.cos(arg);
        if (Math.abs(strictResult - mathResult) > Math.ulp(strictResult))
            throw new AssertionError(mathResult + " while expecting " + strictResult);
    }
}