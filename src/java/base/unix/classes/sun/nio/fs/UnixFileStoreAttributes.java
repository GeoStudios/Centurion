/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.unix.classes.sun.nio.fs;

/*
 * @since Java 2
 * @author Logan Abernathy
 * @edited 20/4/2023 
 */

class UnixFileStoreAttributes {
    private long f_frsize;          // block size
    private long f_blocks;          // total
    private long f_bfree;           // free
    private long f_bavail;          // usable

    private UnixFileStoreAttributes() {
    }

    static UnixFileStoreAttributes get(UnixPath path) throws UnixException {
        UnixFileStoreAttributes attrs = new UnixFileStoreAttributes();
        UnixNativeDispatcher.statvfs(path, attrs);
        return attrs;
    }

    long blockSize() {
        return f_frsize;
    }

    long totalBlocks() {
        return f_blocks;
    }

    long freeBlocks() {
        return f_bfree;
    }

    long availableBlocks() {
        return f_bavail;
    }

}
