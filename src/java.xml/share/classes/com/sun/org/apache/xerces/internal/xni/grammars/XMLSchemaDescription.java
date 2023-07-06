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

package java.xml.share.classes.com.sun.org.apache.xerces.internal.xni.grammars;

import java.xml.share.classes.com.sun.org.apache.xerces.internal.xni.QName;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.xni.XMLAttributes;

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */

/**
 * All information specific to XML Schema grammars.
 *
 *
 */
public interface XMLSchemaDescription extends XMLGrammarDescription {

    // used to indicate what triggered the call
    /**
     * Indicate that the current schema document is &lt;include&gt;d by another
     * schema document.
     */
    short CONTEXT_INCLUDE   = 0;
    /**
     * Indicate that the current schema document is &lt;redefine&gt;d by another
     * schema document.
     */
    short CONTEXT_REDEFINE  = 1;
    /**
     * Indicate that the current schema document is &lt;import&gt;ed by another
     * schema document.
     */
    short CONTEXT_IMPORT    = 2;
    /**
     * Indicate that the current schema document is being preparsed.
     */
    short CONTEXT_PREPARSE  = 3;
    /**
     * Indicate that the parse of the current schema document is triggered
     * by xsi:schemaLocation/noNamespaceSchemaLocation attribute(s) in the
     * instance document. This value is only used if we don't defer the loading
     * of schema documents.
     */
    short CONTEXT_INSTANCE  = 4;
    /**
     * Indicate that the parse of the current schema document is triggered by
     * the occurrence of an element whose namespace is the target namespace
     * of this schema document. This value is only used if we do defer the
     * loading of schema documents until a component from that namespace is
     * referenced from the instance.
     */
    short CONTEXT_ELEMENT   = 5;
    /**
     * Indicate that the parse of the current schema document is triggered by
     * the occurrence of an attribute whose namespace is the target namespace
     * of this schema document. This value is only used if we do defer the
     * loading of schema documents until a component from that namespace is
     * referenced from the instance.
     */
    short CONTEXT_ATTRIBUTE = 6;
    /**
     * Indicate that the parse of the current schema document is triggered by
     * the occurrence of an "xsi:type" attribute, whose value (a QName) has
     * the target namespace of this schema document as its namespace.
     * This value is only used if we do defer the loading of schema documents
     * until a component from that namespace is referenced from the instance.
     */
    short CONTEXT_XSITYPE   = 7;

    /**
     * Get the context. The returned value is one of the pre-defined
     * CONTEXT_xxx constants.
     *
     * @return  the value indicating the context
     */
    short getContextType();

    /**
     * If the context is "include" or "redefine", then return the target
     * namespace of the enclosing schema document; otherwise, the expected
     * target namespace of this document.
     *
     * @return  the expected/enclosing target namespace
     */
    String getTargetNamespace();

    /**
     * For import and references from the instance document, it's possible to
     * have multiple hints for one namespace. So this method returns an array,
     * which contains all location hints.
     *
     * @return  an array of all location hints associated to the expected
     *          target namespace
     */
    String[] getLocationHints();

    /**
     * If a call is triggered by an element/attribute/xsi:type in the instance,
     * this call returns the name of such triggering component: the name of
     * the element/attribute, or the value of the xsi:type.
     *
     * @return  the name of the triggering component
     */
    QName getTriggeringComponent();

    /**
     * If a call is triggered by an attribute or xsi:type, then this method
     * returns the enclosing element of such element.
     *
     * @return  the name of the enclosing element
     */
    QName getEnclosingElementName();

    /**
     * If a call is triggered by an element/attribute/xsi:type in the instance,
     * this call returns all attribute of such element (or enclosing element).
     *
     * @return  all attributes of the tiggering/enclosing element
     */
    XMLAttributes getAttributes();

} // XSDDescription
