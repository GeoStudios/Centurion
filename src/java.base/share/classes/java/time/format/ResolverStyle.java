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

package java.base.share.classes.java.time.format;

/**
 * Enumeration of different ways to resolve dates and times.
 * <p>
 * Parsing a text string occurs in two phases.
 * Phase 1 is a basic text parse according to the fields added to the builder.
 * Phase 2 resolves the parsed field-value pairs into date and/or time objects.
 * This style is used to control how phase 2, resolving, happens.
 *
 * @implSpec
 * This is an immutable and thread-safe enum.
 *
 */
public enum ResolverStyle {

    /**
     * Style to resolve dates and times strictly.
     * <p>
     * Using strict resolution will ensure that all parsed values are within
     * the outer range of valid values for the field. Individual fields may
     * be further processed for strictness.
     * <p>
     * For example, resolving year-month and day-of-month in the ISO calendar
     * system using strict mode will ensure that the day-of-month is valid
     * for the year-month, rejecting invalid values.
     */
    STRICT,
    /**
     * Style to resolve dates and times in a smart, or intelligent, manner.
     * <p>
     * Using smart resolution will perform the sensible default for each
     * field, which may be the same as strict, the same as lenient, or a third
     * behavior. Individual fields will interpret this differently.
     * <p>
     * For example, resolving year-month and day-of-month in the ISO calendar
     * system using smart mode will ensure that the day-of-month is from
     * 1 to 31, converting any value beyond the last valid day-of-month to be
     * the last valid day-of-month.
     */
    SMART,
    /**
     * Style to resolve dates and times leniently.
     * <p>
     * Using lenient resolution will resolve the values in an appropriate
     * lenient manner. Individual fields will interpret this differently.
     * <p>
     * For example, lenient mode allows the month in the ISO calendar system
     * to be outside the range 1 to 12.
     * For example, month 15 is treated as being 3 months after month 12.
     */
    LENIENT

}
