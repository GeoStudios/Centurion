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
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.ConstantPoolGen;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.IFEQ;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.ILOAD;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.INVOKEINTERFACE;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.INVOKESTATIC;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.Instruction;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.Instructionjava.util.java.util.java.util.List;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.PUSH;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.Constants;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.Flowjava.util.java.util.java.util.List;
import java.xml.share.classes.com.sun.org.apache.xml.internal.dtm.DTM;

/**
 * @LastModified: Oct 2017
 */
public final class ReferenceType extends Type {
    protected ReferenceType() {}

    public String toString() {
        return "reference";
    }

    public boolean identicalTo(Type other) {
        return this == other;
    }

    public String toSignature() {
        return "Ljava/lang/Object;";
    }

    public com.sun.org.apache.bcel.internal.generic.Type toJCType() {
        return com.sun.org.apache.bcel.internal.generic.Type.OBJECT;
    }

    /**
     * Translates a reference to an object of internal type <code>type</code>.
     * The translation to int is undefined since references
     * are always converted to reals in arithmetic expressions.
     *
     * @see     com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type#translateTo
     */
    public void translateTo(ClassGenerator classGen, MethodGenerator methodGen,
                            Type type) {
        if (type == Type.String) {
            translateTo(classGen, methodGen, (StringType) type);
        }
        else if (type == Type.Real) {
            translateTo(classGen, methodGen, (RealType) type);
        }
        else if (type == Type.Boolean) {
            translateTo(classGen, methodGen, (BooleanType) type);
        }
        else if (type == Type.NodeSet) {
            translateTo(classGen, methodGen, (NodeSetType) type);
        }
        else if (type == Type.Node) {
            translateTo(classGen, methodGen, (NodeType) type);
        }
        else if (type == Type.ResultTree) {
            translateTo(classGen, methodGen, (ResultTreeType) type);
        }
        else if (type == Type.Object) {
            translateTo(classGen, methodGen, (ObjectType) type);
        }
        else if (type == Type.Reference ) {
        }
        else {
            ErrorMsg err = new ErrorMsg(ErrorMsg.INTERNAL_ERR, type.toString());
            classGen.getParser().reportError(Constants.FATAL, err);
        }
    }

    /**
     * Translates reference into object of internal type <code>type</code>.
     *
     * @see     com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type#translateTo
     */
    public void translateTo(ClassGenerator classGen, MethodGenerator methodGen,
                            StringType type) {
        final int current = methodGen.getLocalIndex("current");
        ConstantPoolGen cpg = classGen.getConstantPool();
        InstructionList il = methodGen.getInstructionList();

        // If no current, conversion is a top-level
        if (current < 0) {
            il.append(new PUSH(cpg, DTM.ROOT_NODE));  // push root node
        }
        else {
            il.append(new ILOAD(current));
        }
        il.append(methodGen.loadDOM());
        final int stringF = cpg.addMethodref(BASIS_LIBRARY_CLASS,
                                             "stringF",
                                             "("
                                             + OBJECT_SIG
                                             + NODE_SIG
                                             + DOM_INTF_SIG
                                             + ")" + STRING_SIG);
        il.append(new INVOKESTATIC(stringF));
    }

    /**
     * Translates a reference into an object of internal type <code>type</code>.
     *
     * @see     com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type#translateTo
     */
    public void translateTo(ClassGenerator classGen, MethodGenerator methodGen,
                            RealType type) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();

