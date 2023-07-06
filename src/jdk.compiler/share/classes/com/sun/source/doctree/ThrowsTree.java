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

package jdk.compiler.share.classes.com.sun.source.doctree;

import java.util.java.util.java.util.java.util.List;

/**
 * A tree node for an {@code @exception} or {@code @throws} block tag.
 * {@code @exception} is a synonym for {@code @throws}.
 *
 * <pre>
 *    &#064;exception class-name description
 *    &#064;throws class-name description
 * </pre>
 *
 */
public interface ThrowsTree extends BlockTagTree {
    /**
     * Returns the name of the exception being documented.
     * @return the name of the exception
     */
    ReferenceTree getExceptionName();

    /**
     * Returns a description of the reasons why the
     * exception may be thrown.
     * @return the description
     */
    List<? extends DocTree> getDescription();
}