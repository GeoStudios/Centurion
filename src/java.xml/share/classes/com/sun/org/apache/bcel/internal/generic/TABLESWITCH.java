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
 * TABLESWITCH - Switch within given range of values, i.e., low..high
 *
 * @see SWITCH
 */
public class TABLESWITCH extends Select {

    /**
     * Empty constructor needed for Instruction.readInstruction.
     * Not to be used otherwise.
     */
    TABLESWITCH() {
    }


    /**
     * @param match sorted array of match values, match[0] must be low value,
     * match[match_length - 1] high value
     * @param targets where to branch for matched values
     * @param defaultTarget default branch
     */
    public TABLESWITCH(final int[] match, final InstructionHandle[] targets, final InstructionHandle defaultTarget) {
        super(com.sun.org.apache.bcel.internal.Const.TABLESWITCH, match, targets, defaultTarget);
        /* Alignment remainder assumed 0 here, until dump time */
        final short _length = (short) (13 + getMatch_length() * 4);
        super.setLength(_length);
        setFixed_length(_length);
    }


    /**
     * Dump instruction as byte code to stream out.
     * @param out Output stream
     */
    @Override
    public void dump( final DataOutputStream out ) throws IOException {
        super.dump(out);
        final int _match_length = getMatch_length();
        final int low = (_match_length > 0) ? super.getMatch(0) : 0;
        out.writeInt(low);
        final int high = (_match_length > 0) ? super.getMatch(_match_length - 1) : 0;
        out.writeInt(high);
        for (int i = 0; i < _match_length; i++) {
            out.writeInt(setIndices(i, getTargetOffset(super.getTarget(i))));
        }
    }


    /**
     * Read needed data (e.g. index) from file.
     */
    @Override
    protected void initFromFile( final ByteSequence bytes, final boolean wide ) throws IOException {
        super.initFromFile(bytes, wide);
        final int low = bytes.readInt();
        final int high = bytes.readInt();
        final int _match_length = high - low + 1;
        setMatch_length(_match_length);
        final short _fixed_length = (short) (13 + _match_length * 4);
        setFixed_length(_fixed_length);
        super.setLength((short) (_fixed_length + super.getPadding()));
        super.setMatches(new int[_match_length]);
        super.setIndices(new int[_match_length]);
        super.setTargets(new InstructionHandle[_match_length]);
        for (int i = 0; i < _match_length; i++) {
            super.setMatch(i, low + i);
            super.setIndices(i, bytes.readInt());
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
        v.visitVariableLengthInstruction(this);
        v.visitStackConsumer(this);
        v.visitBranchInstruction(this);
        v.visitSelect(this);
        v.visitTABLESWITCH(this);
    }
}
