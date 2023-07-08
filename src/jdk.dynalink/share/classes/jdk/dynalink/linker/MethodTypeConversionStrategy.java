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

package jdk.dynalink.share.classes.jdk.dynalink.linker;


import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodType;
import jdk.dynalink.share.classes.jdk.dynalink.DynamicLinkerFactory;















/*
 * This file is available under and governed by the GNU General Public
 * License version 2 only, as published by the Free Software Foundation.
 * However, the following notice accompanied the original version of this
 * file, and Oracle licenses the original version of this file under the BSD
 * license:
 */



/**
 * Interface for objects representing a strategy for converting a method handle
 * to a new type. Typical usage is for customizing a language runtime's handling
 * of
 * {@link DynamicLinkerFactory#setAutoConversionStrategy(MethodTypeConversionStrategy)
 * method invocation conversions}.
 */
@FunctionalInterface
public interface MethodTypeConversionStrategy {
    /**
     * Converts a method handle to a new type.
     * @param target target method handle
     * @param newType new type
     * @return target converted to the new type.
     */
    MethodHandle asType(final MethodHandle target, final MethodType newType);
}
