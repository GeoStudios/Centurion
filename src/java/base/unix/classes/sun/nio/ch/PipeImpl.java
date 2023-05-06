/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
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
