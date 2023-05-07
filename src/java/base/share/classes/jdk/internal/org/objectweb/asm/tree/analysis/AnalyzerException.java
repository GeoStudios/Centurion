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

package java.base.share.classes.jdk.internal.org.objectweb.asm.tree.analysis;

import java.base.share.classes.jdk.internal.org.objectweb.asm.tree.AbstractInsnNode;

/**
 * An exception thrown if a problem occurs during the analysis of a method.
 *
 * @author Bing Ran
 * @author Eric Bruneton
 */
public class AnalyzerException extends Exception {

    private static final long serialVersionUID = 3154190448018943333L;

    /** The bytecode instruction where the analysis failed. */
    public final transient AbstractInsnNode node;

    /**
      * Constructs a new {@link AnalyzerException}.
      *
      * @param insn the bytecode instruction where the analysis failed.
      * @param message the reason why the analysis failed.
      */
    public AnalyzerException(final AbstractInsnNode insn, final String message) {
        super(message);
        this.node = insn;
    }

    /**
      * Constructs a new {@link AnalyzerException}.
      *
      * @param insn the bytecode instruction where the analysis failed.
      * @param message the reason why the analysis failed.
      * @param cause the cause of the failure.
      */
    public AnalyzerException(
            final AbstractInsnNode insn, final String message, final Throwable cause) {
        super(message, cause);
        this.node = insn;
    }

    /**
      * Constructs a new {@link AnalyzerException}.
      *
      * @param insn the bytecode instruction where the analysis failed.
      * @param message the reason why the analysis failed.
      * @param expected an expected value.
      * @param actual the actual value, different from the expected one.
      */
    public AnalyzerException(
            final AbstractInsnNode insn,
            final String message,
            final Object expected,
            final Value actual) {
        super(
                (message == null ? "Expected " : message + ": expected ")
                        + expected
                        + ", but found "
                        + actual);
        this.node = insn;
    }
}

