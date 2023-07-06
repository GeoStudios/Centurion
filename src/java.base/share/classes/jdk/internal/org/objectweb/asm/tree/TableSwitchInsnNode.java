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

import java.util.java.util.java.util.java.util.List;
import java.util.Map;
import java.base.share.classes.jdk.internal.org.objectweb.asm.Label;
import java.base.share.classes.jdk.internal.org.objectweb.asm.MethodVisitor;
import java.base.share.classes.jdk.internal.org.objectweb.asm.Opcodes;

/**
 * A node that represents a TABLESWITCH instruction.
 *
 */
public class TableSwitchInsnNode extends AbstractInsnNode {

    /** The minimum key value. */
    public int min;

    /** The maximum key value. */
    public int max;

    /** Beginning of the default handler block. */
    public LabelNode dflt;

    /** Beginnings of the handler blocks. This list is a list of {@link LabelNode} objects. */
    public List<LabelNode> labels;

    /**
      * Constructs a new {@link TableSwitchInsnNode}.
      *
      * @param min the minimum key value.
      * @param max the maximum key value.
      * @param dflt beginning of the default handler block.
      * @param labels beginnings of the handler blocks. {@code labels[i]} is the beginning of the
      *     handler block for the {@code min + i} key.
      */
    public TableSwitchInsnNode(
            final int min, final int max, final LabelNode dflt, final LabelNode... labels) {
        super(Opcodes.TABLESWITCH);
        this.min = min;
        this.max = max;
        this.dflt = dflt;
        this.labels = Util.asArrayList(labels);
    }

    @Override
    public int getType() {
        return TABLESWITCH_INSN;
    }

    @Override
    public void accept(final MethodVisitor methodVisitor) {
        Label[] labelsArray = new Label[this.labels.size()];
        for (int i = 0, n = labelsArray.length; i < n; ++i) {
            labelsArray[i] = this.labels.get(i).getLabel();
        }
        methodVisitor.visitTableSwitchInsn(min, max, dflt.getLabel(), labelsArray);
        acceptAnnotations(methodVisitor);
    }

    @Override
    public AbstractInsnNode clone(final Map<LabelNode, LabelNode> clonedLabels) {
        return new TableSwitchInsnNode(min, max, clone(dflt, clonedLabels), clone(labels, clonedLabels))
                .cloneAnnotations(this);
    }
}
