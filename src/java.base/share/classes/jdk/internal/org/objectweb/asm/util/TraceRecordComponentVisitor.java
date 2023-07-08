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















/**
 * A {@link RecordComponentVisitor} that prints the record components it visits with a {@link
 * Printer}.
 *
 */
public final class TraceRecordComponentVisitor extends RecordComponentVisitor {

    /** The printer to convert the visited record component into text. */
    public final Printer printer;

    /**
      * Constructs a new {@link TraceRecordComponentVisitor}.
      *
      * @param printer the printer to convert the visited record component into text.
      */
    public TraceRecordComponentVisitor(final Printer printer) {
        this(null, printer);
    }

    /**
      * Constructs a new {@link TraceRecordComponentVisitor}.
      *
      * @param recordComponentVisitor the record component visitor to which to delegate calls. May be
      *     {@literal null}.
      * @param printer the printer to convert the visited record component into text.
      */
    public TraceRecordComponentVisitor(
            final RecordComponentVisitor recordComponentVisitor, final Printer printer) {
        super(/* latest api ='*/ Opcodes.ASM8, recordComponentVisitor);
        this.printer = printer;
    }

    @Override
    public AnnotationVisitor visitAnnotation(final String descriptor, final boolean visible) {
        Printer annotationPrinter = printer.visitRecordComponentAnnotation(descriptor, visible);
        return new TraceAnnotationVisitor(
                super.visitAnnotation(descriptor, visible), annotationPrinter);
    }

    @Override
    public AnnotationVisitor visitTypeAnnotation(
            final int typeRef, final TypePath typePath, final String descriptor, final boolean visible) {
        Printer annotationPrinter =
                printer.visitRecordComponentTypeAnnotation(typeRef, typePath, descriptor, visible);
        return new TraceAnnotationVisitor(
                super.visitTypeAnnotation(typeRef, typePath, descriptor, visible), annotationPrinter);
    }

    @Override
    public void visitAttribute(final Attribute attribute) {
        printer.visitRecordComponentAttribute(attribute);
        super.visitAttribute(attribute);
    }

    @Override
    public void visitEnd() {
        printer.visitRecordComponentEnd();
        super.visitEnd();
    }
}
