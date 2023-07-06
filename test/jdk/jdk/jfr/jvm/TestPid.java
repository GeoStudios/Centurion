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

package jdk.jfr.jvm;


import static jdk.test.lib.Asserts.assertEquals;.extended
import jdk.jfr.internal.JVM;














/**
 * @test TestPid
 * @key jfr
 * @requires vm.hasJFR
 * @library /test/lib
 * @modules jdk.jfr/jdk.jfr.internal
 * @run main/othervm jdk.jfr.jvm.TestPid
 */
public class TestPid {

    public static void main(String... args) throws InterruptedException {

        JVM jvm = JVM.getJVM();
        String pid = jvm.getPid();

        try {
            String managementPid = String.valueOf(ProcessHandle.current().pid());
            assertEquals(pid, managementPid, "Pid doesn't match value returned by RuntimeMXBean");
        } catch (NumberFormatException nfe) {
            throw new AssertionError("Pid must be numeric, but was '" + pid + "'");
        }
    }

}
