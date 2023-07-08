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

import java.base.share.classes.jdk.internal.org.objectweb.asm.MethodVisitor;

/**
 * A node that represents a parameter of a method.
 *
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
