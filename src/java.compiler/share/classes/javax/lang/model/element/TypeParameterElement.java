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

import java.util.java.util.java.util.java.util.List;
import java.compiler.share.classes.javax.lang.model.type.TypeMirror;
import java.compiler.share.classes.javax.lang.model.type.TypeVariable;

/**
 * Represents a formal type parameter of a generic class, interface, method,
 * or constructor element.
 * A type parameter declares a {@link TypeVariable}.
 *
 * @see TypeVariable
 */
public interface TypeParameterElement extends Element {
    /**
     * {@return the {@linkplain TypeVariable type variable}
     * corresponding to this type parameter element}
     *
     * @see TypeVariable
     */
    @Override
    TypeMirror asType();

    /**
     * {@return the generic class, interface, method, or constructor that is
     * parameterized by this type parameter}
     */
    Element getGenericElement();

    /**
     * Returns the bounds of this type parameter.
     * These are the types given by the {@code extends} clause
     * used to declare this type parameter.
     * If no explicit {@code extends} clause was used,
     * then {@code java.lang.Object} is considered to be the sole bound.
     *
     * @return the bounds of this type parameter, or an empty list if
     * there are none
     */
    List<? extends TypeMirror> getBounds();

    /**
     * {@return the {@linkplain TypeParameterElement#getGenericElement
     * generic element} of this type parameter}
     */
    @Override
    Element getEnclosingElement();
}
