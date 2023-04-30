/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.sun.nio.ch;

import java.base.share.classes.jdk.internal.ref.Cleaner;


public interface DirectBuffer {

    // Use of the returned address must be guarded if this DirectBuffer
    // is backed by a memory session that is explicitly closeable.
    //
    // Failure to do this means the outcome is undefined including
    // silent unrelated memory mutation and JVM crashes.
    //
    // See JavaNioAccess for methods to safely acquire/release resources.
    public long address();

    public Object attachment();

    public Cleaner cleaner();

}
