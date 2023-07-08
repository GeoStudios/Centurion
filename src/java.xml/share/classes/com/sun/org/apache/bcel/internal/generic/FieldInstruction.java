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


import java.xml.share.classes.com.sun.org.apache.bcel.internal.classfile.ConstantPool;















/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */



/**
 * Super class for the GET/PUTxxx family of instructions.
 *
 */
public abstract class FieldInstruction extends FieldOrMethod {

    /**
     * Empty constructor needed for Instruction.readInstruction.
     * Not to be used otherwise.
     */
    FieldInstruction() {
    }


    /**
     * @param index to constant pool
     */
    protected FieldInstruction(final short opcode, final int index) {
        super(opcode, index);
    }


    /**
     * @return mnemonic for instruction with symbolic references resolved
     */
    @Override
    public String toString( final ConstantPool cp ) {
        return com.sun.org.apache.bcel.internal.Const.getOpcodeName(super.getOpcode()) + " "
                + cp.constantToString(super.getIndex(), com.sun.org.apache.bcel.internal.Const.CONSTANT_Fieldref);
    }


    /** @return size of field (1 or 2)
     */
    protected int getFieldSize( final ConstantPoolGen cpg ) {
        return Type.size(Type.getTypeSize(getSignature(cpg)));
    }


    /** @return return type of referenced field
     */
    @Override
    public Type getType( final ConstantPoolGen cpg ) {
        return getFieldType(cpg);
    }


    /** @return type of field
     */
    public Type getFieldType( final ConstantPoolGen cpg ) {
        return Type.getType(getSignature(cpg));
    }


    /** @return name of referenced field.
     */
    public String getFieldName( final ConstantPoolGen cpg ) {
        return getName(cpg);
    }
}
