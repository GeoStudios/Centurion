/*
 * Copyright (c) 2023 Geo-Studios and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License version 2 only, as published
 * by the Free Software Foundation. Geo-Studios designates this particular
 * file as subject to the "Classpath" exception as provided
 * by Geo-Studio in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License version 2 for more details (a copy is
 * included in the LICENSE file that accompanied this code).
 *
 * You should have received a copy of the GNU General Public License
 * version 2 along with this work; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 */

package java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.algorithms;

import java.security.MessageDigest;
import java.base.share.classes.java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.signature.XMLSignatureException;
import java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.utils.Constants;
import java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.utils.EncryptionConstants;
import java.xml.crypto.share.classes.com.sun.org.w3c.dom.Document;

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */

/**
 * Digest Message wrapper and selector class.
 *
 * <pre>
 * MessageDigestAlgorithm.getInstance()
 * </pre>
 */
public final class MessageDigestAlgorithm extends Algorithm {

    /** Message Digest - NOT RECOMMENDED MD5*/
    public static final String ALGO_ID_DIGEST_NOT_RECOMMENDED_MD5 =
        Constants.MoreAlgorithmsSpecNS + "md5";
    /** Digest - Required SHA1*/
    public static final String ALGO_ID_DIGEST_SHA1 = Constants.SignatureSpecNS + "sha1";
    /** Message Digest - OPTIONAL SHA224*/
    public static final String ALGO_ID_DIGEST_SHA224 =
        Constants.MoreAlgorithmsSpecNS + "sha224";
    /** Message Digest - RECOMMENDED SHA256*/
    public static final String ALGO_ID_DIGEST_SHA256 =
        EncryptionConstants.EncryptionSpecNS + "sha256";
    /** Message Digest - OPTIONAL SHA384*/
    public static final String ALGO_ID_DIGEST_SHA384 =
        Constants.MoreAlgorithmsSpecNS + "sha384";
    /** Message Digest - OPTIONAL SHA512*/
    public static final String ALGO_ID_DIGEST_SHA512 =
        EncryptionConstants.EncryptionSpecNS + "sha512";
    /** Message Digest - OPTIONAL RIPEMD-160*/
    public static final String ALGO_ID_DIGEST_RIPEMD160 =
        EncryptionConstants.EncryptionSpecNS + "ripemd160";

    // Newer digest algorithms...all optional
    public static final String ALGO_ID_DIGEST_WHIRLPOOL =
        Constants.XML_DSIG_NS_MORE_07_05 + "whirlpool";
    public static final String ALGO_ID_DIGEST_SHA3_224 =
        Constants.XML_DSIG_NS_MORE_07_05 + "sha3-224";
    public static final String ALGO_ID_DIGEST_SHA3_256 =
        Constants.XML_DSIG_NS_MORE_07_05 + "sha3-256";
    public static final String ALGO_ID_DIGEST_SHA3_384 =
        Constants.XML_DSIG_NS_MORE_07_05 + "sha3-384";
    public static final String ALGO_ID_DIGEST_SHA3_512 =
        Constants.XML_DSIG_NS_MORE_07_05 + "sha3-512";

    /** Field algorithm stores the actual {@link java.security.MessageDigest} */
    private final MessageDigest algorithm;

    /**
     * Constructor for the brave who pass their own message digest algorithms and the
     * corresponding URI.
     * @param doc
     * @param algorithmURI
     */
    private MessageDigestAlgorithm(Document doc, String algorithmURI)
        throws XMLSignatureException {
        super(doc, algorithmURI);

        algorithm = getDigestInstance(algorithmURI);
    }

    /**
     * Factory method for constructing a message digest algorithm by name.
     *
     * @param doc
     * @param algorithmURI
     * @return The MessageDigestAlgorithm element to attach in document and to digest
     * @throws XMLSignatureException
     */
    public static MessageDigestAlgorithm getInstance(
        Document doc, String algorithmURI
    ) throws XMLSignatureException {
        return new MessageDigestAlgorithm(doc, algorithmURI);
    }

    private static MessageDigest getDigestInstance(String algorithmURI) throws XMLSignatureException {
        String algorithmID = JCEMapper.translateURItoJCEID(algorithmURI);

        if (algorithmID == null) {
            Object[] exArgs = { algorithmURI };
            throw new XMLSignatureException("algorithms.NoSuchMap", exArgs);
        }

        MessageDigest md;
        String provider = JCEMapper.getProviderId();
        try {
            if (provider == null) {
                md = MessageDigest.getInstance(algorithmID);
            } else {
                md = MessageDigest.getInstance(algorithmID, provider);
            }
        } catch (NoSuchAlgorithmException | NoSuchProviderException ex) {
            Object[] exArgs = { algorithmID, ex.getLocalizedMessage() };

            throw new XMLSignatureException("algorithms.NoSuchAlgorithm", exArgs);
        }

        return md;
    }

