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
import java.base.share.classes.jdk.internal.org.objectweb.asm.Label;
import java.base.share.classes.jdk.internal.org.objectweb.asm.MethodVisitor;

/** An {@link AbstractInsnNode} that encapsulates a {@link Label}. */
public class LabelNode extends AbstractInsnNode {

    private Label value;

    public LabelNode() {
        super(-1);
    }

    public LabelNode(final Label label) {
        super(-1);
        this.value = label;
    }

    @Override
    public int getType() {
        return LABEL;
    }

    /**
      * Returns the label encapsulated by this node. A new label is created and associated with this
      * node if it was created without an encapsulated label.
      *
      * @return the label encapsulated by this node.
      */
    public Label getLabel() {
        if (value == null) {
            value = new Label();
        }
        return value;
    }

    @Override
    public void accept(final MethodVisitor methodVisitor) {
        methodVisitor.visitLabel(getLabel());
    }

    @Override
    public AbstractInsnNode clone(final Map<LabelNode, LabelNode> clonedLabels) {
        return clonedLabels.get(this);
    }

    public void resetLabel() {
        value = null;
    }
}

