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

package java.base.unix.classes.sun.nio.ch;

import java.nio.channels.*;
import java.nio.channels.spi.*;

/**
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 20/4/2023 
 */

class PipeImpl
    extends Pipe
{

    // Source and sink channels
    private final SourceChannel source;
    private final SinkChannel sink;

    PipeImpl(SelectorProvider sp) throws IOException {
        long pipeFds = IOUtil.makePipe(true);
        int readFd = (int) (pipeFds >>> 32);
        int writeFd = (int) pipeFds;
        FileDescriptor sourcefd = new FileDescriptor();
        IOUtil.setfdVal(sourcefd, readFd);
        source = new SourceChannelImpl(sp, sourcefd);
        FileDescriptor sinkfd = new FileDescriptor();
        IOUtil.setfdVal(sinkfd, writeFd);
        sink = new SinkChannelImpl(sp, sinkfd);
    }

    public SourceChannel source() {
        return source;
    }

    public SinkChannel sink() {
        return sink;
    }

}
