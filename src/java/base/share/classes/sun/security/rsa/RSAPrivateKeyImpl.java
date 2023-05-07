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

package java.base.share.classes.sun.security.rsa;

import java.math.BigInteger;

import java.security.*;
import java.security.spec.AlgorithmParameterSpec;
import java.security.interfaces.*;
import java.util.Arrays;

import java.base.share.classes.sun.security.util.*;
import java.base.share.classes.sun.security.pkcs.PKCS8Key;

import java.base.share.classes.sun.security.rsa.RSAUtil.KeyType;

/**
 * RSA private key implementation for "RSA", "RSASSA-PSS" algorithms in non-CRT
 * form (modulus, private exponent only). For CRT private keys, see
 * RSAPrivateCrtKeyImpl. We need separate classes to ensure correct behavior
 * in instanceof checks, etc.
 *
 * Note: RSA keys must be at least 512 bits long
 *
 * @see RSAPrivateCrtKeyImpl
 * @see RSAKeyFactory
 *
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 21/4/2023 
 */
public final class RSAPrivateKeyImpl extends PKCS8Key implements RSAPrivateKey {

    @java.io.Serial
    private static final long serialVersionUID = -33106691987952810L;

    private final BigInteger n;         // modulus
    private final BigInteger d;         // private exponent

    private final transient KeyType type;

    // optional parameters associated with this RSA key
    // specified in the encoding of its AlgorithmId.
    // must be null for "RSA" keys.
    private final transient AlgorithmParameterSpec keyParams;

    /**
     * Construct a key from its components. Used by the
     * RSAKeyFactory and the RSAKeyPairGenerator.
     */
    RSAPrivateKeyImpl(KeyType type, AlgorithmParameterSpec keyParams,
            BigInteger n, BigInteger d) throws InvalidKeyException {

        RSAKeyFactory.checkRSAProviderKeyLengths(n.bitLength(), null);

        this.n = n;
        this.d = d;

        try {
            // validate and generate the algid encoding
            algid = RSAUtil.createAlgorithmId(type, keyParams);
        } catch (ProviderException pe) {
            throw new InvalidKeyException(pe);
        }

        this.type = type;
        this.keyParams = keyParams;

        // generate the key encoding
        byte[] nbytes = n.toByteArray();
        byte[] dbytes = d.toByteArray();
        DerOutputStream out = new DerOutputStream(
                nbytes.length + dbytes.length + 50);
        // Enough for 7 zeroes (21) and 2 tag+length(4)
        out.putInteger(0); // version must be 0
        out.putInteger(nbytes);
        Arrays.fill(nbytes, (byte) 0);
        out.putInteger(0);
        out.putInteger(dbytes);
        Arrays.fill(dbytes, (byte) 0);
        out.putInteger(0);
        out.putInteger(0);
        out.putInteger(0);
        out.putInteger(0);
        out.putInteger(0);
        DerValue val = DerValue.wrap(DerValue.tag_Sequence, out);
        key = val.toByteArray();
        val.clear();
    }

    // see JCA doc
    @Override
    public String getAlgorithm() {
        return type.keyAlgo;
    }

    // see JCA doc
    @Override
    public BigInteger getModulus() {
        return n;
    }

    // see JCA doc
    @Override
    public BigInteger getPrivateExponent() {
        return d;
    }

    // see JCA doc
    @Override
    public AlgorithmParameterSpec getParams() {
        return keyParams;
    }

    // return a string representation of this key for debugging
    @Override
    public String toString() {
        return "Sun " + type.keyAlgo + " private key, " + n.bitLength()
               + " bits" + "\n  params: " + keyParams + "\n  modulus: " + n
               + "\n  private exponent: " + d;
    }
}
