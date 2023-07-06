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
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.BranchHandle;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.ConstantPoolGen;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.IFEQ;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.IFNULL;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.ILOAD;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.INVOKEINTERFACE;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.INVOKEVIRTUAL;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.ISTORE;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.InstructionHandle;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.Instructionjava.util.java.util.java.util.List;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.LocalVariableGen;
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
final class Copy extends Instruction {
    private UseAttributeSets _useSets;

    public void parseContents(Parser parser) {
        final String useSets = getAttribute("use-attribute-sets");
        if (useSets.length() > 0) {
            if (!Util.isValidQNames(useSets)) {
                ErrorMsg err = new ErrorMsg(ErrorMsg.INVALID_QNAME_ERR, useSets, this);
                parser.reportError(Constants.ERROR, err);
            }
            _useSets = new UseAttributeSets(useSets, parser);
        }
        parseChildren(parser);
    }

    public void display(int indent) {
        indent(indent);
        Util.println("Copy");
        indent(indent + IndentIncrement);
        displayContents(indent + IndentIncrement);
    }

    public Type typeCheck(SymbolTable stable) throws TypeCheckError {
        if (_useSets != null) {
            _useSets.typeCheck(stable);
        }
        typeCheckContents(stable);
        return Type.Void;
    }

    public void translate(ClassGenerator classGen, MethodGenerator methodGen) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();

        final LocalVariableGen name =
            methodGen.addLocalVariable2("name",
                                        Util.getJCRefType(STRING_SIG),
                                        null);
        final LocalVariableGen length =
            methodGen.addLocalVariable2("length",
                                        Util.getJCRefType("I"),
                                        null);

        // Get the name of the node to copy and save for later
        il.append(methodGen.loadDOM());
        il.append(methodGen.loadCurrentNode());
        il.append(methodGen.loadHandler());
        final int cpy = cpg.addInterfaceMethodref(DOM_INTF,
                                                  "shallowCopy",
                                                  "("
                                                  + NODE_SIG
                                                  + TRANSLET_OUTPUT_SIG
                                                  + ")" + STRING_SIG);
        il.append(new INVOKEINTERFACE(cpy, 3));
        il.append(DUP);
        name.setStart(il.append(new ASTORE(name.getIndex())));
        final BranchHandle ifBlock1 = il.append(new IFNULL(null));

        // Get the length of the node name and save for later
        il.append(new ALOAD(name.getIndex()));
        final int lengthMethod = cpg.addMethodref(STRING_CLASS,"length","()I");
        il.append(new INVOKEVIRTUAL(lengthMethod));
        il.append(DUP);
        length.setStart(il.append(new ISTORE(length.getIndex())));

        // Ignore attribute sets if current node is ROOT. DOM.shallowCopy()
        // returns "" for ROOT, so skip attribute sets if length == 0
        final BranchHandle ifBlock4 = il.append(new IFEQ(null));

        // Copy in attribute sets if specified
        if (_useSets != null) {
            // If the parent of this element will result in an element being
            // output then we know that it is safe to copy out the attributes
            final SyntaxTreeNode parent = getParent();
            if ((parent instanceof LiteralElement) ||
                (parent instanceof LiteralElement)) {
                _useSets.translate(classGen, methodGen);
            }
            // If not we have to check to see if the copy will result in an
            // element being output.
            else {
                // check if element; if not skip to translate body
                il.append(new ILOAD(length.getIndex()));
                final BranchHandle ifBlock2 = il.append(new IFEQ(null));
                // length != 0 -> element -> do attribute sets
                _useSets.translate(classGen, methodGen);
                // not an element; root
                ifBlock2.setTarget(il.append(NOP));
            }
        }

        // Instantiate body of xsl:copy
        ifBlock4.setTarget(il.append(NOP));
        translateContents(classGen, methodGen);

        // Call the output handler's endElement() if we copied an element
        // (The DOM.shallowCopy() method calls startElement().)
        length.setEnd(il.append(new ILOAD(length.getIndex())));
        final BranchHandle ifBlock3 = il.append(new IFEQ(null));
        il.append(methodGen.loadHandler());
        name.setEnd(il.append(new ALOAD(name.getIndex())));
        il.append(methodGen.endElement());

        final InstructionHandle end = il.append(NOP);
        ifBlock1.setTarget(end);
        ifBlock3.setTarget(end);
        methodGen.removeLocalVariable(name);
        methodGen.removeLocalVariable(length);
    }
}
