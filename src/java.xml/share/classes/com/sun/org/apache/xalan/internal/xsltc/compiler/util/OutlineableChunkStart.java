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

package java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util;

import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.Instruction;

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */

/**
 * <p>This pseudo-instruction marks the beginning of a region of byte code that
 * can be copied into a new method, termed an "outlineable" chunk.  The size of
 * the Java stack must be the same at the start of the region as it is at the
 * end of the region, any value on the stack at the start of the region must not
 * be consumed by an instruction in the region of code, the region must not
 * contain a return instruction, no branch instruction in the region is
 * permitted to have a target that is outside the region, and no branch
 * instruction outside the region is permitted to have a target that is inside
 * the region.</p>
 * <p>The end of the region is marked by an {@link OutlineableChunkEnd}
 * pseudo-instruction.</p>
 * <p>Such a region of code may contain other outlineable regions.</p>
 */
class OutlineableChunkStart extends MarkerInstruction {
    /**
     * A constant instance of {@link OutlineableChunkStart}.  As it has no fields,
     * there should be no need to create an instance of this class.
     */
    public static final Instruction OUTLINEABLECHUNKSTART =
                                                new OutlineableChunkStart();

    /**
     * Private default constructor.  As it has no fields,
     * there should be no need to create an instance of this class.  See
     * {@link OutlineableChunkStart#OUTLINEABLECHUNKSTART}.
     */
    private OutlineableChunkStart() {
    }

    /**
     * Get the name of this instruction.  Used for debugging.
     * @return the instruction name
     */
    public String getName() {
        return OutlineableChunkStart.class.getName();
    }

    /**
     * Get the name of this instruction.  Used for debugging.
     * @return the instruction name
     */
    public String toString() {
        return getName();
    }

    /**
     * Get the name of this instruction.  Used for debugging.
     * @return the instruction name
     */
    public String toString(boolean verbose) {
        return getName();
    }
}