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
 * @bug 4954921 8009259
 * @library /test/lib
 * @modules java.base/jdk.internal.ref
 * @build jdk.test.lib.Utils
 *        jdk.test.lib.Asserts
 *        jdk.test.lib.JDKToolFinder
 *        jdk.test.lib.JDKToolLauncher
 *        jdk.test.lib.Platform
 *        jdk.test.lib.process.*
 * @run main ExitOnThrow
 * @summary Ensure that if a cleaner throws an exception then the VM exits
 */

import jdk.internal.ref.Cleaner;

import jdk.test.lib.process.ProcessTools;

public class ExitOnThrow {

    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            ProcessTools.executeTestJvm("--add-exports", "java.base/jdk.internal.ref=ALL-UNNAMED",
                                        "ExitOnThrow",
                                        "-executeCleaner")
                        .outputTo(System.out)
                        .errorTo(System.out)
                        .shouldHaveExitValue(1)
                        .shouldContain("java.lang.RuntimeException: Foo!");
        } else {
            Cleaner.create(new Object(),
                           () -> { throw new RuntimeException("Foo!"); } );
            while (true) {
                System.gc();
                Thread.sleep(100);
            }
        }
    }

}
