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
 * This interface represents the Simple Type Definition schema component. This
 * interface provides several query operations for facet components. Users
 * can either retrieve the defined facets as XML Schema components, using
 * the <code>facets</code> and the <code>multiValueFacets</code> attributes;
 * or users can separately query a facet's properties using methods such as
 * <code>getLexicalFacetValue</code>, <code>isFixedFacet</code>, etc.
 */
public interface XSSimpleTypeDefinition extends XSTypeDefinition {
    // Variety definitions
    /**
     * The variety is absent for the anySimpleType definition.
     */
    short VARIETY_ABSENT            = 0;
    /**
     * <code>Atomic</code> type.
     */
    short VARIETY_ATOMIC            = 1;
    /**
     * <code>List</code> type.
     */
    short VARIETY_LIST              = 2;
    /**
     * <code>Union</code> type.
     */
    short VARIETY_UNION             = 3;

    // Facets
    /**
     * No facets defined.
     */
    short FACET_NONE                = 0;
    /**
     * 4.3.1 Length
     */
    short FACET_LENGTH              = 1;
    /**
     * 4.3.2 minLength.
     */
    short FACET_MINLENGTH           = 2;
    /**
     * 4.3.3 maxLength.
     */
    short FACET_MAXLENGTH           = 4;
    /**
     * 4.3.4 pattern.
     */
    short FACET_PATTERN             = 8;
    /**
     * 4.3.5 whitespace.
     */
    short FACET_WHITESPACE          = 16;
    /**
     * 4.3.7 maxInclusive.
     */
    short FACET_MAXINCLUSIVE        = 32;
    /**
     * 4.3.9 maxExclusive.
     */
    short FACET_MAXEXCLUSIVE        = 64;
    /**
     * 4.3.9 minExclusive.
     */
    short FACET_MINEXCLUSIVE        = 128;
    /**
     * 4.3.10 minInclusive.
     */
    short FACET_MININCLUSIVE        = 256;
    /**
     * 4.3.11 totalDigits .
     */
    short FACET_TOTALDIGITS         = 512;
    /**
     * 4.3.12 fractionDigits.
     */
    short FACET_FRACTIONDIGITS      = 1024;
    /**
     * 4.3.5 enumeration.
     */
    short FACET_ENUMERATION         = 2048;

    /**
     * A constant defined for the 'ordered' fundamental facet: not ordered.
     */
    short ORDERED_FALSE             = 0;
    /**
     * A constant defined for the 'ordered' fundamental facet: partially
     * ordered.
     */
    short ORDERED_PARTIAL           = 1;
    /**
     * A constant defined for the 'ordered' fundamental facet: total ordered.
     */
    short ORDERED_TOTAL             = 2;
    /**
     * [variety]: one of {atomic, list, union} or absent.
     */
    short getVariety();

    /**
     * If variety is <code>atomic</code> the primitive type definition (a
     * built-in primitive datatype definition or the simple ur-type
     * definition) is available, otherwise <code>null</code>.
     */
    XSSimpleTypeDefinition getPrimitiveType();

    /**
     * Returns the closest built-in type category this type represents or
     * derived from. For example, if this simple type is a built-in derived
     * type integer the <code>INTEGER_DV</code> is returned.
     */
    short getBuiltInKind();

    /**
     * If variety is <code>list</code> the item type definition (an atomic or
     * union simple type definition) is available, otherwise
     * <code>null</code>.
     */
    XSSimpleTypeDefinition getItemType();

    /**
     * If variety is <code>union</code> the list of member type definitions (a
     * non-empty sequence of simple type definitions) is available,
     * otherwise an empty <code>XSObjectList</code>.
     */
    XSObjectList getMemberTypes();

    /**
     * [facets]: all facets defined on this type. The value is a bit
     * combination of FACET_XXX constants of all defined facets.
     */
    short getDefinedFacets();

    /**
     * Convenience method. [Facets]: check whether a facet is defined on this
     * type.
     * @param facetName  The name of the facet.
     * @return  True if the facet is defined, false otherwise.
     */
    boolean isDefinedFacet(short facetName);

    /**
     * [facets]: all defined facets for this type which are fixed.
     */
    short getFixedFacets();

    /**
     * Convenience method. [Facets]: check whether a facet is defined and
     * fixed on this type.
     * @param facetName  The name of the facet.
     * @return  True if the facet is fixed, false otherwise.
     */
    boolean isFixedFacet(short facetName);

    /**
     * Convenience method. Returns a value of a single constraining facet for
     * this simple type definition. This method must not be used to retrieve
     * values for <code>enumeration</code> and <code>pattern</code> facets.
     * @param facetName The name of the facet, i.e.
     *   <code>FACET_LENGTH, FACET_TOTALDIGITS</code>.
     *   To retrieve the value for a pattern or
     *   an enumeration, see <code>enumeration</code> and
     *   <code>pattern</code>.
     * @return A value of the facet specified in <code>facetName</code> for
     *   this simple type definition or <code>null</code>.
     */
    String getLexicalFacetValue(short facetName);

    /**
     * A list of enumeration values if it exists, otherwise an empty
     * <code>StringList</code>.
     */
    StringList getLexicalEnumeration();

    /**
     * A list of pattern values if it exists, otherwise an empty
     * <code>StringList</code>.
     */
    StringList getLexicalPattern();

    /**
     *  Fundamental Facet: ordered.
     */
    short getOrdered();

    /**
     * Fundamental Facet: cardinality.
     */
    boolean getFinite();

    /**
     * Fundamental Facet: bounded.
     */
    boolean getBounded();

    /**
     * Fundamental Facet: numeric.
     */
    boolean getNumeric();

    /**
     *  A list of constraining facets if it exists, otherwise an empty
     * <code>XSObjectList</code>. Note: This method must not be used to
     * retrieve values for <code>enumeration</code> and <code>pattern</code>
     * facets.
     */
    XSObjectList getFacets();

    /**
     *  A list of enumeration and pattern constraining facets if it exists,
     * otherwise an empty <code>XSObjectList</code>.
     */
    XSObjectList getMultiValueFacets();

    /**
     * A constraining facet object. An instance of XSFacet or XSMultiValueFacet.
     */
    XSObject getFacet(int facetType);

    /**
     * A sequence of [annotations] or an empty <code>XSObjectList</code>.
     */
    XSObjectList getAnnotations();

}
