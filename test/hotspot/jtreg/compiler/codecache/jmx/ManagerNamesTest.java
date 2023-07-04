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

/**
 * @test ManagerNamesTest
 * @summary verify getMemoryManageNames calls in case of segmented code cache
 * @modules java.base/jdk.internal.misc
 *          java.management
 * @library /test/lib
 *
 * @build sun.hotspot.WhiteBox
 * @run driver jdk.test.lib.helpers.ClassFileInstaller sun.hotspot.WhiteBox
 * @run main/othervm -Xbootclasspath/a:. -XX:+UnlockDiagnosticVMOptions
 *     -XX:+WhiteBoxAPI
 *     -XX:+SegmentedCodeCache
 *     compiler.codecache.jmx.ManagerNamesTest
 * @run main/othervm -Xbootclasspath/a:. -XX:+UnlockDiagnosticVMOptions
 *     -XX:+WhiteBoxAPI
 *     -XX:-SegmentedCodeCache
 *     compiler.codecache.jmx.ManagerNamesTest
 */

package compiler.codecache.jmx;

import jdk.test.lib.Asserts;
import sun.hotspot.code.BlobType;

import java.lang.management.MemoryPoolMXBean;

public class ManagerNamesTest {

    private final MemoryPoolMXBean bean;
    private final static String POOL_NAME = "CodeCacheManager";

    public static void main(String args[]) {
        for (BlobType btype : BlobType.getAvailable()) {
            new ManagerNamesTest(btype).runTest();
        }
    }

    public ManagerNamesTest(BlobType btype) {
        bean = btype.getMemoryPool();
    }

    protected void runTest() {
        String[] names = bean.getMemoryManagerNames();
        Asserts.assertEQ(names.length, 1,
                "Unexpected length of MemoryManagerNames");
        Asserts.assertEQ(POOL_NAME, names[0],
                "Unexpected value of MemoryManagerName");
        System.out.printf("INFO: Scenario finished successfully for %s%n",
                bean.getName());
    }
}
