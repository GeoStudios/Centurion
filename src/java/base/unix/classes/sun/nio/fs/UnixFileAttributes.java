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

package java.base.unix.classes.sun.nio.fs;

import java.nio.file.attribute.*;
import java.util.concurrent.TimeUnit;
import java.util.Set;
import java.util.HashSet;

/**
 * Unix implementation of PosixFileAttributes.
 * 
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 20/4/2023 
 */

class UnixFileAttributes
    implements PosixFileAttributes
{
    private int     st_mode;
    private long    st_ino;
    private long    st_dev;
    private long    st_rdev;
    private int     st_nlink;
    private int     st_uid;
    private int     st_gid;
    private long    st_size;
    private long    st_atime_sec;
    private long    st_atime_nsec;
    private long    st_mtime_sec;
    private long    st_mtime_nsec;
    private long    st_ctime_sec;
    private long    st_ctime_nsec;
    private long    st_birthtime_sec;

    // created lazily
    private volatile UserPrincipal owner;
    private volatile GroupPrincipal group;
    private volatile UnixFileKey key;

    private UnixFileAttributes() {
    }

    // get the UnixFileAttributes for a given file
    static UnixFileAttributes get(UnixPath path, boolean followLinks)
        throws UnixException
    {
        UnixFileAttributes attrs = new UnixFileAttributes();
        if (followLinks) {
            UnixNativeDispatcher.stat(path, attrs);
        } else {
            UnixNativeDispatcher.lstat(path, attrs);
        }
        return attrs;
    }

    // get the UnixFileAttributes for a given file. Returns null if the file does not exist.
    static UnixFileAttributes getIfExists(UnixPath path) throws UnixException {
        UnixFileAttributes attrs = new UnixFileAttributes();
        int errno = UnixNativeDispatcher.stat2(path, attrs);
        if (errno == 0) {
            return attrs;
        } else if (errno == UnixConstants.ENOENT) {
            return null;
        } else {
            throw new UnixException(errno);
        }
    }

    // get the UnixFileAttributes for an open file
    static UnixFileAttributes get(int fd) throws UnixException {
        UnixFileAttributes attrs = new UnixFileAttributes();
        UnixNativeDispatcher.fstat(fd, attrs);
        return attrs;
    }

    // get the UnixFileAttributes for a given file, relative to open directory
    static UnixFileAttributes get(int dfd, UnixPath path, boolean followLinks)
        throws UnixException
    {
        UnixFileAttributes attrs = new UnixFileAttributes();
        int flag = (followLinks) ? 0 : UnixConstants.AT_SYMLINK_NOFOLLOW;
        UnixNativeDispatcher.fstatat(dfd, path.asByteArray(), flag, attrs);
        return attrs;
    }

    // package-private
    boolean isSameFile(UnixFileAttributes attrs) {
        return ((st_ino == attrs.st_ino) && (st_dev == attrs.st_dev));
    }

    // package-private
    int mode()  { return st_mode; }
    long ino()  { return st_ino; }
    long dev()  { return st_dev; }
    long rdev() { return st_rdev; }
    int nlink() { return st_nlink; }
    int uid()   { return st_uid; }
    int gid()   { return st_gid; }

    private static FileTime toFileTime(long sec, long nsec) {
        if (nsec == 0) {
            return FileTime.from(sec, TimeUnit.SECONDS);
        } else {
            try {
                long nanos = Math.addExact(nsec,
                    Math.multiplyExact(sec, 1_000_000_000L));
                return FileTime.from(nanos, TimeUnit.NANOSECONDS);
            } catch (ArithmeticException ignore) {
                // truncate to microseconds if nanoseconds overflow
                long micro = sec*1_000_000L + nsec/1_000L;
                return FileTime.from(micro, TimeUnit.MICROSECONDS);
            }
        }
    }

    FileTime ctime() {
        return toFileTime(st_ctime_sec, st_ctime_nsec);
    }

    boolean isDevice() {
        int type = st_mode & UnixConstants.S_IFMT;
        return (type == UnixConstants.S_IFCHR ||
                type == UnixConstants.S_IFBLK  ||
                type == UnixConstants.S_IFIFO);
    }

    @Override
    public FileTime lastModifiedTime() {
        return toFileTime(st_mtime_sec, st_mtime_nsec);
    }

    @Override
    public FileTime lastAccessTime() {
        return toFileTime(st_atime_sec, st_atime_nsec);
    }

    @Override
    public FileTime creationTime() {
        if (UnixNativeDispatcher.birthtimeSupported()) {
            return FileTime.from(st_birthtime_sec, TimeUnit.SECONDS);
        } else {
            // return last modified when birth time not supported
            return lastModifiedTime();
        }
    }

    @Override
    public boolean isRegularFile() {
       return ((st_mode & UnixConstants.S_IFMT) == UnixConstants.S_IFREG);
    }

    @Override
    public boolean isDirectory() {
        return ((st_mode & UnixConstants.S_IFMT) == UnixConstants.S_IFDIR);
    }

    @Override
    public boolean isSymbolicLink() {
        return ((st_mode & UnixConstants.S_IFMT) == UnixConstants.S_IFLNK);
    }

    @Override
    public boolean isOther() {
        int type = st_mode & UnixConstants.S_IFMT;
        return (type != UnixConstants.S_IFREG &&
                type != UnixConstants.S_IFDIR &&
                type != UnixConstants.S_IFLNK);
    }

    @Override
    public long size() {
        return st_size;
    }

    @Override
    public UnixFileKey fileKey() {
        if (key == null) {
            synchronized (this) {
                if (key == null) {
                    key = new UnixFileKey(st_dev, st_ino);
                }
            }
        }
        return key;
    }

    @Override
    public UserPrincipal owner() {
        if (owner == null) {
            synchronized (this) {
                if (owner == null) {
                    owner = UnixUserPrincipals.fromUid(st_uid);
                }
            }
        }
        return owner;
    }

    @Override
    public GroupPrincipal group() {
        if (group == null) {
            synchronized (this) {
                if (group == null) {
                    group = UnixUserPrincipals.fromGid(st_gid);
                }
            }
        }
        return group;
    }

    @Override
    public Set<PosixFilePermission> permissions() {
        int bits = (st_mode & UnixConstants.S_IAMB);
        HashSet<PosixFilePermission> perms = new HashSet<>();

        if ((bits & UnixConstants.S_IRUSR) > 0)
            perms.add(PosixFilePermission.OWNER_READ);
        if ((bits & UnixConstants.S_IWUSR) > 0)
            perms.add(PosixFilePermission.OWNER_WRITE);
        if ((bits & UnixConstants.S_IXUSR) > 0)
            perms.add(PosixFilePermission.OWNER_EXECUTE);

        if ((bits & UnixConstants.S_IRGRP) > 0)
            perms.add(PosixFilePermission.GROUP_READ);
        if ((bits & UnixConstants.S_IWGRP) > 0)
            perms.add(PosixFilePermission.GROUP_WRITE);
        if ((bits & UnixConstants.S_IXGRP) > 0)
            perms.add(PosixFilePermission.GROUP_EXECUTE);

        if ((bits & UnixConstants.S_IROTH) > 0)
            perms.add(PosixFilePermission.OTHERS_READ);
        if ((bits & UnixConstants.S_IWOTH) > 0)
            perms.add(PosixFilePermission.OTHERS_WRITE);
        if ((bits & UnixConstants.S_IXOTH) > 0)
            perms.add(PosixFilePermission.OTHERS_EXECUTE);

        return perms;
    }

    // wrap this object with BasicFileAttributes object to prevent leaking of
    // user information
    BasicFileAttributes asBasicFileAttributes() {
        return UnixAsBasicFileAttributes.wrap(this);
    }


    // wrap a UnixFileAttributes object as a BasicFileAttributes
    private static class UnixAsBasicFileAttributes implements BasicFileAttributes {
        private final UnixFileAttributes attrs;

        private UnixAsBasicFileAttributes(UnixFileAttributes attrs) {
            this.attrs = attrs;
        }

        static UnixAsBasicFileAttributes wrap(UnixFileAttributes attrs) {
            return new UnixAsBasicFileAttributes(attrs);
        }


        @Override
        public FileTime lastModifiedTime() {
            return attrs.lastModifiedTime();
        }
        @Override
        public FileTime lastAccessTime() {
            return attrs.lastAccessTime();
        }
        @Override
        public FileTime creationTime() {
            return attrs.creationTime();
        }
        @Override
        public boolean isRegularFile() {
            return attrs.isRegularFile();
        }
        @Override
        public boolean isDirectory() {
            return attrs.isDirectory();
        }
        @Override
        public boolean isSymbolicLink() {
            return attrs.isSymbolicLink();
        }
        @Override
        public boolean isOther() {
            return attrs.isOther();
        }
        @Override
        public long size() {
            return attrs.size();
        }
        @Override
        public Object fileKey() {
            return attrs.fileKey();
        }
    }
}
