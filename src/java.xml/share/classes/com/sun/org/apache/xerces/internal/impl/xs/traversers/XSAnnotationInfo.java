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

package java.xml.share.classes.com.sun.org.apache.xerces.internal.impl.xs.traversers;


import java.xml.share.classes.com.sun.org.w3c.dom.Element;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.impl.xs.opti.ElementImpl;















/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */



/**
 * Objects of this class contain the textual representation of
 * an XML schema annotation as well as information on the location
 * of the annotation in the document it originated from.
 *
 * @xerces.internal
 *
 */
final class XSAnnotationInfo {

    /** Textual representation of annotation. **/
    String fAnnotation;

    /** Line number of &lt;annotation&gt; element. **/
    int fLine;

    /** Column number of &lt;annotation&gt; element. **/
    int fColumn;

    /** Character offset of &lt;annotation&gt; element. **/
    int fCharOffset;

    /** Next annotation. **/
    XSAnnotationInfo next;

    XSAnnotationInfo(String annotation, int line, int column, int charOffset) {
        fAnnotation = annotation;
        fLine = line;
        fColumn = column;
        fCharOffset = charOffset;
    }

    XSAnnotationInfo(String annotation, Element annotationDecl) {
        fAnnotation = annotation;
        if (annotationDecl instanceof ElementImpl annotationDeclImpl) {
            fLine = annotationDeclImpl.getLineNumber();
            fColumn = annotationDeclImpl.getColumnNumber();
            fCharOffset = annotationDeclImpl.getCharacterOffset();
        }
        else {
            fLine = -1;
            fColumn = -1;
            fCharOffset = -1;
        }
    }
} // XSAnnotationInfo
