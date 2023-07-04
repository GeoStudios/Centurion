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
 * A node that represents a field instruction. A field instruction is an instruction that loads or
 * stores the value of a field of an object.
 *
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
