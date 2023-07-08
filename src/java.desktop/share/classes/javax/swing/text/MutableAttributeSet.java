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

package java.desktop.share.classes.javax.swing.text;


import java.util.Enumeration;















/**
 * A generic interface for a mutable collection of unique attributes.
 *
 * Implementations will probably want to provide a constructor of the
 * form: <pre>{@code
 * public XXXAttributeSet(ConstAttributeSet source);}</pre>
 *
 */
public interface MutableAttributeSet extends AttributeSet {

    /**
     * Creates a new attribute set similar to this one except that it contains
     * an attribute with the given name and value.  The object must be
     * immutable, or not mutated by any client.
     *
     * @param name the name
     * @param value the value
     */
    void addAttribute(Object name, Object value);

    /**
     * Creates a new attribute set similar to this one except that it contains
     * the given attributes and values.
     *
     * @param attributes the set of attributes
     */
    void addAttributes(AttributeSet attributes);

    /**
     * Removes an attribute with the given {@code name}.
     *
     * @param name the attribute name
     */
    void removeAttribute(Object name);

    /**
     * Removes an attribute set with the given {@code names}.
     *
     * @param names the set of names
     */
    void removeAttributes(Enumeration<?> names);

    /**
     * Removes a set of attributes with the given {@code name}.
     *
     * @param attributes the set of attributes
     */
    void removeAttributes(AttributeSet attributes);

    /**
     * Sets the resolving parent.  This is the set
     * of attributes to resolve through if an attribute
     * isn't defined locally.
     *
     * @param parent the parent
     */
    void setResolveParent(AttributeSet parent);

}
