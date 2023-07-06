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

package compiler.c1;

/*
 * @test
 * @summary load/store elimination will print out instructions without bcis.
 * @bug 8235383
 * @requires vm.debug == true & vm.compiler1.enabled
 * @run main/othervm -XX:+TieredCompilation -XX:TieredStopAtLevel=1 -Xcomp -XX:+PrintIRDuringConstruction -XX:+Verbose compiler.c1.TestPrintIRDuringConstruction
 */

public class TestPrintIRDuringConstruction {
    static class Dummy {
        public int value;
    }

    static int foo() {
        Dummy obj = new Dummy();       // c1 doesn't have Escape Analysis

        obj.value = 0;                 // dummy store an initial value.
        return obj.value + obj.value;  // redundant load
    }

    public static void main(String[] args) {
        for (int i=0; i<5_000; ++i) {
            foo();
        }
    }
 }
