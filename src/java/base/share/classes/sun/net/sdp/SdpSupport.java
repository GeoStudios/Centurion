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

package java.base.share.classes.sun.net.sdp;

import java.io.IOException;
import java.io.FileDescriptor;

import java.base.share.classes.jdk.internal.access.SharedSecrets;
import java.base.share.classes.jdk.internal.access.JavaIOFileDescriptorAccess;
import java.base.share.classes.sun.security.action.GetPropertyAction;


/**
 * This class defines methods for creating SDP sockets or "converting" existing
 * file descriptors, referencing (unbound) TCP sockets, to SDP.
 */

public final class SdpSupport {
    private static final String os = GetPropertyAction.privilegedGetProperty("os.name");
    private static final boolean isSupported = os.equals("Linux");
    private static final JavaIOFileDescriptorAccess fdAccess =
        SharedSecrets.getJavaIOFileDescriptorAccess();

    private SdpSupport() { }

    /**
     * Creates a SDP socket, returning file descriptor referencing the socket.
     */
    public static FileDescriptor createSocket() throws IOException {
        if (!isSupported)
            throw new UnsupportedOperationException("SDP not supported on this platform");
        int fdVal = create0();
        FileDescriptor fd = new FileDescriptor();
        fdAccess.set(fd, fdVal);
        return fd;
    }

    /**
     * Converts an existing file descriptor, that references an unbound TCP socket,
     * to SDP.
     */
    public static void convertSocket(FileDescriptor fd) throws IOException {
        if (!isSupported)
            throw new UnsupportedOperationException("SDP not supported on this platform");
        int fdVal = fdAccess.get(fd);
        convert0(fdVal);
    }

    private static native int create0() throws IOException;

    private static native void convert0(int fd) throws IOException;

    static {
        jdk.internal.loader.BootLoader.loadLibrary("net");
    }
}
