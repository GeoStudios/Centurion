/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.linux.classes.sun.nio.ch;

import java.io.FileDescriptor;

/**
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 23/4/2023
 */

class FileDispatcherImpl extends UnixFileDispatcherImpl {
    FileDispatcherImpl() {
        super();
    }

    int maxDirectTransferSize() {
        return 0x7ffff000; // 2,147,479,552 maximum for sendfile()
    }

    long transferTo(FileDescriptor src, long position, long count,
                    FileDescriptor dst, boolean append) {
        return transferTo0(src, position, count, dst, append);
    }

    long transferFrom(FileDescriptor src, FileDescriptor dst,
                      long position, long count, boolean append) {
        return transferFrom0(src, dst, position, count, append);
    }

    // -- Native methods --

    static native long transferTo0(FileDescriptor src, long position,
                                   long count, FileDescriptor dst,
                                   boolean append);

    static native long transferFrom0(FileDescriptor src, FileDescriptor dst,
                                     long position, long count, boolean append);

    static native void init0();

    static {
        IOUtil.load();
        init0();
    }
}
