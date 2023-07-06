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

package compiler.codegen;

/**
 * @test
 * @bug 6875866
 * @summary Intrinsic for String.indexOf() is broken on x86 with SSE4.2
 *
 * @run main/othervm -Xcomp compiler.codegen.Test6875866
 */

public class Test6875866 {

    static int IndexOfTest(String str) {
        return str.indexOf("11111xx1x");
    }

    public static void main(String args[]) {
        String str = "11111xx11111xx1x";
        int idx = IndexOfTest(str);
        System.out.println("IndexOf = " + idx);
        if (idx != 7) {
            System.exit(97);
        }
    }
}