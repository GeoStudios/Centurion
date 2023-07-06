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
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.INVOKESPECIAL;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.Instructionjava.util.java.util.java.util.List;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.ClassGenerator;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.ErrorMsg;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodGenerator;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.TypeCheckError;
import java.util.Arrayjava.util.java.util.java.util.List;
import java.util.java.util.java.util.java.util.List;
import java.util.StringTokenizer;















/**
 * @LastModified: Oct 2017
 */
final class UseAttributeSets extends Instruction {

    // Only error that can occur:
    private final static String ATTR_SET_NOT_FOUND =
        "";

    // Contains the names of all references attribute sets
    private final List<QName> _sets = new ArrayList<>(2);

    /**
     * Constructur - define initial attribute sets to use
     */
    public UseAttributeSets(String setNames, Parser parser) {
        setParser(parser);
        addAttributeSets(setNames);
    }

    /**
     * This method is made public to enable an AttributeSet object to merge
     * itself with another AttributeSet (including any other AttributeSets
     * the two may inherit from).
     */
    public void addAttributeSets(String setNames) {
        if ((setNames != null) && (!setNames.equals(Constants.EMPTYSTRING))) {
            final StringTokenizer tokens = new StringTokenizer(setNames);
            while (tokens.hasMoreTokens()) {
                final QName qname =
                    getParser().getQNameIgnoreDefaultNs(tokens.nextToken());
                _sets.add(qname);
            }
        }
    }

    /**
     * Do nada.
     */
    public Type typeCheck(SymbolTable stable) throws TypeCheckError {
        return Type.Void;
    }

    /**
     * Generate a call to the method compiled for this attribute set
     */
    public void translate(ClassGenerator classGen, MethodGenerator methodGen) {

        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();
        final SymbolTable symbolTable = getParser().getSymbolTable();

        for (QName name : _sets) {
            // Get the AttributeSet reference from the symbol table
            final AttributeSet attrs = symbolTable.lookupAttributeSet(name);
            // Compile the call to the set's method if the set exists
            if (attrs != null) {
                final String methodName = attrs.getMethodName();
                il.append(classGen.loadTranslet());
                il.append(methodGen.loadDOM());
                il.append(methodGen.loadIterator());
                il.append(methodGen.loadHandler());
                il.append(methodGen.loadCurrentNode());
                final int method = cpg.addMethodref(classGen.getClassName(),
                        methodName, ATTR_SET_SIG);
                il.append(new INVOKESPECIAL(method));
            }
            // Generate an error if the attribute set does not exist
            else {
                final Parser parser = getParser();
                final String atrs = name.toString();
                reportError(this, parser, ErrorMsg.ATTRIBSET_UNDEF_ERR, atrs);
            }
        }
    }
}
