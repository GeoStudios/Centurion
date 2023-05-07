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

package java.base.share.classes.jdk.internal.org.objectweb.asm.commons;

import java.base.share.classes.jdk.internal.org.objectweb.asm.AnnotationVisitor;
import java.base.share.classes.jdk.internal.org.objectweb.asm.Opcodes;

/**
 * An {@link AnnotationVisitor} adapter for type remapping.
 *
 * @deprecated use {@link AnnotationRemapper} instead.
 * @author Eugene Kuleshov
 */
@Deprecated
public class RemappingAnnotationAdapter extends AnnotationVisitor {

    protected final Remapper remapper;

    public RemappingAnnotationAdapter(
            final AnnotationVisitor annotationVisitor, final Remapper remapper) {
        this(Opcodes.ASM9, annotationVisitor, remapper);
    }

    protected RemappingAnnotationAdapter(
            final int api, final AnnotationVisitor annotationVisitor, final Remapper remapper) {
        super(api, annotationVisitor);
        this.remapper = remapper;
    }

    @Override
    public void visit(final String name, final Object value) {
        av.visit(name, remapper.mapValue(value));
    }

    @Override
    public void visitEnum(final String name, final String descriptor, final String value) {
        av.visitEnum(name, remapper.mapDesc(descriptor), value);
    }

    @Override
    public AnnotationVisitor visitAnnotation(final String name, final String descriptor) {
        AnnotationVisitor annotationVisitor = av.visitAnnotation(name, remapper.mapDesc(descriptor));
        return annotationVisitor == null
                ? null
                : (annotationVisitor == av
                        ? this
                        : new RemappingAnnotationAdapter(annotationVisitor, remapper));
    }

    @Override
    public AnnotationVisitor visitArray(final String name) {
        AnnotationVisitor annotationVisitor = av.visitArray(name);
        return annotationVisitor == null
                ? null
                : (annotationVisitor == av
                        ? this
                        : new RemappingAnnotationAdapter(annotationVisitor, remapper));
    }
}

