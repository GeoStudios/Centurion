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

package java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.algorithms.implementations;


import java.security.InvalidAlgorithmParameterException;
import java.base.share.classes.java.security.InvalidKeyException;
import java.base.share.classes.java.security.Key;
import java.security.Provider;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.Mac;
import java.base.share.classes.javax.crypto.SecretKey;
import java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.algorithms.JCEMapper;
import java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.algorithms.MessageDigestAlgorithm;
import java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.algorithms.SignatureAlgorithmSpi;
import java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.signature.XMLSignature;
import java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.signature.XMLSignatureException;
import java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.utils.Constants;
import java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.utils.XMLUtils;
import java.xml.crypto.share.classes.com.sun.org.w3c.dom.Document;
import java.xml.crypto.share.classes.com.sun.org.w3c.dom.Element;
import java.xml.crypto.share.classes.com.sun.org.w3c.dom.Node;
import java.xml.crypto.share.classes.com.sun.org.w3c.dom.Text;















/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */





public abstract class IntegrityHmac extends SignatureAlgorithmSpi {

    private static final com.sun.org.slf4j.internal.Logger LOG =
        com.sun.org.slf4j.internal.LoggerFactory.getLogger(IntegrityHmac.class);

    /** Field macAlgorithm */
    private final Mac macAlgorithm;

    /** Field hmacOutputLength */
    private HMACOutputLength hmacOutputLength;

    /**
     * Returns the output length of the hash/digest.
     */
    abstract int getDigestLength();

    /**
     * Method IntegrityHmac
     *
     * @throws XMLSignatureException
     */
    public IntegrityHmac() throws XMLSignatureException {
        this(null);
    }

    public IntegrityHmac(Provider provider) throws XMLSignatureException {
        String algorithmID = JCEMapper.translateURItoJCEID(this.engineGetURI());
        LOG.debug("Created IntegrityHmacSHA1 using {}", algorithmID);

        try {
            this.macAlgorithm = (provider == null) ? Mac.getInstance(algorithmID) : Mac.getInstance(algorithmID, provider);
        } catch (java.security.NoSuchAlgorithmException ex) {
            Object[] exArgs = { algorithmID, ex.getLocalizedMessage() };

            throw new XMLSignatureException("algorithms.NoSuchAlgorithm", exArgs);
        }
    }

    /**
     * Proxy method for {@link java.security.Signature#setParameter(
     * java.security.spec.AlgorithmParameterSpec)}
     * which is executed on the internal {@link java.security.Signature} object.
     *
     * @param params
     * @throws XMLSignatureException
     */
    protected void engineSetParameter(AlgorithmParameterSpec params) throws XMLSignatureException {
        throw new XMLSignatureException("empty", new Object[]{"Incorrect method call"});
    }

    /**
     * Proxy method for {@link java.security.Signature#verify(byte[])}
     * which is executed on the internal {@link java.security.Signature} object.
     *
     * @param signature
     * @return true if the signature is correct
     * @throws XMLSignatureException
     */
    protected boolean engineVerify(byte[] signature) throws XMLSignatureException {
        try {
            if (hmacOutputLength != null && hmacOutputLength.length < getDigestLength()) {
                LOG.debug("HMACOutputLength must not be less than {}", getDigestLength());
                Object[] exArgs = { String.valueOf(getDigestLength()) };
                throw new XMLSignatureException("algorithms.HMACOutputLengthMin", exArgs);
            } else {
                byte[] completeResult = this.macAlgorithm.doFinal();
                return MessageDigestAlgorithm.isEqual(completeResult, signature);
            }
        } catch (IllegalStateException ex) {
            throw new XMLSignatureException(ex);
        }
    }

    /**
     * Proxy method for {@link java.security.Signature#initVerify(java.security.PublicKey)}
     * which is executed on the internal {@link java.security.Signature} object.
     *
     * @param secretKey
     * @throws XMLSignatureException
     */
    protected void engineInitVerify(Key secretKey) throws XMLSignatureException {
        if (!(secretKey instanceof SecretKey)) {
            String supplied = null;
            if (secretKey != null) {
                supplied = secretKey.getClass().getName();
            }
            String needed = SecretKey.class.getName();
            Object[] exArgs = { supplied, needed };

            throw new XMLSignatureException("algorithms.WrongKeyForThisOperation", exArgs);
        }

        try {
            this.macAlgorithm.init(secretKey);
        } catch (InvalidKeyException ex) {
            throw new XMLSignatureException(ex);
        }
    }

