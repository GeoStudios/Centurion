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
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.INVOKEINTERFACE;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.INVOKEVIRTUAL;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.Instructionjava.util.java.util.java.util.List;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.PUSH;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.ClassGenerator;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.ErrorMsg;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodGenerator;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.TypeCheckError;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.Util;















/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */



/**
 */
final class ValueOf extends Instruction {
    private Expression _select;
    private boolean _escaping = true;
    private boolean _isString = false;

    public void display(int indent) {
        indent(indent);
        Util.println("ValueOf");
        indent(indent + IndentIncrement);
        Util.println("select " + _select.toString());
    }

    public void parseContents(Parser parser) {
        _select = parser.parseExpression(this, "select", null);

        // make sure required attribute(s) have been set
        if (_select.isDummy()) {
            reportError(this, parser, ErrorMsg.REQUIRED_ATTR_ERR, "select");
            return;
        }
        final String str = getAttribute("disable-output-escaping");
        if ((str != null) && (str.equals("yes"))) _escaping = false;
    }

    public Type typeCheck(SymbolTable stable) throws TypeCheckError {
        Type type = _select.typeCheck(stable);

        // Prefer to handle the value as a node; fall back to String, otherwise
        if (type != null && !type.identicalTo(Type.Node)) {
            /***
             *** %HZ% Would like to treat result-tree fragments in the same
             *** %HZ% way as node sets for value-of, but that's running into
             *** %HZ% some snags.  Instead, they'll be converted to String
            if (type.identicalTo(Type.ResultTree)) {
                _select = new CastExpr(new CastExpr(_select, Type.NodeSet),
                                       Type.Node);
            } else
            ***/
            if (type.identicalTo(Type.NodeSet)) {
                _select = new CastExpr(_select, Type.Node);
            } else {
                _isString = true;
                if (!type.identicalTo(Type.String)) {
                    _select = new CastExpr(_select, Type.String);
                }
                _isString = true;
            }
        }
        return Type.Void;
    }

    public void translate(ClassGenerator classGen, MethodGenerator methodGen) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();
        final int setEscaping = cpg.addInterfaceMethodref(OUTPUT_HANDLER,
                                                          "setEscaping","(Z)Z");

        // Turn off character escaping if so is wanted.
        if (!_escaping) {
            il.append(methodGen.loadHandler());
            il.append(new PUSH(cpg,false));
            il.append(new INVOKEINTERFACE(setEscaping,2));
        }

        // Translate the contents.  If the value is a string, use the
        // translet.characters(String, TranslatOutputHandler) method.
        // Otherwise, the value is a node, and the
        // dom.characters(int node, TransletOutputHandler) method can dispatch
        // the string value of the node to the output handler more efficiently.
        if (_isString) {
            final int characters = cpg.addMethodref(TRANSLET_CLASS,
                                                    CHARACTERSW,
                                                    CHARACTERSW_SIG);

            il.append(classGen.loadTranslet());
            _select.translate(classGen, methodGen);
            il.append(methodGen.loadHandler());
            il.append(new INVOKEVIRTUAL(characters));
        } else {
            final int characters = cpg.addInterfaceMethodref(DOM_INTF,
                                                             CHARACTERS,
                                                             CHARACTERS_SIG);

            il.append(methodGen.loadDOM());
            _select.translate(classGen, methodGen);
            il.append(methodGen.loadHandler());
            il.append(new INVOKEINTERFACE(characters, 3));
        }

        // Restore character escaping setting to whatever it was.
        if (!_escaping) {
            il.append(methodGen.loadHandler());
            il.append(SWAP);
            il.append(new INVOKEINTERFACE(setEscaping,2));
            il.append(POP);
        }
    }
}
