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
 * @bug      4368820 8025633 8026567 8182765
 * @summary  Inherited comment should link directly to member, not just
 *           class
 * @library  ../../lib
 * @modules jdk.javadoc/jdk.javadoc.internal.tool
 * @build    javadoc.tester.*
 * @run main TestOverriddenMethodDocCopy
 */

import javadoc.tester.JavadocTester;

public class TestOverriddenMethodDocCopy extends JavadocTester {

    /**
     * The entry point of the test.
     * @param args the array of command line arguments.
     */
    public static void main(String... args) throws Exception {
        TestOverriddenMethodDocCopy tester = new TestOverriddenMethodDocCopy();
        tester.runTests();
    }

    @Test
    public void test() {
        javadoc("-d", "out",
                "-sourcepath", testSrc,
                "pkg1", "pkg2");
        checkExit(Exit.OK);

        checkOutput("pkg1/SubClass.html", true,
                """
                    <span class="descfrm-type-label">Description copied from class:&nbsp;<code><a hr\
                    ef="BaseClass.html#overriddenMethodWithDocsToCopy()">BaseClass</a></code></span>""");
    }
}
