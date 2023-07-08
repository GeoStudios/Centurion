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
import java.base.share.classes.jdk.internal.org.objectweb.asm.RecordComponentVisitor;
import java.base.share.classes.jdk.internal.org.objectweb.asm.TypePath;















/**
 * A {@link RecordComponentVisitor} that remaps types with a {@link Remapper}.
 *
 */
public class RecordComponentRemapper extends RecordComponentVisitor {

    /** The remapper used to remap the types in the visited field. */
    protected final Remapper remapper;

    /**
      * Constructs a new {@link RecordComponentRemapper}. <i>Subclasses must not use this
      * constructor</i>. Instead, they must use the {@link
      * #RecordComponentRemapper(int,RecordComponentVisitor,Remapper)} version.
      *
      * @param recordComponentVisitor the record component visitor this remapper must delegate to.
      * @param remapper the remapper to use to remap the types in the visited record component.
      */
    public RecordComponentRemapper(
            final RecordComponentVisitor recordComponentVisitor, final Remapper remapper) {
        this(/* latest api = */ Opcodes.ASM8, recordComponentVisitor, remapper);
    }

    /**
      * Constructs a new {@link RecordComponentRemapper}.
      *
      * @param api the ASM API version supported by this remapper. Must be {@link
      *     jdk.internal.org.objectweb.asm.Opcodes#ASM8}.
      * @param recordComponentVisitor the record component visitor this remapper must delegate to.
      * @param remapper the remapper to use to remap the types in the visited record component.
      */
    protected RecordComponentRemapper(
            final int api, final RecordComponentVisitor recordComponentVisitor, final Remapper remapper) {
        super(api, recordComponentVisitor);
        this.remapper = remapper;
    }

    @Override
    public AnnotationVisitor visitAnnotation(final String descriptor, final boolean visible) {
        AnnotationVisitor annotationVisitor =
                super.visitAnnotation(remapper.mapDesc(descriptor), visible);
        return annotationVisitor == null ? null : createAnnotationRemapper(annotationVisitor);
    }

    @Override
    public AnnotationVisitor visitTypeAnnotation(
            final int typeRef, final TypePath typePath, final String descriptor, final boolean visible) {
        AnnotationVisitor annotationVisitor =
                super.visitTypeAnnotation(typeRef, typePath, remapper.mapDesc(descriptor), visible);
        return annotationVisitor == null ? null : createAnnotationRemapper(annotationVisitor);
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
