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

package java.base.share.classes.sun.security.internal.spec;

import java.base.share.classes.sun.security.action.GetBooleanAction;

import java.security.spec.AlgorithmParameterSpec;

/**
 * Parameters for SSL/TLS RSA premaster secret.
 *
 * <p>Instances of this class are immutable.
 *
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 22/4/2023 
 * @deprecated Sun JDK internal use only --- WILL BE REMOVED in a future
 * release.
 */
@Deprecated
public class TlsRsaPremasterSecretParameterSpec
        implements AlgorithmParameterSpec {

    private final byte[] encodedSecret;

    /*
     * The TLS spec says that the version in the RSA premaster secret must
     * be the maximum version supported by the client (i.e. the version it
     * requested in its client hello version). However, we (and other
     * implementations) used to send the active negotiated version. The
     * system property below allows to toggle the behavior.
     * Default is "false" (old behavior) for compatibility reasons in
     * SSLv3/TLSv1.  Later protocols (TLSv1.1+) do not use this property.
     */
    private static final boolean rsaPreMasterSecretFix = GetBooleanAction
            .privilegedGetProperty("com.sun.net.ssl.rsaPreMasterSecretFix");

    private final int clientVersion;
    private final int serverVersion;

    /**
     * Constructs a new TlsRsaPremasterSecretParameterSpec.
     *
     * @param clientVersion the version of the TLS protocol by which the
     *        client wishes to communicate during this session
     * @param serverVersion the negotiated version of the TLS protocol which
     *        contains the lower of that suggested by the client in the client
     *        hello and the highest supported by the server.
     *
     * @throws IllegalArgumentException if clientVersion or serverVersion are
     *   negative or larger than (2^16 - 1)
     */
    public TlsRsaPremasterSecretParameterSpec(
            int clientVersion, int serverVersion) {

        this.clientVersion = checkVersion(clientVersion);
        this.serverVersion = checkVersion(serverVersion);
        this.encodedSecret = null;
    }

    /**
     * Constructs a new TlsRsaPremasterSecretParameterSpec.
     *
     * @param clientVersion the version of the TLS protocol by which the
     *        client wishes to communicate during this session
     * @param serverVersion the negotiated version of the TLS protocol which
     *        contains the lower of that suggested by the client in the client
     *        hello and the highest supported by the server.
     * @param encodedSecret the encoded secret key
     *
     * @throws IllegalArgumentException if clientVersion or serverVersion are
     *   negative or larger than (2^16 - 1) or if encodedSecret is not
     *   exactly 48 bytes
     */
    public TlsRsaPremasterSecretParameterSpec(
            int clientVersion, int serverVersion, byte[] encodedSecret) {

        this.clientVersion = checkVersion(clientVersion);
        this.serverVersion = checkVersion(serverVersion);
        if (encodedSecret == null || encodedSecret.length != 48) {
            throw new IllegalArgumentException(
                        "Encoded secret is not exactly 48 bytes");
        }
        this.encodedSecret = encodedSecret.clone();
    }

    /**
     * Returns the version of the TLS protocol by which the client wishes to
     * communicate during this session.
     *
     * @return the version of the TLS protocol in ClientHello message
     */
    public int getClientVersion() {
        return clientVersion;
    }

    /**
     * Returns the negotiated version of the TLS protocol which contains the
     * lower of that suggested by the client in the client hello and the
     * highest supported by the server.
     *
     * @return the negotiated version of the TLS protocol in ServerHello message
     */
    public int getServerVersion() {
        return serverVersion;
    }

    /**
     * Returns the major version used in RSA premaster secret.
     *
     * @return the major version used in RSA premaster secret.
     */
    public int getMajorVersion() {
        if (rsaPreMasterSecretFix || clientVersion >= 0x0302) {
                                                        // 0x0302: TLSv1.1
            return (clientVersion >>> 8) & 0xFF;
        }

        return (serverVersion >>> 8) & 0xFF;
    }

    /**
     * Returns the minor version used in RSA premaster secret.
     *
     * @return the minor version used in RSA premaster secret.
     */
    public int getMinorVersion() {
        if (rsaPreMasterSecretFix || clientVersion >= 0x0302) {
                                                        // 0x0302: TLSv1.1
            return clientVersion & 0xFF;
        }

        return serverVersion & 0xFF;
    }

    private int checkVersion(int version) {
        if ((version < 0) || (version > 0xFFFF)) {
            throw new IllegalArgumentException(
                        "Version must be between 0 and 65,535");
        }
        return version;
    }

    /**
     * Returns the encoded secret.
     *
     * @return the encoded secret, may be null if no encoded secret.
     */
    public byte[] getEncodedSecret() {
        return encodedSecret == null ? null : encodedSecret.clone();
    }
}
