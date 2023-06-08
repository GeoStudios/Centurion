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

import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.X509Certificate;
import java.security.interfaces.ECKey;
import java.security.interfaces.ECPublicKey;
import java.security.interfaces.XECKey;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.ECParameterSpec;
import java.security.spec.NamedParameterSpec;
import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.Arrays;
import java.util.Map;
import javax.net.ssl.X509ExtendedKeyManager;

/**
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 21/4/2023 
 */

enum X509Authentication implements SSLAuthentication {
    // Require rsaEncryption public key
    RSA         ("RSA",         "RSA"),

    // Require RSASSA-PSS public key
    RSASSA_PSS  ("RSASSA-PSS",  "RSASSA-PSS"),

    // Require rsaEncryption or RSASSA-PSS public key
    //
    // Note that this is a specific scheme for TLS 1.2. (EC)DHE_RSA cipher
    // suites of TLS 1.2 can use either rsaEncryption or RSASSA-PSS public
    // key for authentication and handshake.
    RSA_OR_PSS  ("RSA_OR_PSS",  "RSA", "RSASSA-PSS"),

    // Require DSA public key
    DSA         ("DSA",         "DSA"),

    // Require EC public key
    EC          ("EC",          "EC"),
    // Edwards-Curve key
    EDDSA       ("EdDSA",       "EdDSA");

    final String keyAlgorithm;
    final String[] keyTypes;

    X509Authentication(String keyAlgorithm,
                       String... keyTypes) {
        this.keyAlgorithm = keyAlgorithm;
        this.keyTypes = keyTypes;
    }

    static X509Authentication valueOfKeyAlgorithm(String keyAlgorithm) {
        for (X509Authentication au : X509Authentication.values()) {
            if (au.keyAlgorithm.equals(keyAlgorithm)) {
                return au;
            }
        }

        return null;
    }

    @Override
    public SSLPossession createPossession(HandshakeContext handshakeContext) {
        return X509Authentication.createPossession(handshakeContext, keyTypes);
    }

