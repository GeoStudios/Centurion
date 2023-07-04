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
package com.sun.org.apache.xalan.internal.xsltc.compiler;

import com.sun.org.apache.bcel.internal.generic.ConstantPoolGen;
import com.sun.org.apache.bcel.internal.generic.INVOKEINTERFACE;
import com.sun.org.apache.bcel.internal.generic.INVOKESTATIC;
import com.sun.org.apache.bcel.internal.generic.INVOKEVIRTUAL;
import com.sun.org.apache.bcel.internal.generic.InstructionList;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ClassGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ErrorMsg;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.NodeSetType;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.NodeType;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ReferenceType;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ResultTreeType;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.TypeCheckError;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Util;

/**
 */
final class CopyOf extends Instruction {
    private Expression _select;

    public void display(int indent) {
        indent(indent);
        Util.println("CopyOf");
        indent(indent + IndentIncrement);
        Util.println("select " + _select.toString());
    }

    public void parseContents(Parser parser) {
        _select = parser.parseExpression(this, "select", null);
        // make sure required attribute(s) have been set
        if (_select.isDummy()) {
            reportError(this, parser, ErrorMsg.REQUIRED_ATTR_ERR, "select");
        }
    }

    public Type typeCheck(SymbolTable stable) throws TypeCheckError {
        final Type tselect = _select.typeCheck(stable);
        if (tselect instanceof NodeType ||
            tselect instanceof NodeSetType ||
            tselect instanceof ReferenceType ||
            tselect instanceof ResultTreeType) {
            // falls through
        }
        else {
            _select = new CastExpr(_select, Type.String);
        }
        return Type.Void;
    }

    public void translate(ClassGenerator classGen, MethodGenerator methodGen) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();
        final Type tselect = _select.getType();

        final String CPY1_SIG = "("+NODE_ITERATOR_SIG+TRANSLET_OUTPUT_SIG+")V";
        final int cpy1 = cpg.addInterfaceMethodref(DOM_INTF, "copy", CPY1_SIG);

        final String CPY2_SIG = "("+NODE_SIG+TRANSLET_OUTPUT_SIG+")V";
        final int cpy2 = cpg.addInterfaceMethodref(DOM_INTF, "copy", CPY2_SIG);

        final String getDoc_SIG = "()"+NODE_SIG;
        final int getDoc = cpg.addInterfaceMethodref(DOM_INTF, "getDocument", getDoc_SIG);


        if (tselect instanceof NodeSetType) {
            il.append(methodGen.loadDOM());

            // push NodeIterator
            _select.translate(classGen, methodGen);
            _select.startIterator(classGen, methodGen);

            // call copy from the DOM 'library'
            il.append(methodGen.loadHandler());
            il.append(new INVOKEINTERFACE(cpy1, 3));
        }
        else if (tselect instanceof NodeType) {
            il.append(methodGen.loadDOM());
            _select.translate(classGen, methodGen);
            il.append(methodGen.loadHandler());
            il.append(new INVOKEINTERFACE(cpy2, 3));
        }
        else if (tselect instanceof ResultTreeType) {
            _select.translate(classGen, methodGen);
            // We want the whole tree, so we start with the root node
            il.append(DUP); //need a pointer to the DOM ;
            il.append(new INVOKEINTERFACE(getDoc,1)); //ICONST_0);
            il.append(methodGen.loadHandler());
            il.append(new INVOKEINTERFACE(cpy2, 3));
        }
        else if (tselect instanceof ReferenceType) {
            _select.translate(classGen, methodGen);
            il.append(methodGen.loadHandler());
            il.append(methodGen.loadCurrentNode());
            il.append(methodGen.loadDOM());
            final int copy = cpg.addMethodref(BASIS_LIBRARY_CLASS, "copy",
                                              "("
                                              + OBJECT_SIG
                                              + TRANSLET_OUTPUT_SIG
                                              + NODE_SIG
                                              + DOM_INTF_SIG
                                              + ")V");
            il.append(new INVOKESTATIC(copy));
        }
        else {
            il.append(classGen.loadTranslet());
            _select.translate(classGen, methodGen);
            il.append(methodGen.loadHandler());
            il.append(new INVOKEVIRTUAL(cpg.addMethodref(TRANSLET_CLASS,
                                                         CHARACTERSW,
                                                         CHARACTERSW_SIG)));
        }

    }
}
