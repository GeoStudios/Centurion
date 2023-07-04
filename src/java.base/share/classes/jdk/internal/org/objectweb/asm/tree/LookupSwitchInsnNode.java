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

import java.util.List;
import java.util.Map;
import jdk.internal.org.objectweb.asm.Label;
import jdk.internal.org.objectweb.asm.MethodVisitor;
import jdk.internal.org.objectweb.asm.Opcodes;

/**
 * A node that represents a LOOKUPSWITCH instruction.
 *
 */
public class LookupSwitchInsnNode extends AbstractInsnNode {

    /** Beginning of the default handler block. */
    public LabelNode dflt;

    /** The values of the keys. */
    public List<Integer> keys;

    /** Beginnings of the handler blocks. */
    public List<LabelNode> labels;

    /**
      * Constructs a new {@link LookupSwitchInsnNode}.
      *
      * @param dflt beginning of the default handler block.
      * @param keys the values of the keys.
      * @param labels beginnings of the handler blocks. {@code labels[i]} is the beginning of the
      *     handler block for the {@code keys[i]} key.
      */
    public LookupSwitchInsnNode(final LabelNode dflt, final int[] keys, final LabelNode[] labels) {
        super(Opcodes.LOOKUPSWITCH);
        this.dflt = dflt;
        this.keys = Util.asArrayList(keys);
        this.labels = Util.asArrayList(labels);
    }

    @Override
    public int getType() {
        return LOOKUPSWITCH_INSN;
    }

    @Override
    public void accept(final MethodVisitor methodVisitor) {
        int[] keysArray = new int[this.keys.size()];
        for (int i = 0, n = keysArray.length; i < n; ++i) {
            keysArray[i] = this.keys.get(i).intValue();
        }
        Label[] labelsArray = new Label[this.labels.size()];
        for (int i = 0, n = labelsArray.length; i < n; ++i) {
            labelsArray[i] = this.labels.get(i).getLabel();
        }
        methodVisitor.visitLookupSwitchInsn(dflt.getLabel(), keysArray, labelsArray);
        acceptAnnotations(methodVisitor);
    }

    @Override
    public AbstractInsnNode clone(final Map<LabelNode, LabelNode> clonedLabels) {
        LookupSwitchInsnNode clone =
                new LookupSwitchInsnNode(clone(dflt, clonedLabels), null, clone(labels, clonedLabels));
        clone.keys.addAll(keys);
        return clone.cloneAnnotations(this);
    }
}
