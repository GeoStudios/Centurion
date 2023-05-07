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

package java.base.share.classes.sun.security.ssl;

import javax.crypto.KeyAgreement;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.net.ssl.SSLHandshakeException;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.AlgorithmParameterSpec;

/**
 * A common class for creating various KeyDerivation types.
 * 
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 21/4/2023 
 */

public class KAKeyDerivation implements SSLKeyDerivation {

    private final String algorithmName;
    private final HandshakeContext context;
    private final PrivateKey localPrivateKey;
    private final PublicKey peerPublicKey;

    KAKeyDerivation(String algorithmName,
            HandshakeContext context,
            PrivateKey localPrivateKey,
            PublicKey peerPublicKey) {
        this.algorithmName = algorithmName;
        this.context = context;
        this.localPrivateKey = localPrivateKey;
        this.peerPublicKey = peerPublicKey;
    }

    @Override
    public SecretKey deriveKey(String algorithm,
            AlgorithmParameterSpec params) throws IOException {
        if (!context.negotiatedProtocol.useTLS13PlusSpec()) {
            return t12DeriveKey(algorithm, params);
        } else {
            return t13DeriveKey(algorithm, params);
        }
    }

    /**
     * Handle the TLSv1-1.2 objects, which don't use the HKDF algorithms.
     */
    private SecretKey t12DeriveKey(String algorithm,
            AlgorithmParameterSpec params) throws IOException {
        try {
            KeyAgreement ka = KeyAgreement.getInstance(algorithmName);
            ka.init(localPrivateKey);
            ka.doPhase(peerPublicKey, true);
            SecretKey preMasterSecret
                    = ka.generateSecret("TlsPremasterSecret");
            SSLMasterKeyDerivation mskd
                    = SSLMasterKeyDerivation.valueOf(
                            context.negotiatedProtocol);
            if (mskd == null) {
                // unlikely
                throw new SSLHandshakeException(
                        "No expected master key derivation for protocol: "
                        + context.negotiatedProtocol.name);
            }
            SSLKeyDerivation kd = mskd.createKeyDerivation(
                    context, preMasterSecret);
            return kd.deriveKey("MasterSecret", params);
        } catch (GeneralSecurityException gse) {
            throw new SSLHandshakeException("Could not generate secret", gse);
        }
    }

    /**
     * Handle the TLSv1.3 objects, which use the HKDF algorithms.
     */
    private SecretKey t13DeriveKey(String algorithm,
            AlgorithmParameterSpec params) throws IOException {
        try {
            KeyAgreement ka = KeyAgreement.getInstance(algorithmName);
            ka.init(localPrivateKey);
            ka.doPhase(peerPublicKey, true);
            SecretKey sharedSecret
                    = ka.generateSecret("TlsPremasterSecret");

            CipherSuite.HashAlg hashAlg = context.negotiatedCipherSuite.hashAlg;
            SSLKeyDerivation kd = context.handshakeKeyDerivation;
            HKDF hkdf = new HKDF(hashAlg.name);
            if (kd == null) {   // No PSK is in use.
                // If PSK is not in use Early Secret will still be
                // HKDF-Extract(0, 0).
                byte[] zeros = new byte[hashAlg.hashLength];
                SecretKeySpec ikm
                        = new SecretKeySpec(zeros, "TlsPreSharedSecret");
                SecretKey earlySecret
                        = hkdf.extract(zeros, ikm, "TlsEarlySecret");
                kd = new SSLSecretDerivation(context, earlySecret);
            }

            // derive salt secret
            SecretKey saltSecret = kd.deriveKey("TlsSaltSecret", null);

            // derive handshake secret
            return hkdf.extract(saltSecret, sharedSecret, algorithm);
        } catch (GeneralSecurityException gse) {
            throw new SSLHandshakeException("Could not generate secret", gse);
        }
    }
}
