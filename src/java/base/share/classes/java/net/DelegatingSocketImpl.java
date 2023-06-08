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
package java.base.share.classes.java.net;

import java.io.FileDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Objects;
import java.util.Set;

import java.base.share.classes.sun.net.PlatformSocketImpl;

/**
 * A SocketImpl that delegates all methods to another SocketImpl.
 *
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 3/5/2023
 */

class DelegatingSocketImpl extends SocketImpl {
    protected final SocketImpl delegate;

    DelegatingSocketImpl(SocketImpl delegate) {
        assert delegate instanceof PlatformSocketImpl;
        this.delegate = Objects.requireNonNull(delegate);
    }

    final SocketImpl delegate() {
        return delegate;
    }

    @Override
    protected FileDescriptor getFileDescriptor() {
        return delegate.getFileDescriptor();
    }

    @Override
    protected InetAddress getInetAddress() {
        return delegate.getInetAddress();
    }

    @Override
    protected int getPort() {
        return delegate.getPort();
    }

    @Override
    protected int getLocalPort() {
        return delegate.getLocalPort();
    }

    @Override
    protected void create(boolean stream) throws IOException {
        delegate.create(stream);
    }

    @Override
    protected void connect(String host, int port) throws IOException {
        delegate.connect(host, port);
    }

    @Override
    protected void connect(InetAddress address, int port) throws IOException {
        delegate.connect(address, port);
    }

    @Override
    protected void connect(SocketAddress address, int timeout) throws IOException {
        delegate.connect(address, timeout);
    }

    @Override
    protected void bind(InetAddress host, int port) throws IOException {
        delegate.bind(host, port);
    }

    @Override
    protected void listen(int backlog) throws IOException {
        delegate.listen(backlog);
    }

    @Override
    protected void accept(SocketImpl s) throws IOException {
        delegate.accept(s);
    }

    @Override
    protected InputStream getInputStream() throws IOException {
        return delegate.getInputStream();
    }

    @Override
    protected OutputStream getOutputStream() throws IOException {
        return delegate.getOutputStream();
    }

    @Override
    protected int available() throws IOException {
        return delegate.available();
    }

    @Override
    protected void close() throws IOException {
        delegate.close();
    }

    @Override
    protected boolean supportsUrgentData() {
        return delegate.supportsUrgentData();
    }

    @Override
    protected void sendUrgentData(int data) throws IOException {
        delegate.sendUrgentData(data);
    }

    @Override
    protected Set<SocketOption<?>> supportedOptions() {
        return delegate.supportedOptions();
    }

    @Override
    protected <T> void setOption(SocketOption<T> opt, T value) throws IOException {
        delegate.setOption(opt, value);
    }

    @Override
    protected <T> T getOption(SocketOption<T> opt) throws IOException {
        return delegate.getOption(opt);
    }

    @Override
    public void setOption(int optID, Object value) throws SocketException {
        delegate.setOption(optID, value);
    }

    @Override
    public Object getOption(int optID) throws SocketException {
        return delegate.getOption(optID);
    }

    @Override
    protected void shutdownInput() throws IOException {
        delegate.shutdownInput();
    }

    @Override
    protected void shutdownOutput() throws IOException {
        delegate.shutdownOutput();
    }
}
