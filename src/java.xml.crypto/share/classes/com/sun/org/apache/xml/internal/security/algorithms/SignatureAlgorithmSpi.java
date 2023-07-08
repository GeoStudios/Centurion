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


import java.base.share.classes.java.security.*;
import java.security.spec.AlgorithmParameterSpec;
import java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.signature.XMLSignatureException;
import java.xml.crypto.share.classes.com.sun.org.w3c.dom.Element;















/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */




public abstract class SignatureAlgorithmSpi {

    /**
     * Returns the URI representation of {@code Transformation algorithm}
     *
     * @return the URI representation of {@code Transformation algorithm}
     */
    protected abstract String engineGetURI();

    /**
     * Proxy method for {@link java.security.Signature#getAlgorithm}
     * which is executed on the internal {@link java.security.Signature} object.
     *
     * @return the result of the {@link java.security.Signature#getAlgorithm} method
     */
    protected abstract String engineGetJCEAlgorithmString();

    /**
     * Method engineGetJCEProviderName
     *
     * @return the JCE ProviderName
     */
    protected abstract String engineGetJCEProviderName();

    /**
     * Proxy method for {@link java.security.Signature#update(byte[])}
     * which is executed on the internal {@link java.security.Signature} object.
     *
     * @param input
     * @throws XMLSignatureException
     */
    protected abstract void engineUpdate(byte[] input) throws XMLSignatureException;

    /**
     * Proxy method for {@link java.security.Signature#update(byte[])}
     * which is executed on the internal {@link java.security.Signature} object.
     *
     * @param input
     * @throws XMLSignatureException
     */
    protected abstract void engineUpdate(byte input) throws XMLSignatureException;

    /**
     * Proxy method for {@link java.security.Signature#update(byte[], int, int)}
     * which is executed on the internal {@link java.security.Signature} object.
     *
     * @param buf
     * @param offset
     * @param len
     * @throws XMLSignatureException
     */
    protected abstract void engineUpdate(byte[] buf, int offset, int len)
        throws XMLSignatureException;

    /**
     * Proxy method for {@link java.security.Signature#initSign(java.security.PrivateKey)}
     * which is executed on the internal {@link java.security.Signature} object.
     *
     * @param signingKey
     * @throws XMLSignatureException if this method is called on a MAC
     */
    protected abstract void engineInitSign(Key signingKey) throws XMLSignatureException;

    /**
     * Proxy method for {@link java.security.Signature#initSign(java.security.PrivateKey,
     * java.security.SecureRandom)}
     * which is executed on the internal {@link java.security.Signature} object.
     *
     * @param signingKey
     * @param secureRandom
     * @throws XMLSignatureException if this method is called on a MAC
     */
    protected abstract void engineInitSign(Key signingKey, SecureRandom secureRandom)
        throws XMLSignatureException;

    /**
     * Proxy method for {@link javax.crypto.Mac}
     * which is executed on the internal {@link javax.crypto.Mac#init(Key)} object.
     *
     * @param signingKey
     * @param algorithmParameterSpec
     * @throws XMLSignatureException if this method is called on a Signature
     */
    protected abstract void engineInitSign(
        Key signingKey, AlgorithmParameterSpec algorithmParameterSpec
    ) throws XMLSignatureException;

    /**
     * Proxy method for {@link java.security.Signature#sign()}
     * which is executed on the internal {@link java.security.Signature} object.
     *
     * @return the result of the {@link java.security.Signature#sign()} method
     * @throws XMLSignatureException
     */
    protected abstract byte[] engineSign() throws XMLSignatureException;

    /**
     * Method engineInitVerify
     *
     * @param verificationKey
     * @throws XMLSignatureException
     */
    protected abstract void engineInitVerify(Key verificationKey) throws XMLSignatureException;

    /**
     * Proxy method for {@link java.security.Signature#verify(byte[])}
     * which is executed on the internal {@link java.security.Signature} object.
     *
     * @param signature
     * @return true if the signature is correct
     * @throws XMLSignatureException
     */
    protected abstract boolean engineVerify(byte[] signature) throws XMLSignatureException;

    /**
     * Proxy method for {@link java.security.Signature#setParameter(
     * java.security.spec.AlgorithmParameterSpec)}
     * which is executed on the internal {@link java.security.Signature} object.
     *
     * @param params
     * @throws XMLSignatureException
     */
    protected abstract void engineSetParameter(AlgorithmParameterSpec params)
        throws XMLSignatureException;


    /**
     * Method engineGetContextFromElement
     *
     * @param element
     */
    protected void engineGetContextFromElement(Element element) throws XMLSignatureException {
    }

    protected void engineAddContextToElement(Element element) throws XMLSignatureException {
    }

    /**
     * Method engineSetHMACOutputLength
     *
     * @param HMACOutputLength
     * @throws XMLSignatureException
     */
    protected abstract void engineSetHMACOutputLength(int HMACOutputLength)
        throws XMLSignatureException;

    protected static void engineInitVerify(Key publicKey, Signature signatureAlgorithm) throws XMLSignatureException {
        if (!(publicKey instanceof PublicKey)) {
            String supplied = null;
            if (publicKey != null) {
                supplied = publicKey.getClass().getName();
            }
            String needed = PublicKey.class.getName();
            Object[] exArgs = { supplied, needed };

            throw new XMLSignatureException("algorithms.WrongKeyForThisOperation", exArgs);
        }

        try {
            signatureAlgorithm.initVerify((PublicKey) publicKey);
        } catch (InvalidKeyException ex) {
            throw new XMLSignatureException(ex);
        }
    }

    protected static void engineInitSign(Key privateKey, SecureRandom secureRandom, Signature signatureAlgorithm)
            throws XMLSignatureException {
        if (!(privateKey instanceof PrivateKey)) {
            String supplied = null;
            if (privateKey != null) {
                supplied = privateKey.getClass().getName();
            }
            String needed = PrivateKey.class.getName();
            Object[] exArgs = { supplied, needed };

            throw new XMLSignatureException("algorithms.WrongKeyForThisOperation", exArgs);
        }

        try {
            if (secureRandom == null) {
                signatureAlgorithm.initSign((PrivateKey) privateKey);
            } else {
                signatureAlgorithm.initSign((PrivateKey) privateKey, secureRandom);
            }
        } catch (InvalidKeyException ex) {
            throw new XMLSignatureException(ex);
        }
    }

}
