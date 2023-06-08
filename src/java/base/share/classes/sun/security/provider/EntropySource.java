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

package java.base.share.classes.sun.security.provider;

/**
 * An interface of a source of entropy input.
 *
 * @since Alpha cdk-1.1
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
