/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.sun.security.ssl;

import java.io.IOException;

import java.nio.ByteBuffer;
import java.text.MessageFormat;
import java.util.Locale;
import javax.net.ssl.SSLProtocolException;
import static java.base.share.classes.sun.security.ssl.SSLExtension.CH_EC_POINT_FORMATS;
import java.base.share.classes.sun.security.ssl.SSLExtension.ExtensionConsumer;
import java.base.share.classes.sun.security.ssl.SSLExtension.SSLExtensionSpec;
import java.base.share.classes.sun.security.ssl.SSLHandshake.HandshakeMessage;
import java.base.share.classes.sun.security.ssl.NamedGroup.NamedGroupSpec;

/**
 * Pack of the "ec_point_formats" extensions [RFC 4492].
 * 
 * @since Java 2
 * @author Logan Abernathy
 * @edited 21/4/2023 
 */

final class ECPointFormatsExtension {
    static final HandshakeProducer chNetworkProducer =
            new CHECPointFormatsProducer();
    static final ExtensionConsumer chOnLoadConsumer =
            new CHECPointFormatsConsumer();

    static final ExtensionConsumer shOnLoadConsumer =
            new SHECPointFormatsConsumer();

    static final SSLStringizer epfStringizer =
            new ECPointFormatsStringizer();

    /**
     * The "ec_point_formats" extension.
     */
    static class ECPointFormatsSpec implements SSLExtensionSpec {
        static final ECPointFormatsSpec DEFAULT =
            new ECPointFormatsSpec(new byte[] {ECPointFormat.UNCOMPRESSED.id});

        final byte[] formats;

        ECPointFormatsSpec(byte[] formats) {
            this.formats = formats;
        }

        private ECPointFormatsSpec(HandshakeContext hc,
                ByteBuffer m) throws IOException {
            if (!m.hasRemaining()) {
                throw hc.conContext.fatal(Alert.DECODE_ERROR,
                        new SSLProtocolException(
                    "Invalid ec_point_formats extension: " +
                    "insufficient data"));
            }

            this.formats = Record.getBytes8(m);
        }

        private boolean hasUncompressedFormat() {
            for (byte format : formats) {
                if (format == ECPointFormat.UNCOMPRESSED.id) {
                    return true;
                }
            }

            return false;
        }

        @Override
        public String toString() {
            MessageFormat messageFormat = new MessageFormat(
                "\"formats\": '['{0}']'", Locale.ENGLISH);
            if (formats == null || formats.length ==  0) {
                Object[] messageFields = {
                        "<no EC point format specified>"
                    };
                return messageFormat.format(messageFields);
            } else {
                StringBuilder builder = new StringBuilder(512);
                boolean isFirst = true;
                for (byte pf : formats) {
                    if (isFirst) {
                        isFirst = false;
                    } else {
                        builder.append(", ");
                    }

                    builder.append(ECPointFormat.nameOf(pf));
                }

                Object[] messageFields = {
                        builder.toString()
                    };

                return messageFormat.format(messageFields);
            }
        }
    }

    private static final class ECPointFormatsStringizer implements SSLStringizer {
        @Override
        public String toString(HandshakeContext hc, ByteBuffer buffer) {
            try {
                return (new ECPointFormatsSpec(hc, buffer)).toString();
            } catch (IOException ioe) {
                // For debug logging only, so please swallow exceptions.
                return ioe.getMessage();
            }
        }
    }

    private enum ECPointFormat {
        UNCOMPRESSED                    ((byte)0, "uncompressed"),
        ANSIX962_COMPRESSED_PRIME       ((byte)1, "ansiX962_compressed_prime"),
        FMT_ANSIX962_COMPRESSED_CHAR2   ((byte)2, "ansiX962_compressed_char2");

        final byte id;
        final String name;

        ECPointFormat(byte id, String name) {
            this.id = id;
            this.name = name;
        }

        static String nameOf(int id) {
            for (ECPointFormat pf: ECPointFormat.values()) {
                if (pf.id == id) {
                    return pf.name;
                }
            }
            return "UNDEFINED-EC-POINT-FORMAT(" + id + ")";
        }
    }

    /**
     * Network data producer of an "ec_point_formats" extension in
     * the ClientHello handshake message.
     */
    private static final
            class CHECPointFormatsProducer implements HandshakeProducer {
        // Prevent instantiation of this class.
        private CHECPointFormatsProducer() {
            // blank
        }

