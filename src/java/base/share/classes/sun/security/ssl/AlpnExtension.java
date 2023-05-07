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
import java.nio.charset.Charset;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.Security;
import java.util.*;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLProtocolException;
import javax.net.ssl.SSLSocket;
import java.base.share.classes.sun.security.ssl.SSLExtension.ExtensionConsumer;
import java.base.share.classes.sun.security.ssl.SSLExtension.SSLExtensionSpec;
import java.base.share.classes.sun.security.ssl.SSLHandshake.HandshakeMessage;

/**
 * Pack of the "application_layer_protocol_negotiation" extensions [RFC 7301].
 * 
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 21/4/2023 
 */

final class AlpnExtension {
    static final HandshakeProducer chNetworkProducer = new CHAlpnProducer();
    static final ExtensionConsumer chOnLoadConsumer = new CHAlpnConsumer();
    static final HandshakeAbsence chOnLoadAbsence = new CHAlpnAbsence();

    static final HandshakeProducer shNetworkProducer = new SHAlpnProducer();
    static final ExtensionConsumer shOnLoadConsumer = new SHAlpnConsumer();
    static final HandshakeAbsence shOnLoadAbsence = new SHAlpnAbsence();

    // Note: we reuse ServerHello operations for EncryptedExtensions for now.
    // Please be careful about any code or specification changes in the future.
    static final HandshakeProducer eeNetworkProducer = new SHAlpnProducer();
    static final ExtensionConsumer eeOnLoadConsumer = new SHAlpnConsumer();
    static final HandshakeAbsence eeOnLoadAbsence = new SHAlpnAbsence();

    static final SSLStringizer alpnStringizer = new AlpnStringizer();

    // Encoding Charset to convert between String and byte[]
    static final Charset alpnCharset;

    static {
        @SuppressWarnings("removal")
        String alpnCharsetString = AccessController.doPrivileged(
                (PrivilegedAction<String>) ()
                        -> Security.getProperty("jdk.tls.alpnCharset"));
        if ((alpnCharsetString == null)
                || (alpnCharsetString.length() == 0)) {
            alpnCharsetString = "ISO_8859_1";
        }
        alpnCharset = Charset.forName(alpnCharsetString);
    }

    /**
     * The "application_layer_protocol_negotiation" extension.
     *
     * See RFC 7301 for the specification of this extension.
     */
    static final class AlpnSpec implements SSLExtensionSpec {
        final List<String> applicationProtocols;

        private AlpnSpec(String[] applicationProtocols) {
            this.applicationProtocols = List.of(applicationProtocols);
        }

        private AlpnSpec(HandshakeContext hc,
                ByteBuffer buffer) throws IOException {
            // ProtocolName protocol_name_list<2..2^16-1>, RFC 7301.
            if (buffer.remaining() < 2) {
                throw hc.conContext.fatal(Alert.DECODE_ERROR,
                        new SSLProtocolException(
                    "Invalid application_layer_protocol_negotiation: " +
                    "insufficient data (length=" + buffer.remaining() + ")"));
            }

            int listLen = Record.getInt16(buffer);
            if (listLen < 2 || listLen != buffer.remaining()) {
                throw hc.conContext.fatal(Alert.DECODE_ERROR,
                        new SSLProtocolException(
                    "Invalid application_layer_protocol_negotiation: " +
                    "incorrect list length (length=" + listLen + ")"));
            }

            List<String> protocolNames = new LinkedList<>();
            while (buffer.hasRemaining()) {
                // opaque ProtocolName<1..2^8-1>, RFC 7301.
                byte[] bytes = Record.getBytes8(buffer);
                if (bytes.length == 0) {
                    throw hc.conContext.fatal(Alert.DECODE_ERROR,
                            new SSLProtocolException(
                        "Invalid application_layer_protocol_negotiation " +
                        "extension: empty application protocol name"));
                }

                String appProtocol = new String(bytes, alpnCharset);
                protocolNames.add(appProtocol);
            }

            this.applicationProtocols =
                    Collections.unmodifiableList(protocolNames);
        }

        @Override
        public String toString() {
            return applicationProtocols.toString();
        }
    }

    private static final class AlpnStringizer implements SSLStringizer {
        @Override
        public String toString(HandshakeContext hc, ByteBuffer buffer) {
            try {
                return (new AlpnSpec(hc, buffer)).toString();
            } catch (IOException ioe) {
                // For debug logging only, so please swallow exceptions.
                return ioe.getMessage();
            }
        }
    }

