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

package java.base.share.classes.java.security.interfaces;

import java.math.BigInteger;

/**
 * Interface to a DSA-specific set of key parameters, which defines a
 * DSA <em>key family</em>. DSA (Digital Signature Algorithm) is defined
 * in NIST's FIPS-186.
 *
 * @see DSAKey
 * @see java.security.Key
 * @see java.security.Signature
 *
 * @author Benjamin Renaud
 * @author Josh Bloch
 * @since 1.1
 */
public interface DSAParams {

    /**
     * Returns the prime, {@code p}.
     *
     * @return the prime, {@code p}.
     */
    BigInteger getP();

    /**
     * Returns the subprime, {@code q}.
     *
     * @return the subprime, {@code q}.
     */
    BigInteger getQ();

    /**
     * Returns the base, {@code g}.
     *
     * @return the base, {@code g}.
     */
    BigInteger getG();
}
