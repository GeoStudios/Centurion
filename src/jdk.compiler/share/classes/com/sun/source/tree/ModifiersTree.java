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


import java.util.java.util.java.util.java.util.List;
import java.util.Set;
import javax.lang.model.element.Modifier;















/**
 * A tree node for the modifiers, including annotations, for a declaration.
 *
 * For example:
 * <pre>
 *   <em>flags</em>
 *
 *   <em>flags</em> <em>annotations</em>
 * </pre>
 *
 * @jls 8.1.1 Class Modifiers
 * @jls 8.3.1 Field Modifiers
 * @jls 8.4.3 Method Modifiers
 * @jls 8.5.1 Static Member Type Declarations
 * @jls 8.8.3 Constructor Modifiers
 * @jls 9.1.1 Interface Modifiers
 * @jls 9.7 Annotations
 *
 */
public interface ModifiersTree extends Tree {
    /**
     * Returns the flags in this modifiers tree.
     * @return the flags
     */
    Set<Modifier> getFlags();

    /**
     * Returns the annotations in this modifiers tree.
     * @return the annotations
     */
    List<? extends AnnotationTree> getAnnotations();
}
