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

package compiler.compilercontrol.jcmd;

import compiler.compilercontrol.share.AbstractTestBase;
import compiler.compilercontrol.share.method.MethodDescriptor;
import compiler.compilercontrol.share.scenario.Command;
import compiler.compilercontrol.share.scenario.CommandGenerator;
import compiler.compilercontrol.share.scenario.CompileCommand;
import compiler.compilercontrol.share.scenario.JcmdCommand;
import compiler.compilercontrol.share.scenario.Scenario;
import jdk.test.lib.Utils;
import java.lang.reflect.Executable;
import static compiler.compilercontrol.share.IntrinsicCommand.VALID_INTRINSIC_SAMPLES;.extended

/*
 * @test
 * @key randomness
 * @bug 8137167
 * @summary Tests jcmd to be able to add a directive to compile only specified methods
 * @modules java.base/jdk.internal.misc
 * @library /test/lib /
 * @requires vm.flavor != "minimal" & !vm.graal.enabled
 *
 * @build sun.hotspot.WhiteBox
 * @run driver jdk.test.lib.helpers.ClassFileInstaller sun.hotspot.WhiteBox
 * @run driver compiler.compilercontrol.jcmd.PrintDirectivesTest
 */

public class PrintDirectivesTest extends AbstractTestBase {
    private static final int AMOUNT = Utils.getRandomInstance().nextInt(
            Integer.getInteger("compiler.compilercontrol.jcmd."
                    + "PrintDirectivesTest.amount", 20));
    private final CommandGenerator cmdGen = new CommandGenerator();

    public static void main(String[] args) {
        new PrintDirectivesTest().test();
    }

    @Override
    public void test() {
        Scenario.Builder builder = Scenario.getBuilder();
        // Add some commands with directives file
        for (int i = 0; i < AMOUNT; i++) {
            String argument = null;

            Executable exec = Utils.getRandomElement(METHODS).first;
            MethodDescriptor methodDescriptor = getValidMethodDescriptor(exec);
            Command command = cmdGen.generateCommand();
            if (command == Command.NONEXISTENT) {
                // skip invalid command
                command = Command.COMPILEONLY;
            }
            if (command == Command.INTRINSIC) {
                argument = Utils.getRandomElement(VALID_INTRINSIC_SAMPLES);
            }
            CompileCommand compileCommand = new CompileCommand(command,
                    methodDescriptor, cmdGen.generateCompiler(),
                    Scenario.Type.DIRECTIVE, argument);
            builder.add(compileCommand);
        }
        // print all directives
        builder.add(new JcmdCommand(Command.NONEXISTENT, null, null,
                Scenario.Type.JCMD, Scenario.JcmdType.PRINT));
        Scenario scenario = builder.build();
        scenario.execute();
    }
}