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


import java.io.java.io.java.io.java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.base.share.classes.java.security.Key;
import java.base.share.classes.java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Provider;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.SignatureException;
import java.security.interfaces.DSAKey;
import java.security.spec.AlgorithmParameterSpec;
import java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.algorithms.JCEMapper;
import java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.algorithms.SignatureAlgorithmSpi;
import java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.signature.XMLSignature;
import java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.signature.XMLSignatureException;
import java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.utils.Constants;
import java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.utils.JavaUtils;
import java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.utils.XMLUtils;















/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */




public class SignatureDSA extends SignatureAlgorithmSpi {

    public static final String URI = Constants.SignatureSpecNS + "dsa-sha1";

    private static final com.sun.org.slf4j.internal.Logger LOG =
        com.sun.org.slf4j.internal.LoggerFactory.getLogger(SignatureDSA.class);

    /** Field algorithm */
    private final Signature signatureAlgorithm;

    /** size of Q */
    private int size;

    /**
     * Method engineGetURI
     *
     * {@inheritDoc}
     */
    protected String engineGetURI() {
        return XMLSignature.ALGO_ID_SIGNATURE_DSA;
    }

    /**
     * Constructor SignatureDSA
     *
     * @throws XMLSignatureException
     */
    public SignatureDSA() throws XMLSignatureException {
        this(null);
    }

    public SignatureDSA(Provider provider) throws XMLSignatureException {
        String algorithmID = JCEMapper.translateURItoJCEID(engineGetURI());
        LOG.debug("Created SignatureDSA using {}", algorithmID);

        try {
            if (provider == null) {
                String providerId = JCEMapper.getProviderId();
                if (providerId == null) {
                    this.signatureAlgorithm = Signature.getInstance(algorithmID);

                } else {
                    this.signatureAlgorithm = Signature.getInstance(algorithmID, providerId);
                }

            } else {
                this.signatureAlgorithm = Signature.getInstance(algorithmID, provider);
            }

        } catch (NoSuchAlgorithmException | NoSuchProviderException ex) {
            Object[] exArgs = {algorithmID, ex.getLocalizedMessage()};
            throw new XMLSignatureException("algorithms.NoSuchAlgorithm", exArgs);
        }
    }

    /**
     * {@inheritDoc}
     */
    protected void engineSetParameter(AlgorithmParameterSpec params)
        throws XMLSignatureException {
        try {
            this.signatureAlgorithm.setParameter(params);
        } catch (InvalidAlgorithmParameterException ex) {
            throw new XMLSignatureException(ex);
        }
    }

    /**
     * {@inheritDoc}
     */
    protected boolean engineVerify(byte[] signature)
        throws XMLSignatureException {
        try {
            if (LOG.isDebugEnabled()) {
                LOG.debug("Called DSA.verify() on " + XMLUtils.encodeToString(signature));
            }

            byte[] jcebytes = JavaUtils.convertDsaXMLDSIGtoASN1(signature, size / 8);

            return this.signatureAlgorithm.verify(jcebytes);
        } catch (SignatureException | IOException ex) {
            throw new XMLSignatureException(ex);
        }
    }

    /**
     * {@inheritDoc}
     */
    protected void engineInitVerify(Key publicKey) throws XMLSignatureException {
        engineInitVerify(publicKey, this.signatureAlgorithm);
        size = ((DSAKey)publicKey).getParams().getQ().bitLength();
    }

    /**
     * {@inheritDoc}
     */
    protected byte[] engineSign() throws XMLSignatureException {
        try {
            byte[] jcebytes = this.signatureAlgorithm.sign();

            return JavaUtils.convertDsaASN1toXMLDSIG(jcebytes, size / 8);
        } catch (IOException | SignatureException ex) {
            throw new XMLSignatureException(ex);
        }
    }

    /**
     * {@inheritDoc}
     */
    protected void engineInitSign(Key privateKey, SecureRandom secureRandom)
        throws XMLSignatureException {
        engineInitSign(privateKey, secureRandom, this.signatureAlgorithm);
        size = ((DSAKey)privateKey).getParams().getQ().bitLength();
    }

    /**
     * {@inheritDoc}
     */
    protected void engineInitSign(Key privateKey) throws XMLSignatureException {
        engineInitSign(privateKey, (SecureRandom)null);
    }

    /**
     * {@inheritDoc}
     */
    protected void engineUpdate(byte[] input) throws XMLSignatureException {
        try {
            this.signatureAlgorithm.update(input);
        } catch (SignatureException ex) {
            throw new XMLSignatureException(ex);
        }
    }

    /**
     * {@inheritDoc}
     */
    protected void engineUpdate(byte input) throws XMLSignatureException {
        try {
            this.signatureAlgorithm.update(input);
        } catch (SignatureException ex) {
            throw new XMLSignatureException(ex);
        }
    }

    /**
     * {@inheritDoc}
     */
    protected void engineUpdate(byte[] buf, int offset, int len) throws XMLSignatureException {
        try {
            this.signatureAlgorithm.update(buf, offset, len);
        } catch (SignatureException ex) {
            throw new XMLSignatureException(ex);
        }
    }

    /**
     * Method engineGetJCEAlgorithmString
     *
     * {@inheritDoc}
     */
    protected String engineGetJCEAlgorithmString() {
        return this.signatureAlgorithm.getAlgorithm();
    }

    /**
     * Method engineGetJCEProviderName
     *
     * {@inheritDoc}
     */
    protected String engineGetJCEProviderName() {
        return this.signatureAlgorithm.getProvider().getName();
    }

    /**
     * Method engineSetHMACOutputLength
     *
     * @param HMACOutputLength
     * @throws XMLSignatureException
     */
    protected void engineSetHMACOutputLength(int HMACOutputLength) throws XMLSignatureException {
        throw new XMLSignatureException("algorithms.HMACOutputLengthOnlyForHMAC");
    }

    /**
     * Method engineInitSign
     *
     * @param signingKey
     * @param algorithmParameterSpec
     * @throws XMLSignatureException
     */
    protected void engineInitSign(
        Key signingKey, AlgorithmParameterSpec algorithmParameterSpec
    ) throws XMLSignatureException {
        throw new XMLSignatureException("algorithms.CannotUseAlgorithmParameterSpecOnDSA");
    }

    public static class SHA256 extends SignatureDSA {

        public SHA256() throws XMLSignatureException {
            super();
        }

        public SHA256(Provider provider) throws XMLSignatureException {
            super(provider);
        }

        @Override
        public String engineGetURI() {
            return XMLSignature.ALGO_ID_SIGNATURE_DSA_SHA256;
        }
    }
}
