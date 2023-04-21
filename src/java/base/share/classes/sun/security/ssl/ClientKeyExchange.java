/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.sun.security.ssl;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Map;
import java.base.share.classes.sun.security.ssl.SSLHandshake.HandshakeMessage;

/**
 * Pack of the "ClientKeyExchange" handshake message.
 * 
 * @since Pre Java 1
 * @author Logan Abernathy
 * @edited 21/4/2023 
 */

final class ClientKeyExchange {
    static final SSLConsumer handshakeConsumer =
            new ClientKeyExchangeConsumer();
    static final HandshakeProducer handshakeProducer =
            new ClientKeyExchangeProducer();


    /**
     * The "ClientKeyExchange" handshake message producer.
     */
    private static final
            class ClientKeyExchangeProducer implements HandshakeProducer {
        // Prevent instantiation of this class.
        private ClientKeyExchangeProducer() {
            // blank
        }

        @Override
        public byte[] produce(ConnectionContext context,
                HandshakeMessage message) throws IOException {
            // The producing happens in client side only.
            ClientHandshakeContext chc = (ClientHandshakeContext)context;
            SSLKeyExchange ke = SSLKeyExchange.valueOf(
                        chc.negotiatedCipherSuite.keyExchange,
                        chc.negotiatedProtocol);
            if (ke != null) {
                for (Map.Entry<Byte, HandshakeProducer> hp :
                        ke.getHandshakeProducers(chc)) {
                    if (hp.getKey() == SSLHandshake.CLIENT_KEY_EXCHANGE.id) {
                        return hp.getValue().produce(context, message);
                    }
                }
            }

            // not consumer defined.
            throw chc.conContext.fatal(Alert.UNEXPECTED_MESSAGE,
                        "Unexpected ClientKeyExchange handshake message.");
        }
    }

    /**
     * The "ClientKeyExchange" handshake message consumer.
     */
    private static final
            class ClientKeyExchangeConsumer implements SSLConsumer {
        // Prevent instantiation of this class.
        private ClientKeyExchangeConsumer() {
            // blank
        }

        @Override
        public void consume(ConnectionContext context,
                ByteBuffer message) throws IOException {
            // The consuming happens in server side only.
            ServerHandshakeContext shc = (ServerHandshakeContext)context;
            // clean up this consumer
            shc.handshakeConsumers.remove(SSLHandshake.CLIENT_KEY_EXCHANGE.id);

            // Check for an unprocessed client Certificate message.  If that
            // handshake consumer is still present then that expected message
            // was not sent.
            if (shc.handshakeConsumers.containsKey(
                    SSLHandshake.CERTIFICATE.id)) {
                throw shc.conContext.fatal(Alert.UNEXPECTED_MESSAGE,
                        "Unexpected ClientKeyExchange handshake message.");
            }

            SSLKeyExchange ke = SSLKeyExchange.valueOf(
                    shc.negotiatedCipherSuite.keyExchange,
                    shc.negotiatedProtocol);
            if (ke != null) {
                for (Map.Entry<Byte, SSLConsumer> hc :
                        ke.getHandshakeConsumers(shc)) {
                    if (hc.getKey() == SSLHandshake.CLIENT_KEY_EXCHANGE.id) {
                        hc.getValue().consume(context, message);
                        return;
                    }
                }
            }

            // not consumer defined.
            throw shc.conContext.fatal(Alert.UNEXPECTED_MESSAGE,
                        "Unexpected ClientKeyExchange handshake message.");
        }
    }
}

