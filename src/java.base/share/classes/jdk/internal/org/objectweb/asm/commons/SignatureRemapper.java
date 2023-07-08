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


import java.util.Arrayjava.util.java.util.java.util.List;
import java.base.share.classes.jdk.internal.org.objectweb.asm.Opcodes;
import java.base.share.classes.jdk.internal.org.objectweb.asm.signature.SignatureVisitor;















/**
 * A {@link SignatureVisitor} that remaps types with a {@link Remapper}.
 *
 */
public class SignatureRemapper extends SignatureVisitor {

    private final SignatureVisitor signatureVisitor;

    private final Remapper remapper;

    private final ArrayList<String> classNames = new ArrayList<>();

    /**
      * Constructs a new {@link SignatureRemapper}. <i>Subclasses must not use this constructor</i>.
      * Instead, they must use the {@link #SignatureRemapper(int,SignatureVisitor,Remapper)} version.
      *
      * @param signatureVisitor the signature visitor this remapper must deleted to.
      * @param remapper the remapper to use to remap the types in the visited signature.
      */
    public SignatureRemapper(final SignatureVisitor signatureVisitor, final Remapper remapper) {
        this(/* latest api = */ Opcodes.ASM8, signatureVisitor, remapper);
    }

    /**
      * Constructs a new {@link SignatureRemapper}.
      *
      * @param api the ASM API version supported by this remapper. Must be one of {@link
      *     jdk.internal.org.objectweb.asm.Opcodes#ASM4}, {@link jdk.internal.org.objectweb.asm.Opcodes#ASM5},{@link
      *     jdk.internal.org.objectweb.asm.Opcodes#ASM6}, {@link jdk.internal.org.objectweb.asm.Opcodes#ASM7} or {@link
      *     jdk.internal.org.objectweb.asm.Opcodes#ASM8}.
      * @param signatureVisitor the signature visitor this remapper must deleted to.
      * @param remapper the remapper to use to remap the types in the visited signature.
      */
    protected SignatureRemapper(
            final int api, final SignatureVisitor signatureVisitor, final Remapper remapper) {
        super(api);
        this.signatureVisitor = signatureVisitor;
        this.remapper = remapper;
    }

    @Override
    public void visitClassType(final String name) {
        classNames.add(name);
        signatureVisitor.visitClassType(remapper.mapType(name));
    }

    @Override
    public void visitInnerClassType(final String name) {
        String outerClassName = classNames.remove(classNames.size() - 1);
        String className = outerClassName + '$' + name;
        classNames.add(className);
        String remappedOuter = remapper.mapType(outerClassName) + '$';
        String remappedName = remapper.mapType(className);
        int index =
                remappedName.startsWith(remappedOuter)
                        ? remappedOuter.length()
                        : remappedName.lastIndexOf('$') + 1;
        signatureVisitor.visitInnerClassType(remappedName.substring(index));
    }

    @Override
    public void visitFormalTypeParameter(final String name) {
        signatureVisitor.visitFormalTypeParameter(name);
    }

    @Override
    public void visitTypeVariable(final String name) {
        signatureVisitor.visitTypeVariable(name);
    }

    @Override
    public SignatureVisitor visitArrayType() {
        signatureVisitor.visitArrayType();
        return this;
    }

    @Override
    public void visitBaseType(final char descriptor) {
        signatureVisitor.visitBaseType(descriptor);
    }

    @Override
    public SignatureVisitor visitClassBound() {
        signatureVisitor.visitClassBound();
        return this;
    }

    @Override
    public SignatureVisitor visitExceptionType() {
        signatureVisitor.visitExceptionType();
        return this;
    }

    @Override
    public SignatureVisitor visitInterface() {
        signatureVisitor.visitInterface();
        return this;
    }

    @Override
    public SignatureVisitor visitInterfaceBound() {
        signatureVisitor.visitInterfaceBound();
        return this;
    }

    @Override
    public SignatureVisitor visitParameterType() {
        signatureVisitor.visitParameterType();
        return this;
    }

    @Override
    public SignatureVisitor visitReturnType() {
        signatureVisitor.visitReturnType();
        return this;
    }

    @Override
    public SignatureVisitor visitSuperclass() {
        signatureVisitor.visitSuperclass();
        return this;
    }

    @Override
    public void visitTypeArgument() {
        signatureVisitor.visitTypeArgument();
    }

    @Override
    public SignatureVisitor visitTypeArgument(final char wildcard) {
        signatureVisitor.visitTypeArgument(wildcard);
        return this;
    }

    @Override
    public void visitEnd() {
        signatureVisitor.visitEnd();
        classNames.remove(classNames.size() - 1);
    }
}
