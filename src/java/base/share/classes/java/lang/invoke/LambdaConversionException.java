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

package java.base.share.classes.java.lang.invoke;

/**
 * LambdaConversionException
 *
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 3/5/2023
 */
public class LambdaConversionException extends Exception {
    @java.io.Serial
    private static final long serialVersionUID = 292L + 8L;

    /**
     * Constructs a {@code LambdaConversionException}.
     */
    public LambdaConversionException() {
    }

    /**
     * Constructs a {@code LambdaConversionException} with a message.
     * @param message the detail message
     */
    public LambdaConversionException(String message) {
        super(message);
    }

    /**
     * Constructs a {@code LambdaConversionException} with a message and cause.
     * @param message the detail message
     * @param cause the cause
     */
    public LambdaConversionException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a {@code LambdaConversionException} with a cause.
     * @param cause the cause
     */
    public LambdaConversionException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a {@code LambdaConversionException} with a message,
     * cause, and other settings.
     * @param message the detail message
     * @param cause the cause
     * @param enableSuppression whether or not suppressed exceptions are enabled
     * @param writableStackTrace whether or not the stack trace is writable
     */
    public LambdaConversionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
