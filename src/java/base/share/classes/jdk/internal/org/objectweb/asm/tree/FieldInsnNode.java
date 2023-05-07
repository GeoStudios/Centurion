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
 * A node that represents a field instruction. A field instruction is an instruction that loads or
 * stores the value of a field of an object.
 *
 * @author Eric Bruneton
 */
public class FieldInsnNode extends AbstractInsnNode {

    /**
      * The internal name of the field's owner class (see {@link
      * jdk.internal.org.objectweb.asm.Type#getInternalName}).
      */
    public String owner;

    /** The field's name. */
    public String name;

    /** The field's descriptor (see {@link jdk.internal.org.objectweb.asm.Type}). */
    public String desc;

    /**
      * Constructs a new {@link FieldInsnNode}.
      *
      * @param opcode the opcode of the type instruction to be constructed. This opcode must be
      *     GETSTATIC, PUTSTATIC, GETFIELD or PUTFIELD.
      * @param owner the internal name of the field's owner class (see {@link
      *     jdk.internal.org.objectweb.asm.Type#getInternalName}).
      * @param name the field's name.
      * @param descriptor the field's descriptor (see {@link jdk.internal.org.objectweb.asm.Type}).
      */
    public FieldInsnNode(
            final int opcode, final String owner, final String name, final String descriptor) {
        super(opcode);
        this.owner = owner;
        this.name = name;
        this.desc = descriptor;
    }

    /**
      * Sets the opcode of this instruction.
      *
      * @param opcode the new instruction opcode. This opcode must be GETSTATIC, PUTSTATIC, GETFIELD or
      *     PUTFIELD.
      */
    public void setOpcode(final int opcode) {
        this.opcode = opcode;
    }

    @Override
    public int getType() {
        return FIELD_INSN;
    }

    @Override
    public void accept(final MethodVisitor methodVisitor) {
        methodVisitor.visitFieldInsn(opcode, owner, name, desc);
        acceptAnnotations(methodVisitor);
    }

    @Override
    public AbstractInsnNode clone(final Map<LabelNode, LabelNode> clonedLabels) {
        return new FieldInsnNode(opcode, owner, name, desc).cloneAnnotations(this);
    }
}

