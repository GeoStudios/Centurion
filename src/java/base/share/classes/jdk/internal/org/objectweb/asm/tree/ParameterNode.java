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

package java.base.share.classes.jdk.internal.org.objectweb.asm.tree;

import java.base.share.classes.jdk.internal.org.objectweb.asm.MethodVisitor;

/**
 * A node that represents a parameter of a method.
 *
 * @author Remi Forax
 */
public class ParameterNode {

    /** The parameter's name. */
    public String name;

    /**
      * The parameter's access flags (see {@link jdk.internal.org.objectweb.asm.Opcodes}). Valid values are {@code
      * ACC_FINAL}, {@code ACC_SYNTHETIC} and {@code ACC_MANDATED}.
      */
    public int access;

    /**
      * Constructs a new {@link ParameterNode}.
      *
      * @param access The parameter's access flags. Valid values are {@code ACC_FINAL}, {@code
      *     ACC_SYNTHETIC} or/and {@code ACC_MANDATED} (see {@link jdk.internal.org.objectweb.asm.Opcodes}).
      * @param name the parameter's name.
      */
    public ParameterNode(final String name, final int access) {
        this.name = name;
        this.access = access;
    }

    /**
      * Makes the given visitor visit this parameter declaration.
      *
      * @param methodVisitor a method visitor.
      */
    public void accept(final MethodVisitor methodVisitor) {
        methodVisitor.visitParameter(name, access);
    }
}

