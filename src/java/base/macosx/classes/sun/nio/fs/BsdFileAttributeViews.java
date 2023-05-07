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

import java.base.share.classes.java.io.IOException;
import java.base.share.classes.java.nio.file.attribute.FileTime;
import java.base.share.classes.java.util.concurrent.TimeUnit;
import static java.base.macosx.classes.sun.nio.fs.BsdNativeDispatcher.*;
import static java.base.sgha.classes.sun.nio.fs.UnixNativeDispatcher.lutimes;

/**
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 23/4/2023
 */

class BsdFileAttributeViews {
    //
    // Use setattrlist(2) system call which can set creation, modification,
    // and access times.
    //
    private static void setTimes(UnixPath path, FileTime lastModifiedTime,
                                 FileTime lastAccessTime, FileTime createTime,
                                 boolean followLinks) throws IOException
    {
        // null => don't change
        if (lastModifiedTime == null && lastAccessTime == null &&
            createTime == null) {
            // no effect
            return;
        }

        // permission check
        path.checkWrite();

        boolean useLutimes = false;
        try {
            useLutimes = !followLinks &&
                UnixFileAttributes.get(path, false).isSymbolicLink();
        } catch (UnixException x) {
            x.rethrowAsIOException(path);
        }

        int fd = -1;
        if (!useLutimes) {
            try {
                fd = path.openForAttributeAccess(followLinks);
            } catch (UnixException x) {
                x.rethrowAsIOException(path);
            }
        }

        try {
            // not all volumes support setattrlist(2), so set the last
            // modified and last access times using futimens(2)/lutimes(3)
            if (lastModifiedTime != null || lastAccessTime != null) {
                // if not changing both attributes then need existing attributes
                if (lastModifiedTime == null || lastAccessTime == null) {
                    try {
                        UnixFileAttributes attrs = UnixFileAttributes.get(fd);
                        if (lastModifiedTime == null)
                            lastModifiedTime = attrs.lastModifiedTime();
                        if (lastAccessTime == null)
                            lastAccessTime = attrs.lastAccessTime();
                    } catch (UnixException x) {
                        x.rethrowAsIOException(path);
                    }
                }

                // update times
                TimeUnit timeUnit = useLutimes ?
                    TimeUnit.MICROSECONDS : TimeUnit.NANOSECONDS;
                long modValue = lastModifiedTime.to(timeUnit);
                long accessValue= lastAccessTime.to(timeUnit);

                boolean retry = false;
                try {
                    if (useLutimes)
                        lutimes(path, accessValue, modValue);
                    else
                        futimens(fd, accessValue, modValue);
                } catch (UnixException x) {
                    // if futimens fails with EINVAL and one/both of the times is
                    // negative then we adjust the value to the epoch and retry.
                    if (x.errno() == UnixConstants.EINVAL &&
                        (modValue < 0L || accessValue < 0L)) {
                        retry = true;
                    } else {
                        x.rethrowAsIOException(path);
                    }
                }
                if (retry) {
                    if (modValue < 0L) modValue = 0L;
                    if (accessValue < 0L) accessValue= 0L;
                    try {
                        if (useLutimes)
                            lutimes(path, accessValue, modValue);
                        else
                            futimens(fd, accessValue, modValue);
                    } catch (UnixException x) {
                        x.rethrowAsIOException(path);
                    }
                }
            }

            // set the creation time using setattrlist
            if (createTime != null) {
                long createValue = createTime.to(TimeUnit.NANOSECONDS);
                int commonattr = UnixConstants.ATTR_CMN_CRTIME;
                try {
                    if (useLutimes)
                        setattrlist(path, commonattr, 0L, 0L, createValue,
                            followLinks ?  0 : UnixConstants.FSOPT_NOFOLLOW);
                    else
                        fsetattrlist(fd, commonattr, 0L, 0L, createValue,
                            followLinks ?  0 : UnixConstants.FSOPT_NOFOLLOW);
                } catch (UnixException x) {
                    x.rethrowAsIOException(path);
                }
            }
        } finally {
            if (!useLutimes)
                close(fd, e -> null);
        }
    }

    static class Basic extends UnixFileAttributeViews.Basic {
        Basic(UnixPath file, boolean followLinks) {
            super(file, followLinks);
        }

        @Override
        public void setTimes(FileTime lastModifiedTime,
                             FileTime lastAccessTime,
                             FileTime createTime) throws IOException
        {
            BsdFileAttributeViews.setTimes(file, lastModifiedTime,
                                           lastAccessTime, createTime,
                                           followLinks);
        }
    }

    static class Posix extends UnixFileAttributeViews.Posix {
        Posix(UnixPath file, boolean followLinks) {
            super(file, followLinks);
        }

        @Override
        public void setTimes(FileTime lastModifiedTime,
                             FileTime lastAccessTime,
                             FileTime createTime) throws IOException
        {
            BsdFileAttributeViews.setTimes(file, lastModifiedTime,
                                           lastAccessTime, createTime,
                                           followLinks);
        }
    }

    static class Unix extends UnixFileAttributeViews.Unix {
        Unix(UnixPath file, boolean followLinks) {
            super(file, followLinks);
        }

        @Override
        public void setTimes(FileTime lastModifiedTime,
                             FileTime lastAccessTime,
                             FileTime createTime) throws IOException
        {
            BsdFileAttributeViews.setTimes(file, lastModifiedTime,
                                           lastAccessTime, createTime,
                                           followLinks);
        }
    }

    static Basic createBasicView(UnixPath file, boolean followLinks) {
        return new Basic(file, followLinks);
    }

    static Posix createPosixView(UnixPath file, boolean followLinks) {
        return new Posix(file, followLinks);
    }

    static Unix createUnixView(UnixPath file, boolean followLinks) {
        return new Unix(file, followLinks);
    }
}
