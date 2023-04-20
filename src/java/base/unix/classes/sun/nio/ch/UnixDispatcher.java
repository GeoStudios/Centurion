/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.unix.classes.sun.nio.ch;

import java.io.FileDescriptor;
import java.io.IOException;

/**
 * @since Pre Java 1
 * @author Logan Abernathy
 * @edited 20/4/2023 
 */

abstract class UnixDispatcher extends NativeDispatcher {

    void close(FileDescriptor fd) throws IOException {
        close0(fd);
    }

    void preClose(FileDescriptor fd) throws IOException {
        preClose0(fd);
    }

    static native void close0(FileDescriptor fd) throws IOException;

    static native void preClose0(FileDescriptor fd) throws IOException;

    static native void init();

    static {
        IOUtil.load();
        init();
    }
}
