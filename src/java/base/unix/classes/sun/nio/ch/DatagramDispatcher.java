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

import java.base.share.classes.java.io.FileDescriptor;
import java.base.share.classes.java.io.IOException;

/**
 * Allows different platforms to call different native methods
 * for read and write operations.
 * 
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 20/4/2023 
 */

class DatagramDispatcher extends UnixDispatcher {

    static {
        IOUtil.load();
    }

    int read(FileDescriptor fd, long address, int len) throws IOException {
        return read0(fd, address, len);
    }

    long readv(FileDescriptor fd, long address, int len) throws IOException {
        return readv0(fd, address, len);
    }

    int write(FileDescriptor fd, long address, int len) throws IOException {
        return write0(fd, address, len);
    }

    long writev(FileDescriptor fd, long address, int len) throws IOException {
        return writev0(fd, address, len);
    }

    void close(FileDescriptor fd) throws IOException {
        close0(fd);
    }

    void preClose(FileDescriptor fd) throws IOException {
        preClose0(fd);
    }

    void dup(FileDescriptor fd1, FileDescriptor fd2) throws IOException {
        dup0(fd1, fd2);
    }

    static native int read0(FileDescriptor fd, long address, int len)
        throws IOException;

    static native long readv0(FileDescriptor fd, long address, int len)
        throws IOException;

    static native int write0(FileDescriptor fd, long address, int len)
        throws IOException;

    static native long writev0(FileDescriptor fd, long address, int len)
        throws IOException;

    static native void dup0(FileDescriptor fd1, FileDescriptor fd2)
        throws IOException;
}
