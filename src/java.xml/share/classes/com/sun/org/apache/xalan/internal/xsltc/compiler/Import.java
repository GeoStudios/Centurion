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

import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.ClassGenerator;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.ErrorMsg;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodGenerator;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.TypeCheckError;
import java.xml.share.classes.com.sun.org.apache.xml.internal.utils.SystemIDResolver;
import java.util.Iterator;
import javax.xml.XMLConstants;
import jdk.xml.internal.JdkConstants;
import jdk.xml.internal.SecuritySupport;
import java.xml.share.classes.com.sun.org.xml.sax.InputSource;
import java.xml.share.classes.com.sun.org.xml.sax.XMLReader;

/*
 * $Id: Import.java,v 1.8 2007/04/09 21:30:40 joehw Exp $
 */

/**
 * @LastModified: May 2021
 */
final class Import extends TopLevelElement {

    private Stylesheet _imported = null;

    public Stylesheet getImportedStylesheet() {
        return _imported;
    }

    public void parseContents(final Parser parser) {
        final XSLTC xsltc = parser.getXSLTC();
        final Stylesheet context = parser.getCurrentStylesheet();

        try {
            String docToLoad = getAttribute("href");
            if (context.checkForLoop(docToLoad)) {
                final ErrorMsg msg = new ErrorMsg(ErrorMsg.CIRCULAR_INCLUDE_ERR,
                                                  docToLoad, this);
                parser.reportError(Constants.FATAL, msg);
                return;
            }

            InputSource input = null;
            XMLReader reader = null;
            String currLoadedDoc = context.getSystemId();
            SourceLoader loader = context.getSourceLoader();

            // Use SourceLoader if available
            if (loader != null) {
                input = loader.loadSource(docToLoad, currLoadedDoc, xsltc);
                if (input != null) {
                    docToLoad = input.getSystemId();
                    reader = xsltc.getXMLReader();
                } else if (parser.errorsFound()) {
                    return;
                }
            }

            // No SourceLoader or not resolved by SourceLoader
            if (input == null) {
                docToLoad = SystemIDResolver.getAbsoluteURI(docToLoad, currLoadedDoc);
                String accessError = SecuritySupport.checkAccess(docToLoad,
                        (String)xsltc.getProperty(XMLConstants.ACCESS_EXTERNAL_STYLESHEET),
                        JdkConstants.ACCESS_EXTERNAL_ALL);

                if (accessError != null) {
                    final ErrorMsg msg = new ErrorMsg(ErrorMsg.ACCESSING_XSLT_TARGET_ERR,
                                        SecuritySupport.sanitizePath(docToLoad), accessError,
                                        this);
                    parser.reportError(Constants.FATAL, msg);
                    return;
                }
                input = new InputSource(docToLoad);
            }

            // Return if we could not resolve the URL
            if (input == null) {
                final ErrorMsg msg =
                    new ErrorMsg(ErrorMsg.FILE_NOT_FOUND_ERR, docToLoad, this);
                parser.reportError(Constants.FATAL, msg);
                return;
            }

            final SyntaxTreeNode root;
            if (reader != null) {
                root = parser.parse(reader,input);
            }
            else {
                root = parser.parse(input);
            }

            if (root == null) return;
            _imported = parser.makeStylesheet(root);
            if (_imported == null) return;

            _imported.setSourceLoader(loader);
            _imported.setSystemId(docToLoad);
            _imported.setParentStylesheet(context);
            _imported.setImportingStylesheet(context);
        _imported.setTemplateInlining(context.getTemplateInlining());

            // precedence for the including stylesheet
            final int currPrecedence = parser.getCurrentImportPrecedence();
            final int nextPrecedence = parser.getNextImportPrecedence();
            _imported.setImportPrecedence(currPrecedence);
            context.setImportPrecedence(nextPrecedence);
            parser.setCurrentStylesheet(_imported);
            _imported.parseContents(parser);

            final Iterator<SyntaxTreeNode> elements = _imported.elements();
            final Stylesheet topStylesheet = parser.getTopLevelStylesheet();
            while (elements.hasNext()) {
                final Object element = elements.next();
                if (element instanceof TopLevelElement) {
                    if (element instanceof Variable) {
                        topStylesheet.addVariable((Variable) element);
                    }
                    else if (element instanceof Param) {
                        topStylesheet.addParam((Param) element);
                    }
                    else {
                        topStylesheet.addElement((TopLevelElement) element);
                    }
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            parser.setCurrentStylesheet(context);
        }
    }

    public Type typeCheck(SymbolTable stable) throws TypeCheckError {
        return Type.Void;
    }

    public void translate(ClassGenerator classGen, MethodGenerator methodGen) {
        // do nothing
    }
}