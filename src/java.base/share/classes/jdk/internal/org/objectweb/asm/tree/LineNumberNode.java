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















/**
 * A node that represents a line number declaration. These nodes are pseudo instruction nodes in
 * order to be inserted in an instruction list.
 *
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
