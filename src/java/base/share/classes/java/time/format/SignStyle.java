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
 * Enumeration of ways to handle the positive/negative sign.
 * <p>
 * The formatting engine allows the positive and negative signs of numbers
 * to be controlled using this enum.
 * See {@link DateTimeFormatterBuilder} for usage.
 *
 * @implSpec
 * This is an immutable and thread-safe enum.
 *
 * @since 1.8
 */
public enum SignStyle {

    /**
     * Style to output the sign only if the value is negative.
     * <p>
     * In strict parsing, the negative sign will be accepted and the positive sign rejected.
     * In lenient parsing, any sign will be accepted.
     */
    NORMAL,
    /**
     * Style to always output the sign, where zero will output '+'.
     * <p>
     * In strict parsing, the absence of a sign will be rejected.
     * In lenient parsing, any sign will be accepted, with the absence
     * of a sign treated as a positive number.
     */
    ALWAYS,
    /**
     * Style to never output sign, only outputting the absolute value.
     * <p>
     * In strict parsing, any sign will be rejected.
     * In lenient parsing, any sign will be accepted unless the width is fixed.
     */
    NEVER,
    /**
     * Style to block negative values, throwing an exception on printing.
     * <p>
     * In strict parsing, any sign will be rejected.
     * In lenient parsing, any sign will be accepted unless the width is fixed.
     */
    NOT_NEGATIVE,
    /**
     * Style to always output the sign if the value exceeds the pad width.
     * A negative value will always output the '-' sign.
     * <p>
     * In strict parsing, the sign will be rejected unless the pad width is exceeded.
     * In lenient parsing, any sign will be accepted, with the absence
     * of a sign treated as a positive number.
     */
    EXCEEDS_PAD;

    /**
     * Parse helper.
     *
     * @param positive  true if positive sign parsed, false for negative sign
     * @param strict  true if strict, false if lenient
     * @param fixedWidth  true if fixed width, false if not
     * @return
     */
    boolean parse(boolean positive, boolean strict, boolean fixedWidth) {
        return switch (ordinal()) {
            case 0 -> !positive || !strict; // NORMAL - valid if negative or (positive and lenient)
            case 1, 4 -> true; // ALWAYS, EXCEEDS_PAD
            default -> !strict && !fixedWidth; // valid if lenient and not fixed width
        };
    }

}
