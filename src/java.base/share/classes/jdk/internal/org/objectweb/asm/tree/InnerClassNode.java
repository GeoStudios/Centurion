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

import java.base.share.classes.jdk.internal.org.objectweb.asm.ClassVisitor;

/**
 * A node that represents an inner class.
 *
 */
public class InnerClassNode {

    /** The internal name of an inner class (see {@link jdk.internal.org.objectweb.asm.Type#getInternalName()}). */
    public String name;

    /**
      * The internal name of the class to which the inner class belongs (see {@link
      * jdk.internal.org.objectweb.asm.Type#getInternalName()}). May be {@literal null}.
      */
    public String outerName;

    /**
      * The (simple) name of the inner class inside its enclosing class. May be {@literal null} for
      * anonymous inner classes.
      */
    public String innerName;

    /** The access flags of the inner class as originally declared in the enclosing class. */
    public int access;

    /**
      * Constructs a new {@link InnerClassNode}.
      *
      * @param name the internal name of an inner class (see {@link
      *     jdk.internal.org.objectweb.asm.Type#getInternalName()}).
      * @param outerName the internal name of the class to which the inner class belongs (see {@link
      *     jdk.internal.org.objectweb.asm.Type#getInternalName()}). May be {@literal null}.
      * @param innerName the (simple) name of the inner class inside its enclosing class. May be
      *     {@literal null} for anonymous inner classes.
      * @param access the access flags of the inner class as originally declared in the enclosing
      *     class.
      */
    public InnerClassNode(
            final String name, final String outerName, final String innerName, final int access) {
        this.name = name;
        this.outerName = outerName;
        this.innerName = innerName;
        this.access = access;
    }

    /**
      * Makes the given class visitor visit this inner class.
      *
      * @param classVisitor a class visitor.
      */
    public void accept(final ClassVisitor classVisitor) {
        classVisitor.visitInnerClass(name, outerName, innerName, access);
    }
}
