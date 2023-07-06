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

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */

/**
 * Super class for JSR - Jump to subroutine
 *
 */
public abstract class JsrInstruction extends BranchInstruction implements UnconditionalBranch,
        TypedInstruction, StackProducer {

    JsrInstruction(final short opcode, final InstructionHandle target) {
        super(opcode, target);
    }

    /**
     * Empty constructor needed for Instruction.readInstruction.
     * Not to be used otherwise.
     */
    JsrInstruction() {
    }

    /** @return return address type
     */
    @Override
    public Type getType( final ConstantPoolGen cp ) {
        return new ReturnaddressType(physicalSuccessor());
    }

    /**
     * Returns an InstructionHandle to the physical successor
     * of this JsrInstruction. <B>For this method to work,
     * this JsrInstruction object must not be shared between
     * multiple InstructionHandle objects!</B>
     * Formally, there must not be InstructionHandle objects
     * i, j where i != j and i.getInstruction() == this ==
     * j.getInstruction().
     * @return an InstructionHandle to the "next" instruction that
     * will be executed when RETurned from a subroutine.
     */
    public InstructionHandle physicalSuccessor() {
        InstructionHandle ih = super.getTarget();
        // Rewind!
        while (ih.getPrev() != null) {
            ih = ih.getPrev();
        }
        // Find the handle for "this" JsrInstruction object.
        while (ih.getInstruction() != this) {
            ih = ih.getNext();
        }
        final InstructionHandle toThis = ih;
        while (ih != null) {
            ih = ih.getNext();
            if ((ih != null) && (ih.getInstruction() == this)) {
                throw new IllegalStateException("physicalSuccessor() called on a shared JsrInstruction.");
            }
        }
        // Return the physical successor
        return toThis.getNext();
    }
}
