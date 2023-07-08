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

package java.base.share.classes.jdk.internal.org.objectweb.asm;

/**
 * Information about the input stack map frame at the "current" instruction of a method. This is
 * implemented as a Frame subclass for a "basic block" containing only one instruction.
 *
 */
final class CurrentFrame extends Frame {

    CurrentFrame(final Label owner) {
        super(owner);
    }

    /**
      * Sets this CurrentFrame to the input stack map frame of the next "current" instruction, i.e. the
      * instruction just after the given one. It is assumed that the value of this object when this
      * method is called is the stack map frame status just before the given instruction is executed.
      */
    @Override
    void execute(
            final int opcode, final int arg, final Symbol symbolArg, final SymbolTable symbolTable) {
        super.execute(opcode, arg, symbolArg, symbolTable);
        Frame successor = new Frame(null);
        merge(symbolTable, successor, 0);
        copyFrom(successor);
    }
}
