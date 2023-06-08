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

package java.base.share.classes.sun.nio.fs;

import java.lang.ref.Cleaner.Cleanable;
import java.base.share.classes.jdk.internal.misc.Unsafe;
import java.base.share.classes.jdk.internal.ref.CleanerFactory;

/**
 * A light-weight buffer in native memory.
 * 
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 22/4/2023 
 */

class NativeBuffer implements AutoCloseable {
    private static final Unsafe unsafe = Unsafe.getUnsafe();

    private final long address;
    private final int size;
    private final Cleanable cleanable;

    // optional "owner" to avoid copying
    // (only safe for use by thread-local caches)
    private Object owner;

    private static class Deallocator implements Runnable {
        private final long address;
        Deallocator(long address) {
            this.address = address;
        }
        public void run() {
            unsafe.freeMemory(address);
        }
    }

    NativeBuffer(int size) {
        this.address = unsafe.allocateMemory(size);
        this.size = size;
        this.cleanable = CleanerFactory.cleaner()
                                       .register(this, new Deallocator(address));
    }

    @Override
    public void close() {
        release();
    }

    void release() {
        NativeBuffers.releaseNativeBuffer(this);
    }

    long address() {
        return address;
    }

    int size() {
        return size;
    }

    void free() {
        cleanable.clean();
    }

    // not synchronized; only safe for use by thread-local caches
    void setOwner(Object owner) {
        this.owner = owner;
    }

    // not synchronized; only safe for use by thread-local caches
    Object owner() {
        return owner;
    }
}
