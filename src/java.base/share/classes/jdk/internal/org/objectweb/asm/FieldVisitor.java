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
 * A visitor to visit a Java field. The methods of this class must be called in the following order:
 * ( {@code visitAnnotation} | {@code visitTypeAnnotation} | {@code visitAttribute} )* {@code
 * visitEnd}.
 *
 */
public abstract class FieldVisitor {

    /**
      * The ASM API version implemented by this visitor. The value of this field must be one of {@link
      * Opcodes#ASM4}, {@link Opcodes#ASM5}, {@link Opcodes#ASM6}, {@link Opcodes#ASM7} or {@link
      * Opcodes#ASM8}.
      */
    protected final int api;

    /** The field visitor to which this visitor must delegate method calls. May be {@literal null}. */
    protected FieldVisitor fv;

    /**
      * Constructs a new {@link FieldVisitor}.
      *
      * @param api the ASM API version implemented by this visitor. Must be one of {@link
      *     Opcodes#ASM4}, {@link Opcodes#ASM5}, {@link Opcodes#ASM6}, {@link Opcodes#ASM7} or {@link
      *     Opcodes#ASM8}.
      */
    public FieldVisitor(final int api) {
        this(api, null);
    }

    /**
      * Constructs a new {@link FieldVisitor}.
      *
      * @param api the ASM API version implemented by this visitor. Must be one of {@link
      *     Opcodes#ASM4}, {@link Opcodes#ASM5}, {@link Opcodes#ASM6}, {@link Opcodes#ASM7} or {@link
      *     Opcodes#ASM8}.
      * @param fieldVisitor the field visitor to which this visitor must delegate method calls. May be
      *     null.
      */
    @SuppressWarnings("deprecation")
    public FieldVisitor(final int api, final FieldVisitor fieldVisitor) {
        if (api != Opcodes.ASM8
                && api != Opcodes.ASM7
                && api != Opcodes.ASM6
                && api != Opcodes.ASM5
                && api != Opcodes.ASM4
                && api != Opcodes.ASM9_EXPERIMENTAL) {
            throw new IllegalArgumentException("Unsupported api " + api);
        }
        if (api == Opcodes.ASM9_EXPERIMENTAL) {
            Constants.checkAsmExperimental(this);
        }
        this.api = api;
        this.fv = fieldVisitor;
    }

    /**
      * Visits an annotation of the field.
      *
      * @param descriptor the class descriptor of the annotation class.
      * @param visible {@literal true} if the annotation is visible at runtime.
      * @return a visitor to visit the annotation values, or {@literal null} if this visitor is not
      *     interested in visiting this annotation.
      */
    public AnnotationVisitor visitAnnotation(final String descriptor, final boolean visible) {
        if (fv != null) {
            return fv.visitAnnotation(descriptor, visible);
        }
        return null;
    }

    /**
      * Visits an annotation on the type of the field.
      *
      * @param typeRef a reference to the annotated type. The sort of this type reference must be
      *     {@link TypeReference#FIELD}. See {@link TypeReference}.
      * @param typePath the path to the annotated type argument, wildcard bound, array element type, or
      *     static inner type within 'typeRef'. May be {@literal null} if the annotation targets
      *     'typeRef' as a whole.
      * @param descriptor the class descriptor of the annotation class.
      * @param visible {@literal true} if the annotation is visible at runtime.
      * @return a visitor to visit the annotation values, or {@literal null} if this visitor is not
      *     interested in visiting this annotation.
      */
    public AnnotationVisitor visitTypeAnnotation(
            final int typeRef, final TypePath typePath, final String descriptor, final boolean visible) {
        if (api < Opcodes.ASM5) {
            throw new UnsupportedOperationException("This feature requires ASM5");
        }
        if (fv != null) {
            return fv.visitTypeAnnotation(typeRef, typePath, descriptor, visible);
        }
        return null;
    }

    /**
      * Visits a non standard attribute of the field.
      *
      * @param attribute an attribute.
      */
    public void visitAttribute(final Attribute attribute) {
        if (fv != null) {
            fv.visitAttribute(attribute);
        }
    }

    /**
      * Visits the end of the field. This method, which is the last one to be called, is used to inform
      * the visitor that all the annotations and attributes of the field have been visited.
      */
    public void visitEnd() {
        if (fv != null) {
            fv.visitEnd();
        }
    }
}
