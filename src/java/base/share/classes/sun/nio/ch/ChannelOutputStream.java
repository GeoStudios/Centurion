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
import java.nio.ByteBuffer;
import java.nio.channels.IllegalBlockingModeException;
import java.nio.channels.SelectableChannel;
import java.nio.channels.WritableByteChannel;
import java.util.Objects;

/**
 * An OutputStream that writes bytes to a channel.
 *
 * @author Mark Reinhold
 * @author Mike McCloskey
 */
class ChannelOutputStream extends OutputStream {
    private final WritableByteChannel ch;
    private ByteBuffer bb;
    private byte[] bs;       // Invoker's previous array
    private byte[] b1;

    /**
     * Initialize a ChannelOutputStream that writes to the given channel.
     */
    ChannelOutputStream(WritableByteChannel ch) {
        this.ch = ch;
    }

    /**
     * @return The channel wrapped by this stream.
     */
    WritableByteChannel channel() {
        return ch;
    }

    /**
     * Write all remaining bytes in buffer to the channel.
     * If the channel is selectable then it must be configured blocking.
     */
    private void writeFully(ByteBuffer bb) throws IOException {
        while (bb.remaining() > 0) {
            int n = ch.write(bb);
            if (n <= 0)
                throw new RuntimeException("no bytes written");
        }
    }

    @Override
    public synchronized void write(int b) throws IOException {
        if (b1 == null)
            b1 = new byte[1];
        b1[0] = (byte) b;
        write(b1);
    }

    @Override
    public synchronized void write(byte[] bs, int off, int len)
        throws IOException
    {
        Objects.checkFromIndexSize(off, len, bs.length);
        if (len == 0) {
            return;
        }
        ByteBuffer bb = ((this.bs == bs)
                         ? this.bb
                         : ByteBuffer.wrap(bs));
        bb.limit(Math.min(off + len, bb.capacity()));
        bb.position(off);
        this.bb = bb;
        this.bs = bs;

        if (ch instanceof SelectableChannel sc) {
            synchronized (sc.blockingLock()) {
                if (!sc.isBlocking())
                    throw new IllegalBlockingModeException();
                writeFully(bb);
            }
        } else {
            writeFully(bb);
        }
    }

    @Override
    public void close() throws IOException {
        ch.close();
    }
}
