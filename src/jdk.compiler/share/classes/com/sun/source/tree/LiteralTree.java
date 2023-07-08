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

package jdk.compiler.share.classes.com.sun.source.tree;

/**
 * A tree node for a literal expression.
 * Use {@link #getKind getKind} to determine the kind of literal.
 *
 * For example:
 * <pre>
 *   <em>value</em>
 * </pre>
 *
 * @jls 15.28 Constant Expressions
 *
 */
public interface LiteralTree extends ExpressionTree {
    /**
     * Returns the value of the literal expression.
     * The value will be a boxed primitive value, a String, or {@code null}.
     * @return the value
     */
    Object getValue();
}
