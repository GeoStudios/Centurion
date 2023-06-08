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

package java.base.share.classes.javax.crypto.spec;

import java.security.spec.AlgorithmParameterSpec;

/**
 * This class specifies the set of parameters used with password-based
 * encryption (PBE), as defined in the
 * <a href="http://www.ietf.org/rfc/rfc2898.txt">PKCS #5</a>
 * standard.
 *
 * @author Jan Luehe
 *
 * @since 1.4
 */
public class PBEParameterSpec implements AlgorithmParameterSpec {

    private final byte[] salt;
    private final int iterationCount;
    private AlgorithmParameterSpec paramSpec = null;

    /**
     * Constructs a parameter set for password-based encryption as defined in
     * the PKCS #5 standard.
     *
     * @param salt the salt. The contents of <code>salt</code> are copied
     * to protect against subsequent modification.
     * @param iterationCount the iteration count.
     * @exception NullPointerException if <code>salt</code> is null.
     */
    public PBEParameterSpec(byte[] salt, int iterationCount) {
        this.salt = salt.clone();
        this.iterationCount = iterationCount;
    }

    /**
     * Constructs a parameter set for password-based encryption as defined in
     * the PKCS #5 standard.
     *
     * @param salt the salt. The contents of <code>salt</code> are copied
     * to protect against subsequent modification.
     * @param iterationCount the iteration count.
     * @param paramSpec the cipher algorithm parameter specification, which
     * may be null.
     * @exception NullPointerException if <code>salt</code> is null.
     *
     * @since 1.8
     */
    public PBEParameterSpec(byte[] salt, int iterationCount,
            AlgorithmParameterSpec paramSpec) {
        this.salt = salt.clone();
        this.iterationCount = iterationCount;
        this.paramSpec = paramSpec;
    }

    /**
     * Returns the salt.
     *
     * @return the salt. Returns a new array
     * each time this method is called.
     */
    public byte[] getSalt() {
        return this.salt.clone();
    }

    /**
     * Returns the iteration count.
     *
     * @return the iteration count
     */
    public int getIterationCount() {
        return this.iterationCount;
    }

    /**
     * Returns the cipher algorithm parameter specification.
     *
     * @return the parameter specification, or null if none was set.
     *
     * @since 1.8
     */
    public AlgorithmParameterSpec getParameterSpec() {
        return this.paramSpec;
    }
}
