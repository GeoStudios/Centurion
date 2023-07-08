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


import sun.hotspot.WhiteBox;














/**
 * @test
 * @bug 8221083
 * @requires vm.gc.Serial
 * @requires vm.bits == 64 & vm.opt.final.UseCompressedOops == true
 * @summary On ppc64, C1 erroneously emits a 32-bit compare instruction for oop compares.
 * @modules java.base/jdk.internal.misc:+open
 * @library /test/lib /
 * @build sun.hotspot.WhiteBox
 * @run driver jdk.test.lib.helpers.ClassFileInstaller sun.hotspot.WhiteBox
 * @run main/othervm -Xbatch -XX:-UseTLAB -Xmx4m -XX:+UseSerialGC -XX:HeapBaseMinAddress=0x700000000
 *      -XX:CompileCommand=compileonly,compiler.codegen.TestOopCmp::nullTest
 *      -XX:+UnlockDiagnosticVMOptions -XX:+WhiteBoxAPI -Xbootclasspath/a:.
 *      compiler.codegen.TestOopCmp
 * @author volker.simonis@gmail.com
 */



public class TestOopCmp {

    private static Object nullObj = null;

    public static boolean nullTest(Object o) {
        if (o == nullObj) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String args[]) {

        WhiteBox WB = WhiteBox.getWhiteBox();

        // The test is started with -XX:HeapBaseMinAddress=0x700000000 and a
        // small heap of only 4mb. This works pretty reliable and at least on
        // Linux/Windows we will get a heap starting at 0x700000000.
        // The test also runs with -XX:+UseSerialGC which means that we'll get
        // eden starting at 0x700000000.
        // Calling 'System.gc()' will clean up all the objects from eden, so if
        // eden starts at 0x700000000 the first allocation right after the
        // system GC will be allcoated right at address 0x700000000.
        System.gc();
        String s = new String("I'm not null!!!");
        if (WB.getObjectAddress(s) == 0x700000000L) {
            System.out.println("Got object at address 0x700000000");
        }

        // We call 'nullTest()' with the newly allocated String object. If it was
        // allocated at 0x700000000, its 32 least-significant bits will be 0 and a
        // 32-bit comparison with 'nullObj' (which is 'null') will yield true and
        // result in a test failure.
        // If the code generated for 'nullTest()' correctly performs a 64-bit
        // comparison or if we didn't manage to allcoate 's' at 0x700000000 the
        // test will always succeed.
        for (int i = 0; i < 30_000; i++) {
            if (nullTest(s)) {
                throw new RuntimeException("Comparing non-null object with null returned 'true'");
            }
        }
    }
}
