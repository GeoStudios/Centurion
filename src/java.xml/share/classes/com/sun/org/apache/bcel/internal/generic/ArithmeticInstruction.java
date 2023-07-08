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


import java.xml.share.classes.com.sun.org.apache.bcel.internal.Const;















/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */



/**
 * Super class for the family of arithmetic instructions.
 *
 */
public abstract class ArithmeticInstruction extends Instruction implements TypedInstruction,
        StackProducer, StackConsumer {

    /**
     * Empty constructor needed for Instruction.readInstruction.
     * Not to be used otherwise.
     */
    ArithmeticInstruction() {
    }


    /**
     * @param opcode of instruction
     */
    protected ArithmeticInstruction(final short opcode) {
        super(opcode, (short) 1);
    }


    /** @return type associated with the instruction
     */
    @Override
    public Type getType( final ConstantPoolGen cp ) {
        final short _opcode = super.getOpcode();
        switch (_opcode) {
            case Const.DADD:
            case Const.DDIV:
            case Const.DMUL:
            case Const.DNEG:
            case Const.DREM:
            case Const.DSUB:
                return Type.DOUBLE;
            case Const.FADD:
            case Const.FDIV:
            case Const.FMUL:
            case Const.FNEG:
            case Const.FREM:
            case Const.FSUB:
                return Type.FLOAT;
            case Const.IADD:
            case Const.IAND:
            case Const.IDIV:
            case Const.IMUL:
            case Const.INEG:
            case Const.IOR:
            case Const.IREM:
            case Const.ISHL:
            case Const.ISHR:
            case Const.ISUB:
            case Const.IUSHR:
            case Const.IXOR:
                return Type.INT;
            case Const.LADD:
            case Const.LAND:
            case Const.LDIV:
            case Const.LMUL:
            case Const.LNEG:
            case Const.LOR:
            case Const.LREM:
            case Const.LSHL:
            case Const.LSHR:
            case Const.LSUB:
            case Const.LUSHR:
            case Const.LXOR:
                return Type.LONG;
            default: // Never reached
                throw new ClassGenException("Unknown type " + _opcode);
        }
    }
}
