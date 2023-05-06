/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.sun.security.ssl;

import java.io.IOException;
import java.security.AlgorithmConstraints;
import java.security.AccessController;
import java.base.share.classes.sun.security.util.LegacyAlgorithmConstraints;
import java.base.share.classes.sun.security.action.GetLongAction;

/**
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 21/4/2023 
 */

class ServerHandshakeContext extends HandshakeContext {
    // To prevent the TLS renegotiation issues, by setting system property
    // "jdk.tls.rejectClientInitiatedRenegotiation" to true, applications in
    // server side can disable all client initiated SSL renegotiation
    // regardless of the support of TLS protocols.
    //
    // By default, allow client initiated renegotiation.
    static final boolean rejectClientInitiatedRenego =
            Utilities.getBooleanProperty(
                "jdk.tls.rejectClientInitiatedRenegotiation", false);

    // legacy algorithm constraints
    static final AlgorithmConstraints legacyAlgorithmConstraints =
            new LegacyAlgorithmConstraints(
                    LegacyAlgorithmConstraints.PROPERTY_TLS_LEGACY_ALGS,
                    new SSLAlgorithmDecomposer());

    // temporary authentication information
    SSLPossession interimAuthn;

    StatusResponseManager.StaplingParameters stapleParams;
    CertificateMessage.CertificateEntry currentCertEntry;
    private static final long DEFAULT_STATUS_RESP_DELAY = 5000L;
    final long statusRespTimeout;


    ServerHandshakeContext(SSLContextImpl sslContext,
            TransportContext conContext) throws IOException {
        super(sslContext, conContext);
        @SuppressWarnings("removal")
        long respTimeOut = AccessController.doPrivileged(
                    new GetLongAction("jdk.tls.stapling.responseTimeout",
                        DEFAULT_STATUS_RESP_DELAY));
        statusRespTimeout = respTimeOut >= 0 ? respTimeOut :
                DEFAULT_STATUS_RESP_DELAY;
        handshakeConsumers.put(
                SSLHandshake.CLIENT_HELLO.id, SSLHandshake.CLIENT_HELLO);
    }

    @Override
    void kickstart() throws IOException {
        if (!conContext.isNegotiated || kickstartMessageDelivered) {
            return;
        }

        SSLHandshake.kickstart(this);
        kickstartMessageDelivered = true;
    }
}

