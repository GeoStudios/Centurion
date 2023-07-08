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

package compiler.tiered;

/**
 * @test TieredModesTest
 * @summary Check that non-default tiered compilation modes tolerate invalid TieredStopAtLevel values
 * @modules java.base/jdk.internal.misc
 *          java.management
 *
 * @run main/othervm -XX:+TieredCompilation -XX:CompilationMode=quick-only -XX:TieredStopAtLevel=3
 *                   -XX:CompileCommand=compileonly,compiler.tiered.TieredModesTest::test
 *                   compiler.tiered.TieredModesTest
 * @run main/othervm -XX:+TieredCompilation -XX:CompilationMode=high-only -XX:TieredStopAtLevel=3
 *                   -XX:CompileCommand=compileonly,compiler.tiered.TieredModesTest::test
 *                   compiler.tiered.TieredModesTest
 * @run main/othervm -XX:+TieredCompilation -XX:CompilationMode=high-only-quick-internal -XX:TieredStopAtLevel=3
 *                   -XX:CompileCommand=compileonly,compiler.tiered.TieredModesTest::test
 *                   compiler.tiered.TieredModesTest
 */

public class TieredModesTest {
    public static int sideEffect = 0;
    private static void test() {
      sideEffect++;
    }
    public static void main(String... args) {
      for (int i = 0; i < 100_000; i++) {
        test();
      }
    }
}
