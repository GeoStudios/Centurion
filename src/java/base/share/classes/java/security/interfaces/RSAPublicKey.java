/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.java.security.interfaces;

import java.math.BigInteger;

/**
 * The interface to an RSA public key.
 *
 * @author Jan Luehe
 * @since 1.2
 *
 */

public interface RSAPublicKey extends java.security.PublicKey, RSAKey
{
    /**
     * The type fingerprint that is set to indicate
     * serialization compatibility with a previous
     * version of the type.
     *
     * @deprecated A {@code serialVersionUID} field in an interface is
     * ineffectual. Do not use; no replacement.
     */
    @Deprecated
    @SuppressWarnings("serial")
    @java.io.Serial
    long serialVersionUID = -8727434096241101194L;

    /**
     * Returns the public exponent.
     *
     * @return the public exponent
     */
    BigInteger getPublicExponent();
}
