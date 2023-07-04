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
import jdk.internal.org.objectweb.asm.Opcodes;

/**
 * A node that represents a MULTIANEWARRAY instruction.
 *
 */
public class MultiANewArrayInsnNode extends AbstractInsnNode {

    /** An array type descriptor (see {@link jdk.internal.org.objectweb.asm.Type}). */
    public String desc;

    /** Number of dimensions of the array to allocate. */
    public int dims;

    /**
      * Constructs a new {@link MultiANewArrayInsnNode}.
      *
      * @param descriptor an array type descriptor (see {@link jdk.internal.org.objectweb.asm.Type}).
      * @param numDimensions the number of dimensions of the array to allocate.
      */
    public MultiANewArrayInsnNode(final String descriptor, final int numDimensions) {
        super(Opcodes.MULTIANEWARRAY);
        this.desc = descriptor;
        this.dims = numDimensions;
    }

    @Override
    public int getType() {
        return MULTIANEWARRAY_INSN;
    }

    @Override
    public void accept(final MethodVisitor methodVisitor) {
        methodVisitor.visitMultiANewArrayInsn(desc, dims);
        acceptAnnotations(methodVisitor);
    }

    @Override
    public AbstractInsnNode clone(final Map<LabelNode, LabelNode> clonedLabels) {
        return new MultiANewArrayInsnNode(desc, dims).cloneAnnotations(this);
    }
}
