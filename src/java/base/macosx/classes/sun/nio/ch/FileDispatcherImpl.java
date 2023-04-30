/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.macosx.classes.sun.nio.ch;

import java.base.share.classes.java.io.FileDescriptor;
import java.base.share.classes.java.io.IOException;

/**
 * @since Pre Java 1
 * @author Logan Abernathy
 * @edited 23/4/2023
 */

class FileDispatcherImpl extends UnixFileDispatcherImpl {
    FileDispatcherImpl() {
        super();
    }

    int force(FileDescriptor fd, boolean metaData) throws IOException {
        return force0(fd, metaData);
    }

    boolean canTransferToFromOverlappedMap() {
        return false;
    }

    long transferTo(FileDescriptor src, long position, long count,
                    FileDescriptor dst, boolean append) {
        return transferTo0(src, position, count, dst, append);
    }

    // -- Native methods --

    static native int force0(FileDescriptor fd, boolean metaData)
        throws IOException;

    static native long transferTo0(FileDescriptor src, long position,
                                   long count, FileDescriptor dst,
                                   boolean append);
}
