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
import java.nio.BufferOverflowException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.util.ArrayList;

/**
 * A compact implementation of HandshakeContext for post-handshake messages
 * 
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 21/4/2023 
 */

final class PostHandshakeContext extends HandshakeContext {
    PostHandshakeContext(TransportContext context) throws IOException {
        super(context);

        if (!negotiatedProtocol.useTLS13PlusSpec()) {
            throw conContext.fatal(Alert.UNEXPECTED_MESSAGE,
                "Post-handshake not supported in " + negotiatedProtocol.name);
        }

        this.localSupportedSignAlgs = new ArrayList<>(
            context.conSession.getLocalSupportedSignatureSchemes());

        // Add the potential post-handshake consumers.
        if (context.sslConfig.isClientMode) {
            handshakeConsumers.putIfAbsent(
                    SSLHandshake.KEY_UPDATE.id,
                    SSLHandshake.KEY_UPDATE);
            handshakeConsumers.putIfAbsent(
                    SSLHandshake.NEW_SESSION_TICKET.id,
                    SSLHandshake.NEW_SESSION_TICKET);
        } else {
            handshakeConsumers.putIfAbsent(
                    SSLHandshake.KEY_UPDATE.id,
                    SSLHandshake.KEY_UPDATE);
        }

        handshakeFinished = true;
        handshakeSession = context.conSession;
    }

    @Override
    void kickstart() throws IOException {
        SSLHandshake.kickstart(this);
    }

    @Override
    void dispatch(byte handshakeType, ByteBuffer fragment) throws IOException {
        SSLConsumer consumer = handshakeConsumers.get(handshakeType);
        if (consumer == null) {
            throw conContext.fatal(Alert.UNEXPECTED_MESSAGE,
                    "Unexpected post-handshake message: " +
                            SSLHandshake.nameOf(handshakeType));
        }

        try {
            consumer.consume(this, fragment);
        } catch (UnsupportedOperationException unsoe) {
            throw conContext.fatal(Alert.UNEXPECTED_MESSAGE,
                    "Unsupported post-handshake message: " +
                            SSLHandshake.nameOf(handshakeType), unsoe);
        } catch (BufferUnderflowException | BufferOverflowException be) {
            throw conContext.fatal(Alert.DECODE_ERROR,
                    "Illegal handshake message: " +
                    SSLHandshake.nameOf(handshakeType), be);
        }
    }

    static boolean isConsumable(TransportContext context, byte handshakeType) {
        if (handshakeType == SSLHandshake.KEY_UPDATE.id) {
            // The KeyUpdate handshake message does not apply to TLS 1.2 and
            // previous protocols.
            return context.protocolVersion.useTLS13PlusSpec();
        }

        if (handshakeType == SSLHandshake.NEW_SESSION_TICKET.id) {
            // The new session ticket handshake message could be consumer in
            // client side only.
            return context.sslConfig.isClientMode;
        }

        // No more post-handshake message supported currently.
        return false;
    }
}
