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
 * Used for scheme specific parsing and evaluation of an XPointer expression.
 * This interface applies to both ShortHand and SchemeBased XPointer
 * expressions.
 * </p>
 *
 * @xerces.internal
 *
 */
public interface XPointerPart {

    // The start element event
    int EVENT_ELEMENT_START = 0;

    // The end element event
    int EVENT_ELEMENT_END = 1;

    // The empty element event
    int EVENT_ELEMENT_EMPTY = 2;

    /**
     * Provides scheme specific parsing of a XPointer expression i.e.
     * the PointerPart or ShortHandPointer.
     *
     * @param  part A String representing the PointerPart or ShortHandPointer.
     * @throws XNIException Thrown if the PointerPart string does not conform to
     *         the syntax defined by its scheme.
     *
     */
    void parseXPointer(String part) throws XNIException;

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
     * @throws XNIException Thrown to signal an error
     *
     */
    boolean resolveXPointer(QName element, XMLAttributes attributes,
            Augmentations augs, int event) throws XNIException;

    /**
     * Returns true if the XPointer expression resolves to a resource fragment
     * specified as input else returns false.
     *
     * @return True if the xpointer expression matches a fragment in the resource
     *         else returns false.
     * @throws XNIException Thrown to signal an error
     *
     */
    boolean isFragmentResolved() throws XNIException;

    /**
     * Returns true if the XPointer expression resolves to a non-element child
     * of the current resource fragment.
     *
     * @return True if the XPointer expression resolves to a non-element child
     *         of the current resource fragment.
     * @throws XNIException Thrown to signal an error
     *
     */
    boolean isChildFragmentResolved() throws XNIException;

    /**
     * Returns a String containing the scheme name of the PointerPart
     * or the name of the ShortHand Pointer.
     *
     * @return A String containing the scheme name of the PointerPart.
     *
     */
    String getSchemeName();

    /**
     * Returns a String containing the scheme data of the PointerPart.
     *
     * @return A String containing the scheme data of the PointerPart.
     *
     */
    String getSchemeData();

    /**
     * Sets the scheme name of the PointerPart or the ShortHand Pointer name.
     *
     * @param schemeName A String containing the scheme name of the PointerPart.
     *
     */
    void setSchemeName(String schemeName);

    /**
     * Sets the scheme data of the PointerPart.
     *
     * @param schemeData A String containing the scheme data of the PointerPart.
     *
     */
    void setSchemeData(String schemeData);

}
