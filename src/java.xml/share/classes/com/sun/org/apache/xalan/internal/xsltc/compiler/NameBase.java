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

package java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler;

import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.ConstantPoolGen;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.INVOKESTATIC;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.Instructionjava.util.java.util.java.util.List;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.ClassGenerator;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodGenerator;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.TypeCheckError;
import java.util.java.util.java.util.java.util.List;

/**
 * @LastModified: Oct 2017
 */
class NameBase extends FunctionCall {

    private Expression _param = null;
    private Type       _paramType = Type.Node;

    /**
     * Handles calls with no parameter (current node is implicit parameter).
     */
    public NameBase(QName fname) {
        super(fname);
    }

    /**
     * Handles calls with one parameter (either node or node-set).
     */
    public NameBase(QName fname, List<Expression> arguments) {
        super(fname, arguments);
        _param = argument(0);
    }

    /**
     * Check that we either have no parameters or one parameter that is
     * either a node or a node-set.
     */
    public Type typeCheck(SymbolTable stable) throws TypeCheckError {

        // Check the argument type (if any)
        switch(argumentCount()) {
        case 0:
            _paramType = Type.Node;
            break;
        case 1:
            _paramType = _param.typeCheck(stable);
            break;
        default:
            throw new TypeCheckError(this);
        }

        // The argument has to be a node, a node-set or a node reference
        if ((_paramType != Type.NodeSet) &&
            (_paramType != Type.Node) &&
            (_paramType != Type.Reference)) {
            throw new TypeCheckError(this);
        }

        return (_type = Type.String);
    }

    public Type getType() {
        return _type;
    }

    /**
     * Translate the code required for getting the node for which the
     * QName, local-name or namespace URI should be extracted.
     */
    public void translate(ClassGenerator classGen,
                          MethodGenerator methodGen) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();

        il.append(methodGen.loadDOM());

        // Function was called with no parameters
        if (argumentCount() == 0) {
            il.append(methodGen.loadContextNode());
        }
        // Function was called with node parameter
        else if (_paramType == Type.Node) {
            _param.translate(classGen, methodGen);
        }
        else if (_paramType == Type.Reference) {
            _param.translate(classGen, methodGen);
            il.append(new INVOKESTATIC(cpg.addMethodref
                                       (BASIS_LIBRARY_CLASS,
                                        "referenceToNodeSet",
                                        "("
                                        + OBJECT_SIG
                                        + ")"
                                        + NODE_ITERATOR_SIG)));
            il.append(methodGen.nextNode());
        }
        // Function was called with node-set parameter
        else {
            _param.translate(classGen, methodGen);
            _param.startIterator(classGen, methodGen);
            il.append(methodGen.nextNode());
        }
    }
}
