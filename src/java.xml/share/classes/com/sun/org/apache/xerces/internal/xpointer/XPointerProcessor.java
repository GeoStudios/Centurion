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

package java.xml.share.classes.com.sun.org.apache.xerces.internal.xpointer;


import java.xml.share.classes.com.sun.org.apache.xerces.internal.xni.Augmentations;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.xni.QName;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.xni.XMLAttributes;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.xni.XNIException;















/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */



/**
 * <p>
 * The XPointerProcessor is responsible for parsing an XPointer
 * expression and and providing scheme specific resolution of
 * the document fragment pointed to be the pointer.
 * </p>
 *
 * @xerces.internal
 *
 */
public interface XPointerProcessor {

    // The start element event
    int EVENT_ELEMENT_START = 0;

    // The end element event
    int EVENT_ELEMENT_END = 1;

    // The empty element event
    int EVENT_ELEMENT_EMPTY = 2;

    /**
     * Parses an XPointer expression.  It performs scheme specific processing
     * depending on the pointer parts and sets up a Vector of XPointerParts
     * in the order (left-to-right) they appear in the XPointer expression.
     *
     * @param  xpointer A String representing the xpointer expression.
     * @throws XNIException Thrown if the xpointer string does not conform to
     *         the XPointer Framework syntax or the syntax of the pointer part does
     *         not conform to its definition for its scheme.
     */
    void parseXPointer(String xpointer) throws XNIException;

    /**
     * Evaluates an XML resource with respect to an XPointer expressions
     * by checking if it's element and attributes parameters match the
     * criteria specified in the xpointer expression.
     *
     * @param element - The name of the element.
     * @param attributes - The element attributes.
     * @param augs - Additional information that may include infoset augmentations
     * @param event - An integer indicating
     *                0 - The start of an element
     *                1 - The end of an element
     *                2 - An empty element call
     * @return true if the element was resolved by the xpointer
     * @throws XNIException Thrown to signal an error
     */
    boolean resolveXPointer(QName element, XMLAttributes attributes,
            Augmentations augs, int event) throws XNIException;

    /**
     * Returns true if the XPointer expression resolves to the current resource fragment
     * or Node which is part of the input resource being streamed else returns false.
     *
     * @return True if the xpointer expression matches a node/fragment in the resource
     *         else returns false.
     * @throws XNIException Thrown to signal an error
     */
    boolean isFragmentResolved() throws XNIException;

    /**
     * Returns true if the XPointer expression resolves any subresource of the
     * input resource.
     *
     * @return True if the xpointer expression matches a fragment in the resource
     *         else returns false.
     * @throws XNIException Thrown to signal an error
     */
    boolean isXPointerResolved() throws XNIException;

}
