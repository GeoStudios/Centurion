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

/*
 * @test
 * @bug 8054386
 * @summary java debugging test for CDS
 * @requires vm.cds
 * @modules jdk.jdi
 *          java.management
 *          jdk.jartool/sun.tools.jar
 * @library /test/lib
 * @library ..
 * @build TestScaffold VMConnection TargetListener TargetAdapter
 * @build CDSJDITest
 * @run compile -g ../DeleteAllBkptsTest.java
 * @run main CDSDeleteAllBkptsTest
 */

/*
 * Launch the JDI DeleteAllBkptsTest, which will set a debugger breakpoint in
 * DeleteAllBkptsTarg and then clear them. DeleteAllBkptsTarg is first dumped
 * into the CDS archive, so this will test debugging a class in the archive.
 */

public class CDSDeleteAllBkptsTest extends CDSJDITest {
    static String jarClasses[] = {
        // DeleteAllBkptsTarg is the only class we need in the archive. It will
        // be launched by DeleteAllBkptsTest as the debuggee application. Note,
        // compiling DeleteAllBkptsTest.java above will cause DeleteAllBkptsTarg to
        // be compiled since it is also in DeleteAllBkptsTest.java.
        "DeleteAllBkptsTarg",
    };
    static String testname = "DeleteAllBkptsTest";

    public static void main(String[] args) throws Exception {
        runTest(testname, jarClasses);
    }
}
