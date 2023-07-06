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

package runtime.Locals;

import jdk.test.lib.Asserts;

/*
 * @test
 * @bug 8265756
 * @library /test/lib /
 * @compile TestLargeLocalVarsStackRegionHelper.jasm
 * @run main runtime.Locals.TestLargeLocalVarsStackRegion
 */

public class TestLargeLocalVarsStackRegion {

    // Some platforms (such as windows-aarch64) may have
    // stack page touch order restrictions.
    // Test calls method with large local vars stack region
    // to trigger usage of several stack memory pages and
    // check the validity of the touch order.
    //
    // Helper method is written in jasm as this allows to
    // specify local vars stack region size directly.
    public static void main(String args[]) {
        Asserts.assertEQ(TestLargeLocalVarsStackRegionHelper.tst(), 0);
    }
}
