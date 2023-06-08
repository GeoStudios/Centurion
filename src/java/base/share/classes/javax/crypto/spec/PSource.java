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

/**
 * This class specifies the source for encoding input P in OAEP Padding,
 * as defined in the
 * <a href="https://tools.ietf.org/rfc/rfc8017.txt">PKCS#1 v2.2</a> standard.
 * <pre>
 * PSourceAlgorithm ::= AlgorithmIdentifier {
 *   {PKCS1PSourceAlgorithms}
 * }
 * </pre>
 * where
 * <pre>
 * PKCS1PSourceAlgorithms    ALGORITHM-IDENTIFIER ::= {
 *   { OID id-pSpecified PARAMETERS EncodingParameters },
 *   ...  -- Allows for future expansion --
 * }
 * EncodingParameters ::= OCTET STRING(SIZE(0..MAX))
 * </pre>
 * @author Valerie Peng
 *
 * @since 1.5
 */
public class PSource {

    private final String pSrcName;

    /**
     * Constructs a source of the encoding input P for OAEP
     * padding as defined in the PKCS #1 standard using the
     * specified PSource algorithm.
     * @param pSrcName the algorithm for the source of the
     * encoding input P.
     * @exception NullPointerException if <code>pSrcName</code>
     * is null.
     */
    protected PSource(String pSrcName) {
        if (pSrcName == null) {
            throw new NullPointerException("pSource algorithm is null");
        }
        this.pSrcName = pSrcName;
    }
    /**
     * Returns the PSource algorithm name.
     *
     * @return the PSource algorithm name.
     */
    public String getAlgorithm() {
        return pSrcName;
    }

    /**
     * This class is used to explicitly specify the value for
     * encoding input P in OAEP Padding.
     *
     * @since 1.5
     */
    public static final class PSpecified extends PSource {

        private final byte[] p;

        /**
         * The encoding input P whose value equals byte[0].
         */
        public static final PSpecified DEFAULT = new PSpecified(new byte[0]);

        /**
         * Constructs the source explicitly with the specified
         * value <code>p</code> as the encoding input P.
         * Note:
         * @param p the value of the encoding input. The contents
         * of the array are copied to protect against subsequent
         * modification.
         * @exception NullPointerException if <code>p</code> is null.
         */
        public PSpecified(byte[] p) {
            super("PSpecified");
            this.p = p.clone();
        }
        /**
         * Returns the value of encoding input P.
         * @return the value of encoding input P. A new array is
         * returned each time this method is called.
         */
        public byte[] getValue() {
            return (p.length==0? p: p.clone());
        }
    }
}
