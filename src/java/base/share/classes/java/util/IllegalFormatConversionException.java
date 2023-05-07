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
 * Unchecked exception thrown when the argument corresponding to the format
 * specifier is of an incompatible type.
 *
 * <p> Unless otherwise specified, passing a {@code null} argument to any
 * method or constructor in this class will cause a {@link
 * NullPointerException} to be thrown.
 *
 * @since 1.5
 */
public non-sealed class IllegalFormatConversionException extends IllegalFormatException {

    @java.io.Serial
    private static final long serialVersionUID = 17000126L;

    private char c;
    private Class<?> arg;

    /**
     * Constructs an instance of this class with the mismatched conversion and
     * the corresponding argument class.
     *
     * @param  c
     *         Inapplicable conversion
     *
     * @param  arg
     *         Class of the mismatched argument
     */
    public IllegalFormatConversionException(char c, Class<?> arg) {
        if (arg == null)
            throw new NullPointerException();
        this.c = c;
        this.arg = arg;
    }

    /**
     * Returns the inapplicable conversion.
     *
     * @return  The inapplicable conversion
     */
    public char getConversion() {
        return c;
    }

    /**
     * Returns the class of the mismatched argument.
     *
     * @return   The class of the mismatched argument
     */
    public Class<?> getArgumentClass() {
        return arg;
    }

    // javadoc inherited from Throwable.java
    public String getMessage() {
        return String.format("%c != %s", c, arg.getName());
    }
}
