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


import jdk.test.lib.Utils;














/*
 * @test
 * @bug 8228888
 * @summary Test PhaseIdealLoop::has_local_phi_input() with phi input with non-dominating control.
 * @library /test/lib
 * @compile StrangeControl.jasm
 * @run main/othervm -Xbatch -XX:CompileCommand=inline,compiler.loopopts.StrangeControl::test
 *                   compiler.loopopts.TestStrangeControl
 */



public class TestStrangeControl {

    public static void main(String[] args) throws Exception {
        Thread thread = new Thread() {
            public void run() {
                // Run this in an own thread because it's basically an endless loop
                StrangeControl.test(42);
            }
        };
        thread.setDaemon(true);
        thread.start();
        // Give thread executing strange control loop enough time to trigger OSR compilation
        Thread.sleep(Utils.adjustTimeout(4000));
    }
}
