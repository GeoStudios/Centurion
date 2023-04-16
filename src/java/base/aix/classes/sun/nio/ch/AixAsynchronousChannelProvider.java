/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
*/

package sun.nio.ch;

import java.nio.channels.*;
import java.nio.channels.spi.AsynchronousChannelProvider;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadFactory;
import java.io.IOException;

/*
 * This is for Syncing Channels by providing a way to open a channel.
 * 
 * @since Pre Devlopment 1
 * @author Logan Abernathy
 */

public class AixAsynchronousChannelProvider
    extends AsynchronousChannelProvider
{
    private static volatile AixPollPort defaultPort;

    private AixPollPort defaultEventPort() throws IOException {
        if (defaultPort == null) {
            synchronized (AixAsynchronousChannelProvider.class) {
                if (defaultPort == null) {
                    defaultPort = new AixPollPort(this, ThreadPool.getDefault()).start();
                }
            }
        }
        return defaultPort;
    }

    public AixAsynchronousChannelProvider() {
    }

    @Override
    public AsynchronousChannelGroup openAsynchronousChannelGroup(int nThreads, ThreadFactory factory)
        throws IOException
    {
        return new AixPollPort(this, ThreadPool.create(nThreads, factory)).start();
    }

    @Override
    public AsynchronousChannelGroup openAsynchronousChannelGroup(ExecutorService executor, int initialSize)
        throws IOException
    {
        return new AixPollPort(this, ThreadPool.wrap(executor, initialSize)).start();
    }

    private Port toPort(AsynchronousChannelGroup group) throws IOException {
        if (group == null) {
            return defaultEventPort();
        } else {
            if (!(group instanceof AixPollPort))
                throw new IllegalChannelGroupException();
            return (Port)group;
        }
    }

    @Override
    public AsynchronousServerSocketChannel openAsynchronousServerSocketChannel(AsynchronousChannelGroup group)
        throws IOException
    {
        return new UnixAsynchronousServerSocketChannelImpl(toPort(group));
    }

    @Override
    public AsynchronousSocketChannel openAsynchronousSocketChannel(AsynchronousChannelGroup group)
        throws IOException
    {
        return new UnixAsynchronousSocketChannelImpl(toPort(group));
    }
}