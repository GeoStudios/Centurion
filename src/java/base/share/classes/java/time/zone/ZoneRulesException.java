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

package java.base.share.classes.java.time.zone;

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
