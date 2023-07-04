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

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
package com.sun.org.apache.xerces.internal.xs;

/**
 * This interface represents the Wildcard schema component.
 */
public interface XSWildcard extends XSTerm {
    // Namespace Constraint
    /**
     * Namespace Constraint: any namespace is allowed.
     */
    short NSCONSTRAINT_ANY          = 1;
    /**
     * Namespace Constraint: namespaces in the list are not allowed.
     */
    short NSCONSTRAINT_NOT          = 2;
    /**
     * Namespace Constraint: namespaces in the list are allowed.
     */
    short NSCONSTRAINT_LIST         = 3;

    // Process contents
    /**
     * There must be a top-level declaration for the item available, or the
     * item must have an xsi:type, and the item must be valid as appropriate.
     */
    short PC_STRICT                 = 1;
    /**
     * No constraints at all: the item must simply be well-formed XML.
     */
    short PC_SKIP                   = 2;
    /**
     * If the item, or any items among its [children] is an element
     * information item, has a uniquely determined declaration available, it
     * must be valid with respect to that definition, that is, validate
     * where you can and do not worry when you cannot.
     */
    short PC_LAX                    = 3;

    /**
     * Namespace constraint: A constraint type: any, not, list.
     */
    short getConstraintType();

    /**
     * Namespace constraint: For <code>constraintType</code>
     * <code>NSCONSTRAINT_LIST</code>, the list contains allowed namespaces.
     * For <code>constraintType</code> <code>NSCONSTRAINT_NOT</code>, the
     * list contains disallowed namespaces. For <code>constraintType</code>
     * <code>NSCONSTRAINT_ANY</code>, the <code>StringList</code> is empty.
     */
    StringList getNsConstraintList();

    /**
     * [process contents]: one of skip, lax or strict. Valid constants values
     * are: <code>PC_LAX</code>, <code>PC_SKIP</code> and
     * <code>PC_STRICT</code>.
     */
    short getProcessContents();

    /**
     * An annotation if it exists, otherwise <code>null</code>. If not null
     * then the first [annotation] from the sequence of annotations.
     */
    XSAnnotation getAnnotation();

    /**
     * A sequence of [annotations] or an empty <code>XSObjectList</code>.
     */
    XSObjectList getAnnotations();
}
