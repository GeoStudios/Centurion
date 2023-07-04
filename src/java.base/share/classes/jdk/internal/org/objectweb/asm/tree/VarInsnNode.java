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

package jdk.internal.org.objectweb.asm.tree;

import java.util.Map;
import jdk.internal.org.objectweb.asm.MethodVisitor;

/**
 * A node that represents a local variable instruction. A local variable instruction is an
 * instruction that loads or stores the value of a local variable.
 *
 */
public class VarInsnNode extends AbstractInsnNode {

    /** The operand of this instruction. This operand is the index of a local variable. */
    public int var;

    /**
      * Constructs a new {@link VarInsnNode}.
      *
      * @param opcode the opcode of the local variable instruction to be constructed. This opcode must
      *     be ILOAD, LLOAD, FLOAD, DLOAD, ALOAD, ISTORE, LSTORE, FSTORE, DSTORE, ASTORE or RET.
      * @param var the operand of the instruction to be constructed. This operand is the index of a
      *     local variable.
      */
    public VarInsnNode(final int opcode, final int var) {
        super(opcode);
        this.var = var;
    }

    /**
      * Sets the opcode of this instruction.
      *
      * @param opcode the new instruction opcode. This opcode must be ILOAD, LLOAD, FLOAD, DLOAD,
      *     ALOAD, ISTORE, LSTORE, FSTORE, DSTORE, ASTORE or RET.
      */
    public void setOpcode(final int opcode) {
        this.opcode = opcode;
    }

    @Override
    public int getType() {
        return VAR_INSN;
    }

    @Override
    public void accept(final MethodVisitor methodVisitor) {
        methodVisitor.visitVarInsn(opcode, var);
        acceptAnnotations(methodVisitor);
    }

    @Override
    public AbstractInsnNode clone(final Map<LabelNode, LabelNode> clonedLabels) {
        return new VarInsnNode(opcode, var).cloneAnnotations(this);
    }
}
