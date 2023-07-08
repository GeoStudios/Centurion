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
 * Exception thrown when the constant pool of a class produced by a {@link ClassWriter} is too
 * large.
 *
 */
public final class ClassTooLargeException extends IndexOutOfBoundsException {
    private static final long serialVersionUID = 160715609518896765L;

    private final String className;
    private final int constantPoolCount;

    /**
      * Constructs a new {@link ClassTooLargeException}.
      *
      * @param className the internal name of the class.
      * @param constantPoolCount the number of constant pool items of the class.
      */
    public ClassTooLargeException(final String className, final int constantPoolCount) {
        super("Class too large: " + className);
        this.className = className;
        this.constantPoolCount = constantPoolCount;
    }

    /**
      * Returns the internal name of the class.
      *
      * @return the internal name of the class.
      */
    public String getClassName() {
        return className;
    }

    /**
      * Returns the number of constant pool items of the class.
      *
      * @return the number of constant pool items of the class.
      */
    public int getConstantPoolCount() {
        return constantPoolCount;
    }
}
