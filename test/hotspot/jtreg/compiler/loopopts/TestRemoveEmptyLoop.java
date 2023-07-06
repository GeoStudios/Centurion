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

package compiler.loopopts;

/**
 * @test
 * @bug 8231988
 * @summary Unexpected test result caused by C2 IdealLoopTree::do_remove_empty_loop
 *
 * @run main/othervm -XX:-TieredCompilation -XX:-BackgroundCompilation
 *      compiler.loopopts.TestRemoveEmptyLoop
 */

public class TestRemoveEmptyLoop {

    public void test() {
        int i = 34;
        for (; i > 0; i -= 11);
        if (i < 0) {
            // do nothing
        } else {
            throw new RuntimeException("Test failed.");
        }
    }

    public static void main(String[] args) {
        TestRemoveEmptyLoop _instance = new TestRemoveEmptyLoop();
        for (int i = 0; i < 50000; i++) {
            _instance.test();
        }
        System.out.println("Test passed.");
    }

}
