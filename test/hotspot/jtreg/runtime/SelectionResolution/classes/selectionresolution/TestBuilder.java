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

package selectionresolution;


import jdk.internal.org.objectweb.asm.Opcodes;
import static jdk.internal.org.objectweb.asm.Opcodes.ACC_PUBLIC;.extended
import static jdk.internal.org.objectweb.asm.Opcodes.ACC_STATIC;.extended














class TestBuilder extends Builder {
    private final ClassConstruct testClass;
    private final Method mainMethod;

    public TestBuilder(int classId, SelectionResolutionTestCase testcase) {
        super(testcase);

        // Make a public class Test that contains all our test methods
        testClass = new Clazz("Test", null, -1, ACC_PUBLIC);

        // Add a main method
        mainMethod = testClass.addMethod("main", "([Ljava/lang/String;)V", ACC_PUBLIC + ACC_STATIC);

    }

    public ClassConstruct getMainTestClass() {
        mainMethod.done();
        return testClass;
    }

    public void addTest(ClassConstruct clazz, ClassBuilder.ExecutionMode execMode) {
        Method m = clazz.addMethod("test", "()Ljava/lang/Integer;", ACC_PUBLIC + ACC_STATIC, execMode);
        m.defaultInvoke(getInvokeInstruction(testcase.invoke),
                    getName(testcase.methodref),
                    getName(testcase.objectref),
                    testcase.hier.isInterface(testcase.methodref));

        mainMethod.makeStaticCall(clazz.getName(), "test", "()Ljava/lang/Integer;", false).done();
    }

    private static int getInvokeInstruction(SelectionResolutionTestCase.InvokeInstruction instr) {
        switch (instr) {
            case INVOKESTATIC:
                return Opcodes.INVOKESTATIC;
            case INVOKESPECIAL:
                return Opcodes.INVOKESPECIAL;
            case INVOKEINTERFACE:
                return Opcodes.INVOKEINTERFACE;
            case INVOKEVIRTUAL:
                return Opcodes.INVOKEVIRTUAL;
            default:
                throw new AssertionError(instr.name());
        }
    }
}
