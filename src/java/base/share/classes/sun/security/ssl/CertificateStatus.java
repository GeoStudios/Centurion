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

import java.io.IOException;
import java.nio.ByteBuffer;
import java.text.MessageFormat;
import java.util.List;
import java.util.ArrayList;
import java.util.Locale;
import javax.net.ssl.SSLHandshakeException;
import java.security.cert.X509Certificate;
import java.base.share.classes.sun.security.provider.certpath.OCSPResponse;
import java.base.share.classes.sun.security.ssl.SSLHandshake.HandshakeMessage;
import static java.base.share.classes.sun.security.ssl.CertStatusExtension.*;
import static java.base.share.classes.sun.security.ssl.CertificateMessage.*;

/**
 * Consumers and producers for the CertificateStatus handshake message.
 * This message takes one of two related but slightly different forms,
 * depending on the type of stapling selected by the server.  The message
 * data will be of the form(s):
 *
 *  [status_request, RFC 6066]
 *
 *  struct {
 *      CertificateStatusType status_type;
 *      select (status_type) {
 *          case ocsp: OCSPResponse;
 *      } response;
 *  } CertificateStatus;
 *
 *  opaque OCSPResponse<1..2^24-1>;
 *
 *  [status_request_v2, RFC 6961]
 *
 *  struct {
 *      CertificateStatusType status_type;
 *      select (status_type) {
 *        case ocsp: OCSPResponse;
 *        case ocsp_multi: OCSPResponseList;
 *      } response;
 *  } CertificateStatus;
 *
 *  opaque OCSPResponse<0..2^24-1>;
 *
 *  struct {
 *      OCSPResponse ocsp_response_list<1..2^24-1>;
 *  } OCSPResponseList;
 *  
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 21/4/2023 
 */

final class CertificateStatus {
    static final SSLConsumer handshakeConsumer =
            new CertificateStatusConsumer();
    static final HandshakeProducer handshakeProducer =
            new CertificateStatusProducer();
    static final HandshakeAbsence handshakeAbsence =
            new CertificateStatusAbsence();

    /**
     * The CertificateStatus handshake message.
     */
    static final class CertificateStatusMessage extends HandshakeMessage {

        final CertStatusRequestType statusType;
        final int encodedResponsesLen;
        final int messageLength;
        final List<byte[]> encodedResponses = new ArrayList<>();

        CertificateStatusMessage(HandshakeContext handshakeContext) {
            super(handshakeContext);

            ServerHandshakeContext shc =
                    (ServerHandshakeContext)handshakeContext;

            // Get the Certificates from the SSLContextImpl amd the Stapling
            // parameters
            StatusResponseManager.StaplingParameters stapleParams =
                    shc.stapleParams;
            if (stapleParams == null) {
                throw new IllegalArgumentException(
                        "Unexpected null stapling parameters");
            }

            X509Certificate[] certChain =
                (X509Certificate[])shc.handshakeSession.getLocalCertificates();
            if (certChain == null) {
                throw new IllegalArgumentException(
                        "Unexpected null certificate chain");
            }

            // Walk the certificate list and add the correct encoded responses
            // to the encoded responses list
            statusType = stapleParams.statReqType;
            int encodedLen = 0;
            if (statusType == CertStatusRequestType.OCSP) {
                // Just worry about the first cert in the chain
                byte[] resp = stapleParams.responseMap.get(certChain[0]);
                if (resp == null) {
                    // A not-found return status means we should include
                    // a zero-length response in CertificateStatus.
                    // This is highly unlikely to happen in practice.
                    resp = new byte[0];
                }
                encodedResponses.add(resp);
                encodedLen += resp.length + 3;
            } else if (statusType == CertStatusRequestType.OCSP_MULTI) {
                for (X509Certificate cert : certChain) {
                    byte[] resp = stapleParams.responseMap.get(cert);
                    if (resp == null) {
                        resp = new byte[0];
                    }
                    encodedResponses.add(resp);
                    encodedLen += resp.length + 3;
                }
            } else {
                throw new IllegalArgumentException(
                        "Unsupported StatusResponseType: " + statusType);
            }

            encodedResponsesLen = encodedLen;
            messageLength = messageLength(statusType, encodedResponsesLen);
        }

        CertificateStatusMessage(HandshakeContext handshakeContext,
                ByteBuffer m) throws IOException {
            super(handshakeContext);

            statusType = CertStatusRequestType.valueOf((byte)Record.getInt8(m));
            if (statusType == CertStatusRequestType.OCSP) {
                byte[] respDER = Record.getBytes24(m);
                // Convert the incoming bytes to a OCSPResponse structure
                if (respDER.length > 0) {
                    encodedResponses.add(respDER);
                    encodedResponsesLen = 3 + respDER.length;
                } else {
                    throw handshakeContext.conContext.fatal(
                            Alert.HANDSHAKE_FAILURE,
                            "Zero-length OCSP Response");
                }
            } else if (statusType == CertStatusRequestType.OCSP_MULTI) {
                int respListLen = Record.getInt24(m);
                encodedResponsesLen = respListLen;

                // Add each OCSP response into the array list in the order
                // we receive them off the wire.  A zero-length array is
                // allowed for ocsp_multi, and means that a response for
                // a given certificate is not available.
                while (respListLen > 0) {
                    byte[] respDER = Record.getBytes24(m);
                    encodedResponses.add(respDER);
                    respListLen -= (respDER.length + 3);
                }

                if (respListLen != 0) {
                    throw handshakeContext.conContext.fatal(
                            Alert.INTERNAL_ERROR,
                            "Bad OCSP response list length");
                }
            } else {
                throw handshakeContext.conContext.fatal(
                        Alert.HANDSHAKE_FAILURE,
                        "Unsupported StatusResponseType: " + statusType);
            }
            messageLength = messageLength(statusType, encodedResponsesLen);
        }

