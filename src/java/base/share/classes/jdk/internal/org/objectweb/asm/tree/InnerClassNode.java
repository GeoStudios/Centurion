/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package jdk.internal.org.objectweb.asm.tree;

import jdk.internal.org.objectweb.asm.ClassVisitor;

/**
 * A node that represents an inner class.
 *
 * @author Eric Bruneton
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

