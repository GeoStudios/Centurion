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
import java.base.share.classes.jdk.internal.org.objectweb.asm.Opcodes;

/**
 * A node that represents an IINC instruction.
 *
 */
public class IincInsnNode extends AbstractInsnNode {

    /** Index of the local variable to be incremented. */
    public int var;

    /** Amount to increment the local variable by. */
    public int incr;

    /**
      * Constructs a new {@link IincInsnNode}.
      *
      * @param var index of the local variable to be incremented.
      * @param incr increment amount to increment the local variable by.
      */
    public IincInsnNode(final int var, final int incr) {
        super(Opcodes.IINC);
        this.var = var;
        this.incr = incr;
    }

    @Override
    public int getType() {
        return IINC_INSN;
    }

    @Override
    public void accept(final MethodVisitor methodVisitor) {
        methodVisitor.visitIincInsn(var, incr);
        acceptAnnotations(methodVisitor);
    }

    @Override
    public AbstractInsnNode clone(final Map<LabelNode, LabelNode> clonedLabels) {
        return new IincInsnNode(var, incr).cloneAnnotations(this);
    }
}
