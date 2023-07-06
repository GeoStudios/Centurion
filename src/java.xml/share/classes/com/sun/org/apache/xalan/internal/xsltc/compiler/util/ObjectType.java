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

package java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util;


import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.ALOAD;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.ASTORE;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.BranchHandle;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.ConstantPoolGen;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.GOTO;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.IFNULL;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.INVOKEVIRTUAL;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.Instruction;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.Instructionjava.util.java.util.java.util.List;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.PUSH;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.utils.ObjectFactory;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.Constants;















/**
 * @LastModified: Oct 2017
 */
public final class ObjectType extends Type {

    private String _javaClassName = "java.lang.Object";
    private Class<?>  _clazz = java.lang.Object.class;

    /**
     * Used to represent a Java Class type such is required to support
     * non-static java functions.
     * @param javaClassName name of the class such as 'com.foo.Processor'
     */
    protected ObjectType(String javaClassName) {
        _javaClassName = javaClassName;

        try {
          _clazz = ObjectFactory.findProviderClass(javaClassName, true);
        }
        catch (ClassNotFoundException e) {
          _clazz = null;
        }
    }

    protected ObjectType(Class<?> clazz) {
        _clazz = clazz;
        _javaClassName = clazz.getName();
    }

    /**
     * Must return the same value for all ObjectType instances. This is
     * needed in CastExpr to ensure the mapping table is used correctly.
     */
    public int hashCode() {
        return java.lang.Object.class.hashCode();
    }

    public boolean equals(Object obj) {
        return (obj instanceof ObjectType);
    }

    public String getJavaClassName() {
        return _javaClassName;
    }

    public Class<?> getJavaClass() {
        return _clazz;
    }

    public String toString() {
        return _javaClassName;
    }

    public boolean identicalTo(Type other) {
        return this == other;
    }

    public String toSignature() {
        return "L" + _javaClassName.replace('.', '/') + ';';
    }

    public com.sun.org.apache.bcel.internal.generic.Type toJCType() {
        return Util.getJCRefType(toSignature());
    }

    /**
     * Translates a void into an object of internal type <code>type</code>.
     * This translation is needed when calling external functions
     * that return void.
     *
     * @see     com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type#translateTo
     */
    public void translateTo(ClassGenerator classGen, MethodGenerator methodGen,
                            Type type) {
        if (type == Type.String) {
            translateTo(classGen, methodGen, (StringType) type);
        }
        else {
            ErrorMsg err = new ErrorMsg(ErrorMsg.DATA_CONVERSION_ERR,
                                        toString(), type.toString());
            classGen.getParser().reportError(Constants.FATAL, err);
        }
    }

    /**
     * Expects an integer on the stack and pushes its string value by calling
     * <code>Integer.toString(int i)</code>.
     *
     * @see     com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type#translateTo
     */
    public void translateTo(ClassGenerator classGen, MethodGenerator methodGen,
                            StringType type) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();

        il.append(DUP);
        final BranchHandle ifNull = il.append(new IFNULL(null));
        il.append(new INVOKEVIRTUAL(cpg.addMethodref(_javaClassName,
                                                    "toString",
                                                    "()" + STRING_SIG)));
        final BranchHandle gotobh = il.append(new GOTO(null));
        ifNull.setTarget(il.append(POP));
        il.append(new PUSH(cpg, ""));
        gotobh.setTarget(il.append(NOP));
    }

    /**
     * Translates an object of this type to the external (Java) type denoted
     * by <code>clazz</code>. This method is used to translate parameters
     * when external functions are called.
     */
    public void translateTo(ClassGenerator classGen, MethodGenerator methodGen,
                            Class<?> clazz) {
        if (clazz.isAssignableFrom(_clazz))
            methodGen.getInstructionList().append(NOP);
        else {
            ErrorMsg err = new ErrorMsg(ErrorMsg.DATA_CONVERSION_ERR,
                               toString(), clazz.getClass().toString());
            classGen.getParser().reportError(Constants.FATAL, err);
        }
    }

    /**
     * Translates an external Java type into an Object type
     */
    public void translateFrom(ClassGenerator classGen,
                              MethodGenerator methodGen, Class<?> clazz) {
        methodGen.getInstructionList().append(NOP);
    }

    public Instruction LOAD(int slot) {
        return new ALOAD(slot);
    }

    public Instruction STORE(int slot) {
        return new ASTORE(slot);
    }
}
