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
import java.io.OutputStream;

/**
 * An OutputStream that writes bytes to a socket channel.
 */
class SocketOutputStream extends OutputStream {
    private final SocketChannelImpl sc;

    /**
     * Initialize a SocketOutputStream that writes to the given socket channel.
     */
    SocketOutputStream(SocketChannelImpl sc) {
        this.sc = sc;
    }

    /**
     * Returns the socket channel.
     */
    SocketChannelImpl channel() {
        return sc;
    }

    @Override
    public void write(int b) throws IOException {
        byte[] a = new byte[]{(byte) b};
        write(a, 0, 1);
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        sc.blockingWriteFully(b, off, len);
    }

    @Override
    public void close() throws IOException {
        sc.close();
    }
}
