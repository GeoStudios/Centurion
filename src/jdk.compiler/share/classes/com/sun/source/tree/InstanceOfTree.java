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
 * A tree node for an {@code instanceof} expression.
 *
 * For example:
 * <pre>
 *   <em>expression</em> instanceof <em>type</em>
 * </pre>
 *
 * @jls 15.20.2 Type Comparison Operator instanceof
 *
 */
public interface InstanceOfTree extends ExpressionTree {
    /**
     * Returns the expression to be tested.
     * @return the expression
     */
    ExpressionTree getExpression();

    /**
     * Returns the type for which to check.
     * @return the type
     * @see #getPattern()
     */
    Tree getType();

    /**
     * Returns the tested pattern, or null if this instanceof does not use
     * a pattern.
     *
     * <p>For instanceof with a pattern, i.e. in the following form:
     * <pre>
     *   <em>expression</em> instanceof <em>type</em> <em>variable name</em>
     * </pre>
     * returns the pattern.
     *
     * <p>For instanceof without a pattern, i.e. in the following form:
     * <pre>
     *   <em>expression</em> instanceof <em>type</em>
     * </pre>
     * returns null.
     *
     * @return the tested pattern, or null if this instanceof does not use a pattern
     */
    PatternTree getPattern();
}