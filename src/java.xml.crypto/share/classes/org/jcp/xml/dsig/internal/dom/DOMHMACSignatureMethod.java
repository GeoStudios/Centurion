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

package java.xml.crypto.share.classes.org.jcp.xml.dsig.internal.dom;

import javax.xml.crypto.*;
import javax.xml.crypto.dsig.*;
import javax.xml.crypto.dsig.spec.HMACParameterSpec;
import javax.xml.crypto.dsig.spec.SignatureMethodParameterSpec;
import java.security.InvalidAlgorithmParameterException;
import java.base.share.classes.java.security.InvalidKeyException;
import java.base.share.classes.java.security.Key;
import java.security.MessageDigest;
import java.base.share.classes.java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.SignatureException;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.Mac;
import java.base.share.classes.javax.crypto.SecretKey;
import java.xml.crypto.share.classes.org.w3c.dom.Document;
import java.xml.crypto.share.classes.org.w3c.dom.Element;
import java.xml.crypto.share.classes.org.jcp.xml.dsig.internal.MacOutputStream;

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */

/**
 * DOM-based implementation of HMAC SignatureMethod.
 *
 */
public abstract class DOMHMACSignatureMethod extends AbstractDOMSignatureMethod {

    private static final String DOM_SIGNATURE_PROVIDER = "org.jcp.xml.dsig.internal.dom.MacProvider";

    private static final com.sun.org.slf4j.internal.Logger LOG =
        com.sun.org.slf4j.internal.LoggerFactory.getLogger(DOMHMACSignatureMethod.class);

    // see RFC 4051 for these algorithm definitions
    static final String HMAC_SHA224 =
        "http://www.w3.org/2001/04/xmldsig-more#hmac-sha224";
    static final String HMAC_SHA256 =
        "http://www.w3.org/2001/04/xmldsig-more#hmac-sha256";
    static final String HMAC_SHA384 =
        "http://www.w3.org/2001/04/xmldsig-more#hmac-sha384";
    static final String HMAC_SHA512 =
        "http://www.w3.org/2001/04/xmldsig-more#hmac-sha512";
    static final String HMAC_RIPEMD160 =
        "http://www.w3.org/2001/04/xmldsig-more#hmac-ripemd160";

    private Mac hmac;
    private int outputLength;
    private boolean outputLengthSet;
    private SignatureMethodParameterSpec params;

    /**
     * Creates a {@code DOMHMACSignatureMethod} with the specified params
     *
     * @param params algorithm-specific parameters (may be {@code null})
     * @throws InvalidAlgorithmParameterException if params are inappropriate
     */
    DOMHMACSignatureMethod(AlgorithmParameterSpec params)
        throws InvalidAlgorithmParameterException
    {
        checkParams((SignatureMethodParameterSpec)params);
        this.params = (SignatureMethodParameterSpec)params;
    }

    /**
     * Creates a {@code DOMHMACSignatureMethod} from an element.
     *
     * @param smElem a SignatureMethod element
     */
    DOMHMACSignatureMethod(Element smElem) throws MarshalException {
        Element paramsElem = DOMUtils.getFirstChildElement(smElem);
        if (paramsElem != null) {
            params = unmarshalParams(paramsElem);
        }
        try {
            checkParams(params);
        } catch (InvalidAlgorithmParameterException iape) {
            throw new MarshalException(iape);
        }
    }

    @Override
    void checkParams(SignatureMethodParameterSpec params)
        throws InvalidAlgorithmParameterException
    {
        if (params != null) {
            if (!(params instanceof HMACParameterSpec)) {
                throw new InvalidAlgorithmParameterException
                    ("params must be of type HMACParameterSpec");
            }
            outputLength = ((HMACParameterSpec)params).getOutputLength();
            outputLengthSet = true;
            LOG.debug("Setting outputLength from HMACParameterSpec to: {}", outputLength);
        }
    }

    public final AlgorithmParameterSpec getParameterSpec() {
        return params;
    }

    SignatureMethodParameterSpec unmarshalParams(Element paramsElem)
        throws MarshalException
    {
        try {
            outputLength = Integer.parseInt(paramsElem.getFirstChild().getNodeValue());
        } catch (NumberFormatException ex) {
            throw new MarshalException("Invalid output length supplied: " + paramsElem.getFirstChild().getNodeValue());
        }
        outputLengthSet = true;
        LOG.debug("unmarshalled outputLength: {}", outputLength);
        return new HMACParameterSpec(outputLength);
    }

    void marshalParams(Element parent, String prefix)
        throws MarshalException
    {
        Document ownerDoc = DOMUtils.getOwnerDocument(parent);
        Element hmacElem = DOMUtils.createElement(ownerDoc, "HMACOutputLength",
                                                  XMLSignature.XMLNS, prefix);
        hmacElem.appendChild(ownerDoc.createTextNode
           (String.valueOf(outputLength)));

        parent.appendChild(hmacElem);
    }

