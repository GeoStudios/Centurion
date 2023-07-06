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

package compiler.compilercontrol.share.pool.subpack;

import compiler.compilercontrol.share.pool.MethodHolder;
import compiler.compilercontrol.share.pool.SubMethodHolder;

/**
 * This is a clone of the c.c.s.pool.sub.Klass used to test pattern matching
 * Full class name contains both suffix (Dup) and prefix (c.c.s.pool.subpack)
 */
public class KlassDup extends MethodHolder {
    public void method(int a, String[] ss, Integer i, byte[] bb, double[][] dd) { }

    public void method() { }

    public static String smethod() {
        return "ABC";
    }

    public static String smethod(int iarg, int[] aarg) {
        return "ABC";
    }

    public static Integer smethod(Integer arg) {
        Integer var = 1024;
        return arg + var;
    }

    // Internal class and constructor
    public static class Internal extends SubMethodHolder {
        public Internal() { }

        public Double method(Float fl) { return Double.valueOf(fl); }

        public Double methodDup() {
            return Math.exp(1.0);
        }

        public static Integer smethod(Integer arg) {
            Integer var = 1024;
            return arg + var;
        }
    }
}