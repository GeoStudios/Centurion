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

package java.base.share.classes.jdk.internal.org.objectweb.asm.tree.analysis;

import java.util.Set;
import java.base.share.classes.jdk.internal.org.objectweb.asm.tree.AbstractInsnNode;

/**
 * A {@link Value} which keeps track of the bytecode instructions that can produce it.
 *
 */
public class SourceValue implements Value {

    /**
      * The size of this value, in 32 bits words. This size is 1 for byte, boolean, char, short, int,
      * float, object and array types, and 2 for long and double.
      */
    public final int size;

    /**
      * The instructions that can produce this value. For example, for the Java code below, the
      * instructions that can produce the value of {@code i} at line 5 are the two ISTORE instructions
      * at line 1 and 3:
      *
      * <pre>
      * 1: i = 0;
      * 2: if (...) {
      * 3:   i = 1;
      * 4: }
      * 5: return i;
      * </pre>
      */
    public final Set<AbstractInsnNode> insns;

    /**
      * Constructs a new {@link SourceValue}.
      *
      * @param size the size of this value, in 32 bits words. This size is 1 for byte, boolean, char,
      *     short, int, float, object and array types, and 2 for long and double.
      */
    public SourceValue(final int size) {
        this(size, new SmallSet<AbstractInsnNode>());
    }

    /**
      * Constructs a new {@link SourceValue}.
      *
      * @param size the size of this value, in 32 bits words. This size is 1 for byte, boolean, char,
      *     short, int, float, object and array types, and 2 for long and double.
      * @param insnNode an instruction that can produce this value.
      */
    public SourceValue(final int size, final AbstractInsnNode insnNode) {
        this.size = size;
        this.insns = new SmallSet<>(insnNode);
    }

    /**
      * Constructs a new {@link SourceValue}.
      *
      * @param size the size of this value, in 32 bits words. This size is 1 for byte, boolean, char,
      *     short, int, float, object and array types, and 2 for long and double.
      * @param insnSet the instructions that can produce this value.
      */
    public SourceValue(final int size, final Set<AbstractInsnNode> insnSet) {
        this.size = size;
        this.insns = insnSet;
    }

    /**
      * Returns the size of this value.
      *
      * @return the size of this value, in 32 bits words. This size is 1 for byte, boolean, char,
      *     short, int, float, object and array types, and 2 for long and double.
      */
    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean equals(final Object value) {
        if (!(value instanceof SourceValue sourceValue)) {
            return false;
        }
        return size == sourceValue.size && insns.equals(sourceValue.insns);
    }

    @Override
    public int hashCode() {
        return insns.hashCode();
    }
}
