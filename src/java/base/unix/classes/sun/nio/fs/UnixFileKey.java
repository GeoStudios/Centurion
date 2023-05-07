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

/**
 * Container for device/inode to uniquely identify file.
 * 
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 20/4/2023 
 */

class UnixFileKey {
    private final long st_dev;
    private final long st_ino;

    UnixFileKey(long st_dev, long st_ino) {
        this.st_dev = st_dev;
        this.st_ino = st_ino;
    }

    @Override
    public int hashCode() {
        return (int)(st_dev ^ (st_dev >>> 32)) +
               (int)(st_ino ^ (st_ino >>> 32));
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (!(obj instanceof UnixFileKey))
            return false;
        UnixFileKey other = (UnixFileKey)obj;
        return (this.st_dev == other.st_dev) && (this.st_ino == other.st_ino);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("(dev=")
          .append(Long.toHexString(st_dev))
          .append(",ino=")
          .append(st_ino)
          .append(')');
        return sb.toString();
    }
}
