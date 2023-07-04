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
 * @test
 * @bug 8203329
 * @summary Verifies the JVMTI GetSystemProperty API returns the updated java.vm.info value
 * @requires vm.jvmti
 * @library /test/lib
 * @run main/othervm/native -agentlib:JvmtiGetSystemPropertyTest JvmtiGetSystemPropertyTest
 *
 */

public class JvmtiGetSystemPropertyTest {
    private static native String getSystemProperty();

    public static void main(String[] args) throws Exception {
        String vm_info = System.getProperty("java.vm.info");
        String vm_info_jvmti = getSystemProperty();
        System.out.println("java.vm.info from java:  " + vm_info);
        System.out.println("java.vm.info from jvmti: " + vm_info_jvmti);
        if (!vm_info.equals(vm_info_jvmti)) {
            throw new RuntimeException("java.vm.info poperties not equal: \"" +
                                       vm_info + "\" != \"" + vm_info_jvmti + "\"");
        }
    }
}