    /**
     * Network data producer of the extension in a ClientHello
     * handshake message.
     */
    private static final class CHAlpnProducer implements HandshakeProducer {
        static final int MAX_AP_LENGTH = 255;
        static final int MAX_AP_LIST_LENGTH = 65535;

        // Prevent instantiation of this class.
        private CHAlpnProducer() {
            // blank
        }

        @Override
        public byte[] produce(ConnectionContext context,
                HandshakeMessage message) throws IOException {
            // The producing happens in client side only.
            ClientHandshakeContext chc = (ClientHandshakeContext)context;

            // Is it a supported and enabled extension?
            if (!chc.sslConfig.isAvailable(SSLExtension.CH_ALPN)) {
                if (SSLLogger.isOn && SSLLogger.isOn("ssl,handshake")) {
                    SSLLogger.info(
                            "Ignore client unavailable extension: " +
                            SSLExtension.CH_ALPN.name);
                }

                chc.applicationProtocol = "";
                chc.conContext.applicationProtocol = "";
                return null;
            }

            String[] laps = chc.sslConfig.applicationProtocols;
            if ((laps == null) || (laps.length == 0)) {
                if (SSLLogger.isOn && SSLLogger.isOn("ssl,handshake")) {
                    SSLLogger.info(
                            "No available application protocols");
                }
                return null;
            }

            // Produce the extension:  first find the overall length
            int listLength = 0;     // ProtocolNameList length
            for (String ap : laps) {
                int length = ap.getBytes(alpnCharset).length;
                if (length == 0) {
                    // log the configuration problem
                    if (SSLLogger.isOn && SSLLogger.isOn("ssl,handshake")) {
                        SSLLogger.severe(
                                "Application protocol name cannot be empty");
                    }

                    throw chc.conContext.fatal(Alert.ILLEGAL_PARAMETER,
                            "Application protocol name cannot be empty");
                }

                if (length <= MAX_AP_LENGTH) {
                    // opaque ProtocolName<1..2^8-1>, RFC 7301.
                    listLength += (length + 1);
                } else {
                    // log the configuration problem
                    if (SSLLogger.isOn && SSLLogger.isOn("ssl,handshake")) {
                        SSLLogger.severe(
                                "Application protocol name (" + ap +
                                ") exceeds the size limit (" +
                                MAX_AP_LENGTH + " bytes)");
                    }

                    throw chc.conContext.fatal(Alert.ILLEGAL_PARAMETER,
                                "Application protocol name (" + ap +
                                ") exceeds the size limit (" +
                                MAX_AP_LENGTH + " bytes)");
                }

                if (listLength > MAX_AP_LIST_LENGTH) {
                    // log the configuration problem
                    if (SSLLogger.isOn && SSLLogger.isOn("ssl,handshake")) {
                        SSLLogger.severe(
                                "The configured application protocols (" +
                                Arrays.toString(laps) +
                                ") exceed the size limit (" +
                                MAX_AP_LIST_LENGTH + " bytes)");
                    }

                    throw chc.conContext.fatal(Alert.ILLEGAL_PARAMETER,
                                "The configured application protocols (" +
                                Arrays.toString(laps) +
                                ") exceed the size limit (" +
                                MAX_AP_LIST_LENGTH + " bytes)");
                }
            }

            // ProtocolName protocol_name_list<2..2^16-1>, RFC 7301.
            byte[] extData = new byte[listLength + 2];
            ByteBuffer m = ByteBuffer.wrap(extData);
            Record.putInt16(m, listLength);

            // opaque ProtocolName<1..2^8-1>;
            for (String ap : laps) {
                Record.putBytes8(m, ap.getBytes(alpnCharset));
            }

            // Update the context.
            chc.handshakeExtensions.put(SSLExtension.CH_ALPN,
                    new AlpnSpec(chc.sslConfig.applicationProtocols));

            return extData;
        }
    }

    /**
     * Network data consumer of the extension in a ClientHello
     * handshake message.
     */
    private static final class CHAlpnConsumer implements ExtensionConsumer {
        // Prevent instantiation of this class.
        private CHAlpnConsumer() {
            // blank
        }