    /**
     * Proxy method for {@link java.security.Signature#sign()}
     * which is executed on the internal {@link java.security.Signature} object.
     *
     * @return the result of the {@link java.security.Signature#sign()} method
     * @throws XMLSignatureException
     */
    protected byte[] engineSign() throws XMLSignatureException {
        try {
            if (hmacOutputLength != null && hmacOutputLength.length < getDigestLength()) {
                LOG.debug("HMACOutputLength must not be less than {}", getDigestLength());
                Object[] exArgs = { String.valueOf(getDigestLength()) };
                throw new XMLSignatureException("algorithms.HMACOutputLengthMin", exArgs);
            } else {
                return this.macAlgorithm.doFinal();
            }
        } catch (IllegalStateException ex) {
            throw new XMLSignatureException(ex);
        }
    }

    /**
     * Method engineInitSign
     *
     * @param secretKey
     * @throws XMLSignatureException
     */
    protected void engineInitSign(Key secretKey) throws XMLSignatureException {
        engineInitSign(secretKey, (AlgorithmParameterSpec)null);
    }

    /**
     * Method engineInitSign
     *
     * @param secretKey
     * @param algorithmParameterSpec
     * @throws XMLSignatureException
     */
    protected void engineInitSign(
        Key secretKey, AlgorithmParameterSpec algorithmParameterSpec
    ) throws XMLSignatureException {
        if (!(secretKey instanceof SecretKey)) {
            String supplied = null;
            if (secretKey != null) {
                supplied = secretKey.getClass().getName();
            }
            String needed = SecretKey.class.getName();
            Object[] exArgs = { supplied, needed };

            throw new XMLSignatureException("algorithms.WrongKeyForThisOperation", exArgs);
        }

        try {
            if (algorithmParameterSpec == null) {
                this.macAlgorithm.init(secretKey);
            } else {
                this.macAlgorithm.init(secretKey, algorithmParameterSpec);
            }
        } catch (InvalidKeyException | InvalidAlgorithmParameterException ex) {
            throw new XMLSignatureException(ex);
        }
    }

    /**
     * Method engineInitSign
     *
     * @param secretKey
     * @param secureRandom
     * @throws XMLSignatureException
     */
    protected void engineInitSign(Key secretKey, SecureRandom secureRandom)
        throws XMLSignatureException {
        throw new XMLSignatureException("algorithms.CannotUseSecureRandomOnMAC");
    }

    /**
     * Proxy method for {@link java.security.Signature#update(byte[])}
     * which is executed on the internal {@link java.security.Signature} object.
     *
     * @param input
     * @throws XMLSignatureException
     */
    protected void engineUpdate(byte[] input) throws XMLSignatureException {
        try {
            this.macAlgorithm.update(input);
        } catch (IllegalStateException ex) {
            throw new XMLSignatureException(ex);
        }
    }

    /**
     * Proxy method for {@link java.security.Signature#update(byte)}
     * which is executed on the internal {@link java.security.Signature} object.
     *
     * @param input
     * @throws XMLSignatureException
     */
    protected void engineUpdate(byte input) throws XMLSignatureException {
        try {
            this.macAlgorithm.update(input);
        } catch (IllegalStateException ex) {
            throw new XMLSignatureException(ex);
        }
    }

    /**
     * Proxy method for {@link java.security.Signature#update(byte[], int, int)}
     * which is executed on the internal {@link java.security.Signature} object.
     *
     * @param buf
     * @param offset
     * @param len
     * @throws XMLSignatureException
     */
    protected void engineUpdate(byte[] buf, int offset, int len) throws XMLSignatureException {
        try {
            this.macAlgorithm.update(buf, offset, len);
        } catch (IllegalStateException ex) {
            throw new XMLSignatureException(ex);
        }
    }