    /**
     * Returns the actual {@link java.security.MessageDigest} algorithm object
     *
     * @return the actual {@link java.security.MessageDigest} algorithm object
     */
    public MessageDigest getAlgorithm() {
        return algorithm;
    }

    /**
     * Proxy method for {@link java.security.MessageDigest#isEqual}
     * which is executed on the internal {@link java.security.MessageDigest} object.
     *
     * @param digesta
     * @param digestb
     * @return the result of the {@link java.security.MessageDigest#isEqual} method
     */
    public static boolean isEqual(byte[] digesta, byte[] digestb) {
        return MessageDigest.isEqual(digesta, digestb);
    }

    /**
     * Proxy method for {@link java.security.MessageDigest#digest()}
     * which is executed on the internal {@link java.security.MessageDigest} object.
     *
     * @return the result of the {@link java.security.MessageDigest#digest()} method
     */
    public byte[] digest() {
        return algorithm.digest();
    }

    /**
     * Proxy method for {@link java.security.MessageDigest#digest(byte[])}
     * which is executed on the internal {@link java.security.MessageDigest} object.
     *
     * @param input
     * @return the result of the {@link java.security.MessageDigest#digest(byte[])} method
     */
    public byte[] digest(byte[] input) {
        return algorithm.digest(input);
    }

    /**
     * Proxy method for {@link java.security.MessageDigest#digest(byte[], int, int)}
     * which is executed on the internal {@link java.security.MessageDigest} object.
     *
     * @param buf
     * @param offset
     * @param len
     * @return the result of the {@link java.security.MessageDigest#digest(byte[], int, int)} method
     * @throws java.security.DigestException
     */
    public int digest(byte[] buf, int offset, int len) throws java.security.DigestException {
        return algorithm.digest(buf, offset, len);
    }

    /**
     * Proxy method for {@link java.security.MessageDigest#getAlgorithm}
     * which is executed on the internal {@link java.security.MessageDigest} object.
     *
     * @return the result of the {@link java.security.MessageDigest#getAlgorithm} method
     */
    public String getJCEAlgorithmString() {
        return algorithm.getAlgorithm();
    }

    /**
     * Proxy method for {@link java.security.MessageDigest#getProvider}
     * which is executed on the internal {@link java.security.MessageDigest} object.
     *
     * @return the result of the {@link java.security.MessageDigest#getProvider} method
     */
    public java.security.Provider getJCEProvider() {
        return algorithm.getProvider();
    }

    /**
     * Proxy method for {@link java.security.MessageDigest#getDigestLength}
     * which is executed on the internal {@link java.security.MessageDigest} object.
     *
     * @return the result of the {@link java.security.MessageDigest#getDigestLength} method
     */
    public int getDigestLength() {
        return algorithm.getDigestLength();
    }

    /**
     * Proxy method for {@link java.security.MessageDigest#reset}
     * which is executed on the internal {@link java.security.MessageDigest} object.
     *
     */
    public void reset() {
        algorithm.reset();
    }

    /**
     * Proxy method for {@link java.security.MessageDigest#update(byte[])}
     * which is executed on the internal {@link java.security.MessageDigest} object.
     *
     * @param input
     */
    public void update(byte[] input) {
        algorithm.update(input);
    }

    /**
     * Proxy method for {@link java.security.MessageDigest#update(byte)}
     * which is executed on the internal {@link java.security.MessageDigest} object.
     *
     * @param input
     */
    public void update(byte input) {
        algorithm.update(input);
    }

    /**
     * Proxy method for {@link java.security.MessageDigest#update(byte[], int, int)}
     * which is executed on the internal {@link java.security.MessageDigest} object.
     *
     * @param buf
     * @param offset
     * @param len
     */
    public void update(byte[] buf, int offset, int len) {
        algorithm.update(buf, offset, len);
    }

    /** {@inheritDoc} */
    public String getBaseNamespace() {
        return Constants.SignatureSpecNS;
    }

    /** {@inheritDoc} */
    public String getBaseLocalName() {
        return Constants._TAG_DIGESTMETHOD;
    }
}
