/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.aix.classes.sun.nio.ch;

import java.io.FileDescriptor;
import java.io.IOException;

/**
 * This Java class FileDispatcherImpl provides methods for performing I/O operations on files. 
 * It extends the UnixFileDispatcherImpl class and includes two methods named force() and 
 * transferTo() which allow a user to force any changes made to a file to be written to disk 
 * and transfer bytes from a source file to a destination file. Both of these methods are 
 * implemented by calling native methods force0() and transferTo0() respectively, which are 
 * implemented in the underlying operating system's C or C++ code. The force() method throws 
 * an IOException if there is any error during the I/O operation.
 * 
 * @since Pre Java 1
 * @author Logan Abernathy
 * @edited 16/4/2023
 */

public class FileDispatcherImpl extends UnixFileDispatcherImpl {
    FileDispatcherImpl() {
        super();
    }

    int force(FileDescriptor fd, boolean metaData) throws IOException {
        return force0(fd, metaData);
    }

    protected long transferTo(FileDescriptor src, long position,
                              long count, FileDescriptor dst,
                              boolean append)
    {
        return transferTo0(src, position, count, dst, append);
    }

    // --- native methods ---

    static native int force0(FileDescriptor fd, boolean metaData)
        throws IOException;

    private static native long transferTo0(FileDescriptor src, long position,
                                           long count, FileDescriptor dst,
                                           boolean append);
}