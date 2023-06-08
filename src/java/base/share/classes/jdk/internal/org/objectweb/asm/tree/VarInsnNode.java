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
 * A node that represents a local variable instruction. A local variable instruction is an
 * instruction that loads or stores the value of a local variable.
 *
 * @author Eric Bruneton
 */
public class VarInsnNode extends AbstractInsnNode {

    /** The operand of this instruction. This operand is the index of a local variable. */
    public int var;

    /**
      * Constructs a new {@link VarInsnNode}.
      *
      * @param opcode the opcode of the local variable instruction to be constructed. This opcode must
      *     be ILOAD, LLOAD, FLOAD, DLOAD, ALOAD, ISTORE, LSTORE, FSTORE, DSTORE, ASTORE or RET.
      * @param varIndex the operand of the instruction to be constructed. This operand is the index of
      *     a local variable.
      */
    public VarInsnNode(final int opcode, final int varIndex) {
        super(opcode);
        this.var = varIndex;
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

