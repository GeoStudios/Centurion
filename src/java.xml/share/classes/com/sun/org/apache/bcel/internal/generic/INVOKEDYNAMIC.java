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


import java.io.DataOutputStream;
import java.io.java.io.java.io.java.io.IOException;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.Const;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.ExceptionConst;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.classfile.ConstantInvokeDynamic;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.classfile.ConstantNameAndType;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.classfile.ConstantPool;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.util.ByteSequence;















/**
 * Class for INVOKEDYNAMIC. Not an instance of InvokeInstruction, since that class
 * expects to be able to get the class of the method. Ignores the bootstrap
 * mechanism entirely.
 *
 * @see
 * <a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.invokedynamic">
 * The invokedynamic instruction in The Java Virtual Machine Specification</a>
 * @LastModified: May 2021
 */
public class INVOKEDYNAMIC extends InvokeInstruction {

    /**
     * Empty constructor needed for Instruction.readInstruction.
     * Not to be used otherwise.
     */
    INVOKEDYNAMIC() {
    }


    public INVOKEDYNAMIC(final int index) {
        super(Const.INVOKEDYNAMIC, index);
    }


    /**
     * Dump instruction as byte code to stream out.
     * @param out Output stream
     */
    @Override
    public void dump( final DataOutputStream out ) throws IOException {
        out.writeByte(super.getOpcode());
        out.writeShort(super.getIndex());
        out.writeByte(0);
        out.writeByte(0);
       }


    /**
     * Read needed data (i.e., index) from file.
     */
    @Override
    protected void initFromFile( final ByteSequence bytes, final boolean wide ) throws IOException {
        super.initFromFile(bytes, wide);
        super.setLength(5);
        bytes.readByte(); // Skip 0 byte
        bytes.readByte(); // Skip 0 byte
    }


    /**
     * @return mnemonic for instruction with symbolic references resolved
     */
    @Override
    public String toString( final ConstantPool cp ) {
        return super.toString(cp);
    }


    @Override
    public Class<?>[] getExceptions() {
        return ExceptionConst.createExceptions(ExceptionConst.EXCS.EXCS_INTERFACE_METHOD_RESOLUTION,
            ExceptionConst.UNSATISFIED_LINK_ERROR,
            ExceptionConst.ABSTRACT_METHOD_ERROR,
            ExceptionConst.ILLEGAL_ACCESS_ERROR,
            ExceptionConst.INCOMPATIBLE_CLASS_CHANGE_ERROR);
    }


    /**
     * Call corresponding visitor method(s). The order is:
     * Call visitor methods of implemented interfaces first, then
     * call methods according to the class hierarchy in descending order,
     * i.e., the most specific visitXXX() call comes last.
     *
     * @param v Visitor object
     */
    @Override
    public void accept( final Visitor v ) {
        v.visitExceptionThrower(this);
        v.visitTypedInstruction(this);
        v.visitStackConsumer(this);
        v.visitStackProducer(this);
        v.visitLoadClass(this);
        v.visitCPInstruction(this);
        v.visitFieldOrMethod(this);
        v.visitInvokeInstruction(this);
        v.visitINVOKEDYNAMIC(this);
    }

    /**
     * Override the parent method because our classname is held elsewhere.
     *
     * @param cpg the ConstantPool generator
     * @deprecated in FieldOrMethod
     *
     * @return name of the referenced class/interface
     */
    @Override
    @Deprecated
    public String getClassName( final ConstantPoolGen cpg ) {
        final ConstantPool cp = cpg.getConstantPool();
        final ConstantInvokeDynamic cid = (ConstantInvokeDynamic) cp.getConstant(super.getIndex(), Const.CONSTANT_InvokeDynamic);
        return ((ConstantNameAndType) cp.getConstant(cid.getNameAndTypeIndex())).getName(cp);
    }


    /**
     * Since InvokeDynamic doesn't refer to a reference type, just return java.lang.Object,
     * as that is the only type we can say for sure the reference will be.
     *
     * @param cpg
     *            the ConstantPoolGen used to create the instruction
     * @return an ObjectType for java.lang.Object
     */
    @Override
    public ReferenceType getReferenceType(final ConstantPoolGen cpg) {
        return new ObjectType(Object.class.getName());
    }
}