        private static int messageLength(
                CertStatusRequestType statusType, int encodedResponsesLen) {
            if (statusType == CertStatusRequestType.OCSP) {
                return 1 + encodedResponsesLen;
            } else if (statusType == CertStatusRequestType.OCSP_MULTI) {
                return 4 + encodedResponsesLen;
            }

            return -1;
        }

        @Override
        public SSLHandshake handshakeType() {
            return SSLHandshake.CERTIFICATE_STATUS;
        }

        @Override
        public int messageLength() {
            return messageLength;
        }

        @Override
        public void send(HandshakeOutStream s) throws IOException {
            s.putInt8(statusType.id);
            if (statusType == CertStatusRequestType.OCSP) {
                s.putBytes24(encodedResponses.get(0));
            } else if (statusType == CertStatusRequestType.OCSP_MULTI) {
                s.putInt24(encodedResponsesLen);
                for (byte[] respBytes : encodedResponses) {
                    s.putBytes24(respBytes);
                }
            } else {
                // It is highly unlikely that we will fall into this section
                // of the code.
                throw new SSLHandshakeException("Unsupported status_type: " +
                        statusType.id);
            }
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();

            // Stringify the encoded OCSP response list
            for (byte[] respDER : encodedResponses) {
                if (respDER.length > 0) {
                    try {
                        OCSPResponse oResp = new OCSPResponse(respDER);
                        sb.append(oResp.toString()).append("\n");
                    } catch (IOException ioe) {
                        sb.append("OCSP Response Exception: ").append(ioe)
                                .append("\n");
                    }
                } else {
                    sb.append("<Zero-length entry>\n");
                }
            }

            MessageFormat messageFormat = new MessageFormat(
                    """
                            "CertificateStatus": '{'
                              "type"                : "{0}",
                              "responses "          : [
                            {1}
                              ]
                            '}'""",
                Locale.ENGLISH);
            Object[] messageFields = {
                statusType.name,
                Utilities.indent(Utilities.indent(sb.toString()))
            };

            return messageFormat.format(messageFields);
        }
    }

    /**
     * The CertificateStatus handshake message consumer.
     */
    private static final class CertificateStatusConsumer
            implements SSLConsumer {
        // Prevent instantiation of this class.
        private CertificateStatusConsumer() {
            // blank
        }

        @Override
        public void consume(ConnectionContext context,
                ByteBuffer message) throws IOException {
            ClientHandshakeContext chc = (ClientHandshakeContext)context;
            CertificateStatusMessage cst =
                    new CertificateStatusMessage(chc, message);

            // Log the message
            if (SSLLogger.isOn && SSLLogger.isOn("ssl,handshake")) {
                SSLLogger.fine(
                        "Consuming server CertificateStatus handshake message",
                        cst);
            }

            // Pin the received responses to the SSLSessionImpl.  It will
            // be retrieved by the X509TrustManagerImpl during the certificate
            // checking phase.
            chc.handshakeSession.setStatusResponses(cst.encodedResponses);

            // Now perform the check
            T12CertificateConsumer.checkServerCerts(chc, chc.deferredCerts);

            // Update the handshake consumers to remove this message, indicating
            // that it has been processed.
            chc.handshakeConsumers.remove(SSLHandshake.CERTIFICATE_STATUS.id);
        }
    }

    /**
     * The CertificateStatus handshake message consumer.
     */
    private static final class CertificateStatusProducer
            implements HandshakeProducer {
        // Prevent instantiation of this class.
        private CertificateStatusProducer() {
            // blank
        }

        @Override
        public byte[] produce(ConnectionContext context,
                HandshakeMessage message) throws IOException {
            // Only the server-side should be a producer of this message
            ServerHandshakeContext shc = (ServerHandshakeContext)context;

            // If stapling is not active, immediately return without producing
            // a message or any further processing.
            if (!shc.staplingActive) {
                return null;
            }

            // Create the CertificateStatus message from info in the
            CertificateStatusMessage csm = new CertificateStatusMessage(shc);
            if (SSLLogger.isOn && SSLLogger.isOn("ssl,handshake")) {
                SSLLogger.fine(
                    "Produced server CertificateStatus handshake message", csm);
            }

            // Output the handshake message.
            csm.write(shc.handshakeOutput);
            shc.handshakeOutput.flush();

            // The handshake message has been delivered.
            return null;
        }
    }

    private static final class CertificateStatusAbsence
            implements HandshakeAbsence {
        // Prevent instantiation of this class
        private CertificateStatusAbsence() {
            // blank
        }

        @Override
        public void absent(ConnectionContext context,
                HandshakeMessage message) throws IOException {
            ClientHandshakeContext chc = (ClientHandshakeContext)context;

            // Processing should only continue if stapling is active
            if (chc.staplingActive) {
                // Because OCSP stapling is active, it means two things
                // if we're here: 1) The server hello asserted the
                // status_request[_v2] extension.  2) The CertificateStatus
                // message was not sent.  This means that cert path checking
                // was deferred, but must happen immediately.
                if (SSLLogger.isOn && SSLLogger.isOn("ssl,handshake")) {
                    SSLLogger.fine("Server did not send CertificateStatus, " +
                            "checking cert chain without status info.");
                }
                T12CertificateConsumer.checkServerCerts(chc, chc.deferredCerts);
            }
        }
    }
}

