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
import jdk.internal.org.objectweb.asm.Label;
import jdk.internal.org.objectweb.asm.MethodVisitor;

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
