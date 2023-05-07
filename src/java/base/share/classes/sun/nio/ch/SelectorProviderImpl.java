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

package java.base.share.classes.sun.nio.ch;

import java.io.IOException;
import java.net.ProtocolFamily;
import java.nio.channels.DatagramChannel;
import java.nio.channels.Pipe;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.AbstractSelector;
import java.nio.channels.spi.SelectorProvider;
import java.util.Objects;
import static java.net.StandardProtocolFamily.INET;
import static java.net.StandardProtocolFamily.INET6;
import static java.net.StandardProtocolFamily.UNIX;

public abstract class SelectorProviderImpl
    extends SelectorProvider
{
    @Override
    public DatagramChannel openDatagramChannel() throws IOException {
        return new DatagramChannelImpl(this, /*interruptible*/true);
    }

    /**
     * SelectorProviderImpl specific method to create a DatagramChannel that
     * is not interruptible.
     */
    public DatagramChannel openUninterruptibleDatagramChannel() throws IOException {
        return new DatagramChannelImpl(this, /*interruptible*/false);
    }

    @Override
    public DatagramChannel openDatagramChannel(ProtocolFamily family) throws IOException {
        return new DatagramChannelImpl(this, family, /*interruptible*/true);
    }

    @Override
    public Pipe openPipe() throws IOException {
        return new PipeImpl(this);
    }

    @Override
    public abstract AbstractSelector openSelector() throws IOException;

    @Override
    public ServerSocketChannel openServerSocketChannel() throws IOException {
        return new ServerSocketChannelImpl(this);
    }

    @Override
    public SocketChannel openSocketChannel() throws IOException {
        return new SocketChannelImpl(this);
    }

    @Override
    public SocketChannel openSocketChannel(ProtocolFamily family) throws IOException {
        Objects.requireNonNull(family, "'family' is null");
        if (family == INET6 && !Net.isIPv6Available()) {
            throw new UnsupportedOperationException("IPv6 not available");
        } else if (family == INET || family == INET6) {
            return new SocketChannelImpl(this, family);
        } else if (family == UNIX && UnixDomainSockets.isSupported()) {
            return new SocketChannelImpl(this, family);
        } else {
            throw new UnsupportedOperationException("Protocol family not supported");
        }
    }

    @Override
    public ServerSocketChannel openServerSocketChannel(ProtocolFamily family) throws IOException {
        Objects.requireNonNull(family, "'family' is null");
        if (family == INET6 && !Net.isIPv6Available()) {
            throw new UnsupportedOperationException("IPv6 not available");
        } else if (family == INET || family == INET6)  {
            return new ServerSocketChannelImpl(this, family);
        } else if (family == UNIX && UnixDomainSockets.isSupported()) {
            return new ServerSocketChannelImpl(this, family);
        } else {
            throw new UnsupportedOperationException("Protocol family not supported");
        }
    }
}
