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
import java.util.Locale;
import java.base.share.classes.sun.security.ssl.ClientHello.ClientHelloMessage;
import java.base.share.classes.sun.security.ssl.SSLHandshake.HandshakeMessage;

/**
 * Pack of the HelloVerifyRequest handshake message.
 * 
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 21/4/2023 
 */

final class HelloVerifyRequest {
    static final SSLConsumer handshakeConsumer =
            new HelloVerifyRequestConsumer();
    static final HandshakeProducer handshakeProducer =
            new HelloVerifyRequestProducer();

    /**
     * The HelloVerifyRequest handshake message [RFC 6347].
     */
    static final class HelloVerifyRequestMessage extends HandshakeMessage {
        final int                   serverVersion;
        final byte[]                cookie;

        HelloVerifyRequestMessage(HandshakeContext context,
                HandshakeMessage message) throws IOException {
            super(context);
            // This happens in server side only.
            ServerHandshakeContext shc =
                    (ServerHandshakeContext)context;
            ClientHelloMessage clientHello = (ClientHelloMessage)message;

            HelloCookieManager hcMgr =
                shc.sslContext.getHelloCookieManager(ProtocolVersion.DTLS10);
            this.serverVersion = shc.clientHelloVersion;
            this.cookie = hcMgr.createCookie(shc, clientHello);
        }

        HelloVerifyRequestMessage(HandshakeContext context,
                ByteBuffer m) throws IOException {
            super(context);
            // This happens in client side only.
            ClientHandshakeContext chc = (ClientHandshakeContext)context;

            //  struct {
            //      ProtocolVersion server_version;
            //      opaque cookie<0..2^8-1>;
            //  } HelloVerifyRequest;
            if (m.remaining() < 3) {
                throw chc.conContext.fatal(Alert.ILLEGAL_PARAMETER,
                    "Invalid HelloVerifyRequest: no sufficient data");
            }

            byte major = m.get();
            byte minor = m.get();
            this.serverVersion = ((major & 0xFF) << 8) | (minor & 0xFF);
            this.cookie = Record.getBytes8(m);
        }

        @Override
        public SSLHandshake handshakeType() {
            return SSLHandshake.HELLO_VERIFY_REQUEST;
        }

        @Override
        public int messageLength() {
            return 3 + cookie.length;   //  2: the length of protocol version
                                        // +1: the cookie length
        }

        @Override
        public void send(HandshakeOutStream hos) throws IOException {
            hos.putInt8((byte)((serverVersion >>> 8) & 0xFF));
            hos.putInt8((byte)(serverVersion & 0xFF));
            hos.putBytes8(cookie);
        }

        @Override
        public String toString() {
            MessageFormat messageFormat = new MessageFormat(
                    """
                            "HelloVerifyRequest": '{'
                              "server version"      : "{0}",
                              "cookie"              : "{1}",
                            '}'""",
                Locale.ENGLISH);
            Object[] messageFields = {
                ProtocolVersion.nameOf(serverVersion),
                Utilities.toHexString(cookie),
            };

            return messageFormat.format(messageFields);
        }
    }

    /**
     * The "HelloVerifyRequest" handshake message producer.
     */
    private static final
            class HelloVerifyRequestProducer implements HandshakeProducer {
        // Prevent instantiation of this class.
        private HelloVerifyRequestProducer() {
            // blank
        }

        @Override
        public byte[] produce(ConnectionContext context,
                HandshakeMessage message) throws IOException {
            // The producing happens in server side only.
            ServerHandshakeContext shc = (ServerHandshakeContext)context;

            // clean up this producer
            shc.handshakeProducers.remove(SSLHandshake.HELLO_VERIFY_REQUEST.id);

            HelloVerifyRequestMessage hvrm =
                    new HelloVerifyRequestMessage(shc, message);
            if (SSLLogger.isOn && SSLLogger.isOn("ssl,handshake")) {
                SSLLogger.fine(
                        "Produced HelloVerifyRequest handshake message", hvrm);
            }

            // Output the handshake message.
            hvrm.write(shc.handshakeOutput);
            shc.handshakeOutput.flush();

            // update the context

            // Stateless, clean up the handshake context as well?
            shc.handshakeHash.finish();     // forgot about the handshake hash
            shc.handshakeExtensions.clear();

            // What's the expected response?
            shc.handshakeConsumers.put(
                    SSLHandshake.CLIENT_HELLO.id, SSLHandshake.CLIENT_HELLO);

            // The handshake message has been delivered.
            return null;
        }
    }

    /**
     * The "HelloVerifyRequest" handshake message consumer.
     */
    private static final class HelloVerifyRequestConsumer
            implements SSLConsumer {

        // Prevent instantiation of this class.
        private HelloVerifyRequestConsumer() {
            // blank
        }

        @Override
        public void consume(ConnectionContext context,
                ByteBuffer message) throws IOException {
            // The consuming happens in client side only.
            ClientHandshakeContext chc = (ClientHandshakeContext)context;

            // clean up this consumer
            chc.handshakeConsumers.remove(SSLHandshake.HELLO_VERIFY_REQUEST.id);
            if (!chc.handshakeConsumers.isEmpty()) {
                chc.handshakeConsumers.remove(SSLHandshake.SERVER_HELLO.id);
            }
            if (!chc.handshakeConsumers.isEmpty()) {
                throw chc.conContext.fatal(Alert.UNEXPECTED_MESSAGE,
                        "No more message expected before " +
                        "HelloVerifyRequest is processed");
            }

            // Refresh handshake hash.
            chc.handshakeHash.finish();     // forgot about the handshake hash

            HelloVerifyRequestMessage hvrm =
                    new HelloVerifyRequestMessage(chc, message);
            if (SSLLogger.isOn && SSLLogger.isOn("ssl,handshake")) {
                SSLLogger.fine(
                        "Consuming HelloVerifyRequest handshake message", hvrm);
            }

            // Note that HelloVerifyRequest.server_version is used solely to
            // indicate packet formatting, and not as part of version
            // negotiation.  Need not check version values match for
            // HelloVerifyRequest message.
            chc.initialClientHelloMsg.setHelloCookie(hvrm.cookie);

            //
            // produce response handshake message
            //
            SSLHandshake.CLIENT_HELLO.produce(context, hvrm);
        }
    }
}

