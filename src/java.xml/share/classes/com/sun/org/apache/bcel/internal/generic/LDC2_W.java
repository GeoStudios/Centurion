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

/**
 * LDC2_W - Push long or double from constant pool
 *
 * <PRE>Stack: ... -&gt; ..., item.word1, item.word2</PRE>
 *
 * @LastModified: May 2021
 */
public class LDC2_W extends CPInstruction implements PushInstruction {

    /**
     * Empty constructor needed for Instruction.readInstruction.
     * Not to be used otherwise.
     */
    LDC2_W() {
    }

    public LDC2_W(final int index) {
        super(com.sun.org.apache.bcel.internal.Const.LDC2_W, index);
    }

    @Override
    public Type getType( final ConstantPoolGen cpg ) {
        switch (cpg.getConstantPool().getConstant(super.getIndex()).getTag()) {
            case com.sun.org.apache.bcel.internal.Const.CONSTANT_Long:
                return Type.LONG;
            case com.sun.org.apache.bcel.internal.Const.CONSTANT_Double:
                return Type.DOUBLE;
            default: // Never reached
                throw new IllegalArgumentException("Unknown constant type " + super.getOpcode());
        }
    }

    public Number getValue( final ConstantPoolGen cpg ) {
        final com.sun.org.apache.bcel.internal.classfile.Constant c = cpg.getConstantPool().getConstant(super.getIndex());
        switch (c.getTag()) {
            case com.sun.org.apache.bcel.internal.Const.CONSTANT_Long:
                return ((com.sun.org.apache.bcel.internal.classfile.ConstantLong) c).getBytes();
            case com.sun.org.apache.bcel.internal.Const.CONSTANT_Double:
                return ((com.sun.org.apache.bcel.internal.classfile.ConstantDouble) c).getBytes();
            default: // Never reached
                throw new IllegalArgumentException("Unknown or invalid constant type at " + super.getIndex());
        }
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
        v.visitStackProducer(this);
        v.visitPushInstruction(this);
        v.visitTypedInstruction(this);
        v.visitCPInstruction(this);
        v.visitLDC2_W(this);
    }
}
