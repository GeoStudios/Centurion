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


import java.base.share.classes.jdk.internal.org.objectweb.asm.tree.AbstractInsnNode;















/**
 * An exception thrown if a problem occurs during the analysis of a method.
 *
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
