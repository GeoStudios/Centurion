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
import java.base.share.classes.jdk.internal.org.objectweb.asm.Handle;
import java.base.share.classes.jdk.internal.org.objectweb.asm.Label;
import java.base.share.classes.jdk.internal.org.objectweb.asm.MethodVisitor;
import java.base.share.classes.jdk.internal.org.objectweb.asm.Opcodes;
import java.base.share.classes.jdk.internal.org.objectweb.asm.TypePath;















/**
 * A {@link MethodVisitor} that remaps types with a {@link Remapper}.
 *
 */
public class MethodRemapper extends MethodVisitor {

    /** The remapper used to remap the types in the visited field. */
    protected final Remapper remapper;

    /**
      * Constructs a new {@link MethodRemapper}. <i>Subclasses must not use this constructor</i>.
      * Instead, they must use the {@link #MethodRemapper(int,MethodVisitor,Remapper)} version.
      *
      * @param methodVisitor the method visitor this remapper must deleted to.
      * @param remapper the remapper to use to remap the types in the visited method.
      */
    public MethodRemapper(final MethodVisitor methodVisitor, final Remapper remapper) {
        this(/* latest api = */ Opcodes.ASM8, methodVisitor, remapper);
    }

    /**
      * Constructs a new {@link MethodRemapper}.
      *
      * @param api the ASM API version supported by this remapper. Must be one of {@link
      *     jdk.internal.org.objectweb.asm.Opcodes#ASM4}, {@link jdk.internal.org.objectweb.asm.Opcodes#ASM5} or {@link
      *     jdk.internal.org.objectweb.asm.Opcodes#ASM6}, {@link jdk.internal.org.objectweb.asm.Opcodes#ASM7} or {@link
      *     jdk.internal.org.objectweb.asm.Opcodes#ASM8}.
      * @param methodVisitor the method visitor this remapper must deleted to.
      * @param remapper the remapper to use to remap the types in the visited method.
      */
    protected MethodRemapper(
            final int api, final MethodVisitor methodVisitor, final Remapper remapper) {
        super(api, methodVisitor);
        this.remapper = remapper;
    }

    @Override
    public AnnotationVisitor visitAnnotationDefault() {
        AnnotationVisitor annotationVisitor = super.visitAnnotationDefault();
        return annotationVisitor == null
                ? annotationVisitor
                : createAnnotationRemapper(annotationVisitor);
    }

    @Override
    public AnnotationVisitor visitAnnotation(final String descriptor, final boolean visible) {
        AnnotationVisitor annotationVisitor =
                super.visitAnnotation(remapper.mapDesc(descriptor), visible);
        return annotationVisitor == null
                ? annotationVisitor
                : createAnnotationRemapper(annotationVisitor);
    }

    @Override
    public AnnotationVisitor visitTypeAnnotation(
            final int typeRef, final TypePath typePath, final String descriptor, final boolean visible) {
        AnnotationVisitor annotationVisitor =
                super.visitTypeAnnotation(typeRef, typePath, remapper.mapDesc(descriptor), visible);
        return annotationVisitor == null
                ? annotationVisitor
                : createAnnotationRemapper(annotationVisitor);
    }

    @Override
    public AnnotationVisitor visitParameterAnnotation(
            final int parameter, final String descriptor, final boolean visible) {
        AnnotationVisitor annotationVisitor =
                super.visitParameterAnnotation(parameter, remapper.mapDesc(descriptor), visible);
        return annotationVisitor == null
                ? annotationVisitor
                : createAnnotationRemapper(annotationVisitor);
    }

    @Override
    public void visitFrame(
            final int type,
            final int numLocal,
            final Object[] local,
            final int numStack,
            final Object[] stack) {
        super.visitFrame(
                type,
                numLocal,
                remapFrameTypes(numLocal, local),
                numStack,
                remapFrameTypes(numStack, stack));
    }

    private Object[] remapFrameTypes(final int numTypes, final Object[] frameTypes) {
        if (frameTypes == null) {
            return frameTypes;
        }
        Object[] remappedFrameTypes = null;
        for (int i = 0; i < numTypes; ++i) {
            if (frameTypes[i] instanceof String) {
                if (remappedFrameTypes == null) {
                    remappedFrameTypes = new Object[numTypes];
                    System.arraycopy(frameTypes, 0, remappedFrameTypes, 0, numTypes);
                }
                remappedFrameTypes[i] = remapper.mapType((String) frameTypes[i]);
            }
        }
        return remappedFrameTypes == null ? frameTypes : remappedFrameTypes;
    }

