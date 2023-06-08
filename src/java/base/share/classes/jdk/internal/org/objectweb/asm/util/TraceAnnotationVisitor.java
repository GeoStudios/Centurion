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

package java.base.share.classes.jdk.internal.org.objectweb.asm.util;

import java.base.share.classes.jdk.internal.org.objectweb.asm.AnnotationVisitor;
import java.base.share.classes.jdk.internal.org.objectweb.asm.Opcodes;

/**
 * An {@link AnnotationVisitor} that prints the annotations it visits with a {@link Printer}.
 *
 * @author Eric Bruneton
 */
public final class TraceAnnotationVisitor extends AnnotationVisitor {

    /** The printer to convert the visited annotation into text. */
    private final Printer printer;

    /**
      * Constructs a new {@link TraceAnnotationVisitor}.
      *
      * @param printer the printer to convert the visited annotation into text.
      */
    public TraceAnnotationVisitor(final Printer printer) {
        this(null, printer);
    }

    /**
      * Constructs a new {@link TraceAnnotationVisitor}.
      *
      * @param annotationVisitor the annotation visitor to which to delegate calls. May be {@literal
      *     null}.
      * @param printer the printer to convert the visited annotation into text.
      */
    public TraceAnnotationVisitor(final AnnotationVisitor annotationVisitor, final Printer printer) {
        super(/* latest api = */ Opcodes.ASM9, annotationVisitor);
        this.printer = printer;
    }

    @Override
    public void visit(final String name, final Object value) {
        printer.visit(name, value);
        super.visit(name, value);
    }

    @Override
    public void visitEnum(final String name, final String descriptor, final String value) {
        printer.visitEnum(name, descriptor, value);
        super.visitEnum(name, descriptor, value);
    }

    @Override
    public AnnotationVisitor visitAnnotation(final String name, final String descriptor) {
        Printer annotationPrinter = printer.visitAnnotation(name, descriptor);
        return new TraceAnnotationVisitor(super.visitAnnotation(name, descriptor), annotationPrinter);
    }

    @Override
    public AnnotationVisitor visitArray(final String name) {
        Printer arrayPrinter = printer.visitArray(name);
        return new TraceAnnotationVisitor(super.visitArray(name), arrayPrinter);
    }

    @Override
    public void visitEnd() {
        printer.visitAnnotationEnd();
        super.visitEnd();
    }
}

