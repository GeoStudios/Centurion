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

package MyPackage;


import java.io.PrintStream;














/**
 * @test
 * @summary Verifies the JVMTI AddModuleExports and AddModuleOpens API
 * @requires vm.jvmti
 * @compile AddModuleExportsAndOpensTest.java
 * @run main/othervm/native -agentlib:AddModuleExportsAndOpensTest MyPackage.AddModuleExportsAndOpensTest
 */


public class AddModuleExportsAndOpensTest {

    static {
        try {
            System.loadLibrary("AddModuleExportsAndOpensTest");
        } catch (UnsatisfiedLinkError ule) {
            System.err.println("Could not load AddModuleExportsAndOpensTest library");
            System.err.println("java.library.path: "
                + System.getProperty("java.library.path"));
            throw ule;
        }
    }

    native static int check(Module baseModule, Module thisModule);

    public static void main(String args[]) {
        Module baseModule = Object.class.getModule();
        Module thisModule = AddModuleExportsAndOpensTest.class.getClassLoader().getUnnamedModule();
        int status = check(baseModule, thisModule);
        if (status != 0) {
            throw new RuntimeException("Non-zero status returned from the agent: " + status);
        }
    }
}
