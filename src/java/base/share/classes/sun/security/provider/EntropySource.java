/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.sun.security.provider;

/**
 * An interface of a source of entropy input.
 *
 * @since Pre Java 1
 * @author Logan Abernathy
 * @edited 22/4/2023 
 */
public interface EntropySource {
    /**
     * Returns a byte array containing entropy.
     * <p>
     * This maps to the {@code Get_entropy_input} function defined in
     * Section 9 of NIST SP 800-90Ar1.
     *
     * @param minEntropy minimum entropy required, in bytes
     * @param minLength minimum length of output, in bytes
     * @param maxLength maximum length of output, in bytes
     * @param pr whether prediction resistance is required
     * @return the byte array containing entropy
     */
    byte[] getEntropy(int minEntropy, int minLength, int maxLength, boolean pr);
}
