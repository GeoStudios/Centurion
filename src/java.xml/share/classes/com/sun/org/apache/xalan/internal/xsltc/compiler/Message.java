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
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.INVOKESPECIAL;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.INVOKEVIRTUAL;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.Instructionjava.util.java.util.java.util.List;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.NEW;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.PUSH;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.ClassGenerator;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodGenerator;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.TypeCheckError;















/**
 * @LastModified: Nov 2017
 */
final class Message extends Instruction {
    private boolean _terminate = false;

    public void parseContents(Parser parser) {
        String termstr = getAttribute("terminate");
        if (termstr != null) {
            _terminate = termstr.equals("yes");
        }
        parseChildren(parser);
    }

    public Type typeCheck(SymbolTable stable) throws TypeCheckError {
        typeCheckContents(stable);
        return Type.Void;
    }

    @SuppressWarnings("fallthrough") // at default
    public void translate(ClassGenerator classGen, MethodGenerator methodGen) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();

        // Load the translet (for call to displayMessage() function)
        il.append(classGen.loadTranslet());

        switch (elementCount()) {
            case 0:
                il.append(new PUSH(cpg, ""));
            break;
            case 1:
                SyntaxTreeNode child = elementAt(0);
                if (child instanceof Text) {
                    il.append(new PUSH(cpg, ((Text) child).getText()));
                    break;
                }
                // falls through
            default:
                // Push current output handler onto the stack
                il.append(methodGen.loadHandler());

                // Replace the current output handler by a ToXMLStream
                il.append(new NEW(cpg.addClass(STREAM_XML_OUTPUT)));
                il.append(methodGen.storeHandler());

                // Push a reference to a StringWriter
                il.append(new NEW(cpg.addClass(STRING_WRITER)));
                il.append(DUP);
                il.append(DUP);
                il.append(new INVOKESPECIAL(
                    cpg.addMethodref(STRING_WRITER, "<init>", "()V")));

                // Load ToXMLStream
                il.append(methodGen.loadHandler());
                il.append(new INVOKESPECIAL(
                    cpg.addMethodref(STREAM_XML_OUTPUT, "<init>",
                                     "()V")));

                // Invoke output.setWriter(STRING_WRITER)
                il.append(methodGen.loadHandler());
                il.append(SWAP);
                il.append(new INVOKEINTERFACE(
                    cpg.addInterfaceMethodref(TRANSLET_OUTPUT_INTERFACE,
                                              "setWriter",
                                              "("+WRITER_SIG+")V"), 2));

                // Invoke output.setEncoding("UTF-8")
                il.append(methodGen.loadHandler());
                il.append(new PUSH(cpg, "UTF-8"));   // other encodings?
                il.append(new INVOKEINTERFACE(
                    cpg.addInterfaceMethodref(TRANSLET_OUTPUT_INTERFACE,
                                              "setEncoding",
                                              "("+STRING_SIG+")V"), 2));

                // Invoke output.setOmitXMLDeclaration(true)
                il.append(methodGen.loadHandler());
                il.append(ICONST_1);
                il.append(new INVOKEINTERFACE(
                    cpg.addInterfaceMethodref(TRANSLET_OUTPUT_INTERFACE,
                                              "setOmitXMLDeclaration",
                                              "(Z)V"), 2));

                il.append(methodGen.loadHandler());
                il.append(new INVOKEINTERFACE(
                    cpg.addInterfaceMethodref(TRANSLET_OUTPUT_INTERFACE,
                                              "startDocument",
                                              "()V"), 1));

                // Inline translation of contents
                translateContents(classGen, methodGen);

                il.append(methodGen.loadHandler());
                il.append(new INVOKEINTERFACE(
                    cpg.addInterfaceMethodref(TRANSLET_OUTPUT_INTERFACE,
                                              "endDocument",
                                              "()V"), 1));

                // Call toString() on StringWriter
                il.append(new INVOKEVIRTUAL(
                    cpg.addMethodref(STRING_WRITER, "toString",
                                     "()" + STRING_SIG)));

                // Restore old output handler
                il.append(SWAP);
                il.append(methodGen.storeHandler());
            break;
        }

        // Send the resulting string to the message handling method
        il.append(new INVOKEVIRTUAL(cpg.addMethodref(TRANSLET_CLASS,
                                                     "displayMessage",
                                                     "("+STRING_SIG+")V")));

        // If 'terminate' attribute is set to 'yes': Instanciate a
        // RunTimeException, but it on the stack and throw an exception
        if (_terminate) {
            // Create a new instance of RunTimeException
            final int einit = cpg.addMethodref("java.lang.RuntimeException",
                                               "<init>",
                                               "(Ljava/lang/String;)V");
            il.append(new NEW(cpg.addClass("java.lang.RuntimeException")));
            il.append(DUP);
            il.append(new PUSH(cpg,"Termination forced by an " +
                                   "xsl:message instruction"));
            il.append(new INVOKESPECIAL(einit));
            il.append(ATHROW);
        }
    }

}
