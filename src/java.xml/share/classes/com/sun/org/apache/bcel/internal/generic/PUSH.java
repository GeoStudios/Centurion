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

package com.sun.org.apache.bcel.internal.generic;

import com.sun.org.apache.bcel.internal.Const;

/**
 * Wrapper class for push operations, which are implemented either as BIPUSH,
 * LDC or xCONST_n instructions.
 *
 * @LastModified: Jan 2020
 */
public final class PUSH implements CompoundInstruction, VariableLengthInstruction {

    private final Instruction instruction;


    /**
     * This constructor also applies for values of type short, char, byte
     *
     * @param cp Constant pool
     * @param value to be pushed
     */
    public PUSH(final ConstantPoolGen cp, final int value) {
        if ((value >= -1) && (value <= 5)) {
            instruction = InstructionConst.getInstruction(Const.ICONST_0 + value);
        } else if (Instruction.isValidByte(value)) {
            instruction = new BIPUSH((byte) value);
        } else if (Instruction.isValidShort(value)) {
            instruction = new SIPUSH((short) value);
        } else {
            instruction = new LDC(cp.addInteger(value));
        }
    }


    /**
     * @param cp Constant pool
     * @param value to be pushed
     */
    public PUSH(final ConstantPoolGen cp, final boolean value) {
        instruction = InstructionConst.getInstruction(Const.ICONST_0 + (value ? 1 : 0));
    }


    /**
     * @param cp Constant pool
     * @param value to be pushed
     */
    public PUSH(final ConstantPoolGen cp, final float value) {
        if (value == 0.0) {
            instruction = InstructionConst.FCONST_0;
        } else if (value == 1.0) {
            instruction = InstructionConst.FCONST_1;
        } else if (value == 2.0) {
            instruction = InstructionConst.FCONST_2;
        } else {
            instruction = new LDC(cp.addFloat(value));
        }
    }


    /**
     * @param cp Constant pool
     * @param value to be pushed
     */
    public PUSH(final ConstantPoolGen cp, final long value) {
        if (value == 0) {
            instruction = InstructionConst.LCONST_0;
        } else if (value == 1) {
            instruction = InstructionConst.LCONST_1;
        } else {
            instruction = new LDC2_W(cp.addLong(value));
        }
    }


    /**
     * @param cp Constant pool
     * @param value to be pushed
     */
    public PUSH(final ConstantPoolGen cp, final double value) {
        if (value == 0.0) {
            instruction = InstructionConst.DCONST_0;
        } else if (value == 1.0) {
            instruction = InstructionConst.DCONST_1;
        } else {
            instruction = new LDC2_W(cp.addDouble(value));
        }
    }


    /**
     * @param cp Constant pool
     * @param value to be pushed
     */
    public PUSH(final ConstantPoolGen cp, final String value) {
        if (value == null) {
            instruction = InstructionConst.ACONST_NULL;
        } else {
            instruction = new LDC(cp.addString(value));
        }
    }

    /**
     *
     * @param cp
     * @param value
     */
    public PUSH(final ConstantPoolGen cp, final ObjectType value) {
        if (value == null) {
            instruction = InstructionConst.ACONST_NULL;
        } else {
            instruction = new LDC(cp.addClass(value));
        }
    }

    /**
     * @param cp Constant pool
     * @param value to be pushed
     */
    public PUSH(final ConstantPoolGen cp, final Number value) {
        if ((value instanceof Integer) || (value instanceof Short) || (value instanceof Byte)) {
            instruction = new PUSH(cp, value.intValue()).instruction;
        } else if (value instanceof Double) {
            instruction = new PUSH(cp, value.doubleValue()).instruction;
        } else if (value instanceof Float) {
            instruction = new PUSH(cp, value.floatValue()).instruction;
        } else if (value instanceof Long) {
            instruction = new PUSH(cp, value.longValue()).instruction;
        } else {
            throw new ClassGenException("What's this: " + value);
        }
    }


    /**
     * creates a push object from a Character value. Warning: Make sure not to attempt to allow
     * autoboxing to create this value parameter, as an alternative constructor will be called
     *
     * @param cp Constant pool
     * @param value to be pushed
     */
    public PUSH(final ConstantPoolGen cp, final Character value) {
        this(cp, value.charValue());
    }


    /**
     * @param cp Constant pool
     * @param value to be pushed
     */
    public PUSH(final ConstantPoolGen cp, final Boolean value) {
        this(cp, value.booleanValue());
    }


    @Override
    public InstructionList getInstructionList() {
        return new InstructionList(instruction);
    }


    public Instruction getInstruction() {
        return instruction;
    }


    /**
     * @return mnemonic for instruction
     */
    @Override
    public String toString() {
        return instruction + " (PUSH)";
    }
}
