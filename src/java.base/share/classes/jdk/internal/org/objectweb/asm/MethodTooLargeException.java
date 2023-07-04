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

package jdk.internal.org.objectweb.asm;

/**
 * Exception thrown when the Code attribute of a method produced by a {@link ClassWriter} is too
 * large.
 *
 */
public final class MethodTooLargeException extends IndexOutOfBoundsException {
    private static final long serialVersionUID = 6807380416709738314L;

    private final String className;
    private final String methodName;
    private final String descriptor;
    private final int codeSize;

    /**
      * Constructs a new {@link MethodTooLargeException}.
      *
      * @param className the internal name of the owner class.
      * @param methodName the name of the method.
      * @param descriptor the descriptor of the method.
      * @param codeSize the size of the method's Code attribute, in bytes.
      */
    public MethodTooLargeException(
            final String className,
            final String methodName,
            final String descriptor,
            final int codeSize) {
        super("Method too large: " + className + "." + methodName + " " + descriptor);
        this.className = className;
        this.methodName = methodName;
        this.descriptor = descriptor;
        this.codeSize = codeSize;
    }

    /**
      * Returns the internal name of the owner class.
      *
      * @return the internal name of the owner class.
      */
    public String getClassName() {
        return className;
    }

    /**
      * Returns the name of the method.
      *
      * @return the name of the method.
      */
    public String getMethodName() {
        return methodName;
    }

    /**
      * Returns the descriptor of the method.
      *
      * @return the descriptor of the method.
      */
    public String getDescriptor() {
        return descriptor;
    }

    /**
      * Returns the size of the method's Code attribute, in bytes.
      *
      * @return the size of the method's Code attribute, in bytes.
      */
    public int getCodeSize() {
        return codeSize;
    }
}
