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
import java.base.share.classes.jdk.internal.org.objectweb.asm.Opcodes;















/**
 * An {@link AnnotationVisitor} that prints the annotations it visits with a {@link Printer}.
 *
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
        super(/* latest api = */ Opcodes.ASM8, annotationVisitor);
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
