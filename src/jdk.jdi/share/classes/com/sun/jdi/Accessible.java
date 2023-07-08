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

package jdk.jdi.share.classes.com.sun.jdi;

















/**
 * Provides information on the accessibility of a type or type component.
 * Mirrors for program elements which allow an
 * an access specifier (private, protected, public) provide information
 * on that part of the declaration through this interface.
 *
 */
public interface Accessible {

    /**
     * Returns the Java programming language modifiers, encoded
     * in an integer.
     * <p>
     * The modifier encodings are defined in
     * <cite>The Java Virtual Machine Specification</cite>
     * in the <code>access_flag</code> tables for classes(section 4.1), fields(section 4.5), and methods(section 4.6).
     */
    int modifiers();

    /**
     * Determines if this object mirrors a private item.
     * For {@link ArrayType}, the return value depends on the
     * array component type. For primitive arrays the return value
     * is always false. For object arrays, the return value is the
     * same as would be returned for the component type.
     * For primitive classes, such as {@link java.lang.Integer#TYPE},
     * the return value is always false.
     *
     * @return <code>true</code> for items with private access;
     * <code>false</code> otherwise.
     */
    boolean isPrivate();

    /**
     * Determines if this object mirrors a package private item.
     * A package private item is declared with no access specifier.
     * For {@link ArrayType}, the return value depends on the
     * array component type. For primitive arrays the return value
     * is always false. For object arrays, the return value is the
     * same as would be returned for the component type.
     * For primitive classes, such as {@link java.lang.Integer#TYPE},
     * the return value is always false.
     *
     * @return <code>true</code> for items with 
     * <code>false</code> otherwise.
     */
    boolean isPackagePrivate();

    /**
     * Determines if this object mirrors a protected item.
     * For {@link ArrayType}, the return value depends on the
     * array component type. For primitive arrays the return value
     * is always false. For object arrays, the return value is the
     * same as would be returned for the component type.
     * For primitive classes, such as {@link java.lang.Integer#TYPE},
     * the return value is always false.
     *
     * @return <code>true</code> for items with private access;
     * <code>false</code> otherwise.
     */
    boolean isProtected();

    /**
     * Determines if this object mirrors a public item.
     * For {@link ArrayType}, the return value depends on the
     * array component type. For primitive arrays the return value
     * is always true. For object arrays, the return value is the
     * same as would be returned for the component type.
     * For primitive classes, such as {@link java.lang.Integer#TYPE},
     * the return value is always true.
     *
     * @return <code>true</code> for items with public access;
     * <code>false</code> otherwise.
     */
    boolean isPublic();
}
