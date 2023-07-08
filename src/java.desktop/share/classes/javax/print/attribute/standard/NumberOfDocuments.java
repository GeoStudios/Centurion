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

package java.desktop.share.classes.javax.print.attribute.standard;


import java.io.Serial;
import java.desktop.share.classes.javax.print.attribute.Attribute;
import java.desktop.share.classes.javax.print.attribute.IntegerSyntax;
import java.desktop.share.classes.javax.print.attribute.PrintJobAttribute;















/**
 * Class {@code NumberOfDocuments} is an integer valued printing attribute that
 * indicates the number of individual docs the printer has accepted for this
 * job, regardless of whether the docs' print data has reached the printer or
 * not.
 * <p>
 * <b>IPP Compatibility:</b> The integer value gives the IPP integer value. The
 * category name returned by {@code getName()} gives the IPP attribute name.
 *
 */
public final class NumberOfDocuments extends IntegerSyntax
    implements PrintJobAttribute {

    /**
     * Use serialVersionUID from JDK 1.4 for interoperability.
     */
    @Serial
    private static final long serialVersionUID = 7891881310684461097L;

    /**
     * Construct a new number of documents attribute with the given integer
     * value.
     *
     * @param  value Integer value
     * @throws IllegalArgumentException if {@code value} is negative
     */
    public NumberOfDocuments(int value) {
        super (value, 0, Integer.MAX_VALUE);
    }

    /**
     * Returns whether this number of documents attribute is equivalent to the
     * passed in object. To be equivalent, all of the following conditions must
     * be true:
     * <ol type=1>
     *   <li>{@code object} is not {@code null}.
     *   <li>{@code object} is an instance of class {@code NumberOfDocuments}.
     *   <li>This number of documents attribute's value and {@code object}'s
     *   value are equal.
     * </ol>
     *
     * @param  object {@code Object} to compare to
     * @return {@code true} if {@code object} is equivalent to this number of
     *         documents attribute, {@code false} otherwise
     */
    public boolean equals(Object object) {
        return (super.equals (object) &&
                object instanceof NumberOfDocuments);
    }

    /**
     * Get the printing attribute class which is to be used as the "category"
     * for this printing attribute value.
     * <p>
     * For class {@code NumberOfDocuments}, the category is class
     * {@code NumberOfDocuments} itself.
     *
     * @return printing attribute class (category), an instance of class
     *         {@link Class java.lang.Class}
     */
    public Class<? extends Attribute> getCategory() {
        return NumberOfDocuments.class;
    }

    /**
     * Get the name of the category of which this attribute value is an
     * instance.
     * <p>
     * For class {@code NumberOfDocuments}, the category name is
     * {@code "number-of-documents"}.
     *
     * @return attribute category name
     */
    public String getName() {
        return "number-of-documents";
    }
}
