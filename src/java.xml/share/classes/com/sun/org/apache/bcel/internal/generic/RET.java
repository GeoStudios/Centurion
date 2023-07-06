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
import java.xml.share.classes.com.sun.org.apache.bcel.internal.util.ByteSequence;















/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */




/**
 * RET - Return from subroutine
 *
 * <PRE>Stack: ... -&gt; ...</PRE>
 *
 */
public class RET extends Instruction implements IndexedInstruction, TypedInstruction {

    private boolean wide;
    private int index; // index to local variable containg the return address


    /**
     * Empty constructor needed for Instruction.readInstruction.
     * Not to be used otherwise.
     */
    RET() {
    }


    public RET(final int index) {
        super(com.sun.org.apache.bcel.internal.Const.RET, (short) 2);
        setIndex(index); // May set wide as side effect
    }


    /**
     * Dump instruction as byte code to stream out.
     * @param out Output stream
     */
    @Override
    public void dump( final DataOutputStream out ) throws IOException {
        if (wide) {
            out.writeByte(com.sun.org.apache.bcel.internal.Const.WIDE);
        }
        out.writeByte(super.getOpcode());
        if (wide) {
            out.writeShort(index);
        } else {
            out.writeByte(index);
        }
    }


    private void setWide() {
        wide = index > com.sun.org.apache.bcel.internal.Const.MAX_BYTE;
        if (wide) {
            super.setLength(4); // Including the wide byte
        } else {
            super.setLength(2);
        }
    }


    /**
     * Read needed data (e.g. index) from file.
     */
    @Override
    protected void initFromFile( final ByteSequence bytes, final boolean wide ) throws IOException {
        this.wide = wide;
        if (wide) {
            index = bytes.readUnsignedShort();
            super.setLength(4);
        } else {
            index = bytes.readUnsignedByte();
            super.setLength(2);
        }
    }


    /**
     * @return index of local variable containg the return address
     */
    @Override
    public final int getIndex() {
        return index;
    }


    /**
     * Set index of local variable containg the return address
     */
    @Override
    public final void setIndex( final int n ) {
        if (n < 0) {
            throw new ClassGenException("Negative index value: " + n);
        }
        index = n;
        setWide();
    }


    /**
     * @return mnemonic for instruction
     */
    @Override
    public String toString( final boolean verbose ) {
        return super.toString(verbose) + " " + index;
    }


    /** @return return address type
     */
    @Override
    public Type getType( final ConstantPoolGen cp ) {
        return ReturnaddressType.NO_TARGET;
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
        v.visitRET(this);
    }
}
