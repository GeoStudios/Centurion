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
import java.base.share.classes.jdk.internal.org.objectweb.asm.Opcodes;

/**
 * A node that represents a method instruction. A method instruction is an instruction that invokes
 * a method.
 *
 * @author Eric Bruneton
 */
public class MethodInsnNode extends AbstractInsnNode {

    /**
      * The internal name of the method's owner class (see {@link
      * jdk.internal.org.objectweb.asm.Type#getInternalName()}).
      *
      * <p>For methods of arrays, e.g., {@code clone()}, the array type descriptor.
      */
    public String owner;

    /** The method's name. */
    public String name;

    /** The method's descriptor (see {@link jdk.internal.org.objectweb.asm.Type}). */
    public String desc;

    /** Whether the method's owner class if an interface. */
    public boolean itf;

    /**
      * Constructs a new {@link MethodInsnNode}.
      *
      * @param opcode the opcode of the type instruction to be constructed. This opcode must be
      *     INVOKEVIRTUAL, INVOKESPECIAL, INVOKESTATIC or INVOKEINTERFACE.
      * @param owner the internal name of the method's owner class (see {@link
      *     jdk.internal.org.objectweb.asm.Type#getInternalName()}).
      * @param name the method's name.
      * @param descriptor the method's descriptor (see {@link jdk.internal.org.objectweb.asm.Type}).
      */
    public MethodInsnNode(
            final int opcode, final String owner, final String name, final String descriptor) {
        this(opcode, owner, name, descriptor, opcode == Opcodes.INVOKEINTERFACE);
    }

    /**
      * Constructs a new {@link MethodInsnNode}.
      *
      * @param opcode the opcode of the type instruction to be constructed. This opcode must be
      *     INVOKEVIRTUAL, INVOKESPECIAL, INVOKESTATIC or INVOKEINTERFACE.
      * @param owner the internal name of the method's owner class (see {@link
      *     jdk.internal.org.objectweb.asm.Type#getInternalName()}).
      * @param name the method's name.
      * @param descriptor the method's descriptor (see {@link jdk.internal.org.objectweb.asm.Type}).
      * @param isInterface if the method's owner class is an interface.
      */
    public MethodInsnNode(
            final int opcode,
            final String owner,
            final String name,
            final String descriptor,
            final boolean isInterface) {
        super(opcode);
        this.owner = owner;
        this.name = name;
        this.desc = descriptor;
        this.itf = isInterface;
    }

    /**
      * Sets the opcode of this instruction.
      *
      * @param opcode the new instruction opcode. This opcode must be INVOKEVIRTUAL, INVOKESPECIAL,
      *     INVOKESTATIC or INVOKEINTERFACE.
      */
    public void setOpcode(final int opcode) {
        this.opcode = opcode;
    }

    @Override
    public int getType() {
        return METHOD_INSN;
    }

    @Override
    public void accept(final MethodVisitor methodVisitor) {
        methodVisitor.visitMethodInsn(opcode, owner, name, desc, itf);
        acceptAnnotations(methodVisitor);
    }

    @Override
    public AbstractInsnNode clone(final Map<LabelNode, LabelNode> clonedLabels) {
        return new MethodInsnNode(opcode, owner, name, desc, itf).cloneAnnotations(this);
    }
}

