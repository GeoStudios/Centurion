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

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
package com.sun.org.apache.xerces.internal.xs;

/**
 * This interface represents a complex or simple type definition.
 */
public interface XSTypeDefinition extends XSObject {
    /**
     * The object describes a complex type.
     */
    short COMPLEX_TYPE              = 15;
    /**
     * The object describes a simple type.
     */
    short SIMPLE_TYPE               = 16;
    /**
     * Return whether this type definition is a simple type or complex type.
     */
    short getTypeCategory();

    /**
     * {base type definition}: either a simple type definition or a complex
     * type definition.
     */
    XSTypeDefinition getBaseType();

    /**
     * {final}. For a complex type definition it is a subset of {extension,
     * restriction}. For a simple type definition it is a subset of
     * {extension, list, restriction, union}.
     * @param restriction  Extension, restriction, list, union constants
     *   (defined in <code>XSConstants</code>).
     * @return True if <code>restriction</code> is in the final set,
     *   otherwise false.
     */
    boolean isFinal(short restriction);

    /**
     * For complex types the returned value is a bit combination of the subset
     * of {<code>DERIVATION_EXTENSION, DERIVATION_RESTRICTION</code>}
     * corresponding to <code>final</code> set of this type or
     * <code>DERIVATION_NONE</code>. For simple types the returned value is
     * a bit combination of the subset of {
     * <code>DERIVATION_RESTRICTION, DERIVATION_EXTENSION, DERIVATION_UNION, DERIVATION_LIST</code>
     * } corresponding to <code>final</code> set of this type or
     * <code>DERIVATION_NONE</code>.
     */
    short getFinal();

    /**
     *  Convenience attribute. A boolean that specifies if the type definition
     * is anonymous.
     */
    boolean getAnonymous();

    /**
     * Convenience method which checks if this type is derived from the given
     * <code>ancestorType</code>.
     * @param ancestorType  An ancestor type definition.
     * @param derivationMethod  A bit combination representing a subset of {
     *   <code>DERIVATION_RESTRICTION, DERIVATION_EXTENSION, DERIVATION_UNION, DERIVATION_LIST</code>
     *   }.
     * @return  True if this type is derived from <code>ancestorType</code>
     *   using only derivation methods from the <code>derivationMethod</code>
     *   .
     */
    boolean derivedFromType(XSTypeDefinition ancestorType,
                                   short derivationMethod);

    /**
     * Convenience method which checks if this type is derived from the given
     * ancestor type.
     * @param namespace  An ancestor type namespace.
     * @param name  An ancestor type name.
     * @param derivationMethod  A bit combination representing a subset of {
     *   <code>DERIVATION_RESTRICTION, DERIVATION_EXTENSION, DERIVATION_UNION, DERIVATION_LIST</code>
     *   }.
     * @return  True if this type is derived from <code>ancestorType</code>
     *   using only derivation methods from the <code>derivationMethod</code>
     *   .
     */
    boolean derivedFrom(String namespace,
                               String name,
                               short derivationMethod);

}
