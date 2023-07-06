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

package java.xml.share.classes.com.sun.org.apache.xerces.internal.jaxp.validation;

import javax.xml.transform.dom.DOMResult;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.xni.XNIException;
import java.xml.share.classes.com.sun.org.w3c.dom.CDATASection;
import java.xml.share.classes.com.sun.org.w3c.dom.Comment;
import java.xml.share.classes.com.sun.org.w3c.dom.DocumentType;
import java.xml.share.classes.com.sun.org.w3c.dom.ProcessingInstruction;
import java.xml.share.classes.com.sun.org.w3c.dom.Text;

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */

/**
 * <p>An extension to XMLDocumentHandler for building DOM structures.</p>
 *
 */
interface DOMDocumentHandler extends XMLDocumentHandler {

    /**
     * <p>Sets the <code>DOMResult</code> object which
     * receives the constructed DOM nodes.</p>
     *
     * @param result the object which receives the constructed DOM nodes
     */
    void setDOMResult(DOMResult result);

    /**
     * A document type declaration.
     *
     * @param node a DocumentType node
     *
     * @exception XNIException Thrown by handler to signal an error.
     */
    void doctypeDecl(DocumentType node) throws XNIException;

    void characters(Text node) throws XNIException;

    void cdata(CDATASection node) throws XNIException;

    /**
     * A comment.
     *
     * @param node a Comment node
     *
     * @exception XNIException Thrown by application to signal an error.
     */
    void comment(Comment node) throws XNIException;

    /**
     * A processing instruction. Processing instructions consist of a
     * target name and, optionally, text data. The data is only meaningful
     * to the application.
     * <p>
     * Typically, a processing instruction's data will contain a series
     * of pseudo-attributes. These pseudo-attributes follow the form of
     * element attributes but are <strong>not</strong> parsed or presented
     * to the application as anything other than text. The application is
     * responsible for parsing the data.
     *
     * @param node a ProcessingInstruction node
     *
     * @exception XNIException Thrown by handler to signal an error.
     */
    void processingInstruction(ProcessingInstruction node) throws XNIException;

    void setIgnoringCharacters(boolean ignore);
}
