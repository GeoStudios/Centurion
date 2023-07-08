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

package java.compiler.share.classes.javax.lang.model.element;

















/**
 * Represents a value of an annotation interface element.
 * A value is of one of the following types:
 * <ul><li> a wrapper class (such as {@link Integer}) for a primitive type
 *     <li> {@code String}
 *     <li> {@code TypeMirror}
 *     <li> {@code VariableElement} (representing an enum constant)
 *     <li> {@code AnnotationMirror}
 *     <li> {@code List<? extends AnnotationValue>}
 *              (representing the elements, in declared order, if the value is an array)
 * </ul>
 *
 */
public interface AnnotationValue {

    /**
     * {@return the value}
     */
    Object getValue();

    /**
     * {@return a string representation of this value}
     * This is returned in a form suitable for representing this value
     * in the source code of an annotation.
     */
    String toString();

    /**
     * Applies a visitor to this value.
     *
     * @param <R> the return type of the visitor's methods
     * @param <P> the type of the additional parameter to the visitor's methods
     * @param v   the visitor operating on this value
     * @param p   additional parameter to the visitor
     * @return a visitor-specified result
     */
    <R, P> R accept(AnnotationValueVisitor<R, P> v, P p);
}
