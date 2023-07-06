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
import java.xml.share.classes.com.sun.org.apache.bcel.internal.util.ByteSequence;















/**
 * NEWARRAY -  Create new array of basic type (int, short, ...)
 * <PRE>Stack: ..., count -&gt; ..., arrayref</PRE>
 * type must be one of T_INT, T_SHORT, ...
 *
 * @LastModified: Jan 2020
 */
public class NEWARRAY extends Instruction implements AllocationInstruction, ExceptionThrower,
        StackProducer {

    private byte type;


    /**
     * Empty constructor needed for Instruction.readInstruction.
     * Not to be used otherwise.
     */
    NEWARRAY() {
    }


    public NEWARRAY(final byte type) {
        super(com.sun.org.apache.bcel.internal.Const.NEWARRAY, (short) 2);
        this.type = type;
    }


    public NEWARRAY(final BasicType type) {
        this(type.getType());
    }


    /**
     * Dump instruction as byte code to stream out.
     * @param out Output stream
     */
    @Override
    public void dump( final DataOutputStream out ) throws IOException {
        out.writeByte(super.getOpcode());
        out.writeByte(type);
    }


    /**
     * @return numeric code for basic element type
     */
    public final byte getTypecode() {
        return type;
    }


    /**
     * @return type of constructed array
     */
    public final Type getType() {
        return new ArrayType(BasicType.getType(type), 1);
    }


    /**
     * @return mnemonic for instruction
     */
    @Override
    public String toString( final boolean verbose ) {
        return super.toString(verbose) + " " + com.sun.org.apache.bcel.internal.Const.getTypeName(type);
    }


    /**
     * Read needed data (e.g. index) from file.
     */
    @Override
    protected void initFromFile( final ByteSequence bytes, final boolean wide ) throws IOException {
        type = bytes.readByte();
        super.setLength(2);
    }


    @Override
    public Class<?>[] getExceptions() {
        return new Class<?>[] {
            ExceptionConst.NEGATIVE_ARRAY_SIZE_EXCEPTION
        };
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
        v.visitAllocationInstruction(this);
        v.visitExceptionThrower(this);
        v.visitStackProducer(this);
        v.visitNEWARRAY(this);
    }
}
