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

package java.xml.share.classes.com.sun.org.apache.bcel.internal.generic;


import java.util.StringTokenizer;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.Const;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.classfile.Constant;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.classfile.ConstantCP;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.classfile.ConstantPool;















/**
 * Super class for the INVOKExxx family of instructions.
 *
 * @LastModified: Jan 2020
 */
public abstract class InvokeInstruction extends FieldOrMethod implements ExceptionThrower,
        StackConsumer, StackProducer {

    /**
     * Empty constructor needed for Instruction.readInstruction.
     * Not to be used otherwise.
     */
    InvokeInstruction() {
    }


    /**
     * @param index to constant pool
     */
    protected InvokeInstruction(final short opcode, final int index) {
        super(opcode, index);
    }


    /**
     * @return mnemonic for instruction with symbolic references resolved
     */
    @Override
    public String toString( final ConstantPool cp ) {
        final Constant c = cp.getConstant(super.getIndex());
        final StringTokenizer tok = new StringTokenizer(cp.constantToString(c));

        final String opcodeName = Const.getOpcodeName(super.getOpcode());

        final StringBuilder sb = new StringBuilder(opcodeName);
        if (tok.hasMoreTokens()) {
            sb.append(" ");
            sb.append(tok.nextToken().replace('.', '/'));
            if (tok.hasMoreTokens()) {
                sb.append(tok.nextToken());
            }
        }

        return sb.toString();
    }


    /**
     * Also works for instructions whose stack effect depends on the
     * constant pool entry they reference.
     * @return Number of words consumed from stack by this instruction
     */
    @Override
    public int consumeStack( final ConstantPoolGen cpg ) {
        int sum;
        if ((super.getOpcode() == Const.INVOKESTATIC) || (super.getOpcode() == Const.INVOKEDYNAMIC)) {
            sum = 0;
        } else {
            sum = 1; // this reference
        }

        final String signature = getSignature(cpg);
        sum += Type.getArgumentTypesSize(signature);
        return sum;
    }


    /**
     * Also works for instructions whose stack effect depends on the
     * constant pool entry they reference.
     * @return Number of words produced onto stack by this instruction
     */
    @Override
    public int produceStack( final ConstantPoolGen cpg ) {
        final String signature = getSignature(cpg);
        return Type.getReturnTypeSize(signature);
    }

    /**
     * This overrides the deprecated version as we know here that the referenced class
     * may legally be an array.
     *
     * @deprecated in FieldOrMethod
     *
     * @return name of the referenced class/interface
     * @throws IllegalArgumentException if the referenced class is an array (this should not happen)
     */
    @Override
    @Deprecated
    public String getClassName( final ConstantPoolGen cpg ) {
        final ConstantPool cp = cpg.getConstantPool();
        final ConstantCP cmr = (ConstantCP) cp.getConstant(super.getIndex());
        final String className = cp.getConstantString(cmr.getClassIndex(), Const.CONSTANT_Class);
        return className.replace('/', '.');
    }

    /** @return return type of referenced method.
     */
    @Override
    public Type getType( final ConstantPoolGen cpg ) {
        return getReturnType(cpg);
    }


    /** @return name of referenced method.
     */
    public String getMethodName( final ConstantPoolGen cpg ) {
        return getName(cpg);
    }


    /** @return return type of referenced method.
     */
    public Type getReturnType( final ConstantPoolGen cpg ) {
        return Type.getReturnType(getSignature(cpg));
    }


    /** @return argument types of referenced method.
     */
    public Type[] getArgumentTypes( final ConstantPoolGen cpg ) {
        return Type.getArgumentTypes(getSignature(cpg));
    }

}
