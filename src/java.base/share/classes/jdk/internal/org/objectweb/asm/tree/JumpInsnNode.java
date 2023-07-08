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

package java.base.share.classes.jdk.internal.org.objectweb.asm.tree;


import java.util.Map;
import java.base.share.classes.jdk.internal.org.objectweb.asm.MethodVisitor;















/**
 * A node that represents a jump instruction. A jump instruction is an instruction that may jump to
 * another instruction.
 *
 */
public class JumpInsnNode extends AbstractInsnNode {

    /**
      * The operand of this instruction. This operand is a label that designates the instruction to
      * which this instruction may jump.
      */
    public LabelNode label;

    /**
      * Constructs a new {@link JumpInsnNode}.
      *
      * @param opcode the opcode of the type instruction to be constructed. This opcode must be IFEQ,
      *     IFNE, IFLT, IFGE, IFGT, IFLE, IF_ICMPEQ, IF_ICMPNE, IF_ICMPLT, IF_ICMPGE, IF_ICMPGT,
      *     IF_ICMPLE, IF_ACMPEQ, IF_ACMPNE, GOTO, JSR, IFNULL or IFNONNULL.
      * @param label the operand of the instruction to be constructed. This operand is a label that
      *     designates the instruction to which the jump instruction may jump.
      */
    public JumpInsnNode(final int opcode, final LabelNode label) {
        super(opcode);
        this.label = label;
    }

    /**
      * Sets the opcode of this instruction.
      *
      * @param opcode the new instruction opcode. This opcode must be IFEQ, IFNE, IFLT, IFGE, IFGT,
      *     IFLE, IF_ICMPEQ, IF_ICMPNE, IF_ICMPLT, IF_ICMPGE, IF_ICMPGT, IF_ICMPLE, IF_ACMPEQ,
      *     IF_ACMPNE, GOTO, JSR, IFNULL or IFNONNULL.
      */
    public void setOpcode(final int opcode) {
        this.opcode = opcode;
    }

    @Override
    public int getType() {
        return JUMP_INSN;
    }

    @Override
    public void accept(final MethodVisitor methodVisitor) {
        methodVisitor.visitJumpInsn(opcode, label.getLabel());
        acceptAnnotations(methodVisitor);
    }

    @Override
    public AbstractInsnNode clone(final Map<LabelNode, LabelNode> clonedLabels) {
        return new JumpInsnNode(opcode, clone(label, clonedLabels)).cloneAnnotations(this);
    }
}