    @Override
    public void visitFieldInsn(
            final int opcode, final String owner, final String name, final String descriptor) {
        super.visitFieldInsn(
                opcode,
                remapper.mapType(owner),
                remapper.mapFieldName(owner, name, descriptor),
                remapper.mapDesc(descriptor));
    }

    @Override
    public void visitMethodInsn(
            final int opcodeAndSource,
            final String owner,
            final String name,
            final String descriptor,
            final boolean isInterface) {
        if (api < Opcodes.ASM5 && (opcodeAndSource & Opcodes.SOURCE_DEPRECATED) == 0) {
            // Redirect the call to the deprecated version of this method.
            super.visitMethodInsn(opcodeAndSource, owner, name, descriptor, isInterface);
            return;
        }
        super.visitMethodInsn(
                opcodeAndSource,
                remapper.mapType(owner),
                remapper.mapMethodName(owner, name, descriptor),
                remapper.mapMethodDesc(descriptor),
                isInterface);
    }

    @Override
    public void visitInvokeDynamicInsn(
            final String name,
            final String descriptor,
            final Handle bootstrapMethodHandle,
            final Object... bootstrapMethodArguments) {
        Object[] remappedBootstrapMethodArguments = new Object[bootstrapMethodArguments.length];
        for (int i = 0; i < bootstrapMethodArguments.length; ++i) {
            remappedBootstrapMethodArguments[i] = remapper.mapValue(bootstrapMethodArguments[i]);
        }
        super.visitInvokeDynamicInsn(
                remapper.mapInvokeDynamicMethodName(name, descriptor),
                remapper.mapMethodDesc(descriptor),
                (Handle) remapper.mapValue(bootstrapMethodHandle),
                remappedBootstrapMethodArguments);
    }

    @Override
    public void visitTypeInsn(final int opcode, final String type) {
        super.visitTypeInsn(opcode, remapper.mapType(type));
    }

    @Override
    public void visitLdcInsn(final Object value) {
        super.visitLdcInsn(remapper.mapValue(value));
    }

    @Override
    public void visitMultiANewArrayInsn(final String descriptor, final int numDimensions) {
        super.visitMultiANewArrayInsn(remapper.mapDesc(descriptor), numDimensions);
    }

    @Override
    public AnnotationVisitor visitInsnAnnotation(
            final int typeRef, final TypePath typePath, final String descriptor, final boolean visible) {
        AnnotationVisitor annotationVisitor =
                super.visitInsnAnnotation(typeRef, typePath, remapper.mapDesc(descriptor), visible);
        return annotationVisitor == null
                ? annotationVisitor
                : createAnnotationRemapper(annotationVisitor);
    }

    @Override
    public void visitTryCatchBlock(
            final Label start, final Label end, final Label handler, final String type) {
        super.visitTryCatchBlock(start, end, handler, type == null ? null : remapper.mapType(type));
    }

    @Override
    public AnnotationVisitor visitTryCatchAnnotation(
            final int typeRef, final TypePath typePath, final String descriptor, final boolean visible) {
        AnnotationVisitor annotationVisitor =
                super.visitTryCatchAnnotation(typeRef, typePath, remapper.mapDesc(descriptor), visible);
        return annotationVisitor == null
                ? annotationVisitor
                : createAnnotationRemapper(annotationVisitor);
    }

    @Override
    public void visitLocalVariable(
            final String name,
            final String descriptor,
            final String signature,
            final Label start,
            final Label end,
            final int index) {
        super.visitLocalVariable(
                name,
                remapper.mapDesc(descriptor),
                remapper.mapSignature(signature, true),
                start,
                end,
                index);
    }

    @Override
    public AnnotationVisitor visitLocalVariableAnnotation(
            final int typeRef,
            final TypePath typePath,
            final Label[] start,
            final Label[] end,
            final int[] index,
            final String descriptor,
            final boolean visible) {
        AnnotationVisitor annotationVisitor =
                super.visitLocalVariableAnnotation(
                        typeRef, typePath, start, end, index, remapper.mapDesc(descriptor), visible);
        return annotationVisitor == null
                ? annotationVisitor
                : createAnnotationRemapper(annotationVisitor);
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
