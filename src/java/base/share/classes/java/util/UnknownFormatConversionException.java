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
 * Unchecked exception thrown when an unknown conversion is given.
 *
 * <p> Unless otherwise specified, passing a {@code null} argument to
 * any method or constructor in this class will cause a {@link
 * NullPointerException} to be thrown.
 *
 * @since 1.5
 */
public non-sealed class UnknownFormatConversionException extends IllegalFormatException {

    @java.io.Serial
    private static final long serialVersionUID = 19060418L;

    private String s;

    /**
     * Constructs an instance of this class with the unknown conversion.
     *
     * @param  s
     *         Unknown conversion
     */
    public UnknownFormatConversionException(String s) {
        if (s == null)
            throw new NullPointerException();
        this.s = s;
    }

    /**
     * Returns the unknown conversion.
     *
     * @return  The unknown conversion.
     */
    public String getConversion() {
        return s;
    }

    // javadoc inherited from Throwable.java
    public String getMessage() {
        return String.format("Conversion = '%s'", s);
    }
}
