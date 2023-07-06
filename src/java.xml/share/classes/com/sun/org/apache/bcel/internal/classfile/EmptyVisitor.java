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

package java.xml.share.classes.com.sun.org.apache.bcel.internal.classfile;

















/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */


/**
 * Visitor with empty method bodies, can be extended and used in conjunction
 * with the DescendingVisitor class, e.g. By courtesy of David Spencer.
 *
 * @see DescendingVisitor
 */
public class EmptyVisitor implements Visitor
{
    protected EmptyVisitor()
    {
    }

    /**
     */
    @Override
    public void visitAnnotation(final Annotations obj)
    {
    }

    /**
     */
    @Override
    public void visitParameterAnnotation(final ParameterAnnotations obj)
    {
    }

    /**
     */
    @Override
    public void visitAnnotationEntry(final AnnotationEntry obj)
    {
    }

    /**
     */
    @Override
    public void visitAnnotationDefault(final AnnotationDefault obj)
    {
    }

    @Override
    public void visitCode(final Code obj)
    {
    }

    @Override
    public void visitCodeException(final CodeException obj)
    {
    }

    @Override
    public void visitConstantClass(final ConstantClass obj)
    {
    }

    @Override
    public void visitConstantDouble(final ConstantDouble obj)
    {
    }

    @Override
    public void visitConstantFieldref(final ConstantFieldref obj)
    {
    }

    @Override
    public void visitConstantFloat(final ConstantFloat obj)
    {
    }

    @Override
    public void visitConstantInteger(final ConstantInteger obj)
    {
    }

    @Override
    public void visitConstantInterfaceMethodref(final ConstantInterfaceMethodref obj)
    {
    }

    @Override
    public void visitConstantInvokeDynamic(final ConstantInvokeDynamic obj)
    {
    }

    @Override
    public void visitConstantLong(final ConstantLong obj)
    {
    }

    @Override
    public void visitConstantMethodref(final ConstantMethodref obj)
    {
    }

    @Override
    public void visitConstantNameAndType(final ConstantNameAndType obj)
    {
    }

    @Override
    public void visitConstantPool(final ConstantPool obj)
    {
    }

    @Override
    public void visitConstantString(final ConstantString obj)
    {
    }

    @Override
    public void visitConstantUtf8(final ConstantUtf8 obj)
    {
    }

    @Override
    public void visitConstantValue(final ConstantValue obj)
    {
    }

    @Override
    public void visitDeprecated(final Deprecated obj)
    {
    }

    @Override
    public void visitExceptionTable(final ExceptionTable obj)
    {
    }

    @Override
    public void visitField(final Field obj)
    {
    }

    @Override
    public void visitInnerClass(final InnerClass obj)
    {
    }

    @Override
    public void visitInnerClasses(final InnerClasses obj)
    {
    }

    /**
     */
    @Override
    public void visitBootstrapMethods(final BootstrapMethods obj)
    {
    }

    @Override
    public void visitJavaClass(final JavaClass obj)
    {
    }

    @Override
    public void visitLineNumber(final LineNumber obj)
    {
    }

    @Override
    public void visitLineNumberTable(final LineNumberTable obj)
    {
    }

    @Override
    public void visitLocalVariable(final LocalVariable obj)
    {
    }

    @Override
    public void visitLocalVariableTable(final LocalVariableTable obj)
    {
    }

    @Override
    public void visitMethod(final Method obj)
    {
    }

    @Override
    public void visitSignature(final Signature obj)
    {
    }

    @Override
    public void visitSourceFile(final SourceFile obj)
    {
    }

    @Override
    public void visitSynthetic(final Synthetic obj)
    {
    }

    @Override
    public void visitUnknown(final Unknown obj)
    {
    }

    @Override
    public void visitStackMap(final StackMap obj)
    {
    }

    @Override
    public void visitStackMapEntry(final StackMapEntry obj)
    {
    }

    /**
    @Override
    public void visitStackMapTable(StackMapTable obj)
    {
    }
     */

    /**
    @Override
    public void visitStackMapTableEntry(StackMapTableEntry obj)
    {
    }
     */

    /**
     */
    @Override
    public void visitEnclosingMethod(final EnclosingMethod obj)
    {
    }

    /**
     */
    @Override
    public void visitLocalVariableTypeTable(final LocalVariableTypeTable obj)
    {
    }

    /**
     */
    @Override
    public void visitMethodParameters(final MethodParameters obj)
    {
    }

    /**
     */
    @Override
    public void visitMethodParameter(final MethodParameter obj)
    {
    }

    /**
     */
    @Override
    public void visitConstantMethodType(final ConstantMethodType obj)
    {
    }

    /**
     */
    @Override
    public void visitConstantMethodHandle(final ConstantMethodHandle constantMethodHandle) {
    }

    /**
     */
    @Override
    public void visitParameterAnnotationEntry(final ParameterAnnotationEntry parameterAnnotationEntry) {
    }

    /**
     */
    @Override
    public void visitConstantPackage(final ConstantPackage constantPackage) {
    }

    /**
     */
    @Override
    public void visitConstantModule(final ConstantModule constantModule) {
    }

    /**
     */
    @Override
    public void visitConstantDynamic(final ConstantDynamic obj) {
    }

    @Override
    public void visitModule(final Module obj) {
    }

    @Override
    public void visitModuleRequires(final ModuleRequires obj) {
    }

    @Override
    public void visitModuleExports(final ModuleExports obj) {
    }

    @Override
    public void visitModuleOpens(final ModuleOpens obj) {
    }

    @Override
    public void visitModuleProvides(final ModuleProvides obj) {
    }

    @Override
    public void visitModulePackages(final ModulePackages obj) {
    }

    @Override
    public void visitModuleMainClass(final ModuleMainClass obj) {
    }

    @Override
    public void visitNestHost(final NestHost obj) {
    }

    @Override
    public void visitNestMembers(final NestMembers obj) {
    }
}
