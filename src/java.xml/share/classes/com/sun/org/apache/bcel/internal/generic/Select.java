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

import java.io.DataOutputStream;
import java.io.IOException;

import com.sun.org.apache.bcel.internal.util.ByteSequence;

/**
 * Select - Abstract super class for LOOKUPSWITCH and TABLESWITCH instructions.
 *
 * <p>We use our super's {@code target} property as the default target.
 *
 * @see LOOKUPSWITCH
 * @see TABLESWITCH
 * @see InstructionList
 * @LastModified: May 2021
 */
public abstract class Select extends BranchInstruction implements VariableLengthInstruction,

    private int[] match; // matches, i.e., case 1: ... TODO could be package-protected?
    private int[] indices; // target offsets TODO could be package-protected?
    private InstructionHandle[] targets; // target objects in instruction list TODO could be package-protected?
    private int fixed_length; // fixed length defined by subclasses TODO could be package-protected?
    private int match_length; // number of cases TODO could be package-protected?
    private int padding = 0; // number of pad bytes for alignment TODO could be package-protected?

    /**
     * Empty constructor needed for Instruction.readInstruction.
     * Not to be used otherwise.
     */
    Select() {
    }


    /**
     * (Match, target) pairs for switch.
     * `Match' and `targets' must have the same length of course.
     *
     * @param match array of matching values
     * @param targets instruction targets
     * @param defaultTarget default instruction target
     */
    Select(final short opcode, final int[] match, final InstructionHandle[] targets, final InstructionHandle defaultTarget) {
        // don't set default target before instuction is built
        super(opcode, null);
        this.match = match;
        this.targets = targets;
        // now it's safe to set default target
        setTarget(defaultTarget);
        for (final InstructionHandle target2 : targets) {
            notifyTarget(null, target2, this);
        }
        if ((match_length = match.length) != targets.length) {
            throw new ClassGenException("Match and target array have not the same length: Match length: " +
                match.length + " Target length: " + targets.length);
        }
        indices = new int[match_length];
    }


    /**
     * Since this is a variable length instruction, it may shift the following
     * instructions which then need to update their position.
     *
     * Called by InstructionList.setPositions when setting the position for every
     * instruction. In the presence of variable length instructions `setPositions'
     * performs multiple passes over the instruction list to calculate the
     * correct (byte) positions and offsets by calling this function.
     *
     * @param offset additional offset caused by preceding (variable length) instructions
     * @param max_offset the maximum offset that may be caused by these instructions
     * @return additional offset caused by possible change of this instruction's length
     */
    @Override
    protected int updatePosition( final int offset, final int max_offset ) {
        setPosition(getPosition() + offset); // Additional offset caused by preceding SWITCHs, GOTOs, etc.
        final short old_length = (short) super.getLength();
        /* Alignment on 4-byte-boundary, + 1, because of tag byte.
         */
        padding = (4 - ((getPosition() + 1) % 4)) % 4;
        super.setLength((short) (fixed_length + padding)); // Update length
        return super.getLength() - old_length;
    }


    /**
     * Dump instruction as byte code to stream out.
     * @param out Output stream
     */
    @Override
    public void dump( final DataOutputStream out ) throws IOException {
        out.writeByte(super.getOpcode());
        for (int i = 0; i < padding; i++) {
            out.writeByte(0);
        }
        super.setIndex(getTargetOffset()); // Write default target offset
        out.writeInt(super.getIndex());
    }


    /**
     * Read needed data (e.g. index) from file.
     */
    @Override
    protected void initFromFile( final ByteSequence bytes, final boolean wide ) throws IOException {
        padding = (4 - (bytes.getIndex() % 4)) % 4; // Compute number of pad bytes
        for (int i = 0; i < padding; i++) {
            bytes.readByte();
        }
        // Default branch target common for both cases (TABLESWITCH, LOOKUPSWITCH)
        super.setIndex(bytes.readInt());
    }


    /**
     * @return mnemonic for instruction
     */
    @Override
    public String toString( final boolean verbose ) {
        final StringBuilder buf = new StringBuilder(super.toString(verbose));
        if (verbose) {
            for (int i = 0; i < match_length; i++) {
                String s = "null";
                if (targets[i] != null) {
                    s = targets[i].getInstruction().toString();
                }
                buf.append("(").append(match[i]).append(", ").append(s).append(" = {").append(
                        indices[i]).append("})");
            }
        } else {
            buf.append(" ...");
        }
        return buf.toString();
    }


    /**
     * Set branch target for `i'th case
     */
    public void setTarget( final int i, final InstructionHandle target ) { // TODO could be package-protected?
        notifyTarget(targets[i], target, this);
        targets[i] = target;
    }


    /**
     * @param old_ih old target
     * @param new_ih new target
     */
    @Override
    public void updateTarget( final InstructionHandle old_ih, final InstructionHandle new_ih ) {
        boolean targeted = false;
        if (super.getTarget() == old_ih) {
            targeted = true;
            setTarget(new_ih);
        }
        for (int i = 0; i < targets.length; i++) {
            if (targets[i] == old_ih) {
                targeted = true;
                setTarget(i, new_ih);
            }
        }
        if (!targeted) {
            throw new ClassGenException("Not targeting " + old_ih);
        }
    }


    /**
     * @return true, if ih is target of this instruction
     */
    @Override
    public boolean containsTarget( final InstructionHandle ih ) {
        if (super.getTarget() == ih) {
            return true;
        }
        for (final InstructionHandle target2 : targets) {
            if (target2 == ih) {
                return true;
            }
        }
        return false;
    }


    @Override
    protected Object clone() throws CloneNotSupportedException {
        final Select copy = (Select) super.clone();
        copy.match = match.clone();
        copy.indices = indices.clone();
        copy.targets = targets.clone();
        return copy;
    }


    /**
     * Inform targets that they're not targeted anymore.
     */
    @Override
    void dispose() {
        super.dispose();
        for (final InstructionHandle target2 : targets) {
            target2.removeTargeter(this);
        }
    }


    /**
     * @return array of match indices
     */
    public int[] getMatchs() {
        return match;
    }


    /**
     * @return array of match target offsets
     */
    public int[] getIndices() {
        return indices;
    }


    /**
     * @return array of match targets
     */
    public InstructionHandle[] getTargets() {
        return targets;
    }

    /**
     * @return match entry
     */
    final int getMatch(final int index) {
        return match[index];
    }


    /**
     * @return index entry from indices
     */
    final int getIndices(final int index) {
        return indices[index];
    }

    /**
     * @return target entry
     */
    final InstructionHandle getTarget(final int index) {
        return targets[index];
    }


    /**
     * @return the fixed_length
     */
    final int getFixed_length() {
        return fixed_length;
    }


    /**
     * @param fixed_length the fixed_length to set
     */
    final void setFixed_length(final int fixed_length) {
        this.fixed_length = fixed_length;
    }


    /**
     * @return the match_length
     */
    final int getMatch_length() {
        return match_length;
    }


    /**
     * @param match_length the match_length to set
     */
    final int setMatch_length(final int match_length) {
        this.match_length = match_length;
        return match_length;
    }

    /**
     *
     * @param index
     * @param value
     */
    final void setMatch(final int index, final int value) {
        match[index] = value;
    }

    /**
     *
     * @param array
     */
    final void setIndices(final int[] array) {
        indices = array;
    }

    /**
     *
     * @param array
     */
    final void setMatches(final int[] array) {
        match = array;
    }

    /**
     *
     * @param array
     */
    final void setTargets(final InstructionHandle[] array) {
        targets = array;
    }

    /**
     *
     * @return the padding
     */
    final int getPadding() {
        return padding;
    }


    final int setIndices(final int i, final int value) {
        indices[i] = value;
        return value;  // Allow use in nested calls
    }
}
