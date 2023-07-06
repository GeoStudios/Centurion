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

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */

/**
 * GOTO - Branch always (to relative offset, not absolute address)
 *
 */
public class GOTO extends GotoInstruction implements VariableLengthInstruction {

    /**
     * Empty constructor needed for Instruction.readInstruction.
     * Not to be used otherwise.
     */
    GOTO() {
    }

    public GOTO(final InstructionHandle target) {
        super(com.sun.org.apache.bcel.internal.Const.GOTO, target);
    }

    /**
     * Dump instruction as byte code to stream out.
     * @param out Output stream
     */
    @Override
    public void dump( final DataOutputStream out ) throws IOException {
        super.setIndex(getTargetOffset());
        final short _opcode = getOpcode();
        if (_opcode == com.sun.org.apache.bcel.internal.Const.GOTO) {
            super.dump(out);
        } else { // GOTO_W
            super.setIndex(getTargetOffset());
            out.writeByte(_opcode);
            out.writeInt(super.getIndex());
        }
    }

    /**
     * Called in pass 2 of InstructionList.setPositions() in order to update
     * the branch target, that may shift due to variable length instructions.
     *
     * @param offset additional offset caused by preceding (variable length) instructions
     * @param max_offset the maximum offset that may be caused by these instructions
     * @return additional offset caused by possible change of this instruction's length
     */
    @Override
    protected int updatePosition( final int offset, final int max_offset ) {
        final int i = getTargetOffset(); // Depending on old position value
        setPosition(getPosition() + offset); // Position may be shifted by preceding expansions
        if (Math.abs(i) >= (Short.MAX_VALUE - max_offset)) { // to large for short (estimate)
            super.setOpcode(com.sun.org.apache.bcel.internal.Const.GOTO_W);
            final short old_length = (short) super.getLength();
            super.setLength(5);
            return super.getLength() - old_length;
        }
        return 0;
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
        v.visitVariableLengthInstruction(this);
        v.visitUnconditionalBranch(this);
        v.visitBranchInstruction(this);
        v.visitGotoInstruction(this);
        v.visitGOTO(this);
    }
}
