/*
 * Geo Studios Protective License
 *
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 *
 * Whoever collects this software or tool may not distribute the copy that has been obtained.
 *
 * This software or tool may not be used to gain a commercial or monetary advantage.
 *
 * Copyright will be included in any software or tool using this license, no matter the size or type of software or tool.
 *
 * This software or tool is not under any patent, but the software or tool shall not be
 * sold or uploaded as some other product or without the original creators consent and
 * permission. If the following happens, consequences will occur due to following
 * instructions or not following the rules written in this document.
 */

package java.base.share.classes.java.time.format;

/**
 * Enumeration of the style of a localized date, time or date-time formatter.
 * <p>
 * These styles are used when obtaining a date-time style from configuration.
 * See {@link DateTimeFormatter} and {@link DateTimeFormatterBuilder} for usage.
 *
 * @implSpec
 * This is an immutable and thread-safe enum.
 *
 * @since 1.8
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
    SHORT;

}
