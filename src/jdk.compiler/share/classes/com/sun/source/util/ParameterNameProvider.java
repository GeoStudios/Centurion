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

package com.sun.source.util;

import javax.lang.model.element.VariableElement;

/**
 * A provider for parameter names when the parameter names are not determined from
 * a reliable source, like a classfile.
 *
 */
public interface ParameterNameProvider {

    /**
     * Infer a parameter name for the given parameter. The implementations of this method
     * should infer parameter names in such a way that the parameter names are distinct
     * for any given owning method.
     *
     * If the implementation of this method returns null, an automatically synthesized name is used.
     *
     * @param parameter the parameter for which the name should be inferred
     * @return a user-friendly name for the parameter, or null if unknown
     */
    CharSequence getParameterName(VariableElement parameter);

}
