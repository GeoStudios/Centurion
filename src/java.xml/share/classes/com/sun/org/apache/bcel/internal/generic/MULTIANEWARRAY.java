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
import java.xml.share.classes.com.sun.org.apache.bcel.internal.ExceptionConst;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.classfile.ConstantPool;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.util.ByteSequence;















/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */




/**
 * MULTIANEWARRAY - Create new mutidimensional array of references
 * <PRE>Stack: ..., count1, [count2, ...] -&gt; ..., arrayref</PRE>
 *
 */
public class MULTIANEWARRAY extends CPInstruction implements LoadClass, AllocationInstruction,
        ExceptionThrower {

    private short dimensions;


    /**
     * Empty constructor needed for Instruction.readInstruction.
     * Not to be used otherwise.
     */
    MULTIANEWARRAY() {
    }


    public MULTIANEWARRAY(final int index, final short dimensions) {
        super(com.sun.org.apache.bcel.internal.Const.MULTIANEWARRAY, index);
        if (dimensions < 1) {
            throw new ClassGenException("Invalid dimensions value: " + dimensions);
        }
        this.dimensions = dimensions;
        super.setLength(4);
    }


    /**
     * Dump instruction as byte code to stream out.
     * @param out Output stream
     */
    @Override
    public void dump( final DataOutputStream out ) throws IOException {
        out.writeByte(super.getOpcode());
        out.writeShort(super.getIndex());
        out.writeByte(dimensions);
    }


    /**
     * Read needed data (i.e., no. dimension) from file.
     */
    @Override
    protected void initFromFile( final ByteSequence bytes, final boolean wide ) throws IOException {
        super.initFromFile(bytes, wide);
        dimensions = bytes.readByte();
        super.setLength(4);
    }


    /**
     * @return number of dimensions to be created
     */
    public final short getDimensions() {
        return dimensions;
    }


    /**
     * @return mnemonic for instruction
     */
    @Override
    public String toString( final boolean verbose ) {
        return super.toString(verbose) + " " + super.getIndex() + " " + dimensions;
    }


    /**
     * @return mnemonic for instruction with symbolic references resolved
     */
    @Override
    public String toString( final ConstantPool cp ) {
        return super.toString(cp) + " " + dimensions;
    }


    /**
     * Also works for instructions whose stack effect depends on the
     * constant pool entry they reference.
     * @return Number of words consumed from stack by this instruction
     */
    @Override
    public int consumeStack( final ConstantPoolGen cpg ) {
        return dimensions;
    }


    @Override
    public Class<?>[] getExceptions() {
        return ExceptionConst.createExceptions(ExceptionConst.EXCS.EXCS_CLASS_AND_INTERFACE_RESOLUTION,
            ExceptionConst.ILLEGAL_ACCESS_ERROR,
            ExceptionConst.NEGATIVE_ARRAY_SIZE_EXCEPTION);
    }


    @Override
    public ObjectType getLoadClassType( final ConstantPoolGen cpg ) {
        Type t = getType(cpg);
        if (t instanceof ArrayType) {
            t = ((ArrayType) t).getBasicType();
        }
        return (t instanceof ObjectType) ? (ObjectType) t : null;
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
        v.visitLoadClass(this);
        v.visitAllocationInstruction(this);
        v.visitExceptionThrower(this);
        v.visitTypedInstruction(this);
        v.visitCPInstruction(this);
        v.visitMULTIANEWARRAY(this);
    }
}
