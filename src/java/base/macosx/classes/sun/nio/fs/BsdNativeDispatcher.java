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

package java.base.macosx.classes.sun.nio.fs;

import jdk.internal.misc.Blocker;

/**
 * Bsd specific system calls.
 * 
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 23/4/2023
 */

class BsdNativeDispatcher extends UnixNativeDispatcher {
    protected BsdNativeDispatcher() { }

   /**
    * struct fsstat_iter *getfsstat();
    */
    static native long getfsstat() throws UnixException;

   /**
    * int fsstatEntry(struct fsstat_iter * iter, UnixMountEntry entry);
    */
    static native int fsstatEntry(long iter, UnixMountEntry entry)
        throws UnixException;

   /**
    * void endfsstat(struct fsstat_iter * iter);
    */
    static native void endfsstat(long iter) throws UnixException;

    /**
     * int statfs(const char *path, struct statfs *buf);
     * returns buf->f_mntonname (directory on which mounted)
     */
    static byte[] getmntonname(UnixPath path) throws UnixException {
        try (NativeBuffer pathBuffer = copyToNativeBuffer(path)) {
            return getmntonname0(pathBuffer.address());
        }
    }
    static native byte[] getmntonname0(long pathAddress) throws UnixException;

    /**
     * int clonefile(const char * src, const char * dst, int flags);
     */
    static int clonefile(UnixPath src, UnixPath dst, int flags)
        throws UnixException
    {
        try (NativeBuffer srcBuffer = copyToNativeBuffer(src);
            NativeBuffer dstBuffer = copyToNativeBuffer(dst)) {
            long comp = Blocker.begin();
            try {
                return clonefile0(srcBuffer.address(), dstBuffer.address(),
                                  flags);
            } finally {
                Blocker.end(comp);
            }
        }
    }
    private static native int clonefile0(long srcAddress, long dstAddress,
                                         int flags);

    /**
     * setattrlist(const char* path, struct attrlist* attrList, void* attrBuf,
     *             size_t attrBufSize, unsigned long options)
     */
    static void setattrlist(UnixPath path, int commonattr, long modTime,
                            long accTime, long createTime, long options)
        throws UnixException
    {
        try (NativeBuffer buffer = copyToNativeBuffer(path)) {
            long comp = Blocker.begin();
            try {
                setattrlist0(buffer.address(), commonattr, modTime, accTime,
                             createTime, options);
            } finally {
                Blocker.end(comp);
            }
        }
    }
    private static native void setattrlist0(long pathAddress, int commonattr,
                                            long modTime, long accTime,
                                            long createTime, long options)
        throws UnixException;

    /**
     * fsetattrlist(int fd, struct attrlist* attrList, void* attrBuf,
     *              size_t attrBufSize, unsigned long options)
     */
    static void fsetattrlist(int fd, int commonattr, long modTime,
                             long accTime, long createTime, long options)
        throws UnixException
    {
        long comp = Blocker.begin();
        try {
            fsetattrlist0(fd, commonattr, modTime, accTime,
                          createTime, options);
        } finally {
            Blocker.end(comp);
        }
    }
    private static native void fsetattrlist0(int fd, int commonattr,
                                             long modTime, long accTime,
                                             long createTime, long options)
        throws UnixException;

    // initialize field IDs
    private static native void initIDs();

    static {
         initIDs();
    }
}
