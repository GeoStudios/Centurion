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


import java.xml.share.classes.com.sun.org.apache.bcel.internal.ExceptionConst;















/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */



/**
 * Super class for instructions dealing with array access such as IALOAD.
 *
 */
public abstract class ArrayInstruction extends Instruction implements ExceptionThrower,
        TypedInstruction {

    /**
     * Empty constructor needed for Instruction.readInstruction.
     * Not to be used otherwise.
     */
    ArrayInstruction() {
    }


    /**
     * @param opcode of instruction
     */
    protected ArrayInstruction(final short opcode) {
        super(opcode, (short) 1);
    }


    @Override
    public Class<?>[] getExceptions() {
        return ExceptionConst.createExceptions(ExceptionConst.EXCS.EXCS_ARRAY_EXCEPTION);
    }


    /** @return type associated with the instruction
     */
    @Override
    public Type getType( final ConstantPoolGen cp ) {
        final short _opcode = super.getOpcode();
        switch (_opcode) {
            case com.sun.org.apache.bcel.internal.Const.IALOAD:
            case com.sun.org.apache.bcel.internal.Const.IASTORE:
                return Type.INT;
            case com.sun.org.apache.bcel.internal.Const.CALOAD:
            case com.sun.org.apache.bcel.internal.Const.CASTORE:
                return Type.CHAR;
            case com.sun.org.apache.bcel.internal.Const.BALOAD:
            case com.sun.org.apache.bcel.internal.Const.BASTORE:
                return Type.BYTE;
            case com.sun.org.apache.bcel.internal.Const.SALOAD:
            case com.sun.org.apache.bcel.internal.Const.SASTORE:
                return Type.SHORT;
            case com.sun.org.apache.bcel.internal.Const.LALOAD:
            case com.sun.org.apache.bcel.internal.Const.LASTORE:
                return Type.LONG;
            case com.sun.org.apache.bcel.internal.Const.DALOAD:
            case com.sun.org.apache.bcel.internal.Const.DASTORE:
                return Type.DOUBLE;
            case com.sun.org.apache.bcel.internal.Const.FALOAD:
            case com.sun.org.apache.bcel.internal.Const.FASTORE:
                return Type.FLOAT;
            case com.sun.org.apache.bcel.internal.Const.AALOAD:
            case com.sun.org.apache.bcel.internal.Const.AASTORE:
                return Type.OBJECT;
            default:
                throw new ClassGenException("Unknown case in switch" + _opcode);
        }
    }
}
