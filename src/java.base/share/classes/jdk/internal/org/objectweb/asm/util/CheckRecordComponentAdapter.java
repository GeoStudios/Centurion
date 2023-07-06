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

package java.base.share.classes.jdk.internal.org.objectweb.asm.util;


import java.base.share.classes.jdk.internal.org.objectweb.asm.AnnotationVisitor;
import java.base.share.classes.jdk.internal.org.objectweb.asm.Attribute;
import java.base.share.classes.jdk.internal.org.objectweb.asm.Opcodes;
import java.base.share.classes.jdk.internal.org.objectweb.asm.RecordComponentVisitor;
import java.base.share.classes.jdk.internal.org.objectweb.asm.TypePath;
import java.base.share.classes.jdk.internal.org.objectweb.asm.TypeReference;















/**
 * A {@link RecordComponentVisitor} that checks that its methods are properly used.
 *
 */
public class CheckRecordComponentAdapter extends RecordComponentVisitor {

    /** Whether the {@link #visitEnd()} method has been called. */
    private boolean visitEndCalled;

    /**
      * Constructs a new {@link CheckRecordComponentAdapter}. <i>Subclasses must not use this
      * constructor</i>. Instead, they must use the {@link #CheckRecordComponentAdapter(int,
      * RecordComponentVisitor)} version.
      *
      * @param recordComponentVisitor the record component visitor to which this adapter must delegate
      *     calls.
      * @throws IllegalStateException If a subclass calls this constructor.
      */
    public CheckRecordComponentAdapter(final RecordComponentVisitor recordComponentVisitor) {
        this(/* latest api =*/ Opcodes.ASM8, recordComponentVisitor);
        if (getClass() != CheckRecordComponentAdapter.class) {
            throw new IllegalStateException();
        }
    }

    /**
      * Constructs a new {@link CheckRecordComponentAdapter}.
      *
      * @param api the ASM API version implemented by this visitor. Must be {@link Opcodes#ASM8}.
      * @param recordComponentVisitor the record component visitor to which this adapter must delegate
      *     calls.
      */
    protected CheckRecordComponentAdapter(
            final int api, final RecordComponentVisitor recordComponentVisitor) {
        super(api, recordComponentVisitor);
    }

    @Override
    public AnnotationVisitor visitAnnotation(final String descriptor, final boolean visible) {
        checkVisitEndNotCalled();
        // Annotations can only appear in V1_5 or more classes.
        CheckMethodAdapter.checkDescriptor(Opcodes.V1_5, descriptor, false);
        return new CheckAnnotationAdapter(super.visitAnnotation(descriptor, visible));
    }

    @Override
    public AnnotationVisitor visitTypeAnnotation(
            final int typeRef, final TypePath typePath, final String descriptor, final boolean visible) {
        checkVisitEndNotCalled();
        int sort = new TypeReference(typeRef).getSort();
        if (sort != TypeReference.FIELD) {
            throw new IllegalArgumentException(
                    "Invalid type reference sort 0x" + Integer.toHexString(sort));
        }
        CheckClassAdapter.checkTypeRef(typeRef);
        CheckMethodAdapter.checkDescriptor(Opcodes.V1_5, descriptor, false);
        return new CheckAnnotationAdapter(
                super.visitTypeAnnotation(typeRef, typePath, descriptor, visible));
    }

    @Override
    public void visitAttribute(final Attribute attribute) {
        checkVisitEndNotCalled();
        if (attribute == null) {
            throw new IllegalArgumentException("Invalid attribute (must not be null)");
        }
        super.visitAttribute(attribute);
    }

    @Override
    public void visitEnd() {
        checkVisitEndNotCalled();
        visitEndCalled = true;
        super.visitEnd();
    }

    private void checkVisitEndNotCalled() {
        if (visitEndCalled) {
            throw new IllegalStateException("Cannot call a visit method after visitEnd has been called");
        }
    }
}
