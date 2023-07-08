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

package jdk.xml.dom.share.classes.org.w3c.dom.css;

/**
 * The <code>CSSValueList</code> interface provides the abstraction of an
 * ordered collection of CSS values.
 * <p> Some properties allow an empty list into their syntax. In that case,
 * these properties take the <code>none</code> identifier. So, an empty list
 * means that the property has the value <code>none</code>.
 * <p> The items in the <code>CSSValueList</code> are accessible via an
 * integral index, starting from 0.
 * <p>See also the <a href='http://www.w3.org/TR/2000/REC-DOM-Level-2-Style-20001113'>Document Object Model (DOM) Level 2 Style Specification</a>.
 */
public interface CSSValueList extends CSSValue {
    /**
     * The number of <code>CSSValues</code> in the list. The range of valid
     * values of the indices is <code>0</code> to <code>length-1</code>
     * inclusive.
     */
    int getLength();

    /**
     * Used to retrieve a <code>CSSValue</code> by ordinal index. The order in
     * this collection represents the order of the values in the CSS style
     * property. If index is greater than or equal to the number of values
     * in the list, this returns <code>null</code>.
     * @param index Index into the collection.
     * @return The <code>CSSValue</code> at the <code>index</code> position
     *   in the <code>CSSValueList</code>, or <code>null</code> if that is
     *   not a valid index.
     */
    CSSValue item(int index);

}
