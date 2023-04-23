/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package jdk.internal.org.objectweb.asm.tree;

import java.util.Map;
import jdk.internal.org.objectweb.asm.MethodVisitor;
import jdk.internal.org.objectweb.asm.Opcodes;

/**
 * A node that represents a MULTIANEWARRAY instruction.
 *
 * @author Eric Bruneton
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

