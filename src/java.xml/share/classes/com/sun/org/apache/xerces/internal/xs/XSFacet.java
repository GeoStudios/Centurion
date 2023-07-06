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

package java.xml.share.classes.com.sun.org.apache.xerces.internal.xs;

















/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */


/**
 * Describes a constraining facet. Enumeration and pattern facets are exposed
 * via <code>XSMultiValueFacet</code> interface.
 */
public interface XSFacet extends XSObject {
    /**
     * The name of the facet, e.g. <code>FACET_LENGTH, FACET_TOTALDIGITS</code>
     *  (see <code>XSSimpleTypeDefinition</code>).
     */
    short getFacetKind();

    /**
     * A value of this facet.
     */
    String getLexicalFacetValue();

    /**
     * If this facet is length, minLength, maxLength, totalDigits, or
     * fractionDigits, and if the value can fit in "int", then return the value
     * of the facet as an int. If the value can't fit, return -1. Use
     * getActualFacetValue() to get the BigInteger representation. For all other
     * facets, return 0.
     */
    int getIntFacetValue();

    /**
     * If this facet is minInclusive, maxInclusive, minExclusive, or
     * maxExclusive, then return the actual value of the facet. If this facet
     * is length, minLength, maxLength, totalDigits, or fractionDigits, then
     * return a BigInteger representation of the value. If this facet is
     * whiteSpace, then return the String representation of the facet.
     */
    Object getActualFacetValue();

    /**
     * [Facets]: check whether a facet is fixed.
     */
    boolean getFixed();

    /**
     * An annotation if it exists, otherwise <code>null</code>. If not null
     * then the first [annotation] from the sequence of annotations.
     */
    XSAnnotation getAnnotation();

    /**
     * A sequence of [annotations] or an empty <code>XSObjectList</code>.
     */
    XSObjectList getAnnotations();
}
