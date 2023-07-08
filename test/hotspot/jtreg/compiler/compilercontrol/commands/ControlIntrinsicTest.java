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

package compiler.compilercontrol.commands;


import compiler.compilercontrol.share.IntrinsicCommand;
import compiler.compilercontrol.share.IntrinsicCommand.IntrinsicId;
import compiler.compilercontrol.share.scenario.Scenario;














/*
 * @test
 * @bug 8247732
 * @summary Tests CompileCommand=ControlIntrinsic,*.*,+_id
 * @modules java.base/jdk.internal.misc
 * @library /test/lib /
 *
 * @build sun.hotspot.WhiteBox
 * @run driver jdk.test.lib.helpers.ClassFileInstaller sun.hotspot.WhiteBox
 * @run driver compiler.compilercontrol.commands.ControlIntrinsicTest
 */



public class ControlIntrinsicTest {
    public static void main(String[] args) {
        IntrinsicId ids[] = new IntrinsicId[3];

        ids[0] = new IntrinsicId("_newArray", true);
        ids[1] = new IntrinsicId("_minF", false);
        ids[2] = new IntrinsicId("_copyOf", true);
        new IntrinsicCommand(Scenario.Type.OPTION, ids).test();

        // even though intrinsic ids are invalid, hotspot returns 0
        ids[0] = new IntrinsicId("brokenIntrinsic", true);
        ids[1] = new IntrinsicId("invalidIntrinsic", false);
        new IntrinsicCommand(Scenario.Type.OPTION, ids).test();
    }
}
