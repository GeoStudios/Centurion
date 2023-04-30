/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
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
 * @author Remi Forax
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
        super(/* latest api ='*/ Opcodes.ASM9, recordComponentVisitor);
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

