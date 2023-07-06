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

package compiler.whitebox;

/*
 * @test ClearMethodStateTest
 * @bug 8006683 8007288 8022832
 * @summary testing of WB::clearMethodState()
 * @library /test/lib /
 * @modules java.base/jdk.internal.misc
 *          java.management
 *
 * @requires vm.opt.DeoptimizeALot != true
 *
 * @build sun.hotspot.WhiteBox
 * @run driver jdk.test.lib.helpers.ClassFileInstaller sun.hotspot.WhiteBox
 * @run main/othervm -Xbootclasspath/a:. -Xmixed -XX:+UnlockDiagnosticVMOptions
 *                   -XX:+WhiteBoxAPI -XX:+PrintCompilation -XX:-UseCounterDecay
 *                   compiler.whitebox.ClearMethodStateTest
 */

public class ClearMethodStateTest extends CompilerWhiteBoxTest {

    public static void main(String[] args) throws Exception {
        String directive =
                "[{ match:\"*SimpleTestCaseHelper.*\", BackgroundCompilation: false }, " +
                " { match:\"*.*\", inline:\"-*SimpleTestCaseHelper.*\"}]";
        if (WHITE_BOX.addCompilerDirective(directive) != 2) {
            throw new RuntimeException("Could not add directive");
        }
        try {
            CompilerWhiteBoxTest.main(ClearMethodStateTest::new, args);
        } finally {
            WHITE_BOX.removeCompilerDirective(2);
        }
    }

    private ClearMethodStateTest(TestCase testCase) {
        super(testCase);
        // to prevent inlining of #method
        WHITE_BOX.testSetDontInlineMethod(method, true);
    }

    /**
     * Tests {@code WB::clearMethodState()} by calling it before/after
     * compilation. For non-tiered, checks that counters will be rested after
     * clearing of method state.
     *
     * @throws Exception if one of the checks fails.
     */
    @Override
    protected void test() throws Exception {

        checkNotCompiled();
        compile();
        WHITE_BOX.clearMethodState(method);
        checkCompiled();
        WHITE_BOX.clearMethodState(method);
        deoptimize();
        checkNotCompiled();
    }
}