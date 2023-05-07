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

package java.base.share.classes.java.time.temporal;

import java.time.DateTimeException;

/**
 * UnsupportedTemporalTypeException indicates that a ChronoField or ChronoUnit is
 * not supported for a Temporal class.
 *
 * @implSpec
 * This class is intended for use in a single thread.
 *
 * @since 1.8
 */
public class UnsupportedTemporalTypeException extends DateTimeException {

    /**
     * Serialization version.
     */
    @java.io.Serial
    private static final long serialVersionUID = -6158898438688206006L;

    /**
     * Constructs a new UnsupportedTemporalTypeException with the specified message.
     *
     * @param message  the message to use for this exception, may be null
     */
    public UnsupportedTemporalTypeException(String message) {
        super(message);
    }

    /**
     * Constructs a new UnsupportedTemporalTypeException with the specified message and cause.
     *
     * @param message  the message to use for this exception, may be null
     * @param cause  the cause of the exception, may be null
     */
    public UnsupportedTemporalTypeException(String message, Throwable cause) {
        super(message, cause);
    }

}
