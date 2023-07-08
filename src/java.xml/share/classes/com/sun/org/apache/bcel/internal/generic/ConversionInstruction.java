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
 * Super class for the x2y family of instructions.
 *
 */
public abstract class ConversionInstruction extends Instruction implements TypedInstruction,
        StackProducer, StackConsumer {

    /**
     * Empty constructor needed for Instruction.readInstruction.
     * Not to be used otherwise.
     */
    ConversionInstruction() {
    }

    /**
     * @param opcode opcode of instruction
     */
    protected ConversionInstruction(final short opcode) {
        super(opcode, (short) 1);
    }

    /** @return type associated with the instruction
     */
    @Override
    public Type getType( final ConstantPoolGen cp ) {
        final short _opcode = super.getOpcode();
        switch (_opcode) {
            case Const.D2I:
            case Const.F2I:
            case Const.L2I:
                return Type.INT;
            case Const.D2F:
            case Const.I2F:
            case Const.L2F:
                return Type.FLOAT;
            case Const.D2L:
            case Const.F2L:
            case Const.I2L:
                return Type.LONG;
            case Const.F2D:
            case Const.I2D:
            case Const.L2D:
                return Type.DOUBLE;
            case Const.I2B:
                return Type.BYTE;
            case Const.I2C:
                return Type.CHAR;
            case Const.I2S:
                return Type.SHORT;
            default: // Never reached
                throw new ClassGenException("Unknown type " + _opcode);
        }
    }
}
