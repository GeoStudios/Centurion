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
 * Equality of instructions isn't clearly to be defined. You might
 * wish, for example, to compare whether instructions have the same
 * meaning. E.g., whether two INVOKEVIRTUALs describe the same
 * call.
 * <p>
 * The DEFAULT comparator however, considers two instructions
 * to be equal if they have same opcode and point to the same indexes
 * (if any) in the constant pool or the same local variable index. Branch
 * instructions must have the same target.
 * </p>
 *
 * @see Instruction
 */
public interface InstructionComparator {

    InstructionComparator DEFAULT = (i1, i2) -> {
        if (i1.getOpcode() == i2.getOpcode()) {
            if (i1 instanceof BranchInstruction) {
             // BIs are never equal to make targeters work correctly (BCEL-195)
                return false;
//                } else if (i1 == i2) { TODO consider adding this shortcut
//                    return true; // this must be AFTER the BI test
            } else if (i1 instanceof ConstantPushInstruction) {
                return ((ConstantPushInstruction) i1).getValue().equals(
                        ((ConstantPushInstruction) i2).getValue());
            } else if (i1 instanceof IndexedInstruction) {
                return ((IndexedInstruction) i1).getIndex() == ((IndexedInstruction) i2)
                        .getIndex();
            } else if (i1 instanceof NEWARRAY) {
                return ((NEWARRAY) i1).getTypecode() == ((NEWARRAY) i2).getTypecode();
            } else {
                return true;
            }
        }
        return false;
    };


    boolean equals( Instruction i1, Instruction i2 );
}