        @Override
        public void consume(ConnectionContext context,
            HandshakeMessage message, ByteBuffer buffer) throws IOException {
            // The consuming happens in server side only.
            ServerHandshakeContext shc = (ServerHandshakeContext)context;

            // Is it a supported and enabled extension?
            if (!shc.sslConfig.isAvailable(SSLExtension.CH_ALPN)) {
                shc.applicationProtocol = "";
                shc.conContext.applicationProtocol = "";
                if (SSLLogger.isOn && SSLLogger.isOn("ssl,handshake")) {
                    SSLLogger.info(
                            "Ignore server unavailable extension: " +
                            SSLExtension.CH_ALPN.name);
                }
                return;     // ignore the extension
            }

            // Is the extension enabled?
            boolean noAPSelector;
            if (shc.conContext.transport instanceof SSLEngine) {
                noAPSelector = (shc.sslConfig.engineAPSelector == null);
            } else {
                noAPSelector = (shc.sslConfig.socketAPSelector == null);
            }

            boolean noAlpnProtocols =
                    shc.sslConfig.applicationProtocols == null ||
                    shc.sslConfig.applicationProtocols.length == 0;
            if (noAPSelector && noAlpnProtocols) {
                shc.applicationProtocol = "";
                shc.conContext.applicationProtocol = "";
                if (SSLLogger.isOn && SSLLogger.isOn("ssl,handshake")) {
                    SSLLogger.fine(
                            "Ignore server unenabled extension: " +
                            SSLExtension.CH_ALPN.name);
                }
                return;     // ignore the extension
            }

            // Parse the extension.
            AlpnSpec spec = new AlpnSpec(shc, buffer);

            // Update the context.
            if (noAPSelector) {     // noAlpnProtocols is false
                List<String> protocolNames = spec.applicationProtocols;
                boolean matched = false;
                // Use server application protocol preference order.
                for (String ap : shc.sslConfig.applicationProtocols) {
                    if (protocolNames.contains(ap)) {
                        shc.applicationProtocol = ap;
                        shc.conContext.applicationProtocol = ap;
                        matched = true;
                        break;
                    }
                }

                if (!matched) {
                    throw shc.conContext.fatal(Alert.NO_APPLICATION_PROTOCOL,
                            "No matching application layer protocol values");
                }
            }   // Otherwise, applicationProtocol will be set by the
                // application selector callback later.

            shc.handshakeExtensions.put(SSLExtension.CH_ALPN, spec);

            // No impact on session resumption.
            //
            // [RFC 7301] Unlike many other TLS extensions, this extension
            // does not establish properties of the session, only of the
            // connection.  When session resumption or session tickets are
            // used, the previous contents of this extension are irrelevant,
            // and only the values in the new handshake messages are
            // considered.
        }
    }

    /**
     * The absence processing if the extension is not present in
     * a ClientHello handshake message.
     */
    private static final class CHAlpnAbsence implements HandshakeAbsence {
        @Override
        public void absent(ConnectionContext context,
                HandshakeMessage message) throws IOException {
            // The producing happens in server side only.
            ServerHandshakeContext shc = (ServerHandshakeContext)context;

            // Please don't use the previous negotiated application protocol.
            shc.applicationProtocol = "";
            shc.conContext.applicationProtocol = "";
        }
    }

    /**
     * Network data producer of the extension in the ServerHello
     * handshake message.
     */
    private static final class SHAlpnProducer implements HandshakeProducer {
        // Prevent instantiation of this class.
        private SHAlpnProducer() {
            // blank
        }

