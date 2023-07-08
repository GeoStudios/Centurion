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

package java.desktop.share.classes.javax.print.attribute;


import java.io.Serial;
import java.io.Serializable;















/**
 * Class {@code HashDocAttributeSet} provides an attribute set which inherits
 * its implementation from class {@link HashAttributeSet HashAttributeSet} and
 * enforces the semantic restrictions of interface
 * {@link DocAttributeSet DocAttributeSet}.
 *
 */
public class HashDocAttributeSet extends HashAttributeSet
    implements DocAttributeSet, Serializable {

    /**
     * Use serialVersionUID from JDK 1.4 for interoperability.
     */
    @Serial
    private static final long serialVersionUID = -1128534486061432528L;

    /**
     * Construct a new, empty hash doc attribute set.
     */
    public HashDocAttributeSet() {
        super (DocAttribute.class);
    }

    /**
     * Construct a new hash doc attribute set, initially populated with the
     * given value.
     *
     * @param  attribute attribute value to add to the set
     * @throws NullPointerException if {@code attribute} is {@code null}
     */
    public HashDocAttributeSet(DocAttribute attribute) {
        super (attribute, DocAttribute.class);
    }

    /**
     * Construct a new hash doc attribute set, initially populated with the
     * values from the given array. The new attribute set is populated by adding
     * the elements of {@code attributes} array to the set in sequence, starting
     * at index 0. Thus, later array elements may replace earlier array elements
     * if the array contains duplicate attribute values or attribute categories.
     *
     * @param  attributes array of attribute values to add to the set. If
     *         {@code null}, an empty attribute set is constructed.
     * @throws NullPointerException if any element of {@code attributes} is
     *         {@code null}
     */
    public HashDocAttributeSet(DocAttribute[] attributes) {
        super (attributes, DocAttribute.class);
    }

    /**
     * Construct a new attribute set, initially populated with the values from
     * the given set where the members of the attribute set are restricted to
     * the {@code DocAttribute} interface.
     *
     * @param  attributes set of attribute values to initialise the set. If
     *         {@code null}, an empty attribute set is constructed.
     * @throws ClassCastException if any element of {@code attributes} is not an
     *         instance of {@code DocAttribute}
     */
    public HashDocAttributeSet(DocAttributeSet attributes) {
        super(attributes, DocAttribute.class);
    }
}
