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

package java.compiler.share.classes.javax.lang.model.type;

import java.util.java.util.java.util.java.util.List;
import java.compiler.share.classes.javax.lang.model.element.Element;
import java.compiler.share.classes.javax.lang.model.element.TypeElement;
import java.compiler.share.classes.javax.lang.model.util.Types;

/**
 * Represents a declared type, either a class type or an interface type.
 * This includes parameterized types such as {@code java.util.Set<String>}
 * as well as raw types.
 *
 * <p> While a {@code TypeElement} represents a class or interface
 * <i>element</i>, a {@code DeclaredType} represents a class
 * or interface <i>type</i>, the latter being a use
 * (or <i>invocation</i>) of the former.
 * See {@link TypeElement} for more on this distinction.
 *
 * <p> The supertypes (both class and interface types) of a declared
 * type may be found using the {@link
 * Types#directSupertypes(TypeMirror)} method.  This returns the
 * supertypes with any type arguments substituted in.
 *
 * @see TypeElement
 */
public interface DeclaredType extends ReferenceType {

    /**
     * {@return the element corresponding to this type}
     */
    Element asElement();

    /**
     * Returns the type of the innermost enclosing instance or a
     * {@code NoType} of kind {@code NONE} if there is no enclosing
     * instance.  Only types corresponding to inner classes have an
     * enclosing instance.
     *
     * @return a type mirror for the enclosing type
     * @jls 8.1.3 Inner Classes and Enclosing Instances
     * @jls 15.9.2 Determining Enclosing Instances
     */
    TypeMirror getEnclosingType();

    /**
     * Returns the actual type arguments of this type.
     * For a type nested within a parameterized type
     * (such as {@code Outer<String>.Inner<Number>}), only the type
     * arguments of the innermost type are included.
     *
     * @return the actual type arguments of this type, or an empty list
     *           if none
     */
    List<? extends TypeMirror> getTypeArguments();
}
