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
import java.xml.share.classes.com.sun.org.apache.bcel.internal.util.ByteSequence;

/**
 * Abstract super class for instructions dealing with local variables.
 *
 * @LastModified: May 2021
 */
public abstract class LocalVariableInstruction extends Instruction implements TypedInstruction,
        IndexedInstruction {

    private int n = -1; // index of referenced variable
    private short cTag = -1; // compact version, such as ILOAD_0
    private short canonTag = -1; // canonical tag such as ILOAD

    private boolean wide() {
        return n > Const.MAX_BYTE;
    }

    /**
     * Empty constructor needed for Instruction.readInstruction.
     * Not to be used otherwise.
     * tag and length are defined in readInstruction and initFromFile, respectively.
     */
    LocalVariableInstruction(final short canon_tag, final short c_tag) {
        super();
        this.canonTag = canon_tag;
        this.cTag = c_tag;
    }

    /**
     * Empty constructor needed for Instruction.readInstruction.
     * Also used by IINC()!
     */
    LocalVariableInstruction() {
    }

    /**
     * @param opcode Instruction opcode
     * @param cTag Instruction number for compact version, ALOAD_0, e.g.
     * @param n local variable index (unsigned short)
     */
    protected LocalVariableInstruction(final short opcode, final short cTag, final int n) {
        super(opcode, (short) 2);
        this.cTag = cTag;
        canonTag = opcode;
        setIndex(n);
    }

    /**
     * Dump instruction as byte code to stream out.
     * @param out Output stream
     */
    @Override
    public void dump( final DataOutputStream out ) throws IOException {
        if (wide()) {
            out.writeByte(Const.WIDE);
        }
        out.writeByte(super.getOpcode());
        if (super.getLength() > 1) { // Otherwise ILOAD_n, instruction, e.g.
            if (wide()) {
                out.writeShort(n);
            } else {
                out.writeByte(n);
            }
        }
    }

    /**
     * Long output format:
     *
     * &lt;name of opcode&gt; "["&lt;opcode number&gt;"]"
     * "("&lt;length of instruction&gt;")" "&lt;"&lt; local variable index&gt;"&gt;"
     *
     * @param verbose long/short format switch
     * @return mnemonic for instruction
     */
    @Override
    public String toString( final boolean verbose ) {
        final short _opcode = super.getOpcode();
        if (((_opcode >= Const.ILOAD_0) && (_opcode <= Const.ALOAD_3))
         || ((_opcode >= Const.ISTORE_0) && (_opcode <= Const.ASTORE_3))) {
            return super.toString(verbose);
        }
        return super.toString(verbose) + " " + n;
    }

    /**
     * Read needed data (e.g. index) from file.
     * <pre>
     * (ILOAD &lt;= tag &lt;= ALOAD_3) || (ISTORE &lt;= tag &lt;= ASTORE_3)
     * </pre>
     */
    @Override
    protected void initFromFile( final ByteSequence bytes, final boolean wide ) throws IOException {
        if (wide) {
            n = bytes.readUnsignedShort();
            super.setLength(4);
        } else {
            final short _opcode = super.getOpcode();
            if (((_opcode >= Const.ILOAD) && (_opcode <= Const.ALOAD))
             || ((_opcode >= Const.ISTORE) && (_opcode <= Const.ASTORE))) {
                n = bytes.readUnsignedByte();
                super.setLength(2);
            } else if (_opcode <= Const.ALOAD_3) { // compact load instruction such as ILOAD_2
                n = (_opcode - Const.ILOAD_0) % 4;
                super.setLength(1);
            } else { // Assert ISTORE_0 <= tag <= ASTORE_3
                n = (_opcode - Const.ISTORE_0) % 4;
                super.setLength(1);
            }
        }
    }

    /**
     * @return local variable index (n) referred by this instruction.
     */
    @Override
    public final int getIndex() {
        return n;
    }

    /**
     * Set the local variable index.
     * also updates opcode and length
     * TODO Why?
     * @see #setIndexOnly(int)
     */
    @Override
    public void setIndex( final int n ) { // TODO could be package-protected?
        if ((n < 0) || (n > Const.MAX_SHORT)) {
            throw new ClassGenException("Illegal value: " + n);
        }
        this.n = n;
        // Cannot be < 0 as this is checked above
        if (n <= 3) { // Use more compact instruction xLOAD_n
            super.setOpcode((short) (cTag + n));
            super.setLength(1);
        } else {
            super.setOpcode(canonTag);
            if (wide()) {
                super.setLength(4);
            } else {
                super.setLength(2);
            }
        }
    }

    /** @return canonical tag for instruction, e.g., ALOAD for ALOAD_0
     */
    public short getCanonicalTag() {
        return canonTag;
    }

    /**
     * Returns the type associated with the instruction -
     * in case of ALOAD or ASTORE Type.OBJECT is returned.
     * This is just a bit incorrect, because ALOAD and ASTORE
     * may work on every ReferenceType (including Type.NULL) and
     * ASTORE may even work on a ReturnaddressType .
     * @return type associated with the instruction
     */
    @Override
    public Type getType( final ConstantPoolGen cp ) {
        switch (canonTag) {
            case Const.ILOAD:
            case Const.ISTORE:
                return Type.INT;
            case Const.LLOAD:
            case Const.LSTORE:
                return Type.LONG;
            case Const.DLOAD:
            case Const.DSTORE:
                return Type.DOUBLE;
            case Const.FLOAD:
            case Const.FSTORE:
                return Type.FLOAT;
            case Const.ALOAD:
            case Const.ASTORE:
                return Type.OBJECT;
            default:
                throw new ClassGenException("Unknown case in switch" + canonTag);
        }
    }

    /**
     * Sets the index of the referenced variable (n) only
     * @see #setIndex(int)
     */
    final void setIndexOnly(final int n) {
        this.n = n;
    }
}
