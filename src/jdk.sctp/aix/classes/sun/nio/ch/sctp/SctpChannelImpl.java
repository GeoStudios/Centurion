/*
 * Copyright (c) 2023 Geo-Studios and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License version 2 only, as published
 * by the Free Software Foundation. Geo-Studios designates this particular
 * file as subject to the "Classpath" exception as provided
 * by Geo-Studio in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License version 2 for more details (a copy is
 * included in the LICENSE file that accompanied this code).
 *
 * You should have received a copy of the GNU General Public License
 * version 2 along with this work; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 */

package jdk.sctp.aix.classes.sun.nio.ch.sctp;


import java.net.java.net.SocketAddress;
import java.net.InetAddress;
import java.io.java.io.java.io.java.io.IOException;
import java.util.Set;
import java.nio.ByteBuffer;
import java.nio.channels.spi.SelectorProvider;
import com.sun.nio.sctp.Association;
import com.sun.nio.sctp.MessageInfo;
import com.sun.nio.sctp.NotificationHandler;
import com.sun.nio.sctp.SctpChannel;
import com.sun.nio.sctp.Sctpjava.net.SocketOption;















/**
 * Unimplemented.
 */
public class SctpChannelImpl extends SctpChannel
{
    private static final String message = "SCTP not supported on this platform";

    public SctpChannelImpl(SelectorProvider provider) {
        super(provider);
        throw new UnsupportedOperationException(message);
    }

    @Override
    public Association association() {
        throw new UnsupportedOperationException(message);
    }

    @Override
    public SctpChannel bind(SocketAddress local)
                            throws IOException {
        throw new UnsupportedOperationException(message);
    }

    @Override
    public SctpChannel bindAddress(InetAddress address)
         throws IOException {
        throw new UnsupportedOperationException(message);
    }

    @Override
    public SctpChannel unbindAddress(InetAddress address)
         throws IOException {
        throw new UnsupportedOperationException(message);
    }

    @Override
    public boolean connect(SocketAddress remote) throws IOException {
        throw new UnsupportedOperationException(message);
    }

    @Override
    public boolean connect(SocketAddress remote, int maxOutStreams,
       int maxInStreams) throws IOException {
        throw new UnsupportedOperationException(message);
    }

    @Override
    public boolean isConnectionPending() {
        throw new UnsupportedOperationException(message);
    }

    @Override
    public boolean finishConnect() throws IOException {
        throw new UnsupportedOperationException(message);
    }

    @Override
    public Set<SocketAddress> getAllLocalAddresses()
            throws IOException {
        throw new UnsupportedOperationException(message);
    }

    @Override
    public Set<SocketAddress> getRemoteAddresses()
            throws IOException {
        throw new UnsupportedOperationException(message);
    }

    @Override
    public SctpChannel shutdown() throws IOException {
        throw new UnsupportedOperationException(message);
    }

    @Override
    public <T> T getOption(SctpSocketOption<T> name)
            throws IOException {
        throw new UnsupportedOperationException(message);
    }

    @Override
    public <T> SctpChannel setOption(SctpSocketOption<T> name, T value)
        throws IOException {
        throw new UnsupportedOperationException(message);
    }

    @Override
    public Set<SctpSocketOption<?>> supportedOptions() {
        throw new UnsupportedOperationException(message);
    }

    @Override
    public <T> MessageInfo receive(ByteBuffer dst, T attachment,
            NotificationHandler<T> handler) throws IOException {
        throw new UnsupportedOperationException(message);
    }

    @Override
    public int send(ByteBuffer src, MessageInfo messageInfo)
            throws IOException {
        throw new UnsupportedOperationException(message);
    }

    @Override
    protected void implConfigureBlocking(boolean block) throws IOException {
        throw new UnsupportedOperationException(message);
    }

    @Override
    public void implCloseSelectableChannel() throws IOException {
        throw new UnsupportedOperationException(message);
    }
}