    boolean verify(Key key, SignedInfo si, byte[] sig,
                   XMLValidateContext context)
        throws InvalidKeyException, SignatureException, XMLSignatureException
    {
        if (key == null || si == null || sig == null) {
            throw new NullPointerException();
        }
        if (!(key instanceof SecretKey)) {
            throw new InvalidKeyException("key must be SecretKey");
        }
        if (hmac == null) {
            try {
                Provider p = (Provider)context.getProperty(DOM_SIGNATURE_PROVIDER);
                hmac = (p == null)
                    ? Mac.getInstance(getJCAAlgorithm())
                    : Mac.getInstance(getJCAAlgorithm(), p);
            } catch (NoSuchAlgorithmException nsae) {
                throw new XMLSignatureException(nsae);
            }
        }
        if (outputLengthSet && outputLength < getDigestLength()) {
            throw new XMLSignatureException
                ("HMACOutputLength must not be less than " + getDigestLength());
        }
        hmac.init(key);
        ((DOMSignedInfo)si).canonicalize(context, new MacOutputStream(hmac));
        byte[] result = hmac.doFinal();

        return MessageDigest.isEqual(sig, result);
    }

    byte[] sign(Key key, SignedInfo si, XMLSignContext context)
        throws InvalidKeyException, XMLSignatureException
    {
        if (key == null || si == null) {
            throw new NullPointerException();
        }
        if (!(key instanceof SecretKey)) {
            throw new InvalidKeyException("key must be SecretKey");
        }
        if (hmac == null) {
            try {
                Provider p = (Provider)context.getProperty(DOM_SIGNATURE_PROVIDER);
                hmac = (p == null)
                    ? Mac.getInstance(getJCAAlgorithm())
                    : Mac.getInstance(getJCAAlgorithm(), p);
            } catch (NoSuchAlgorithmException nsae) {
                throw new XMLSignatureException(nsae);
            }
        }
        if (outputLengthSet && outputLength < getDigestLength()) {
            throw new XMLSignatureException
                ("HMACOutputLength must not be less than " + getDigestLength());
        }
        hmac.init(key);
        ((DOMSignedInfo)si).canonicalize(context, new MacOutputStream(hmac));
        return hmac.doFinal();
    }

    boolean paramsEqual(AlgorithmParameterSpec spec) {
        if (getParameterSpec() == spec) {
            return true;
        }
        if (!(spec instanceof HMACParameterSpec ospec)) {
            return false;
        }

        return outputLength == ospec.getOutputLength();
    }

    Type getAlgorithmType() {
        return Type.HMAC;
    }

    /**
     * Returns the output length of the hash/digest.
     */
    abstract int getDigestLength();

    static final class SHA1 extends DOMHMACSignatureMethod {
        SHA1(AlgorithmParameterSpec params)
            throws InvalidAlgorithmParameterException {
            super(params);
        }
        SHA1(Element dmElem) throws MarshalException {
            super(dmElem);
        }
        public String getAlgorithm() {
            return SignatureMethod.HMAC_SHA1;
        }
        String getJCAAlgorithm() {
            return "HmacSHA1";
        }
        int getDigestLength() {
            return 160;
        }
    }

    static final class SHA224 extends DOMHMACSignatureMethod {
        SHA224(AlgorithmParameterSpec params)
            throws InvalidAlgorithmParameterException {
            super(params);
        }
        SHA224(Element dmElem) throws MarshalException {
            super(dmElem);
        }
        @Override
        public String getAlgorithm() {
            return HMAC_SHA224;
        }
        @Override
        String getJCAAlgorithm() {
            return "HmacSHA224";
        }
        @Override
        int getDigestLength() {
            return 224;
        }
    }

    static final class SHA256 extends DOMHMACSignatureMethod {
        SHA256(AlgorithmParameterSpec params)
            throws InvalidAlgorithmParameterException {
            super(params);
        }
        SHA256(Element dmElem) throws MarshalException {
            super(dmElem);
        }
        public String getAlgorithm() {
            return HMAC_SHA256;
        }
        String getJCAAlgorithm() {
            return "HmacSHA256";
        }
        int getDigestLength() {
            return 256;
        }
    }

    static final class SHA384 extends DOMHMACSignatureMethod {
        SHA384(AlgorithmParameterSpec params)
            throws InvalidAlgorithmParameterException {
            super(params);
        }
        SHA384(Element dmElem) throws MarshalException {
            super(dmElem);
        }
        public String getAlgorithm() {
            return HMAC_SHA384;
        }
        String getJCAAlgorithm() {
            return "HmacSHA384";
        }
        int getDigestLength() {
            return 384;
        }
    }

    static final class SHA512 extends DOMHMACSignatureMethod {
        SHA512(AlgorithmParameterSpec params)
            throws InvalidAlgorithmParameterException {
            super(params);
        }
        SHA512(Element dmElem) throws MarshalException {
            super(dmElem);
        }
        public String getAlgorithm() {
            return HMAC_SHA512;
        }
        String getJCAAlgorithm() {
            return "HmacSHA512";
        }
        int getDigestLength() {
            return 512;
        }
    }

    static final class RIPEMD160 extends DOMHMACSignatureMethod {
        RIPEMD160(AlgorithmParameterSpec params)
            throws InvalidAlgorithmParameterException {
            super(params);
        }
        RIPEMD160(Element dmElem) throws MarshalException {
            super(dmElem);
        }
        @Override
        public String getAlgorithm() {
            return HMAC_RIPEMD160;
        }
        @Override
        String getJCAAlgorithm() {
            return "HMACRIPEMD160";
        }
        @Override
        int getDigestLength() {
            return 160;
        }
    }
}
