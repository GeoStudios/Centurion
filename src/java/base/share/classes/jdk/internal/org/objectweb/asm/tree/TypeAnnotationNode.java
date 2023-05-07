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

import java.base.share.classes.jdk.internal.org.objectweb.asm.Opcodes;
import java.base.share.classes.jdk.internal.org.objectweb.asm.TypePath;

/**
 * A node that represents a type annotation.
 *
 * @author Eric Bruneton
 */
public class TypeAnnotationNode extends AnnotationNode {

    /** A reference to the annotated type. See {@link jdk.internal.org.objectweb.asm.TypeReference}. */
    public int typeRef;

    /**
      * The path to the annotated type argument, wildcard bound, array element type, or static outer
      * type within the referenced type. May be {@literal null} if the annotation targets 'typeRef' as
      * a whole.
      */
    public TypePath typePath;

    /**
      * Constructs a new {@link AnnotationNode}. <i>Subclasses must not use this constructor</i>.
      * Instead, they must use the {@link #TypeAnnotationNode(int, int, TypePath, String)} version.
      *
      * @param typeRef a reference to the annotated type. See {@link jdk.internal.org.objectweb.asm.TypeReference}.
      * @param typePath the path to the annotated type argument, wildcard bound, array element type, or
      *     static inner type within 'typeRef'. May be {@literal null} if the annotation targets
      *     'typeRef' as a whole.
      * @param descriptor the class descriptor of the annotation class.
      * @throws IllegalStateException If a subclass calls this constructor.
      */
    public TypeAnnotationNode(final int typeRef, final TypePath typePath, final String descriptor) {
        this(/* latest api = */ Opcodes.ASM9, typeRef, typePath, descriptor);
        if (getClass() != TypeAnnotationNode.class) {
            throw new IllegalStateException();
        }
    }

    /**
      * Constructs a new {@link AnnotationNode}.
      *
      * @param api the ASM API version implemented by this visitor. Must be one of the {@code
      *     ASM}<i>x</i> values in {@link Opcodes}.
      * @param typeRef a reference to the annotated type. See {@link jdk.internal.org.objectweb.asm.TypeReference}.
      * @param typePath the path to the annotated type argument, wildcard bound, array element type, or
      *     static inner type within 'typeRef'. May be {@literal null} if the annotation targets
      *     'typeRef' as a whole.
      * @param descriptor the class descriptor of the annotation class.
      */
    public TypeAnnotationNode(
            final int api, final int typeRef, final TypePath typePath, final String descriptor) {
        super(api, descriptor);
        this.typeRef = typeRef;
        this.typePath = typePath;
    }
}

