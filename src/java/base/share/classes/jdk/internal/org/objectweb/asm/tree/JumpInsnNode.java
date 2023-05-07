/*
 * Geo Studios Protective License
 *
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 *
 * Whoever collects this software or tool may not distribute the copy that has been obtained.
 *
 * This software or tool may not be used to gain a commercial or monetary advantage.
 *
 * Copyright will be included in any software or tool using this license, no matter the size or type of software or tool.
 *
 * This software or tool is not under any patent, but the software or tool shall not be
 * sold or uploaded as some other product or without the original creators consent and
 * permission. If the following happens, consequences will occur due to following
 * instructions or not following the rules written in this document.
 */

package java.base.share.classes.jdk.internal.org.objectweb.asm.tree;

import java.util.Map;
import java.base.share.classes.jdk.internal.org.objectweb.asm.MethodVisitor;

/**
 * A node that represents a jump instruction. A jump instruction is an instruction that may jump to
 * another instruction.
 *
 * @author Eric Bruneton
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

