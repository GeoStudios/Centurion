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
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.GETFIELD;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.INVOKESTATIC;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.Instructionjava.util.java.util.java.util.List;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.PUSH;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.ClassGenerator;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.ErrorMsg;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodGenerator;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.TypeCheckError;
import java.util.java.util.java.util.java.util.List;















/**
 * @LastModified: Oct 2017
 */
final class DocumentCall extends FunctionCall {

    private Expression _arg1 = null;
    private Expression _arg2 = null;
    private Type       _arg1Type;

    /**
     * Default function call constructor
     */
    public DocumentCall(QName fname, List<Expression> arguments) {
        super(fname, arguments);
    }

    /**
     * Type checks the arguments passed to the document() function. The first
     * argument can be any type (we must cast it to a string) and contains the
     * URI of the document
     */
    public Type typeCheck(SymbolTable stable) throws TypeCheckError {
        // At least one argument - two at most
        final int ac = argumentCount();
        if ((ac < 1) || (ac > 2)) {
            ErrorMsg msg = new ErrorMsg(ErrorMsg.ILLEGAL_ARG_ERR, this);
            throw new TypeCheckError(msg);
        }
        if (getStylesheet() == null) {
            ErrorMsg msg = new ErrorMsg(ErrorMsg.ILLEGAL_ARG_ERR, this);
            throw new TypeCheckError(msg);
        }

        // Parse the first argument
        _arg1 = argument(0);

        if (_arg1 == null) {// should not happened
            ErrorMsg msg = new ErrorMsg(ErrorMsg.DOCUMENT_ARG_ERR, this);
            throw new TypeCheckError(msg);
        }

        _arg1Type = _arg1.typeCheck(stable);
        if ((_arg1Type != Type.NodeSet) && (_arg1Type != Type.String)) {
            _arg1 = new CastExpr(_arg1, Type.String);
        }

        // Parse the second argument
        if (ac == 2) {
            _arg2 = argument(1);

            if (_arg2 == null) {// should not happened
                ErrorMsg msg = new ErrorMsg(ErrorMsg.DOCUMENT_ARG_ERR, this);
                throw new TypeCheckError(msg);
            }

            final Type arg2Type = _arg2.typeCheck(stable);

            if (arg2Type.identicalTo(Type.Node)) {
                _arg2 = new CastExpr(_arg2, Type.NodeSet);
            } else if (arg2Type.identicalTo(Type.NodeSet)) {
                // falls through
            } else {
                ErrorMsg msg = new ErrorMsg(ErrorMsg.DOCUMENT_ARG_ERR, this);
                throw new TypeCheckError(msg);
            }
        }

        return _type = Type.NodeSet;
    }

    /**
     * Translates the document() function call to a call to LoadDocument()'s
     * static method document().
     */
    public void translate(ClassGenerator classGen, MethodGenerator methodGen) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();
        final int ac = argumentCount();

        final int domField = cpg.addFieldref(classGen.getClassName(),
                                             DOM_FIELD,
                                             DOM_INTF_SIG);

        String docParamList = null;
        if (ac == 1) {
           // documentF(Object,String,AbstractTranslet,DOM)
           docParamList = "("+OBJECT_SIG+STRING_SIG+TRANSLET_SIG+DOM_INTF_SIG
                         +")"+NODE_ITERATOR_SIG;
        } else { //ac == 2; ac < 1 or as >2  was tested in typeChec()
           // documentF(Object,DTMAxisIterator,String,AbstractTranslet,DOM)
           docParamList = "("+OBJECT_SIG+NODE_ITERATOR_SIG+STRING_SIG
                         +TRANSLET_SIG+DOM_INTF_SIG+")"+NODE_ITERATOR_SIG;
        }
        final int docIdx = cpg.addMethodref(LOAD_DOCUMENT_CLASS, "documentF",
                                            docParamList);


        // The URI can be either a node-set or something else cast to a string
        _arg1.translate(classGen, methodGen);
        if (_arg1Type == Type.NodeSet) {
            _arg1.startIterator(classGen, methodGen);
        }

        if (ac == 2) {
            //_arg2 == null was tested in typeChec()
            _arg2.translate(classGen, methodGen);
            _arg2.startIterator(classGen, methodGen);
        }

        // Feck the rest of the parameters on the stack
        il.append(new PUSH(cpg, getStylesheet().getSystemId()));
        il.append(classGen.loadTranslet());
        il.append(DUP);
        il.append(new GETFIELD(domField));
        il.append(new INVOKESTATIC(docIdx));
    }

}
