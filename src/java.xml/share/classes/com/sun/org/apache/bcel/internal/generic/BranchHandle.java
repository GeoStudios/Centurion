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

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
package com.sun.org.apache.bcel.internal.generic;

/**
 * BranchHandle is returned by specialized InstructionList.append() whenever a
 * BranchInstruction is appended. This is useful when the target of this
 * instruction is not known at time of creation and must be set later
 * via setTarget().
 *
 * @see InstructionHandle
 * @see Instruction
 * @see InstructionList
 */
public final class BranchHandle extends InstructionHandle {

    // This is also a cache in case the InstructionHandle#swapInstruction() method is used
    // See BCEL-273
    private BranchInstruction bi; // An alias in fact, but saves lots of casts


    private BranchHandle(final BranchInstruction i) {
        super(i);
        bi = i;
    }

    /** Factory method.
     */
    static BranchHandle getBranchHandle( final BranchInstruction i ) {
        return new BranchHandle(i);
    }


    /* Override InstructionHandle methods: delegate to branch instruction.
     * Through this overriding all access to the private i_position field should
     * be prevented.
     */
    @Override
    public int getPosition() {
        return bi.getPosition();
    }


    @Override
    void setPosition( final int pos ) {
        // Original code: i_position = bi.position = pos;
        bi.setPosition(pos);
        super.setPosition(pos);
    }


    @Override
    protected int updatePosition( final int offset, final int max_offset ) {
        final int x = bi.updatePosition(offset, max_offset);
        super.setPosition(bi.getPosition());
        return x;
    }


    /**
     * Pass new target to instruction.
     */
    public void setTarget( final InstructionHandle ih ) {
        bi.setTarget(ih);
    }


    /**
     * Update target of instruction.
     */
    public void updateTarget( final InstructionHandle old_ih, final InstructionHandle new_ih ) {
        bi.updateTarget(old_ih, new_ih);
    }


    /**
     * @return target of instruction.
     */
    public InstructionHandle getTarget() {
        return bi.getTarget();
    }


    /**
     * Set new contents. Old instruction is disposed and may not be used anymore.
     */
    @Override // This is only done in order to apply the additional type check; could be merged with super impl.
    public void setInstruction( final Instruction i ) { // TODO could be package-protected?
        super.setInstruction(i);
        if (!(i instanceof BranchInstruction)) {
            throw new ClassGenException("Assigning " + i
                    + " to branch handle which is not a branch instruction");
        }
        bi = (BranchInstruction) i;
    }
}