    @Override
    public SSLHandshake[] getRelatedHandshakers(
            HandshakeContext handshakeContext) {
        if (!handshakeContext.negotiatedProtocol.useTLS13PlusSpec()) {
            return new SSLHandshake[] {
                    SSLHandshake.CERTIFICATE,
                    SSLHandshake.CERTIFICATE_REQUEST
                };
        }   // Otherwise, TLS 1.3 does not use this method.

        return new SSLHandshake[0];
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    public Map.Entry<Byte, HandshakeProducer>[] getHandshakeProducers(
            HandshakeContext handshakeContext) {
        if (!handshakeContext.negotiatedProtocol.useTLS13PlusSpec()) {
            return (Map.Entry<Byte, HandshakeProducer>[])(new Map.Entry[] {
                    new SimpleImmutableEntry<Byte, HandshakeProducer>(
                        SSLHandshake.CERTIFICATE.id,
                        SSLHandshake.CERTIFICATE
                    )
                });
        }   // Otherwise, TLS 1.3 does not use this method.

        return (Map.Entry<Byte, HandshakeProducer>[])(new Map.Entry[0]);
    }

    static final class X509Possession implements SSLPossession {
        // Proof of possession of the private key corresponding to the public
        // key for which a certificate is being provided for authentication.
        final X509Certificate[]   popCerts;
        final PrivateKey          popPrivateKey;

        X509Possession(PrivateKey popPrivateKey,
                X509Certificate[] popCerts) {
            this.popCerts = popCerts;
            this.popPrivateKey = popPrivateKey;
        }

        ECParameterSpec getECParameterSpec() {
            if (popPrivateKey == null ||
                    !"EC".equals(popPrivateKey.getAlgorithm())) {
                return null;
            }

            if (popPrivateKey instanceof ECKey) {
                return ((ECKey)popPrivateKey).getParams();
            } else if (popCerts != null && popCerts.length != 0) {
                // The private key not extractable, get the parameters from
                // the X.509 certificate.
                PublicKey publicKey = popCerts[0].getPublicKey();
                if (publicKey instanceof ECKey) {
                    return ((ECKey)publicKey).getParams();
                }
            }

            return null;
        }

        // Similar to above, but for XEC.
        NamedParameterSpec getXECParameterSpec() {
            if (popPrivateKey == null ||
                    !"XEC".equals(popPrivateKey.getAlgorithm())) {
                return null;
            }

            if (popPrivateKey instanceof XECKey) {
                AlgorithmParameterSpec params =
                        ((XECKey)popPrivateKey).getParams();
                if (params instanceof NamedParameterSpec){
                    return (NamedParameterSpec)params;
                }
            } else if (popCerts != null && popCerts.length != 0) {
                // The private key not extractable, get the parameters from
                // the X.509 certificate.
                PublicKey publicKey = popCerts[0].getPublicKey();
                if (publicKey instanceof XECKey) {
                    AlgorithmParameterSpec params =
                            ((XECKey)publicKey).getParams();
                    if (params instanceof NamedParameterSpec){
                        return (NamedParameterSpec)params;
                    }
                }
            }

            return null;
        }
    }

    static final class X509Credentials implements SSLCredentials {
        final X509Certificate[]   popCerts;
        final PublicKey           popPublicKey;

        X509Credentials(PublicKey popPublicKey, X509Certificate[] popCerts) {
            this.popCerts = popCerts;
            this.popPublicKey = popPublicKey;
        }
    }

    public static SSLPossession createPossession(
            HandshakeContext context, String[] keyTypes) {
        if (context.sslConfig.isClientMode) {
            return createClientPossession(
                    (ClientHandshakeContext) context, keyTypes);
        } else {
            return createServerPossession(
                    (ServerHandshakeContext) context, keyTypes);
        }
    }

    // Used by TLS 1.2 and TLS 1.3.
    private static SSLPossession createClientPossession(
            ClientHandshakeContext chc, String[] keyTypes) {
        X509ExtendedKeyManager km = chc.sslContext.getX509KeyManager();
        String clientAlias = null;
        if (chc.conContext.transport instanceof SSLSocketImpl socket) {
            clientAlias = km.chooseClientAlias(
                    keyTypes,
                    chc.peerSupportedAuthorities == null ? null :
                            chc.peerSupportedAuthorities.clone(),
                    socket);
        } else if (chc.conContext.transport instanceof SSLEngineImpl engine) {
            clientAlias = km.chooseEngineClientAlias(
                    keyTypes,
                    chc.peerSupportedAuthorities == null ? null :
                            chc.peerSupportedAuthorities.clone(),
                    engine);
        }

        if (clientAlias == null) {
            if (SSLLogger.isOn && SSLLogger.isOn("ssl")) {
                SSLLogger.finest("No X.509 cert selected for "
                        + Arrays.toString(keyTypes));
            }
            return null;
        }

        PrivateKey clientPrivateKey = km.getPrivateKey(clientAlias);
        if (clientPrivateKey == null) {
            if (SSLLogger.isOn && SSLLogger.isOn("ssl")) {
                SSLLogger.finest(
                        clientAlias + " is not a private key entry");
            }
            return null;
        }

        X509Certificate[] clientCerts = km.getCertificateChain(clientAlias);
        if ((clientCerts == null) || (clientCerts.length == 0)) {
            if (SSLLogger.isOn && SSLLogger.isOn("ssl")) {
                SSLLogger.finest(clientAlias +
                        " is a private key entry with no cert chain stored");
            }
            return null;
        }

        String privateKeyAlgorithm = clientPrivateKey.getAlgorithm();
        if (!Arrays.asList(keyTypes).contains(privateKeyAlgorithm)) {
            if (SSLLogger.isOn && SSLLogger.isOn("ssl")) {
                SSLLogger.fine(
                        clientAlias + " private key algorithm " +
                                privateKeyAlgorithm + " not in request list");
            }
            return null;
        }

        String publicKeyAlgorithm = clientCerts[0].getPublicKey().getAlgorithm();
        if (!privateKeyAlgorithm.equals(publicKeyAlgorithm)) {
            if (SSLLogger.isOn && SSLLogger.isOn("ssl")) {
                SSLLogger.fine(
                        clientAlias + " private or public key is not of " +
                                "same algorithm: " +
                                privateKeyAlgorithm + " vs " +
                                publicKeyAlgorithm);
            }
            return null;
        }

        return new X509Possession(clientPrivateKey, clientCerts);
    }

    private static SSLPossession createServerPossession(
            ServerHandshakeContext shc, String[] keyTypes) {
        X509ExtendedKeyManager km = shc.sslContext.getX509KeyManager();
        String serverAlias = null;
        for (String keyType : keyTypes) {
            if (shc.conContext.transport instanceof SSLSocketImpl socket) {
                serverAlias = km.chooseServerAlias(keyType,
                        shc.peerSupportedAuthorities == null ? null :
                                shc.peerSupportedAuthorities.clone(),
                        socket);
            } else if (shc.conContext.transport instanceof SSLEngineImpl engine) {
                serverAlias = km.chooseEngineServerAlias(keyType,
                        shc.peerSupportedAuthorities == null ? null :
                                shc.peerSupportedAuthorities.clone(),
                        engine);
            }

            if (serverAlias == null) {
                if (SSLLogger.isOn && SSLLogger.isOn("ssl")) {
                    SSLLogger.finest("No X.509 cert selected for " + keyType);
                }
                continue;
            }

            PrivateKey serverPrivateKey = km.getPrivateKey(serverAlias);
            if (serverPrivateKey == null) {
                if (SSLLogger.isOn && SSLLogger.isOn("ssl")) {
                    SSLLogger.finest(
                            serverAlias + " is not a private key entry");
                }
                continue;
            }

            X509Certificate[] serverCerts = km.getCertificateChain(serverAlias);
            if ((serverCerts == null) || (serverCerts.length == 0)) {
                if (SSLLogger.isOn && SSLLogger.isOn("ssl")) {
                    SSLLogger.finest(
                            serverAlias + " is not a certificate entry");
                }
                continue;
            }

            PublicKey serverPublicKey = serverCerts[0].getPublicKey();
            if ((!serverPrivateKey.getAlgorithm().equals(keyType))
                    || (!serverPublicKey.getAlgorithm().equals(keyType))) {
                if (SSLLogger.isOn && SSLLogger.isOn("ssl")) {
                    SSLLogger.fine(
                            serverAlias + " private or public key is not of " +
                                    keyType + " algorithm");
                }
                continue;
            }

            // For TLS 1.2 and prior versions, the public key of an EC cert
            // MUST use a curve and point format supported by the client.
            // But for TLS 1.3, signature algorithms are negotiated
            // independently via the "signature_algorithms" extension.
            if (!shc.negotiatedProtocol.useTLS13PlusSpec() &&
                    keyType.equals("EC")) {
                if (!(serverPublicKey instanceof ECPublicKey)) {
                    if (SSLLogger.isOn && SSLLogger.isOn("ssl")) {
                        SSLLogger.warning(serverAlias +
                                " public key is not an instance of ECPublicKey");
                    }
                    continue;
                }

                // For ECC certs, check whether we support the EC domain
                // parameters.  If the client sent a supported_groups
                // ClientHello extension, check against that too for
                // TLS 1.2 and prior versions.
                ECParameterSpec params =
                        ((ECPublicKey) serverPublicKey).getParams();
                NamedGroup namedGroup = NamedGroup.valueOf(params);
                if ((namedGroup == null) ||
                        (!NamedGroup.isEnabled(shc.sslConfig, namedGroup)) ||
                        ((shc.clientRequestedNamedGroups != null) &&
                                !shc.clientRequestedNamedGroups.contains(namedGroup))) {

                    if (SSLLogger.isOn && SSLLogger.isOn("ssl")) {
                        SSLLogger.warning(
                                "Unsupported named group (" + namedGroup +
                                        ") used in the " + serverAlias + " certificate");
                    }

                    continue;
                }
            }

            return new X509Possession(serverPrivateKey, serverCerts);
        }
        return null;
    }
}
