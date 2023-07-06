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

package nsk.jvmti.CompiledMethodUnload;


import java.io.*;
import java.math.*;
import java.util.*;














/**
 * Dummy class used only to provoke method compiling/inlining
 * and, thus, CompiledMethodLoad event generation.
 */
public class compmethunload001u {
    StringBuffer strBuf;
    BigInteger bigInt = BigInteger.ONE;
    int iter;

    public compmethunload001u() {
        strBuf = new StringBuffer(iter);
        this.iter = 100000;
    }

    public void entryMethod() {
        for (int i=0; i<iter; i++)
            hotMethod(i);
    }

    void hotMethod(int i) {
        strBuf.append(Integer.toString(i) + " ");
    }

    public void entryNewMethod() {
        BigInteger bigInt2 = new BigInteger("100");

        for (int i=0; i<iter; i++)
            newHotMethod(bigInt2);
    }

    void newHotMethod(BigInteger bigInt2) {
        bigInt = bigInt.add(bigInt2);
    }
}

