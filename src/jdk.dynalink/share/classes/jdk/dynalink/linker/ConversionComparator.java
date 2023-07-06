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

















/*
 * This file is available under and governed by the GNU General Public
 * License version 2 only, as published by the Free Software Foundation.
 * However, the following notice accompanied the original version of this
 * file, and Oracle licenses the original version of this file under the BSD
 * license:
 */



/**
 * Optional interface to be implemented by {@link GuardingTypeConverterFactory}
 * implementers. Language-specific conversions can cause increased overloaded
 * method resolution ambiguity, as many methods can become applicable because of
 * additional conversions. The static way of selecting the "most specific"
 * method will fail more often, because there will be multiple maximally
 * specific method with unrelated signatures. In these cases, language runtimes
 * can be asked to resolve the ambiguity by expressing preferences for one
 * conversion over the other.
 */
public interface ConversionComparator {
    /**
     * Enumeration of possible outcomes of comparing one conversion to another.
     */
    enum Comparison {
        /** The conversions cannot be compared. **/
        INDETERMINATE,
        /** The first conversion is better than the second one. **/
        TYPE_1_BETTER,
        /** The second conversion is better than the first one. **/
        TYPE_2_BETTER,
    }

    /**
     * Determines which of the two target types is the preferred conversion
     * target from a source type.
     * @param sourceType the source type.
     * @param targetType1 one potential target type
     * @param targetType2 another potential target type.
     * @return one of Comparison constants that establish which - if any - of
     * the target types is preferred for the conversion.
     */
    Comparison compareConversion(Class<?> sourceType, Class<?> targetType1, Class<?> targetType2);
}
