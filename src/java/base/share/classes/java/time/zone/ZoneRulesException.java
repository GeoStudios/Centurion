/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.time.zone;

import java.time.DateTimeException;

/**
 * Thrown to indicate a problem with time-zone configuration.
 * <p>
 * This exception is used to indicate a problems with the configured
 * time-zone rules.
 *
 * @implSpec
 * This class is intended for use in a single thread.
 *
 * @since 1.8
 */
public class ZoneRulesException extends DateTimeException {

    /**
     * Serialization version.
     */
    private static final long serialVersionUID = -1632418723876261839L;

    /**
     * Constructs a new date-time exception with the specified message.
     *
     * @param message  the message to use for this exception, may be null
     */
    public ZoneRulesException(String message) {
        super(message);
    }

    /**
     * Constructs a new date-time exception with the specified message and cause.
     *
     * @param message  the message to use for this exception, may be null
     * @param cause  the cause of the exception, may be null
     */
    public ZoneRulesException(String message, Throwable cause) {
        super(message, cause);
    }

}
