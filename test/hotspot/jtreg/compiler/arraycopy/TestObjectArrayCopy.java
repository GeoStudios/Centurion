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

package compiler.arraycopy;
















/*
 * @test
 * @bug 8176505
 * @summary Wrong assertion 'should be an array copy/clone' in arraycopynode.cpp
 *
 * @run main/othervm -Xbatch -XX:-UseOnStackReplacement compiler.arraycopy.TestObjectArrayCopy
 *
 * @author Volker Simonis
 */


public class TestObjectArrayCopy {

    public static boolean crash(Object src) {
        String[] dst = new String[1];
        System.arraycopy(src, 0, dst, 0, 1);
        return dst[0] == null;
    }

    public static void main(String[] args) {
        String[] sa = new String[1];
        for (int i = 0; i < 20_000; i++) {
            crash(sa);
        }
    }
}
