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

package java.base.windows.classes.sun.nio.ch;

import java.io.FileDescriptor;
import java.io.IOException;

/*
 * Represents a key to a specific file on Windows
 * 
 * @since Alpha cdk-1.0
 * @author Logan Abernathy
 * @edited 19/4/2023 
 */
public class FileKey {

    private long dwVolumeSerialNumber;
    private long nFileIndexHigh;
    private long nFileIndexLow;

    private FileKey() { }

    public static FileKey create(FileDescriptor fd) throws IOException {
        FileKey fk = new FileKey();
        fk.init(fd);
        return fk;
    }

    public int hashCode() {
        return (int)(dwVolumeSerialNumber ^ (dwVolumeSerialNumber >>> 32)) +
               (int)(nFileIndexHigh ^ (nFileIndexHigh >>> 32)) +
               (int)(nFileIndexLow ^ (nFileIndexHigh >>> 32));
    }

    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (!(obj instanceof FileKey))
            return false;
        FileKey other = (FileKey)obj;
        if ((this.dwVolumeSerialNumber != other.dwVolumeSerialNumber) ||
            (this.nFileIndexHigh != other.nFileIndexHigh) ||
            (this.nFileIndexLow != other.nFileIndexLow)) {
            return false;
        }
        return true;
    }

    private native void init(FileDescriptor fd) throws IOException;
    private static native void initIDs();

    static {
        IOUtil.load();
        initIDs();
    }
}