        @Override
        public byte[] produce(ConnectionContext context,
                HandshakeMessage message) throws IOException {
            // The producing happens in client side only.
            ServerHandshakeContext shc = (ServerHandshakeContext)context;

            // In response to ALPN request only
            AlpnSpec requestedAlps =
                    (AlpnSpec)shc.handshakeExtensions.get(SSLExtension.CH_ALPN);
            if (requestedAlps == null) {
                // Ignore, this extension was not requested and accepted.
                if (SSLLogger.isOn && SSLLogger.isOn("ssl,handshake")) {
                    SSLLogger.fine(
                            "Ignore unavailable extension: " +
                            SSLExtension.SH_ALPN.name);
                }

                shc.applicationProtocol = "";
                shc.conContext.applicationProtocol = "";
                return null;
            }

            List<String> alps = requestedAlps.applicationProtocols;
            if (shc.conContext.transport instanceof SSLEngine) {
                if (shc.sslConfig.engineAPSelector != null) {
                    SSLEngine engine = (SSLEngine)shc.conContext.transport;
                    shc.applicationProtocol =
                        shc.sslConfig.engineAPSelector.apply(engine, alps);
                    if ((shc.applicationProtocol == null) ||
                            (!shc.applicationProtocol.isEmpty() &&
                            !alps.contains(shc.applicationProtocol))) {
                        throw shc.conContext.fatal(
                            Alert.NO_APPLICATION_PROTOCOL,
                            "No matching application layer protocol values");
                    }
                }
            } else {
                if (shc.sslConfig.socketAPSelector != null) {
                    SSLSocket socket = (SSLSocket)shc.conContext.transport;
                    shc.applicationProtocol =
                        shc.sslConfig.socketAPSelector.apply(socket, alps);
                    if ((shc.applicationProtocol == null) ||
                            (!shc.applicationProtocol.isEmpty() &&
                            !alps.contains(shc.applicationProtocol))) {
                        throw shc.conContext.fatal(
                            Alert.NO_APPLICATION_PROTOCOL,
                            "No matching application layer protocol values");
                    }
                }
            }

            if ((shc.applicationProtocol == null) ||
                    (shc.applicationProtocol.isEmpty())) {
                // Ignore, no negotiated application layer protocol.
                shc.applicationProtocol = "";
                shc.conContext.applicationProtocol = "";
                if (SSLLogger.isOn && SSLLogger.isOn("ssl,handshake")) {
                    SSLLogger.warning(
                        "Ignore, no negotiated application layer protocol");
                }

                return null;
            }

            // opaque ProtocolName<1..2^8-1>, RFC 7301.
            byte[] bytes = shc.applicationProtocol.getBytes(alpnCharset);
            int listLen = bytes.length + 1;             // 1: length byte

            // ProtocolName protocol_name_list<2..2^16-1>, RFC 7301.
            byte[] extData = new byte[listLen + 2];     // 2: list length
            ByteBuffer m = ByteBuffer.wrap(extData);
            Record.putInt16(m, listLen);
            Record.putBytes8(m, bytes);

            // Update the context.
            shc.conContext.applicationProtocol = shc.applicationProtocol;

            // Clean or register the extension
            //
            // No further use of the request and respond extension.
            shc.handshakeExtensions.remove(SSLExtension.CH_ALPN);

            return extData;
        }
    }

    /**
     * Network data consumer of the extension in the ServerHello
     * handshake message.
     */
    private static final class SHAlpnConsumer implements ExtensionConsumer {
        // Prevent instantiation of this class.
        private SHAlpnConsumer() {
            // blank
        }

        @Override
        public void consume(ConnectionContext context,
            HandshakeMessage message, ByteBuffer buffer) throws IOException {
            // The producing happens in client side only.
            ClientHandshakeContext chc = (ClientHandshakeContext)context;

            // In response to ALPN request only
            AlpnSpec requestedAlps =
                    (AlpnSpec)chc.handshakeExtensions.get(SSLExtension.CH_ALPN);
            if (requestedAlps == null ||
                    requestedAlps.applicationProtocols.isEmpty()) {
                throw chc.conContext.fatal(Alert.UNEXPECTED_MESSAGE,
                    "Unexpected " + SSLExtension.CH_ALPN.name + " extension");
            }

            // Parse the extension.
            AlpnSpec spec = new AlpnSpec(chc, buffer);

            // Only one application protocol is allowed.
            if (spec.applicationProtocols.size() != 1) {
                throw chc.conContext.fatal(Alert.UNEXPECTED_MESSAGE,
                    "Invalid " + SSLExtension.CH_ALPN.name + " extension: " +
                    "Only one application protocol name " +
                    "is allowed in ServerHello message");
            }

            // The respond application protocol must be one of the requested.
            if (!requestedAlps.applicationProtocols.containsAll(
                    spec.applicationProtocols)) {
                throw chc.conContext.fatal(Alert.UNEXPECTED_MESSAGE,
                    "Invalid " + SSLExtension.CH_ALPN.name + " extension: " +
                    "Only client specified application protocol " +
                    "is allowed in ServerHello message");
            }

            // Update the context.
            chc.applicationProtocol = spec.applicationProtocols.get(0);
            chc.conContext.applicationProtocol = chc.applicationProtocol;

            // Clean or register the extension
            //
            // No further use of the request and respond extension.
            chc.handshakeExtensions.remove(SSLExtension.CH_ALPN);
        }
    }

    /**
     * The absence processing if the extension is not present in
     * the ServerHello handshake message.
     */
    private static final class SHAlpnAbsence implements HandshakeAbsence {
        @Override
        public void absent(ConnectionContext context,
                HandshakeMessage message) throws IOException {
            // The producing happens in client side only.
            ClientHandshakeContext chc = (ClientHandshakeContext)context;

            // Please don't use the previous negotiated application protocol.
            chc.applicationProtocol = "";
            chc.conContext.applicationProtocol = "";
        }
    }
}
