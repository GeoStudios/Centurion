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
