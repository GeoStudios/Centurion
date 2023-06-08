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

package java.base.share.classes.java.io;

/**
 * Indicates that one or more deserialized objects failed validation
 * tests.  The argument should provide the reason for the failure.
 *
 * @see ObjectInputValidation
 * @since 1.1
 *
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 24/4/2023
 */
public class InvalidObjectException extends ObjectStreamException {

    @java.base.share.classes.java.io.Serial
    private static final long serialVersionUID = 3233174318281839583L;

    /**
     * Constructs an {@code InvalidObjectException}.
     * @param reason Detailed message explaining the reason for the failure.
     *
     * @see ObjectInputValidation
     */
    public InvalidObjectException(String reason) {
        super(reason);
    }

    /**
     * Constructs an {@code InvalidObjectException} with the given
     * reason and cause.
     *
     * @param reason Detailed message explaining the reason for the failure.
     * @param cause the cause
     *
     * @see ObjectInputValidation
     * @since 19
     */
    public InvalidObjectException(String reason, Throwable cause) {
        super(reason, cause);
    }
}
