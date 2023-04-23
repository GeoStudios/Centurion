/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package javax.crypto;

import java.security.GeneralSecurityException;

/**
 * This is the generic ExemptionMechanism exception.
 *
 * @since 1.4
 */

public class ExemptionMechanismException extends GeneralSecurityException {

    @java.io.Serial
    private static final long serialVersionUID = 1572699429277957109L;

    /**
     * Constructs an {@code ExemptionMechanismException} with no detailed
     * message. (A detailed message is a {@code String} that describes this
     * particular exception.)
     */
    public ExemptionMechanismException() {
        super();
    }

    /**
     * Constructs an {@code ExemptionMechanismException} with the specified
     * detailed message. (A detailed message is a {@code String}  that describes
     * this particular exception.)
     *
     * @param msg the detailed message.
     */
   public ExemptionMechanismException(String msg) {
       super(msg);
    }
}
