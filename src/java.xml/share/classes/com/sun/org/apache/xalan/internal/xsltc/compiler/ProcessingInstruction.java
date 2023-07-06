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


import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.ALOAD;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.ASTORE;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.ConstantPoolGen;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.GETFIELD;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.INVOKEINTERFACE;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.INVOKESTATIC;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.INVOKEVIRTUAL;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.Instructionjava.util.java.util.java.util.List;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.LocalVariableGen;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.ClassGenerator;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.ErrorMsg;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodGenerator;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.TypeCheckError;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.Util;
import java.xml.share.classes.com.sun.org.apache.xml.internal.utils.XML11Char;















/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */



/**
 */
final class ProcessingInstruction extends Instruction {

    private AttributeValue _name; // name treated as AVT (7.1.3)
    private boolean _isLiteral = false;  // specified name is not AVT

    public void parseContents(Parser parser) {
        final String name  = getAttribute("name");

        if (name.length() > 0) {
            _isLiteral = Util.isLiteral(name);
            if (_isLiteral) {
                if (!XML11Char.isXML11ValidNCName(name)) {
                    ErrorMsg err = new ErrorMsg(ErrorMsg.INVALID_NCNAME_ERR, name, this);
                    parser.reportError(Constants.ERROR, err);
                }
            }
            _name = AttributeValue.create(this, name, parser);
        }
        else
            reportError(this, parser, ErrorMsg.REQUIRED_ATTR_ERR, "name");

        if (name.equals("xml")) {
            reportError(this, parser, ErrorMsg.ILLEGAL_PI_ERR, "xml");
        }
        parseChildren(parser);
    }

    public Type typeCheck(SymbolTable stable) throws TypeCheckError {
        _name.typeCheck(stable);
        typeCheckContents(stable);
        return Type.Void;
    }

    public void translate(ClassGenerator classGen, MethodGenerator methodGen) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();

        if (!_isLiteral) {
            // if the ncname is an AVT, then the ncname has to be checked at runtime if it is a valid ncname
            LocalVariableGen nameValue =
                    methodGen.addLocalVariable2("nameValue",
            Util.getJCRefType(STRING_SIG),
                                                null);

            // store the name into a variable first so _name.translate only needs to be called once
            _name.translate(classGen, methodGen);
            nameValue.setStart(il.append(new ASTORE(nameValue.getIndex())));
            il.append(new ALOAD(nameValue.getIndex()));

            // call checkNCName if the name is an AVT
            final int check = cpg.addMethodref(BASIS_LIBRARY_CLASS, "checkNCName",
                                "("
                                +STRING_SIG
                                +")V");
                                il.append(new INVOKESTATIC(check));

            // Save the current handler base on the stack
            il.append(methodGen.loadHandler());
            il.append(DUP);     // first arg to "attributes" call

            // load name value again
            nameValue.setEnd(il.append(new ALOAD(nameValue.getIndex())));
        } else {
            // Save the current handler base on the stack
            il.append(methodGen.loadHandler());
            il.append(DUP);     // first arg to "attributes" call

            // Push attribute name
            _name.translate(classGen, methodGen);// 2nd arg

        }

        il.append(classGen.loadTranslet());
        il.append(new GETFIELD(cpg.addFieldref(TRANSLET_CLASS,
                                               "stringValueHandler",
                                               STRING_VALUE_HANDLER_SIG)));
        il.append(DUP);
        il.append(methodGen.storeHandler());

        // translate contents with substituted handler
        translateContents(classGen, methodGen);

        // get String out of the handler
        il.append(new INVOKEVIRTUAL(cpg.addMethodref(STRING_VALUE_HANDLER,
                                                     "getValueOfPI",
                                                     "()" + STRING_SIG)));
        // call "processingInstruction"
        final int processingInstruction =
            cpg.addInterfaceMethodref(TRANSLET_OUTPUT_INTERFACE,
                                      "processingInstruction",
                                      "(" + STRING_SIG + STRING_SIG + ")V");
        il.append(new INVOKEINTERFACE(processingInstruction, 3));
        // Restore old handler base from stack
        il.append(methodGen.storeHandler());
    }
}
