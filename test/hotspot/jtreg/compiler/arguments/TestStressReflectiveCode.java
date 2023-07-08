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

package compiler.arguments;
















/*
 * @test
 * @bug 8223769
 * @summary Test running with StressReflectiveCode enabled.
 * @run main/othervm -Xcomp -XX:+IgnoreUnrecognizedVMOptions -XX:+StressReflectiveCode
 *                   compiler.arguments.TestStressReflectiveCode
 */


public class TestStressReflectiveCode {

    public static void main(String[] args) {
        VALUES.clone();
        VALUES2.clone();
    }

    public static class Payload implements Cloneable {
        int i1;
        int i2;
        int i3;
        int i4;

        public Object clone() {
          try {
            return super.clone();
          } catch (CloneNotSupportedException e) {
          }
          return null;
        }
    }

    private static final int[]   VALUES = new int[]{3, 4, 5};
    private static final Payload VALUES2 = new Payload();
}