        il.append(methodGen.loadDOM());
        int index = cpg.addMethodref(BASIS_LIBRARY_CLASS, "numberF",
                                     "("
                                     + OBJECT_SIG
                                     + DOM_INTF_SIG
                                     + ")D");
        il.append(new INVOKESTATIC(index));
    }

    /**
     * Translates a reference to an object of internal type <code>type</code>.
     *
     * @see     com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type#translateTo
     */
    public void translateTo(ClassGenerator classGen, MethodGenerator methodGen,
                            BooleanType type) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();

        int index = cpg.addMethodref(BASIS_LIBRARY_CLASS, "booleanF",
                                     "("
                                     + OBJECT_SIG
                                     + ")Z");
        il.append(new INVOKESTATIC(index));
    }

    /**
     * Casts a reference into a NodeIterator.
     *
     * @see     com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type#translateTo
     */
    public void translateTo(ClassGenerator classGen, MethodGenerator methodGen,
                            NodeSetType type) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();
        int index = cpg.addMethodref(BASIS_LIBRARY_CLASS, "referenceToNodeSet",
                                     "("
                                     + OBJECT_SIG
                                     + ")"
                                     + NODE_ITERATOR_SIG);
        il.append(new INVOKESTATIC(index));

        // Reset this iterator
        index = cpg.addInterfaceMethodref(NODE_ITERATOR, RESET, RESET_SIG);
        il.append(new INVOKEINTERFACE(index, 1));
    }

    /**
     * Casts a reference into a Node.
     *
     * @see com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type#translateTo
     */
    public void translateTo(ClassGenerator classGen, MethodGenerator methodGen,
                            NodeType type) {
        translateTo(classGen, methodGen, Type.NodeSet);
        Type.NodeSet.translateTo(classGen, methodGen, type);
    }

    /**
     * Casts a reference into a ResultTree.
     *
     * @see     com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type#translateTo
     */
    public void translateTo(ClassGenerator classGen, MethodGenerator methodGen,
                            ResultTreeType type) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();
        int index = cpg.addMethodref(BASIS_LIBRARY_CLASS, "referenceToResultTree",
                                     "(" + OBJECT_SIG + ")" + DOM_INTF_SIG);
        il.append(new INVOKESTATIC(index));
    }

    /**
     * Subsume reference into ObjectType.
     *
     * @see     com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type#translateTo
     */
    public void translateTo(ClassGenerator classGen, MethodGenerator methodGen,
                            ObjectType type) {
        methodGen.getInstructionList().append(NOP);
    }

    /**
     * Translates a reference into the Java type denoted by <code>clazz</code>.
     */
    public void translateTo(ClassGenerator classGen, MethodGenerator methodGen,
                            Class<?> clazz) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();

        int referenceToLong = cpg.addMethodref(BASIS_LIBRARY_CLASS,
                                               "referenceToLong",
                                               "(" + OBJECT_SIG + ")J");
        int referenceToDouble = cpg.addMethodref(BASIS_LIBRARY_CLASS,
                                                 "referenceToDouble",
                                                "(" + OBJECT_SIG + ")D");
        int referenceToBoolean = cpg.addMethodref(BASIS_LIBRARY_CLASS,
                                                  "referenceToBoolean",
                                                 "(" + OBJECT_SIG + ")Z");

        if (clazz.getName().equals("java.lang.Object")) {
            il.append(NOP);
        }
        else if (clazz == Double.TYPE) {
            il.append(new INVOKESTATIC(referenceToDouble));
        }
        else if (clazz.getName().equals("java.lang.Double")) {
            il.append(new INVOKESTATIC(referenceToDouble));
            Type.Real.translateTo(classGen, methodGen, Type.Reference);
        }
        else if (clazz == Float.TYPE) {
            il.append(new INVOKESTATIC(referenceToDouble));
            il.append(D2F);
        }
        else if (clazz.getName().equals("java.lang.String")) {
            int index = cpg.addMethodref(BASIS_LIBRARY_CLASS, "referenceToString",
                                         "("
                                         + OBJECT_SIG
                                         + DOM_INTF_SIG
                                         + ")"
                                         + "Ljava/lang/String;");
            il.append(methodGen.loadDOM());
            il.append(new INVOKESTATIC(index));
        }
        else if (clazz.getName().equals("org.w3c.dom.Node")) {
            int index = cpg.addMethodref(BASIS_LIBRARY_CLASS, "referenceToNode",
                                         "("
                                         + OBJECT_SIG
                                         + DOM_INTF_SIG
                                         + ")"
                                         + "Lorg/w3c/dom/Node;");
            il.append(methodGen.loadDOM());
            il.append(new INVOKESTATIC(index));
        }
        else if (clazz.getName().equals("org.w3c.dom.NodeList")) {
            int index = cpg.addMethodref(BASIS_LIBRARY_CLASS, "referenceToNodeList",
                                         "("
                                         + OBJECT_SIG
                                         + DOM_INTF_SIG
                                         + ")"
                                         + "Lorg/w3c/dom/NodeList;");
            il.append(methodGen.loadDOM());
            il.append(new INVOKESTATIC(index));
        }
        else if (clazz.getName().equals("com.sun.org.apache.xalan.internal.xsltc.DOM")) {
            translateTo(classGen, methodGen, Type.ResultTree);
        }
        else if (clazz == Long.TYPE) {
            il.append(new INVOKESTATIC(referenceToLong));
        }
        else if (clazz == Integer.TYPE) {
            il.append(new INVOKESTATIC(referenceToLong));
            il.append(L2I);
        }
        else if (clazz == Short.TYPE) {
            il.append(new INVOKESTATIC(referenceToLong));
            il.append(L2I);
            il.append(I2S);
        }
        else if (clazz == Byte.TYPE) {
            il.append(new INVOKESTATIC(referenceToLong));
            il.append(L2I);
            il.append(I2B);
        }
        else if (clazz == Character.TYPE) {
            il.append(new INVOKESTATIC(referenceToLong));
            il.append(L2I);
            il.append(I2C);
        }
        else if (clazz == java.lang.Boolean.TYPE) {
            il.append(new INVOKESTATIC(referenceToBoolean));
        }
        else if (clazz.getName().equals("java.lang.Boolean")) {
            il.append(new INVOKESTATIC(referenceToBoolean));
            Type.Boolean.translateTo(classGen, methodGen, Type.Reference);
        }
        else {
            ErrorMsg err = new ErrorMsg(ErrorMsg.DATA_CONVERSION_ERR,
                                        toString(), clazz.getName());
            classGen.getParser().reportError(Constants.FATAL, err);
        }
    }

    /**
     * Translates an external Java type into a reference. Only conversion
     * allowed is from java.lang.Object.
     */
    public void translateFrom(ClassGenerator classGen, MethodGenerator methodGen,
                              Class<?> clazz) {
        if (clazz.getName().equals("java.lang.Object")) {
            methodGen.getInstructionList().append(NOP);
        }
        else {
            ErrorMsg err = new ErrorMsg(ErrorMsg.DATA_CONVERSION_ERR,
                                toString(), clazz.getName());
            classGen.getParser().reportError(Constants.FATAL, err);
        }
    }

    /**
     * Expects a reference on the stack and translates it to a non-synthesized
     * boolean. It does not push a 0 or a 1 but instead returns branchhandle
     * list to be appended to the false list.
     *
     * @see com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type#translateToDesynthesized
     */
    public FlowList translateToDesynthesized(ClassGenerator classGen,
                                             MethodGenerator methodGen,
                                             BooleanType type) {
        InstructionList il = methodGen.getInstructionList();
        translateTo(classGen, methodGen, type);
        return new FlowList(il.append(new IFEQ(null)));
    }

    /**
     * Translates an object of this type to its boxed representation.
     */
    public void translateBox(ClassGenerator classGen,
                             MethodGenerator methodGen) {
    }

    /**
     * Translates an object of this type to its unboxed representation.
     */
    public void translateUnBox(ClassGenerator classGen,
                               MethodGenerator methodGen) {
    }

    public Instruction LOAD(int slot) {
        return new ALOAD(slot);
    }

    public Instruction STORE(int slot) {
        return new ASTORE(slot);
    }
}