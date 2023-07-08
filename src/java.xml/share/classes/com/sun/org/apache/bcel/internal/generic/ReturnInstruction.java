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
import java.xml.share.classes.com.sun.org.apache.bcel.internal.ExceptionConst;

/**
 * Super class for the xRETURN family of instructions.
 *
 * @LastModified: Jan 2020
 */
public abstract class ReturnInstruction extends Instruction implements ExceptionThrower,
        TypedInstruction, StackConsumer {

    /**
     * Empty constructor needed for Instruction.readInstruction.
     * Not to be used otherwise.
     */
    ReturnInstruction() {
    }

    /**
     * @param opcode of instruction
     */
    protected ReturnInstruction(final short opcode) {
        super(opcode, (short) 1);
    }

    public Type getType() {
        final short _opcode = super.getOpcode();
        switch (_opcode) {
            case Const.IRETURN:
                return Type.INT;
            case Const.LRETURN:
                return Type.LONG;
            case Const.FRETURN:
                return Type.FLOAT;
            case Const.DRETURN:
                return Type.DOUBLE;
            case Const.ARETURN:
                return Type.OBJECT;
            case Const.RETURN:
                return Type.VOID;
            default: // Never reached
                throw new ClassGenException("Unknown type " + _opcode);
        }
    }

    @Override
    public Class<?>[] getExceptions() {
        return new Class<?>[] {
            ExceptionConst.ILLEGAL_MONITOR_STATE
        };
    }

    /** @return type associated with the instruction
     */
    @Override
    public Type getType( final ConstantPoolGen cp ) {
        return getType();
    }
}
