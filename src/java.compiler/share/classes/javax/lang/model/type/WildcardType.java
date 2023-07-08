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

/**
 * Represents a wildcard type argument.
 * Examples include:    <pre><code>
 *   ?
 *   ? extends Number
 *   ? super T
 * </code></pre>
 *
 * <p> A wildcard may have its upper bound explicitly set by an
 * {@code extends} clause, its lower bound explicitly set by a
 * {@code super} clause, or neither (but not both).
 *
 * @jls 4.5.1 Type Arguments of Parameterized Types
 */
public interface WildcardType extends TypeMirror {

    /**
     * {@return the upper bound of this wildcard}
     * If no upper bound is explicitly declared,
     * {@code null} is returned.
     */
    TypeMirror getExtendsBound();

    /**
     * {@return the lower bound of this wildcard}
     * If no lower bound is explicitly declared,
     * {@code null} is returned.
     */
    TypeMirror getSuperBound();
}
