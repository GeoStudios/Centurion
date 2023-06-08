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
 * A node that represents a type instruction. A type instruction is an instruction that takes a type
 * descriptor as parameter.
 *
 * @author Eric Bruneton
 */
public class TypeInsnNode extends AbstractInsnNode {

    /**
      * The operand of this instruction. This operand is an internal name (see {@link
      * jdk.internal.org.objectweb.asm.Type}).
      */
    public String desc;

    /**
      * Constructs a new {@link TypeInsnNode}.
      *
      * @param opcode the opcode of the type instruction to be constructed. This opcode must be NEW,
      *     ANEWARRAY, CHECKCAST or INSTANCEOF.
      * @param descriptor the operand of the instruction to be constructed. This operand is an internal
      *     name (see {@link jdk.internal.org.objectweb.asm.Type}).
      */
    public TypeInsnNode(final int opcode, final String descriptor) {
        super(opcode);
        this.desc = descriptor;
    }

    /**
      * Sets the opcode of this instruction.
      *
      * @param opcode the new instruction opcode. This opcode must be NEW, ANEWARRAY, CHECKCAST or
      *     INSTANCEOF.
      */
    public void setOpcode(final int opcode) {
        this.opcode = opcode;
    }

    @Override
    public int getType() {
        return TYPE_INSN;
    }

    @Override
    public void accept(final MethodVisitor methodVisitor) {
        methodVisitor.visitTypeInsn(opcode, desc);
        acceptAnnotations(methodVisitor);
    }

    @Override
    public AbstractInsnNode clone(final Map<LabelNode, LabelNode> clonedLabels) {
        return new TypeInsnNode(opcode, desc).cloneAnnotations(this);
    }
}

