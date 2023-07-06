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

package java.base.share.classes.jdk.internal.org.objectweb.asm.commons;


import java.base.share.classes.jdk.internal.org.objectweb.asm.AnnotationVisitor;
import java.base.share.classes.jdk.internal.org.objectweb.asm.Opcodes;















/**
 * An {@link AnnotationVisitor} that remaps types with a {@link Remapper}.
 *
 */
public class AnnotationRemapper extends AnnotationVisitor {

    /** The remapper used to remap the types in the visited annotation. */
    protected final Remapper remapper;

    /**
      * Constructs a new {@link AnnotationRemapper}. <i>Subclasses must not use this constructor</i>.
      * Instead, they must use the {@link #AnnotationRemapper(int,AnnotationVisitor,Remapper)} version.
      *
      * @param annotationVisitor the annotation visitor this remapper must deleted to.
      * @param remapper the remapper to use to remap the types in the visited annotation.
      */
    public AnnotationRemapper(final AnnotationVisitor annotationVisitor, final Remapper remapper) {
        this(/* latest api = */ Opcodes.ASM8, annotationVisitor, remapper);
    }

    /**
      * Constructs a new {@link AnnotationRemapper}.
      *
      * @param api the ASM API version supported by this remapper. Must be one of {@link
      *     jdk.internal.org.objectweb.asm.Opcodes#ASM4}, {@link jdk.internal.org.objectweb.asm.Opcodes#ASM5}, {@link
      *     jdk.internal.org.objectweb.asm.Opcodes#ASM6}, {@link jdk.internal.org.objectweb.asm.Opcodes#ASM7} or {@link
      *     jdk.internal.org.objectweb.asm.Opcodes#ASM8}
      * @param annotationVisitor the annotation visitor this remapper must deleted to.
      * @param remapper the remapper to use to remap the types in the visited annotation.
      */
    protected AnnotationRemapper(
            final int api, final AnnotationVisitor annotationVisitor, final Remapper remapper) {
        super(api, annotationVisitor);
        this.remapper = remapper;
    }

    @Override
    public void visit(final String name, final Object value) {
        super.visit(name, remapper.mapValue(value));
    }

    @Override
    public void visitEnum(final String name, final String descriptor, final String value) {
        super.visitEnum(name, remapper.mapDesc(descriptor), value);
    }

    @Override
    public AnnotationVisitor visitAnnotation(final String name, final String descriptor) {
        AnnotationVisitor annotationVisitor = super.visitAnnotation(name, remapper.mapDesc(descriptor));
        if (annotationVisitor == null) {
            return null;
        } else {
            return annotationVisitor == av ? this : createAnnotationRemapper(annotationVisitor);
        }
    }

    @Override
    public AnnotationVisitor visitArray(final String name) {
        AnnotationVisitor annotationVisitor = super.visitArray(name);
        if (annotationVisitor == null) {
            return null;
        } else {
            return annotationVisitor == av ? this : createAnnotationRemapper(annotationVisitor);
        }
    }

    /**
      * Constructs a new remapper for annotations. The default implementation of this method returns a
      * new {@link AnnotationRemapper}.
      *
      * @param annotationVisitor the AnnotationVisitor the remapper must delegate to.
      * @return the newly created remapper.
      */
    protected AnnotationVisitor createAnnotationRemapper(final AnnotationVisitor annotationVisitor) {
        return new AnnotationRemapper(api, annotationVisitor, remapper);
    }
}
