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

import java.util.Enumeration;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.ConstantPoolGen;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.INVOKESPECIAL;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.INVOKEVIRTUAL;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.Instructionjava.util.java.util.java.util.List;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.NEW;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.ClassGenerator;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodGenerator;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.TypeCheckError;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.Util;

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */

final class ApplyImports extends Instruction {

    private QName      _modeName;
    private int        _precedence;

    public void display(int indent) {
        indent(indent);
        Util.println("ApplyTemplates");
        indent(indent + IndentIncrement);
        if (_modeName != null) {
            indent(indent + IndentIncrement);
            Util.println("mode " + _modeName);
        }
    }

    /**
     * Returns true if this <xsl:apply-imports/> element has parameters
     */
    public boolean hasWithParams() {
        return hasContents();
    }

    /**
     * Determine the lowest import precedence for any stylesheet imported
     * or included by the stylesheet in which this <xsl:apply-imports/>
     * element occured. The templates that are imported by the stylesheet in
     * which this element occured will all have higher import precedence than
     * the integer returned by this method.
     */
    private int getMinPrecedence(int max) {
        // Move to root of include tree
        Stylesheet includeRoot = getStylesheet();
        while (includeRoot._includedFrom != null) {
            includeRoot = includeRoot._includedFrom;
        }

        return includeRoot.getMinimumDescendantPrecedence();
    }

    /**
     * Parse the attributes and contents of an <xsl:apply-imports/> element.
     */
    public void parseContents(Parser parser) {
        // Indicate to the top-level stylesheet that all templates must be
        // compiled into separate methods.
        Stylesheet stylesheet = getStylesheet();
        stylesheet.setTemplateInlining(false);

        // Get the mode we are currently in (might not be any)
        Template template = getTemplate();
        _modeName = template.getModeName();
        _precedence = template.getImportPrecedence();

        // Get the method name for <xsl:apply-imports/> in this mode
        stylesheet = parser.getTopLevelStylesheet();

        parseChildren(parser);  // with-params
    }

    /**
     * Type-check the attributes/contents of an <xsl:apply-imports/> element.
     */
    public Type typeCheck(SymbolTable stable) throws TypeCheckError {
        typeCheckContents(stable);              // with-params
        return Type.Void;
    }

    /**
     * Translate call-template. A parameter frame is pushed only if
     * some template in the stylesheet uses parameters.
     */
    public void translate(ClassGenerator classGen, MethodGenerator methodGen) {
        final Stylesheet stylesheet = classGen.getStylesheet();
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();
        final int current = methodGen.getLocalIndex("current");

        // Push the arguments that are passed to applyTemplates()
        il.append(classGen.loadTranslet());
        il.append(methodGen.loadDOM());
    il.append(methodGen.loadIterator());
        il.append(methodGen.loadHandler());
    il.append(methodGen.loadCurrentNode());

        // Push a new parameter frame in case imported template might expect
        // parameters.  The apply-imports has nothing that it can pass.
        if (stylesheet.hasLocalParams()) {
            il.append(classGen.loadTranslet());
            final int pushFrame = cpg.addMethodref(TRANSLET_CLASS,
                                                   PUSH_PARAM_FRAME,
                                                   PUSH_PARAM_FRAME_SIG);
            il.append(new INVOKEVIRTUAL(pushFrame));
        }

        // Get the [min,max> precedence of all templates imported under the
        // current stylesheet
        final int maxPrecedence = _precedence;
        final int minPrecedence = getMinPrecedence(maxPrecedence);
        final Mode mode = stylesheet.getMode(_modeName);

        // Get name of appropriate apply-templates function for this
        // xsl:apply-imports instruction
        String functionName = mode.functionName(minPrecedence, maxPrecedence);

        // Construct the translet class-name and the signature of the method
        final String className = classGen.getStylesheet().getClassName();
        final String signature = classGen.getApplyTemplatesSigForImport();
        final int applyTemplates = cpg.addMethodref(className,
                                                    functionName,
                                                    signature);
        il.append(new INVOKEVIRTUAL(applyTemplates));

        // Pop any parameter frame that was pushed above.
        if (stylesheet.hasLocalParams()) {
            il.append(classGen.loadTranslet());
            final int pushFrame = cpg.addMethodref(TRANSLET_CLASS,
                                                   POP_PARAM_FRAME,
                                                   POP_PARAM_FRAME_SIG);
            il.append(new INVOKEVIRTUAL(pushFrame));
        }
    }

}
