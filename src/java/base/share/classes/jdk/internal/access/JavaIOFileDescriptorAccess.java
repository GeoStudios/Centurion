/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */
package jdk.internal.access;

import java.io.FileDescriptor;
import java.io.IOException;

import jdk.internal.ref.PhantomCleanable;

/*
 * @author Chris Hegarty
 */

public interface JavaIOFileDescriptorAccess {
    public void set(FileDescriptor fdo, int fd);
    public int get(FileDescriptor fdo);
    public void setAppend(FileDescriptor fdo, boolean append);
    public boolean getAppend(FileDescriptor fdo);
    public void close(FileDescriptor fdo) throws IOException;
    public void registerCleanup(FileDescriptor fdo);
    public void registerCleanup(FileDescriptor fdo, PhantomCleanable<FileDescriptor> cleanable);
    public void unregisterCleanup(FileDescriptor fdo);

    // Only valid on Windows
    public void setHandle(FileDescriptor fdo, long handle);
    public long getHandle(FileDescriptor fdo);
}
