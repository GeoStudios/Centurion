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

import java.util.Arrayjava.util.java.util.java.util.List;
import java.util.java.util.java.util.java.util.List;
import java.base.share.classes.jdk.internal.org.objectweb.asm.tree.JumpInsnNode;
import java.base.share.classes.jdk.internal.org.objectweb.asm.tree.LabelNode;

/**
 * A method subroutine (corresponds to a JSR instruction).
 *
 */
final class Subroutine {

    /** The start of this subroutine. */
    final LabelNode start;

    /**
      * The local variables that are read or written by this subroutine. The i-th element is true if
      * and only if the local variable at index i is read or written by this subroutine.
      */
    final boolean[] localsUsed;

    /** The JSR instructions that jump to this subroutine. */
    final List<JumpInsnNode> callers;

    /**
      * Constructs a new {@link Subroutine}.
      *
      * @param start the start of this subroutine.
      * @param maxLocals the local variables that are read or written by this subroutine.
      * @param caller a JSR instruction that jump to this subroutine.
      */
    Subroutine(final LabelNode start, final int maxLocals, final JumpInsnNode caller) {
        this.start = start;
        this.localsUsed = new boolean[maxLocals];
        this.callers = new ArrayList<>();
        callers.add(caller);
    }

    /**
      * Constructs a copy of the given {@link Subroutine}.
      *
      * @param subroutine the subroutine to copy.
      */
    Subroutine(final Subroutine subroutine) {
        this.start = subroutine.start;
        this.localsUsed = subroutine.localsUsed.clone();
        this.callers = new ArrayList<>(subroutine.callers);
    }

    /**
      * Merges the given subroutine into this subroutine. The local variables read or written by the
      * given subroutine are marked as read or written by this one, and the callers of the given
      * subroutine are added as callers of this one (if both have the same start).
      *
      * @param subroutine another subroutine. This subroutine is left unchanged by this method.
      * @return whether this subroutine has been modified by this method.
      */
    public boolean merge(final Subroutine subroutine) {
        boolean changed = false;
        for (int i = 0; i < localsUsed.length; ++i) {
            if (subroutine.localsUsed[i] && !localsUsed[i]) {
                localsUsed[i] = true;
                changed = true;
            }
        }
        if (subroutine.start == start) {
            for (int i = 0; i < subroutine.callers.size(); ++i) {
                JumpInsnNode caller = subroutine.callers.get(i);
                if (!callers.contains(caller)) {
                    callers.add(caller);
                    changed = true;
                }
            }
        }
        return changed;
    }
}
