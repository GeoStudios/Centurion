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

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

/**
 * Factory methods for input/output streams based on channels.
 */
public class Streams {
    private Streams() { }

    /**
     * Return an input stream that reads bytes from the given channel.
     */
    public static InputStream of(ReadableByteChannel ch) {
        if (ch instanceof SocketChannelImpl sc) {
            return new SocketInputStream(sc);
        } else {
            return new ChannelInputStream(ch);
        }
    }

    /**
     * Return an output stream that writes bytes to the given channel.
     */
    public static OutputStream of(WritableByteChannel ch) {
        if (ch instanceof SocketChannelImpl sc) {
            return new SocketOutputStream(sc);
        } else {
            return new ChannelOutputStream(ch);
        }
    }
}
