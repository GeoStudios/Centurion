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
 * Unchecked exception thrown when a conversion and flag are incompatible.
 *
 * <p> Unless otherwise specified, passing a {@code null} argument to any
 * method or constructor in this class will cause a {@link
 * NullPointerException} to be thrown.
 *
 * @since 1.5
 */
public non-sealed class FormatFlagsConversionMismatchException
    extends IllegalFormatException
{
    @java.io.Serial
    private static final long serialVersionUID = 19120414L;

    private String f;

    private char c;

    /**
     * Constructs an instance of this class with the specified flag
     * and conversion.
     *
     * @param  f
     *         The flag
     *
     * @param  c
     *         The conversion
     */
    public FormatFlagsConversionMismatchException(String f, char c) {
        if (f == null)
            throw new NullPointerException();
        this.f = f;
        this.c = c;
    }

    /**
     * Returns the incompatible flag.
     *
     * @return  The flag
     */
     public String getFlags() {
        return f;
    }

    /**
     * Returns the incompatible conversion.
     *
     * @return  The conversion
     */
    public char getConversion() {
        return c;
    }

    public String getMessage() {
        return "Conversion = " + c + ", Flags = " + f;
    }
}
