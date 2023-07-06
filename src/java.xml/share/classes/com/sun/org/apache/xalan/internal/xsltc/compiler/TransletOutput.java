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
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.INVOKEVIRTUAL;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.Instructionjava.util.java.util.java.util.List;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.PUSH;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.ClassGenerator;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.ErrorMsg;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodGenerator;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.StringType;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.TypeCheckError;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.Util;
import jdk.xml.internal.JdkXmlFeatures;

/**
 */
final class TransletOutput extends Instruction {

    private Expression _filename;
    private boolean _append;

    /**
     * Displays the contents of this <xsltc:output> element.
     */
    public void display(int indent) {
        indent(indent);
        Util.println("TransletOutput: " + _filename);
    }

    /**
     * Parse the contents of this <xsltc:output> element. The only attribute
     * we recognise is the 'file' attribute that contains teh output filename.
     */
    public void parseContents(Parser parser) {
        // Get the output filename from the 'file' attribute
        String filename = getAttribute("file");

        // If the 'append' attribute is set to "yes" or "true",
        // the output is appended to the file.
        String append   = getAttribute("append");

        // Verify that the filename is in fact set
        if ((filename == null) || (filename.equals(EMPTYSTRING))) {
            reportError(this, parser, ErrorMsg.REQUIRED_ATTR_ERR, "file");
        }

        // Save filename as an attribute value template
        _filename = AttributeValue.create(this, filename, parser);

        _append = append != null && (append.equalsIgnoreCase("yes") ||
                append.equalsIgnoreCase("true"));

        parseChildren(parser);
    }

    /**
     * Type checks the 'file' attribute (must be able to convert it to a str).
     */
    public Type typeCheck(SymbolTable stable) throws TypeCheckError {
        final Type type = _filename.typeCheck(stable);
        if (!(type instanceof StringType)) {
            _filename = new CastExpr(_filename, Type.String);
        }
        typeCheckContents(stable);
        return Type.Void;
    }

    /**
     * Compile code that opens the give file for output, dumps the contents of
     * the element to the file, then closes the file.
     */
    public void translate(ClassGenerator classGen, MethodGenerator methodGen) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();
        final boolean isSecureProcessing = classGen.getParser().getXSLTC()
                                           .isSecureProcessing();
        final boolean isExtensionFunctionEnabled = classGen.getParser().getXSLTC()
                .getFeature(JdkXmlFeatures.XmlFeature.ENABLE_EXTENSION_FUNCTION);

        if (isSecureProcessing && !isExtensionFunctionEnabled) {
            int index = cpg.addMethodref(BASIS_LIBRARY_CLASS,
                                         "unallowed_extension_elementF",
                                         "(Ljava/lang/String;)V");
            il.append(new PUSH(cpg, "redirect"));
            il.append(new INVOKESTATIC(index));
            return;
        }

        // Save the current output handler on the stack
        il.append(methodGen.loadHandler());

        final int open =  cpg.addMethodref(TRANSLET_CLASS,
                                           "openOutputHandler",
                                           "(" + STRING_SIG + "Z)" +
                                           TRANSLET_OUTPUT_SIG);

        final int close =  cpg.addMethodref(TRANSLET_CLASS,
                                            "closeOutputHandler",
                                            "("+TRANSLET_OUTPUT_SIG+")V");

        // Create the new output handler (leave it on stack)
        il.append(classGen.loadTranslet());
        _filename.translate(classGen, methodGen);
        il.append(new PUSH(cpg, _append));
        il.append(new INVOKEVIRTUAL(open));

        // Overwrite current handler
        il.append(methodGen.storeHandler());

        // Translate contents with substituted handler
        translateContents(classGen, methodGen);

        // Close the output handler (close file)
        il.append(classGen.loadTranslet());
        il.append(methodGen.loadHandler());
        il.append(new INVOKEVIRTUAL(close));

        // Restore old output handler from stack
        il.append(methodGen.storeHandler());
    }
}