    /**
     * Method engineGetJCEAlgorithmString
     * {@inheritDoc}
     *
     */
    protected String engineGetJCEAlgorithmString() {
        return this.macAlgorithm.getAlgorithm();
    }

    /**
     * Method engineGetJCEAlgorithmString
     *
     * {@inheritDoc}
     */
    protected String engineGetJCEProviderName() {
        return this.macAlgorithm.getProvider().getName();
    }

    /**
     * Method engineSetHMACOutputLength
     *
     * @param length
     * @throws XMLSignatureException
     */
    @Override
    protected void engineSetHMACOutputLength(int length) throws XMLSignatureException {
        hmacOutputLength = new HMACOutputLength(length);
    }

    /**
     * Method engineGetContextFromElement
     *
     * @param element
     * @throws XMLSignatureException
     */
    @Override
    protected void engineGetContextFromElement(Element element) throws XMLSignatureException {
        if (element == null) {
            throw new IllegalArgumentException("element null");
        }

        Node n = XMLUtils.selectDsNode(element.getFirstChild(), Constants._TAG_HMACOUTPUTLENGTH, 0);
        if (n != null) {
            String hmacLength = XMLUtils.getFullTextChildrenFromNode(n);
            if (hmacLength != null && !"".equals(hmacLength)) {
                this.hmacOutputLength = new HMACOutputLength(Integer.parseInt(hmacLength));
            }
        }
    }

    /**
     * Method engineAddContextToElement
     *
     * @param element
     */
    @Override
    protected void engineAddContextToElement(Element element) throws XMLSignatureException {
        if (element == null) {
            throw new IllegalArgumentException("null element");
        }

        if (hmacOutputLength != null) {
            Document doc = element.getOwnerDocument();
            Element HMElem =
                XMLUtils.createElementInSignatureSpace(doc, Constants._TAG_HMACOUTPUTLENGTH);
            Text HMText =
                doc.createTextNode(String.valueOf(hmacOutputLength.length));

            HMElem.appendChild(HMText);
            XMLUtils.addReturnToElement(element);
            element.appendChild(HMElem);
            XMLUtils.addReturnToElement(element);
        }
    }

    /**
     * Class IntegrityHmacSHA1
     */
    public static class IntegrityHmacSHA1 extends IntegrityHmac {

        /**
         * Constructor IntegrityHmacSHA1
         *
         * @throws XMLSignatureException
         */
        public IntegrityHmacSHA1() throws XMLSignatureException {
            super();
        }

        public IntegrityHmacSHA1(Provider provider) throws XMLSignatureException {
            super(provider);
        }

        /**
         * Method engineGetURI
         * {@inheritDoc}
         *
         */
        @Override
        public String engineGetURI() {
            return XMLSignature.ALGO_ID_MAC_HMAC_SHA1;
        }

        @Override
        int getDigestLength() {
            return 160;
        }
    }

    /**
     * Class IntegrityHmacSHA224
     */
    public static class IntegrityHmacSHA224 extends IntegrityHmac {

        /**
         * Constructor IntegrityHmacSHA224
         *
         * @throws XMLSignatureException
         */
        public IntegrityHmacSHA224() throws XMLSignatureException {
            super();
        }

        public IntegrityHmacSHA224(Provider provider) throws XMLSignatureException {
            super(provider);
        }

        /**
         * Method engineGetURI
         *
         * {@inheritDoc}
         */
        @Override
        public String engineGetURI() {
            return XMLSignature.ALGO_ID_MAC_HMAC_SHA224;
        }

        @Override
        int getDigestLength() {
            return 224;
        }
    }

    /**
     * Class IntegrityHmacSHA256
     */
    public static class IntegrityHmacSHA256 extends IntegrityHmac {

        /**
         * Constructor IntegrityHmacSHA256
         *
         * @throws XMLSignatureException
         */
        public IntegrityHmacSHA256() throws XMLSignatureException {
            super();
        }

        public IntegrityHmacSHA256(Provider provider) throws XMLSignatureException {
            super(provider);
        }