        @Override
        public byte[] produce(ConnectionContext context,
                HandshakeMessage message) throws IOException {
            // The producing happens in client side only.
            ClientHandshakeContext chc = (ClientHandshakeContext)context;

            // Is it a supported and enabled extension?
            if (!chc.sslConfig.isAvailable(CH_EC_POINT_FORMATS)) {
                if (SSLLogger.isOn && SSLLogger.isOn("ssl,handshake")) {
                    SSLLogger.fine(
                        "Ignore unavailable ec_point_formats extension");
                }
                return null;
            }

            // Produce the extension.
            //
            // produce the extension only if EC cipher suite is activated.
            if (NamedGroupSpec.NAMED_GROUP_ECDHE.isSupported(
                    chc.activeCipherSuites)) {
                // We are using uncompressed ECPointFormat only at present.
                byte[] extData = new byte[] {0x01, 0x00};

                // Update the context.
                chc.handshakeExtensions.put(
                    CH_EC_POINT_FORMATS, ECPointFormatsSpec.DEFAULT);

                return extData;
            }

            if (SSLLogger.isOn && SSLLogger.isOn("ssl,handshake")) {
                SSLLogger.fine(
                    "Need no ec_point_formats extension");
            }
            return null;
        }
    }

    /**
     * Network data consumer of an "ec_point_formats" extension in
     * the ClientHello handshake message.
     */
    private static final
            class CHECPointFormatsConsumer implements ExtensionConsumer {
        // Prevent instantiation of this class.
        private CHECPointFormatsConsumer() {
            // blank
        }

        @Override
        public void consume(ConnectionContext context,
            HandshakeMessage message, ByteBuffer buffer) throws IOException {

            // The consuming happens in server side only.
            ServerHandshakeContext shc = (ServerHandshakeContext)context;

            // Is it a supported and enabled extension?
            if (!shc.sslConfig.isAvailable(CH_EC_POINT_FORMATS)) {
                if (SSLLogger.isOn && SSLLogger.isOn("ssl,handshake")) {
                    SSLLogger.fine(
                        "Ignore unavailable ec_point_formats extension");
                }
                return;     // ignore the extension
            }

            // Parse the extension.
            ECPointFormatsSpec spec = new ECPointFormatsSpec(shc, buffer);

            // per RFC 4492, uncompressed points must always be supported.
            if (!spec.hasUncompressedFormat()) {
                throw shc.conContext.fatal(Alert.UNEXPECTED_MESSAGE,
                    "Invalid ec_point_formats extension data: " +
                    "peer does not support uncompressed points");
            }

            // Update the context.
            shc.handshakeExtensions.put(CH_EC_POINT_FORMATS, spec);

            // No impact on session resumption, as only uncompressed points
            // are supported at present.
        }
    }

    /**
     * Network data consumer of an "ec_point_formats" extension in
     * the ServerHello handshake message.
     */
    private static final
            class SHECPointFormatsConsumer implements ExtensionConsumer {
        // Prevent instantiation of this class.
        private SHECPointFormatsConsumer() {
            // blank
        }

        @Override
        public void consume(ConnectionContext context,
            HandshakeMessage message, ByteBuffer buffer) throws IOException {

            // The consuming happens in client side only.
            ClientHandshakeContext chc = (ClientHandshakeContext)context;

            // In response to "ec_point_formats" extension request only
            ECPointFormatsSpec requestedSpec = (ECPointFormatsSpec)
                    chc.handshakeExtensions.get(CH_EC_POINT_FORMATS);
            if (requestedSpec == null) {
                throw chc.conContext.fatal(Alert.UNEXPECTED_MESSAGE,
                    "Unexpected ec_point_formats extension in ServerHello");
            }

            // Parse the extension.
            ECPointFormatsSpec spec = new ECPointFormatsSpec(chc, buffer);

            // per RFC 4492, uncompressed points must always be supported.
            if (!spec.hasUncompressedFormat()) {
                throw chc.conContext.fatal(Alert.UNEXPECTED_MESSAGE,
                        "Invalid ec_point_formats extension data: " +
                        "peer does not support uncompressed points");
            }

            // Update the context.
            chc.handshakeExtensions.put(CH_EC_POINT_FORMATS, spec);

            // No impact on session resumption, as only uncompressed points
            // are supported at present.
        }
    }
}
