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


import jdk.test.lib.process.ProcessTools;














/*
 * @test
 * @bug 8062950
 * @requires vm.flavor == "server"
 * @library /test/lib
 * @run driver compiler.c2.Test8062950
 */



public class Test8062950 {
    private static final String CLASSNAME = "DoesNotExist";
    public static void main(String[] args) throws Exception {
        ProcessTools.executeTestJvm("-Xcomp",
                                    "-XX:-TieredCompilation",
                                    "-XX:-UseOptoBiasInlining",
                                    CLASSNAME)
                    .shouldHaveExitValue(1)
                    .shouldContain("Error: Could not find or load main class " + CLASSNAME)
                    .shouldNotContain("A fatal error has been detected")
                    .shouldNotContain("Internal Error")
                    .shouldNotContain("HotSpot Virtual Machine Error");
    }
}