        /**
         * Method engineGetURI
         *
         * {@inheritDoc}
         */
        @Override
        public String engineGetURI() {
            return XMLSignature.ALGO_ID_MAC_HMAC_SHA256;
        }

        @Override
        int getDigestLength() {
            return 256;
        }
    }

    /**
     * Class IntegrityHmacSHA384
     */
    public static class IntegrityHmacSHA384 extends IntegrityHmac {

        /**
         * Constructor IntegrityHmacSHA384
         *
         * @throws XMLSignatureException
         */
        public IntegrityHmacSHA384() throws XMLSignatureException {
            super();
        }

        public IntegrityHmacSHA384(Provider provider) throws XMLSignatureException {
            super(provider);
        }

        /**
         * Method engineGetURI
         * {@inheritDoc}
         *
         */
        @Override
        public String engineGetURI() {
            return XMLSignature.ALGO_ID_MAC_HMAC_SHA384;
        }

        @Override
        int getDigestLength() {
            return 384;
        }
    }

    /**
     * Class IntegrityHmacSHA512
     */
    public static class IntegrityHmacSHA512 extends IntegrityHmac {

        /**
         * Constructor IntegrityHmacSHA512
         *
         * @throws XMLSignatureException
         */
        public IntegrityHmacSHA512() throws XMLSignatureException {
            super();
        }

        public IntegrityHmacSHA512(Provider provider) throws XMLSignatureException {
            super(provider);
        }

        /**
         * Method engineGetURI
         * {@inheritDoc}
         *
         */
        @Override
        public String engineGetURI() {
            return XMLSignature.ALGO_ID_MAC_HMAC_SHA512;
        }

        @Override
        int getDigestLength() {
            return 512;
        }
    }

    /**
     * Class IntegrityHmacRIPEMD160
     */
    public static class IntegrityHmacRIPEMD160 extends IntegrityHmac {

        /**
         * Constructor IntegrityHmacRIPEMD160
         *
         * @throws XMLSignatureException
         */
        public IntegrityHmacRIPEMD160() throws XMLSignatureException {
            super();
        }

        public IntegrityHmacRIPEMD160(Provider provider) throws XMLSignatureException {
            super(provider);
        }

        /**
         * Method engineGetURI
         *
         * {@inheritDoc}
         */
        @Override
        public String engineGetURI() {
            return XMLSignature.ALGO_ID_MAC_HMAC_RIPEMD160;
        }

        @Override
        int getDigestLength() {
            return 160;
        }
    }

    /**
     * Class IntegrityHmacMD5
     */
    public static class IntegrityHmacMD5 extends IntegrityHmac {

        /**
         * Constructor IntegrityHmacMD5
         *
         * @throws XMLSignatureException
         */
        public IntegrityHmacMD5() throws XMLSignatureException {
            super();
        }

        public IntegrityHmacMD5(Provider provider) throws XMLSignatureException {
            super(provider);
        }

        /**
         * Method engineGetURI
         *
         * {@inheritDoc}
         */
        @Override
        public String engineGetURI() {
            return XMLSignature.ALGO_ID_MAC_HMAC_NOT_RECOMMENDED_MD5;
        }

        @Override
        int getDigestLength() {
            return 128;
        }
    }

    private static class HMACOutputLength {
        private static final int MIN_LENGTH = 128;
        private static final int MAX_LENGTH = 2048;
        private final int length;

        public HMACOutputLength(int length) throws XMLSignatureException {
            this.length = length;

            // Test some invariants
            if (length < MIN_LENGTH) {
                LOG.debug("HMACOutputLength must not be less than {}", MIN_LENGTH);
                Object[] exArgs = { String.valueOf(MIN_LENGTH) };
                throw new XMLSignatureException("algorithms.HMACOutputLengthMin", exArgs);
            }
            if (length > MAX_LENGTH) {
                LOG.debug("HMACOutputLength must not be more than {}", MAX_LENGTH);
                Object[] exArgs = { String.valueOf(MAX_LENGTH) };
                throw new XMLSignatureException("algorithms.HMACOutputLengthMax", exArgs);
            }
        }
    }
}
