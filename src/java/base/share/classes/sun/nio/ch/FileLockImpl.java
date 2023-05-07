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
import java.nio.channels.*;

public class FileLockImpl
    extends FileLock
{
    private volatile boolean invalid;

    FileLockImpl(FileChannel channel, long position, long size, boolean shared)
    {
        super(channel, position, size, shared);
    }

    FileLockImpl(AsynchronousFileChannel channel, long position, long size, boolean shared)
    {
        super(channel, position, size, shared);
    }

    public boolean isValid() {
        return !invalid;
    }

    void invalidate() {
        assert Thread.holdsLock(this);
        invalid = true;
    }

    public synchronized void release() throws IOException {
        Channel ch = acquiredBy();
        if (!ch.isOpen())
            throw new ClosedChannelException();
        if (isValid()) {
            if (ch instanceof FileChannelImpl)
                ((FileChannelImpl)ch).release(this);
            else if (ch instanceof AsynchronousFileChannelImpl)
                ((AsynchronousFileChannelImpl)ch).release(this);
            else throw new AssertionError();
            invalidate();
        }
    }
}
