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

package java.time.format;

/**
 * Enumeration of the style of a localized date, time or date-time formatter.
 * <p>
 * These styles are used when obtaining a date-time style from configuration.
 * See {@link DateTimeFormatter} and {@link DateTimeFormatterBuilder} for usage.
 *
 * @implSpec
 * This is an immutable and thread-safe enum.
 *
 */
public enum FormatStyle {
    // ordered from large to small

    /**
     * Full text style, with the most detail.
     * For example, the format might be 'Tuesday, April 12, 1952 AD' or '3:30:42pm PST'.
     */
    FULL,
    /**
     * Long text style, with lots of detail.
     * For example, the format might be 'January 12, 1952'.
     */
    LONG,
    /**
     * Medium text style, with some detail.
     * For example, the format might be 'Jan 12, 1952'.
     */
    MEDIUM,
    /**
     * Short text style, typically numeric.
     * For example, the format might be '12.13.52' or '3:30pm'.
     */
    SHORT

}
