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

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
package com.sun.org.apache.bcel.internal.classfile;

/**
 * Interface to make use of the Visitor pattern programming style. I.e. a class
 * that implements this interface can traverse the contents of a Java class just
 * by calling the `accept' method which all classes have.
 *
 */
public interface Visitor
{
    void visitCode(Code obj);

    void visitCodeException(CodeException obj);

    void visitConstantClass(ConstantClass obj);

    void visitConstantDouble(ConstantDouble obj);

    void visitConstantFieldref(ConstantFieldref obj);

    void visitConstantFloat(ConstantFloat obj);

    void visitConstantInteger(ConstantInteger obj);

    void visitConstantInterfaceMethodref(ConstantInterfaceMethodref obj);

    void visitConstantInvokeDynamic(ConstantInvokeDynamic obj);

    void visitConstantLong(ConstantLong obj);

    void visitConstantMethodref(ConstantMethodref obj);

    void visitConstantNameAndType(ConstantNameAndType obj);

    void visitConstantPool(ConstantPool obj);

    void visitConstantString(ConstantString obj);

    void visitConstantUtf8(ConstantUtf8 obj);

    void visitConstantValue(ConstantValue obj);

    void visitDeprecated(Deprecated obj);

    void visitExceptionTable(ExceptionTable obj);

    void visitField(Field obj);

    void visitInnerClass(InnerClass obj);

    void visitInnerClasses(InnerClasses obj);

    void visitJavaClass(JavaClass obj);

    void visitLineNumber(LineNumber obj);

    void visitLineNumberTable(LineNumberTable obj);

    void visitLocalVariable(LocalVariable obj);

    void visitLocalVariableTable(LocalVariableTable obj);

    void visitMethod(Method obj);

    void visitSignature(Signature obj);

    void visitSourceFile(SourceFile obj);

    void visitSynthetic(Synthetic obj);

    void visitUnknown(Unknown obj);

    void visitStackMap(StackMap obj);

    void visitStackMapEntry(StackMapEntry obj);

    /**
     */
    void visitAnnotation(Annotations obj);

    /**
     */
    void visitParameterAnnotation(ParameterAnnotations obj);

    /**
     */
    void visitAnnotationEntry(AnnotationEntry obj);

    /**
     */
    void visitAnnotationDefault(AnnotationDefault obj);

    /**
     */
    void visitLocalVariableTypeTable(LocalVariableTypeTable obj);

    /**
     */
    void visitEnclosingMethod(EnclosingMethod obj);

    /**
     */
    void visitBootstrapMethods(BootstrapMethods obj);

    /**
     */
    void visitMethodParameters(MethodParameters obj);

    /**
     */
    default void visitMethodParameter(final MethodParameter obj) {
        // empty
    }

    /**
     */
    void visitConstantMethodType(ConstantMethodType obj);

    /**
     */
    void visitConstantMethodHandle(ConstantMethodHandle obj);

    /**
     */
    void visitParameterAnnotationEntry(ParameterAnnotationEntry obj);

    /**
     */
    void visitConstantPackage(ConstantPackage constantPackage);

    /**
     */
    void visitConstantModule(ConstantModule constantModule);

    /**
     */
    default void visitConstantDynamic(final ConstantDynamic constantDynamic) {
        // empty
    }

    /**
     */
    default void visitModule(final Module constantModule) {
        // empty
    }

    /**
     */
    default void visitModuleRequires(final ModuleRequires constantModule) {
        // empty
    }

    /**
     */
    default void visitModuleExports(final ModuleExports constantModule) {
        // empty
    }

    /**
     */
    default void visitModuleOpens(final ModuleOpens constantModule) {
        // empty
    }

    /**
     */
    default void visitModuleProvides(final ModuleProvides constantModule) {
        // empty
    }

    /**
     */
    default void visitModulePackages(final ModulePackages constantModule) {
        // empty
    }

    /**
     */
    default void visitModuleMainClass(final ModuleMainClass obj) {
        // empty
    }

    /**
     */
    default void visitNestHost(final NestHost obj) {
        // empty
    }

    /**
     */
    default void visitNestMembers(final NestMembers obj) {
        // empty
    }
}
