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
 * A node that represents a line number declaration. These nodes are pseudo instruction nodes in
 * order to be inserted in an instruction list.
 *
 * @author Eric Bruneton
 */
public class LineNumberNode extends AbstractInsnNode {

    /** A line number. This number refers to the source file from which the class was compiled. */
    public int line;

    /** The first instruction corresponding to this line number. */
    public LabelNode start;

    /**
      * Constructs a new {@link LineNumberNode}.
      *
      * @param line a line number. This number refers to the source file from which the class was
      *     compiled.
      * @param start the first instruction corresponding to this line number.
      */
    public LineNumberNode(final int line, final LabelNode start) {
        super(-1);
        this.line = line;
        this.start = start;
    }

    @Override
    public int getType() {
        return LINE;
    }

    @Override
    public void accept(final MethodVisitor methodVisitor) {
        methodVisitor.visitLineNumber(line, start.getLabel());
    }

    @Override
    public AbstractInsnNode clone(final Map<LabelNode, LabelNode> clonedLabels) {
        return new LineNumberNode(line, clone(start, clonedLabels));
    }
}

