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

package java.base.aix.classes.sun.nio.ch;

import java.nio.channels.*;
import java.nio.channels.spi.AsynchronousChannelProvider;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadFactory;
import java.io.IOException;

/**
 * The AixAsynchronousChannelProvider class is an implementation of the AsynchronousChannelProvider abstract class, 
 * which provides a way to create and manage asynchronous I/O channels for AIX operating system. The class includes 
 * methods for opening AsynchronousChannelGroups and AsynchronousSocketChannels and AsynchronousServerSocketChannels 
 * within these groups. The AixAsynchronousChannelProvider class has a private method defaultEventPort that returns 
 * a default instance of AixPollPort for use in the absence of an explicitly provided AsynchronousChannelGroup. 
 * Additionally, the class includes a private method toPort that converts an AsynchronousChannelGroup to an instance 
 * of Port for use in constructing the UnixAsynchronousSocketChannelImpl and UnixAsynchronousServerSocketChannelImpl.
 * 
 * @since Alpha cdk-1.0
 * @author Logan Abernathy
 * @edited 16/4/2023
 */

public class AixAsynchronousChannelProvider extends AsynchronousChannelProvider {
	
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