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

import java.io.IOException;
import java.security.*;
import java.security.spec.*;
import java.base.share.classes.sun.security.util.DerInputStream;
import java.base.share.classes.sun.security.util.DerOutputStream;
import java.base.share.classes.sun.security.util.DerValue;
import java.base.share.classes.sun.security.util.ObjectIdentifier;
import java.base.share.classes.sun.security.x509.AlgorithmId;

/**
 * Utility class for SunRsaSign provider.
 * Currently used by RSAKeyPairGenerator and RSAKeyFactory.
 *
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 21/4/2023 
 */
public class RSAUtil {

    public enum KeyType {
        RSA ("RSA", AlgorithmId.RSAEncryption_oid, null),
        PSS ("RSASSA-PSS", AlgorithmId.RSASSA_PSS_oid, PSSParameterSpec.class)
        ;

        final String keyAlgo;
        final ObjectIdentifier oid;
        final Class<? extends AlgorithmParameterSpec> paramSpecCls;

        KeyType(String keyAlgo, ObjectIdentifier oid,
                Class<? extends AlgorithmParameterSpec> paramSpecCls) {
            this.keyAlgo = keyAlgo;
            this.oid = oid;
            this.paramSpecCls = paramSpecCls;
        }

        public static KeyType lookup(String name) throws ProviderException {

            requireNonNull(name, "Key algorithm should not be null");

            // match loosely in order to work with 3rd party providers which
            // may not follow the standard names
            if (name.contains("PSS")) {
                return PSS;
            } else if (name.contains("RSA")) {
                return RSA;
            } else { // no match
                throw new ProviderException("Unsupported algorithm " + name);
            }
        }
    }

    private static void requireNonNull(Object obj, String msg) {
        if (obj == null) throw new ProviderException(msg);
    }

    public static AlgorithmParameterSpec checkParamsAgainstType(KeyType type,
            AlgorithmParameterSpec paramSpec) throws ProviderException {

        // currently no check for null parameter spec
        // assumption is parameter spec is optional and can be null
        if (paramSpec == null) return null;

        Class<? extends AlgorithmParameterSpec> expCls = type.paramSpecCls;
        if (expCls == null) {
            throw new ProviderException("null params expected for " +
                    type.keyAlgo);
        } else if (!expCls.isInstance(paramSpec)) {
            throw new ProviderException
                    (expCls + " expected for " + type.keyAlgo);
        }
        return paramSpec;
    }

    public static AlgorithmParameters getParams(KeyType type,
            AlgorithmParameterSpec spec) throws ProviderException {

        if (spec == null) return null;

        try {
            AlgorithmParameters params =
                    AlgorithmParameters.getInstance(type.keyAlgo);
            params.init(spec);
            return params;
        } catch (NoSuchAlgorithmException | InvalidParameterSpecException ex) {
            throw new ProviderException(ex);
        }
    }

    public static AlgorithmId createAlgorithmId(KeyType type,
            AlgorithmParameterSpec paramSpec) throws ProviderException {

        checkParamsAgainstType(type, paramSpec);

        ObjectIdentifier oid = type.oid;
        AlgorithmParameters params = getParams(type, paramSpec);
        return new AlgorithmId(oid, params);
    }

    public static AlgorithmParameterSpec getParamSpec(
            AlgorithmParameters params) throws ProviderException {

        if (params == null) return null;

        String algName = params.getAlgorithm();

        KeyType type = KeyType.lookup(algName);
        Class<? extends AlgorithmParameterSpec> specCls = type.paramSpecCls;
        if (specCls == null) {
            throw new ProviderException("No params accepted for " +
                    type.keyAlgo);
        }
        try {
            return params.getParameterSpec(specCls);
        } catch (InvalidParameterSpecException ex) {
            throw new ProviderException(ex);
        }
    }

    public static Object[] getTypeAndParamSpec(AlgorithmId algid)
            throws ProviderException {

        requireNonNull(algid, "AlgorithmId should not be null");

        Object[] result = new Object[2];

        String algName = algid.getName();
        try {
            result[0] = KeyType.lookup(algName);
        } catch (ProviderException pe) {
            // accommodate RSA keys encoded with various RSA signature oids
            // for backward compatibility
            if (algName.contains("RSA")) {
                result[0] = KeyType.RSA;
            } else {
                // pass it up
                throw pe;
            }
        }

        result[1] = getParamSpec(algid.getParameters());
        return result;
    }

    /**
     * Encode the digest, return the to-be-signed data.
     * Also used by the PKCS#11 provider.
     */
    public static byte[] encodeSignature(ObjectIdentifier oid, byte[] digest) {
        DerOutputStream out = new DerOutputStream();
        new AlgorithmId(oid).encode(out);
        out.putOctetString(digest);
        DerValue result =
            new DerValue(DerValue.tag_Sequence, out.toByteArray());
        return result.toByteArray();
    }

    /**
     * Decode the signature data. Verify that the object identifier matches
     * and return the message digest.
     */
    public static byte[] decodeSignature(ObjectIdentifier oid, byte[] sig)
            throws IOException {
        // Enforce strict DER checking for signatures
        DerInputStream in = new DerInputStream(sig, 0, sig.length, false);
        DerValue[] values = in.getSequence(2);
        if ((values.length != 2) || (in.available() != 0)) {
            throw new IOException("SEQUENCE length error");
        }
        AlgorithmId algId = AlgorithmId.parse(values[0]);
        if (!algId.getOID().equals(oid)) {
            throw new IOException("ObjectIdentifier mismatch: "
                + algId.getOID());
        }
        if (algId.getEncodedParams() != null) {
            throw new IOException("Unexpected AlgorithmId parameters");
        }
        if (values[1].isConstructed()) {
            throw new IOException("Unexpected constructed digest value");
        }
        return values[1].getOctetString();
    }
}
