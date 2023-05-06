/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.sun.security.rsa;

import java.security.*;

/**
 * This class implements the MGF1 mask generation function defined in PKCS#1
 * v2.2 B.2.1 (https://tools.ietf.org/html/rfc8017#appendix-B.2.1). A mask
 * generation function takes an octet string of variable length and a
 * desired output length as input and outputs an octet string of the
 * desired length. MGF1 is a mask generation function based on a hash
 * function, i.e. message digest algorithm.
 *
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 21/4/2023 
 */
public final class MGF1 {

    private final MessageDigest md;

    /**
     * Construct an instance of MGF1 based on the specified digest algorithm.
     */
    MGF1(String mdAlgo) throws NoSuchAlgorithmException {
        this.md = MessageDigest.getInstance(mdAlgo);
    }

    /**
     * Using the specified seed bytes, generate the mask, xor the mask
     * with the specified output buffer and store the result into the
     * output buffer (essentially replaced in place).
     *
     * @param seed the buffer holding the seed bytes
     * @param seedOfs the index of the seed bytes
     * @param seedLen the length of the seed bytes to be used by MGF1
     * @param maskLen the intended length of the generated mask
     * @param out the output buffer holding the mask
     * @param outOfs the index of the output buffer for the mask
     */
    void generateAndXor(byte[] seed, int seedOfs, int seedLen, int maskLen,
            byte[] out, int outOfs) throws RuntimeException {
        byte[] C = new byte[4]; // 32 bit counter
        byte[] digest = new byte[md.getDigestLength()];
        while (maskLen > 0) {
            md.update(seed, seedOfs, seedLen);
            md.update(C);
            try {
                md.digest(digest, 0, digest.length);
            } catch (DigestException e) {
                // should never happen
                throw new RuntimeException(e.toString());
            }
            for (int i = 0; (i < digest.length) && (maskLen > 0); maskLen--) {
                out[outOfs++] ^= digest[i++];
            }
            if (maskLen > 0) {
                // increment counter
                for (int i = C.length - 1; (++C[i] == 0) && (i > 0); i--) {
                    // empty
                }
            }
        }
    }

    /**
     * Returns the name of this MGF1 instance, i.e. "MGF1" followed by the
     * digest algorithm it based on.
     */
    String getName() {
        return "MGF1" + md.getAlgorithm();
    }
}
