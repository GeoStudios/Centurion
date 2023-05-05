/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.java.lang.invoke;

/**
 * LambdaConversionException
 *
 * @since Java 2
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
