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
 * This interface represents the Complex Type Definition schema component.
 */
public interface XSComplexTypeDefinition extends XSTypeDefinition {
    // Content Model Types
    /**
     * Represents an empty content type. A content type with the distinguished
     * value empty validates elements with no character or element
     * information item children.
     */
    short CONTENTTYPE_EMPTY         = 0;
    /**
     * Represents a simple content type. A content type which is simple
     * validates elements with character-only children.
     */
    short CONTENTTYPE_SIMPLE        = 1;
    /**
     * Represents an element-only content type. An element-only content type
     * validates elements with children that conform to the supplied content
     * model.
     */
    short CONTENTTYPE_ELEMENT       = 2;
    /**
     * Represents a mixed content type.
     */
    short CONTENTTYPE_MIXED         = 3;

    /**
     * [derivation method]: either <code>DERIVATION_EXTENSION</code>,
     * <code>DERIVATION_RESTRICTION</code>, or <code>DERIVATION_NONE</code>
     * (see <code>XSConstants</code>).
     */
    short getDerivationMethod();

    /**
     * [abstract]: a boolean. Complex types for which <code>abstract</code> is
     * true must not be used as the type definition for the validation of
     * element information items.
     */
    boolean getAbstract();

    /**
     *  A set of attribute uses if it exists, otherwise an empty
     * <code>XSObjectList</code>.
     */
    XSObjectList getAttributeUses();

    /**
     * An attribute wildcard if it exists, otherwise <code>null</code>.
     */
    XSWildcard getAttributeWildcard();

    /**
     * [content type]: one of empty (<code>CONTENTTYPE_EMPTY</code>), a simple
     * type definition (<code>CONTENTTYPE_SIMPLE</code>), mixed (
     * <code>CONTENTTYPE_MIXED</code>), or element-only (
     * <code>CONTENTTYPE_ELEMENT</code>).
     */
    short getContentType();

    /**
     * A simple type definition corresponding to a simple content model,
     * otherwise <code>null</code>.
     */
    XSSimpleTypeDefinition getSimpleType();

    /**
     * A particle for a mixed or element-only content model, otherwise
     * <code>null</code>.
     */
    XSParticle getParticle();

    /**
     * [prohibited substitutions]: a subset of {extension, restriction}
     * @param restriction  Extension or restriction constants (see
     *   <code>XSConstants</code>).
     * @return True if <code>restriction</code> is a prohibited substitution,
     *   otherwise false.
     */
    boolean isProhibitedSubstitution(short restriction);

    /**
     *  [prohibited substitutions]: A subset of {extension, restriction} or
     * <code>DERIVATION_NONE</code> represented as a bit flag (see
     * <code>XSConstants</code>).
     */
    short getProhibitedSubstitutions();

    /**
     * A sequence of [annotations] or an empty <code>XSObjectList</code>.
     */
    XSObjectList getAnnotations();

}
