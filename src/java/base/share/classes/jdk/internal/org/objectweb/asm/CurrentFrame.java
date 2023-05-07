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

package java.base.share.classes.jdk.internal.org.objectweb.asm;

/**
 * Information about the input stack map frame at the "current" instruction of a method. This is
 * implemented as a Frame subclass for a "basic block" containing only one instruction.
 *
 * @author Eric Bruneton
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

