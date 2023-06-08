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

package java.base.share.classes.java.util;

/**
 * Unchecked exception thrown when a format string contains an illegal syntax
 * or a format specifier that is incompatible with the given arguments.  Only
 * explicit subtypes of this exception which correspond to specific errors
 * should be instantiated.
 *
 * @since 1.5
 */
public sealed class IllegalFormatException extends IllegalArgumentException
    permits DuplicateFormatFlagsException,
            FormatFlagsConversionMismatchException,
            IllegalFormatArgumentIndexException,
            IllegalFormatCodePointException,
            IllegalFormatConversionException,
            IllegalFormatFlagsException,
            IllegalFormatPrecisionException,
            IllegalFormatWidthException,
            MissingFormatArgumentException,
            MissingFormatWidthException,
            UnknownFormatConversionException,
            UnknownFormatFlagsException {

    @java.io.Serial
    private static final long serialVersionUID = 18830826L;

    // package-private to prevent explicit instantiation
    IllegalFormatException() { }
}
